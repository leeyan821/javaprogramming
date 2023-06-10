package controller.movieList;

import domain.MovieList;
import dto.movie.MovieChange;
import service.movieList.MovieListService;

import java.util.*;

public class MovieListController {
    private MovieListService movieListService;
    public MovieListController() {movieListService = MovieListService.getInstance();}

    public List<String> getAllTheater(){
        return movieListService.getAllTheater();
    }
    public void save(String movie, String theater, String date, String time, Integer room){
        Integer movieNum = movieListService.findNum(movie);
        movieListService.addMovieList(movieNum,theater,date,time,room);
    }

    public List<MovieChange> findMovieByName(String name) {
        MovieChange ch;
        List<MovieChange> result = new ArrayList<>();
        List<MovieList> change = movieListService.findMovieByName(name);
        for (MovieList a: change) {
            ch = new MovieChange();
            ch.setTheater(a.getTheater());
            ch.setRoom(a.getRoom());
            ch.setDate(a.getDate());
            ch.setTime(a.getTime());
            result.add(ch);
        }

        return result;
    }
    public Integer find(String name, String theater, String date, String time, Integer room){
        String movieNum = movieListService.findMovieByName(name).get(0).getMovieNum().toString();
        MovieList re = movieListService.find(movieNum, theater, date, time, room);
        if (re == null)
            return 0;
        return re.getMovieListId();
    }

    public void delete(Integer num) {
        movieListService.delete(num);
    }

    public void update(Integer num, String theater, String date, String time, Integer room) {
        movieListService.update(num, theater,date,time,room);
    }
    public List<String> top(){
        Map<String,Integer> map = movieListService.top();
        List<String> keySet = new ArrayList<>(map.keySet());
        keySet.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return map.get(o1).compareTo(map.get(o2));
            }
        });
        return keySet;
    }
    public Integer deleteMovie(String name){
         if(movieListService.findMovieByName(name).isEmpty()){
             return 0;
         }
         return 1;
    }
}
