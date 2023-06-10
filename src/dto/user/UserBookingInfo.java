package dto.user;

public class UserBookingInfo {
    private String theater; //극장명
    private String date; //날짜
    private String time; //시간
    private Integer room; //상영관 번호

    public UserBookingInfo(String theater, String date, String time, Integer room) {
        this.theater = theater;
        this.date = date;
        this.time = time;
        this.room = room;
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
