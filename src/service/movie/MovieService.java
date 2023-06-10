package service.movie;

import dao.movie.Movie;
import dao.movie.MovieImpl;
import dto.movie.MovieInfo;

import java.util.List;

public class MovieService {
    private Movie dao;
    private static MovieService service;
    private MovieService(){
        dao = MovieImpl.getInstance();
    }
    public static MovieService getInstance(){
        if(service == null) service = new MovieService();
        return service;
    }

    public List<String> getAllMovie() {
        return dao.getAllMovie();
    }

    public List<String> getAllTheater(String selectedMovie) {
        return dao.getAllTheater(selectedMovie);
    }

    public List<String> getAllDate(String movie, String theater) {
        return dao.getAllDate(movie, theater);
    }

    public List<String> getAllTime(String movie, String theater, String date) {
        return dao.getAllTime(movie, theater, date);
    }

    public void addBooking(String userId, int movieListId, List<String> selectedSeat) {
        dao.addBooking(userId, movieListId, selectedSeat);
    }

    public int findByMovieListId(String movie, String theater, String date, String time) {
        return dao.findByMovieListId(movie, theater, date, time);
    }

    public List<String> getBookingList(int num) {
        return dao.getBookingList(num);
    }

    public MovieInfo getMovieInfo(int movieListId) {
        return dao.getMovieInfo(movieListId);
    }
}
