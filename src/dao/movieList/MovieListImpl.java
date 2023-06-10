package dao.movieList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
}
