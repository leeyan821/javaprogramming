package dao.movie;

import dao.DAO;

import java.util.List;

public interface Movie extends DAO {
    List<String> getAllMovie();

    List<String> getAllTheater(String title);

    List<String> getAllDate(String movie, String theater);

    List<String> getAllTime(String movie, String theater, String date);

    int findByMovieListId(String movie, String theater, String date, String time);

    void addBooking(int movieListId, List<String> selectedSeat);

    List<String> getBookingList(int num);

    //추가
    void addMovie(String name);
    List<domain.Movie> getAll();
}
