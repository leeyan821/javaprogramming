package dao.movie;

import domain.MovieList;
import dto.movie.MovieInfo;
import dto.user.UserBookingInfo;
import dto.user.UserBookingList;

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
    public int addBooking(String userId, int movieListId) {
        int bookingNum = 0;
        try {
            conn = getConnect();
            stmt = conn.prepareStatement("insert into booking(userId, movieListId) values (?, ?);", Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, userId);
            stmt.setInt(2, movieListId);
            stmt.executeUpdate();

            rs = stmt.getGeneratedKeys();
            rs.next();
            bookingNum = rs.getInt(1);
        } catch (SQLException e) {
            System.out.println("addBooking"+e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            close(rs,stmt,conn);
        }
        return bookingNum;
    }

    @Override
    public void addBookingSeat(int bookingNum, List<String> selectedSeat) {
        try {
            conn = getConnect();
            stmt = conn.prepareStatement("insert into bookingSeat(bookingNum, seat) values (?, ?);");

            for (int i = 0; i < selectedSeat.size(); i++) {
                stmt.setInt(1, bookingNum);
                stmt.setString(2, selectedSeat.get(i));
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("addBookingSeat"+e);
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
            stmt = conn.prepareStatement("select seat from booking, bookingSeat where booking.bookingNum = bookingSeat.bookingNum and movieListId = ?;");
            stmt.setInt(1, num);

            rs = stmt.executeQuery();
            while (rs.next()) {
                re.add(rs.getString("seat"));
            }
        } catch (SQLException e) {
            System.out.println("getBookingList");
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
            System.out.println("getMovieInfo");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            close(rs,stmt,conn);
        }
        return null;
    }

    @Override
    public List<UserBookingList> getUserBookingList(String userId) {
        List<UserBookingList> re = new ArrayList<>();
        try {
            conn = getConnect();
            stmt = conn.prepareStatement("select DISTINCT bookingNum, bookingDate, movieName from movie, booking, movielist where movie.movieNum = movielist.movieNum and userId = ?;");
            stmt.setString(1, userId);

            rs = stmt.executeQuery();
            while (rs.next()) {
                UserBookingList list = new UserBookingList();
                list.setBookingNum(rs.getInt(1));
                list.setBookingDate(rs.getString(2));
                list.setMovieName(rs.getString(3));
                re.add(list);
            }
            return re;
        } catch (SQLException e) {
            System.out.println("getUserBookingList");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            close(rs, stmt, conn);
        }
        return null;
    }

    @Override
    public UserBookingInfo getUserBookingInfo(Object num) {
        UserBookingInfo u;
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        try {
            conn = getConnect();
            stmt = conn.prepareStatement("SELECT theater, date, time, room FROM movielist, movie, booking where bookingNum = ? " +
                    "and movie.movieNum = movielist.movieNum and booking.movieListId = movielist.movieListId;");
            stmt.setObject(1, num);

            rs = stmt.executeQuery();
            rs.next();
            u = new UserBookingInfo(
                    rs.getString("theater"),
                    rs.getString("date"),
                    timeFormat.format(rs.getTime("time")),
                    rs.getInt("room")
            );

            return u;
        } catch (SQLException e) {
            System.out.println("getUserBookingInfo");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            close(rs,stmt,conn);
        }
        return null;
    }

    @Override
    public void deleteBooking(Object num) {
        try {
            conn = getConnect();
            stmt = conn.prepareStatement("delete from booking where bookingNum = ?;");
            stmt.setObject(1, num);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("deleteBooking");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            close(rs,stmt,conn);
        }
    }
    
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

    //전체 받아오기
    @Override
    public List<domain.Movie> getAll() {
        List<domain.Movie> list = new ArrayList<>();
        domain.Movie m = null;
        try {
            conn = getConnect();
            stmt = conn.prepareStatement("select * from movie");

            rs = stmt.executeQuery();
            while (rs.next()) {
                m = new domain.Movie(rs.getInt("movieNum"),rs.getString("movieName"));
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

    //영화 삭제
    @Override
    public void deleteMovie(String name) {
        try {
            conn = getConnect();
            stmt = conn.prepareStatement("delete from movie where movieName = ?");
            stmt.setString(1, name);

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Delete Error");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            close(rs,stmt,conn);
        }
    }

}
