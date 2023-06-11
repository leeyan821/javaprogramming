package dao.movie;

import dao.DAO;
import dto.movie.MovieInfo;
import dto.user.UserBookingInfo;
import dto.user.UserBookingList;

import java.util.List;

public interface Movie extends DAO {
    List<String> getAllMovie();

    List<String> getAllTheater(String title);

    List<String> getAllDate(String movie, String theater);

    List<String> getAllTime(String movie, String theater, String date);

    int findByMovieListId(String movie, String theater, String date, String time);

    int addBooking(String userId, int movieListId);

    void addBookingSeat(int bookingNum, List<String> selectedSeat);

    List<String> getBookingList(int num);

    //추가
    void addMovie(String name);
    List<domain.Movie> getAll();

    MovieInfo getMovieInfo(int movieListId);

    List<UserBookingList> getUserBookingList(String userId);

    UserBookingInfo getUserBookingInfo(Object num);

    void deleteBooking(Object num);

    void deleteMovie(String name);

    String getUserBookingSeats(Object num);
}
