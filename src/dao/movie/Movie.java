package dao.movie;

import dao.DAO;

import java.util.List;

public interface Movie extends DAO {
    List<String> getAllMovie();

    List<String> getAllTheater(String title);

    List<String> getAllDate(String movie, String theater);

    List<String> getAllTime(String movie, String theater, String date);
}
