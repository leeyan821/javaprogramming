package dto.movie;

import java.sql.Timestamp;

public class MovieChange {
    private String theater; //극장명
    private Integer room; //상영관 번호
    private String date; //날짜
    private Timestamp time; //시간

    public String getTheater() {
        return theater;
    }

    public void setTheater(String theater) {
        this.theater = theater;
    }

    public Integer getRoom() {
        return room;
    }

    public void setRoom(Integer room) {
        this.room = room;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
