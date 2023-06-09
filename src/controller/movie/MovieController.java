package controller.movie;

import service.movie.MovieService;

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
        int num = movieService.findByMovieListId(movie, theater, date, time);
        System.out.println(num);
        return movieService.findByMovieListId(movie, theater, date, time);
    }
    public void addBooking(int id, List<String> selectedSeat) {
        movieService.addBooking(id, selectedSeat);
    }

    public List<String> getBookingList(int num) {
        return movieService.getBookingList(num);
    }
}
