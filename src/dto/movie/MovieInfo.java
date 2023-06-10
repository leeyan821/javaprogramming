package dto.movie;
public class MovieInfo {
    private String title; //영화 제목
    private String theater; //극장명
    private String date; //날짜
    private String time; //시간
    private Integer room; //상영관 번호

    public MovieInfo(String title, String theater, String date, String time, Integer room) {
        this.title = title;
        this.theater = theater;
        this.date = date;
        this.time = time;
        this.room = room;
    }

    public String getTitle() {
        return title;
    }

    public String getTheater() {
        return theater;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public Integer getRoom() {
        return room;
    }
}
