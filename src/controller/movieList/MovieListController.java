package controller.movieList;

import service.movieList.MovieListService;

import java.util.List;

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
}
