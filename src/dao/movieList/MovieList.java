package dao.movieList;

import dao.DAO;

import java.util.List;

public interface MovieList extends DAO {
    List<String> getAllTheater();
    Integer getMovieNum(String n);
    void addMovieList(Integer movieNum, String theater, String date, String time, Integer room);
    List<domain.MovieList> findMovieByName(String name);

    domain.MovieList find(String num, String theater, String date, String time, Integer room);

    void delete(Integer num);

    void update(Integer num, String theater, String date, String time, Integer room);
}
