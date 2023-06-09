package dao.movie;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MovieImpl implements Movie {
    private static MovieImpl instance;
    private MovieImpl() {}
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;
    public static MovieImpl getInstance(){
        if(instance==null) instance = new MovieImpl();
        return instance;
    }

    @Override
    public List<String> getAllMovie() {
        List<String> re = new ArrayList<>();
        try {
            conn = getConnect();
            stmt = conn.prepareStatement("SELECT movieName FROM movie group by movieName;");
            rs = stmt.executeQuery();
            while (rs.next()) {
                re.add(rs.getString("movieName"));
            }

        } catch (SQLException e) {
            System.out.println("");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            close(rs,stmt,conn);
        }
        return re;
    }

    @Override
    public List<String> getAllTheater(String title) {
        List<String> re = new ArrayList<>();
        try {
            conn = getConnect();
            stmt = conn.prepareStatement("SELECT movielist.theater FROM movie, movielist where movie.movieName = ? and movie.movieNum = movielist.movieNum;");
            stmt.setString(1, title);

            rs = stmt.executeQuery();
            while (rs.next()) {
                re.add(rs.getString("theater"));
            }

        } catch (SQLException e) {
            System.out.println("");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            close(rs,stmt,conn);
        }
        return re;
    }

    @Override
    public List<String> getAllDate(String movie, String theater) {
        List<String> re = new ArrayList<>();
        try {
            conn = getConnect();
            stmt = conn.prepareStatement("SELECT date FROM movie, movielist where movie.movieName = ? and theater = ? and movie.movieNum = movielist.movieNum;");
            stmt.setString(1, movie);
            stmt.setString(2, theater);

            rs = stmt.executeQuery();
            while (rs.next()) {
                re.add(rs.getString("date"));
            }

        } catch (SQLException e) {
            System.out.println("");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            close(rs,stmt,conn);
        }
        return re;
    }

    @Override
    public List<String> getAllTime(String movie, String theater, String date) {
        List<String> re = new ArrayList<>();
        String time;
        Time t;
        try {
            conn = getConnect();
            stmt = conn.prepareStatement("SELECT time FROM movie, movielist where movie.movieName = ? and theater = ? and date = ?" +
                    "and movie.movieNum = movielist.movieNum;");
            stmt.setString(1, movie);
            stmt.setString(2, theater);
            stmt.setString(3, date);

            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
            rs = stmt.executeQuery();
            while (rs.next()) {
                t = rs.getTime("time");
                time = dateFormat.format(t);
                re.add(time);
            }

        } catch (SQLException e) {
            System.out.println("");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            close(rs,stmt,conn);
        }
        return re;
    }

    @Override
    public int findByMovieListId(String movie, String theater, String date, String time) {
        try {
            conn = getConnect();
            stmt = conn.prepareStatement("SELECT movieListId FROM movie, movielist where movie.movieName = ? and theater = ? and date = ?" +
                    "and time = ? and movie.movieNum = movielist.movieNum;");
            stmt.setString(1, movie);
            stmt.setString(2, theater);
            stmt.setString(3, date);
            stmt.setString(4, time);

            rs = stmt.executeQuery();
            rs.next();

            return rs.getInt("movieListId");
        } catch (SQLException e) {
            System.out.println("");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            close(rs,stmt,conn);
        }
        return 0;
    }

    @Override
    public void addBooking(int movieListId, List<String> selectedSeat) {
        try {
            conn = getConnect();
            stmt = conn.prepareStatement("insert into booking(movieListId, seat) values (?, ?);");

            for (int i = 0; i < selectedSeat.size(); i++) {
                stmt.setInt(1, movieListId);
                stmt.setString(2, selectedSeat.get(i));
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            close(rs,stmt,conn);
        }
    }

    @Override
    public List<String> getBookingList(int num) {
        List<String> re = new ArrayList<>();
        try {
            conn = getConnect();
            stmt = conn.prepareStatement("SELECT seat FROM booking where movieListId = ?;");
            stmt.setInt(1, num);

            rs = stmt.executeQuery();
            while (rs.next()) {
                re.add(rs.getString("seat"));
            }

        } catch (SQLException e) {
            System.out.println("");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            close(rs,stmt,conn);
        }
        return re;
    }
}
