package service.movieList;

import dao.movieList.MovieList;
import dao.movieList.MovieListImpl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

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
    public List<domain.MovieList> findMovieByName(String name){
        return dao.findMovieByName(name);
    }

    public domain.MovieList find(String movieNum, String theater, String date, String time, Integer room) {
        return dao.find(movieNum, theater, date, time, room);
    }

    public void delete(Integer num) {
        dao.delete(num);
    }

    public void update(Integer num, String theater, String date, String time, Integer room) {
        dao.update(num, theater,date,time,room);
    }
    public Map<String,Integer> top(){ return dao.top();}

    public Integer checkBooking(Integer id) { return dao.checkBooking(id);}

    public Integer checkAddMovie(String theater, String date, String time, String time2, Integer room) {
        return dao.checkAddMovie(theater,date,time,time2,room);
    }

}
