package dao.movieList;

import dao.DAO;

import java.util.List;

public interface MovieList extends DAO {
    List<String> getAllTheater();
    Integer getMovieNum(String n);
    void addMovieList(Integer movieNum, String theater, String date, String time, Integer room);
}
