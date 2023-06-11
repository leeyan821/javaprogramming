package dao.movieList;

import java.awt.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieListImpl implements MovieList{
    private static MovieListImpl instance;
    private MovieListImpl() {}
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;
    public static MovieListImpl getInstance(){
        if(instance==null) instance = new MovieListImpl();
        return instance;
    }
    @Override
    public List<String> getAllTheater() {
        List<String> re = new ArrayList<>();
        try {
            conn = getConnect();
            stmt = conn.prepareStatement("select distinct theater from movielist");
            rs = stmt.executeQuery();
            while (rs.next()) {
                re.add(rs.getString("theater"));
            }

        } catch (SQLException e) {
            System.out.println("Error");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            close(rs,stmt,conn);
        }
        return re;
    }

    @Override
    public Integer getMovieNum(String name) {
        Integer n = null;
        try {
            conn = getConnect();
            stmt = conn.prepareStatement("select movieNum from movie where movieName = ?");
            stmt.setString(1,name);
            rs = stmt.executeQuery();

            rs.next();
            n = rs.getInt("movieNum");

        } catch (SQLException e) {
            System.out.println("Find Num Error");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            close(rs,stmt,conn);
        }
        return n;
    }

    @Override
    public void addMovieList(Integer movieNum, String theater, String date, String time, Integer room) {
        try {
            conn = getConnect();
            stmt = conn.prepareStatement("insert into movielist(movieNum,theater,date,time,room) " +
                    "values(?,?,?,?,?)");
            stmt.setInt(1, movieNum);
            stmt.setString(2,theater);
            stmt.setString(3,date);
            stmt.setString(4,time);
            stmt.setInt(5,room);

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Add MovieList Error");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            close(rs,stmt,conn);
        }
    }

    @Override
    public List<domain.MovieList> findMovieByName(String name) {
        List<domain.MovieList> list = new ArrayList<>();
        try {
            conn = getConnect();
            stmt = conn.prepareStatement("select * from movielist where movieNum = (" +
                    "select movieNum from movie where movieName = ?)");
            stmt.setString(1,name);
            rs = stmt.executeQuery();

            //SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
            while(rs.next()){
                Integer id = rs.getInt("movieListId");
                Integer mNum = rs.getInt("movieNum");
                String the = rs.getString("theater");
                String date = rs.getString("date");
                //Time t = rs.getTime("time");
                //String time = dateFormat.format(t);
                Timestamp time = rs.getTimestamp("time");
                Integer room = rs.getInt("room");
                domain.MovieList a = new domain.MovieList(id,mNum,the,date,time,room);
                list.add(a);
            }

        } catch (SQLException e) {
            System.out.println("Find By Name Error");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            close(rs,stmt,conn);
        }
        return list;
    }

    @Override
    public domain.MovieList find(String num, String theater, String date, String time, Integer room) {
        domain.MovieList list = null;
        try {
            conn = getConnect();
            stmt = conn.prepareStatement("select * from movielist where movieNum = ? and " +
                    "theater = ? and date = ? and time = ? and room = ?");

            stmt.setString(1,num);
            stmt.setString(2,theater);
            stmt.setString(3,date);
            stmt.setString(4,time);
            stmt.setInt(5,room);

            rs = stmt.executeQuery();
            rs.next();
            list = new domain.MovieList(rs.getInt("movieListId"),rs.getInt("movieNum"),
                    rs.getString("theater"), rs.getString("date"), rs.getTimestamp("time"),
                    rs.getInt("room"));

        } catch (SQLException e) {
            System.out.println("Find Error");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            close(rs,stmt,conn);
        }
        return list;
    }

    @Override
    public void delete(Integer num) {
        try {
            conn = getConnect();
            stmt = conn.prepareStatement("delete from movielist where movieListId = ?");
            stmt.setInt(1, num);

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Delete Error");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            close(rs,stmt,conn);
        }
    }

    @Override
    public void update(Integer num, String theater, String date, String time, Integer room) {
        try {
            conn = getConnect();
            stmt = conn.prepareStatement("update movielist set theater = ?, date = ?, time = ?, room = ? " +
                    "where movieListId = ?");
            stmt.setString(1,theater);
            stmt.setString(2,date);
            stmt.setString(3, time);
            stmt.setInt(4,room);
            stmt.setInt(5,num);

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Update Error");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            close(rs,stmt,conn);
        }
    }

    public Map<String,Integer> top(){
        Map<String,Integer> map = new HashMap<>();
        try {
            conn = getConnect();
            stmt = conn.prepareStatement("select count(movieNum), movieNum from movielist group by movieNum");
            rs = stmt.executeQuery();

            while(rs.next()){
                map.put(rs.getString(1),rs.getInt("movieNum"));
            }
        } catch (SQLException e) {
            System.out.println("Top Error");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            close(rs,stmt,conn);
        }
        return map;
    }

    @Override
    public Integer checkBooking(Integer id) {
        Integer re = 0;
        try {
            conn = getConnect();
            stmt = conn.prepareStatement("select bookingNum from booking where movieListId = ?");
            stmt.setInt(1,id);
            rs = stmt.executeQuery();

            while(rs.next())
                re = rs.getInt("bookingNum");
        } catch (SQLException e) {
            System.out.println("Find Num Error");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            close(rs,stmt,conn);
        }
        return re;
    }

    @Override
    public Integer checkAddMovie(String theater, String date, String time, String time2, Integer room) {
        Integer re = 0;
        try {
            conn = getConnect();
            stmt = conn.prepareStatement("select movieListId from movielist where " +
                    "theater = ? and date = ? and time >= ? and time <= ? and room = ?");

            stmt.setString(1, theater);
            stmt.setString(2, date);
            stmt.setString(3, time);
            stmt.setString(4, time2);
            stmt.setInt(5, room);
            rs = stmt.executeQuery();

            while(rs.next())
                re = rs.getInt("movieListId");
        } catch (SQLException e) {
            System.out.println("Find Num Error");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            close(rs,stmt,conn);
        }
        return re;
    }

}
