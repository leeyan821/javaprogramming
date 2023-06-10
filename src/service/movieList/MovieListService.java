package service.movieList;

import dao.movieList.MovieList;
import dao.movieList.MovieListImpl;

import java.util.List;

public class MovieListService {
    private MovieList dao;
    private static MovieListService service;
    private MovieListService() { dao = MovieListImpl.getInstance(); }
    public static MovieListService getInstance(){
        if(service==null) service = new MovieListService();
        return service;
    }
    public List<String> getAllTheater(){
        return dao.getAllTheater();
    }
    public Integer findNum(String n){
        return dao.getMovieNum(n);
    }
    public void addMovieList(Integer movieNum, String theater, String date, String time, Integer room){
        dao.addMovieList(movieNum,theater,date,time,room);
    }
}
