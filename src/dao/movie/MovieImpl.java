package dao.movie;

import domain.Admin;

import domain.MovieList;
import dto.movie.MovieInfo;

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
            stmt = conn.prepareStatement("SELECT distinct movielist.theater FROM movie, movielist where movie.movieName = ? and movie.movieNum = movielist.movieNum;");
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
            stmt = conn.prepareStatement("SELECT distinct date FROM movie, movielist where movie.movieName = ? and theater = ? and movie.movieNum = movielist.movieNum;");
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
            stmt = conn.prepareStatement("SELECT distinct time FROM movie, movielist where movie.movieName = ? and theater = ? and date = ?" +
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
    public void addBooking(String userId, int movieListId, List<String> selectedSeat) {
        try {
            conn = getConnect();
            stmt = conn.prepareStatement("insert into booking(userId, movieListId, seat) values (?, ?, ?);");

            for (int i = 0; i < selectedSeat.size(); i++) {
                stmt.setString(1, userId);
                stmt.setInt(2, movieListId);
                stmt.setString(3, selectedSeat.get(i));
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
        } finally {
            close(rs, stmt, conn);
        }
        return re;
    }

    @Override
    public MovieInfo getMovieInfo(int movieListId) {
        MovieInfo m;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH시 mm분");
        try {
            conn = getConnect();
            stmt = conn.prepareStatement("SELECT movieName, theater, date, time, room FROM movielist, movie where movie.movieNum = movielist.movieNum " +
                    "and movieListId = ?;");
            stmt.setInt(1, movieListId);

            rs = stmt.executeQuery();
            rs.next();
            m = new MovieInfo(
                    rs.getString("movieName"),
                    rs.getString("theater"),
                    dateFormat.format(rs.getDate("date")),
                    timeFormat.format(rs.getTime("time")),
                    rs.getInt("room")
            );
            return m;
        } catch (SQLException e) {
            System.out.println("");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            close(rs,stmt,conn);
        }
        return null;
    }

    @Override
    public String[][] getUserBookingList(String userId) {
        String[][] re = null;
        try {
            conn = getConnect();
            stmt = conn.prepareStatement("SELECT * FROM booking where userId = ?;");
            stmt.setString(1, userId);

            rs = stmt.executeQuery();
            while (rs.next()) {
                for (int i = 0; ; i++) {
                    re[rs.getRow()][i] = rs.getString(i);
                }

            }

        } catch (SQLException e) {
            System.out.println("");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            close(rs, stmt, conn);
        }
        return re;
    }


    //추가
    //영화 추가
    @Override
    public void addMovie(String name) {
        try {
            conn = getConnect();
            stmt = conn.prepareStatement("insert into movie(movieName) values(?)");
            stmt.setString(1, name);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Add Movie Error");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            close(rs,stmt,conn);
        }
    }

    //전체 내림차순 받아오기
    @Override
    public List<domain.Movie> getAll() {
        List<domain.Movie> list = new ArrayList<>();
        domain.Movie m = null;
        try {
            conn = getConnect();
            stmt = conn.prepareStatement("select * from movie order by purchase desc");

            rs = stmt.executeQuery();
            while (rs.next()) {
                m = new domain.Movie(rs.getInt("movieNum"),rs.getString("movieName"),rs.getInt("purchase"));
                list.add(m);
            }

        } catch (SQLException e) {
            System.out.println("Get All Error");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            close(rs,stmt,conn);
        }
        return list;
    }

}
