package domain;

import java.sql.Date;
import java.sql.Timestamp;

public class MovieList {
    private Integer movieListId;
    private Integer movieNum; //영화 번호
    private String theater; //극장명
    private String date; //날짜
    private Timestamp time; //시간
    private Integer room; //상영관 번호

    public MovieList(Integer movieListId, Integer movieNum, String theater, String date, Timestamp time, Integer room) {
        this.movieListId = movieListId;
        this.movieNum = movieNum;
        this.theater = theater;
        this.date = date;
        this.time = time;
        this.room = room;
    }

    public Integer getMovieListId() {
        return movieListId;
    }

    public Integer getMovieNum() {
        return movieNum;
    }

    public String getTheater() {
        return theater;
    }

    public String getDate() {
        return date;
    }

    public Timestamp getTime() {
        return time;
    }

    public Integer getRoom() {
        return room;
    }
}

