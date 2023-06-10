package service.movie;

import dao.movie.Movie;
import dao.movie.MovieImpl;
import dto.movie.MovieInfo;
import dto.user.UserBookingInfo;
import dto.user.UserBookingList;

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
        int num = dao.addBooking(userId, movieListId);
        dao.addBookingSeat(num, selectedSeat);
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

    public List<UserBookingList> getUserBookingList(String userId) {
        return dao.getUserBookingList(userId);
    }

    public void addBookingSeat(int bookingNum, List<String> selectedSeat) {
        dao.addBookingSeat(bookingNum, selectedSeat);
    }

    public UserBookingInfo getUserBookingInfo(Object num) {
        return dao.getUserBookingInfo(num);
    }

    public void deleteBooking(Object num) {
        dao.deleteBooking(num);
    }

    //영화 추가
    public void addMovie(String name){
        dao.addMovie(name);
    }
    //전부 가져오기
    public List<domain.Movie> getAll(){
        return dao.getAll();
    }
}
