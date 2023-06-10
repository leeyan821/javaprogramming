package controller.movie;

import dto.movie.MovieInfo;
import service.movie.MovieService;

import java.util.ArrayList;
import java.util.List;

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

    public int findByMovieListId(String movie, String theater, String date, String time) {
        return movieService.findByMovieListId(movie, theater, date, time);
    }
    public void addBooking(String userId, int movieListId, List<String> selectedSeat) {
        movieService.addBooking(userId, movieListId, selectedSeat);
    }

    public List<String> getBookingList(int num) {
        return movieService.getBookingList(num);
    }

    //추가
    //검색
    public List<String> search(String name){
        List<String> re = new ArrayList<>();
        movieService.getAllMovie().stream().filter(x->x.contains(name)).forEach(x-> re.add(x));
        return re;
    }

    public MovieInfo getMovieInfo(int movieListId) {
        return movieService.getMovieInfo(movieListId);
    }
}
