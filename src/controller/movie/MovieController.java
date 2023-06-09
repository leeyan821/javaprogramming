package controller.movie;

import service.movie.MovieService;

import java.util.*;

public class MovieController {
    private MovieService movieService;
    public MovieController() {
        movieService = MovieService.getInstance();
    }

    public List<String> getAllMovie() {
        return movieService.getAllMovie();
    }
    public List<String> getAllTheater(String movie) {
        return movieService.getAllTheater(movie);
    }
    public List<String> getAllDate(String movie, String theater) {
        return movieService.getAllDate(movie, theater);
    }
    public List<String> getAllTime(String movie, String theater, String date) {
        return movieService.getAllTime(movie, theater, date);
    }

    //추가
    //검색
    public List<String> search(String name){
        List<String> re = new ArrayList<>();
        movieService.getAllMovie().stream().filter(x->x.contains(name)).forEach(x-> re.add(x));
        return re;
    }
}
