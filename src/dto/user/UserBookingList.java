package dto.user;

public class UserBookingList {
    private int bookingNum;
    private String bookingDate;
    private String movieName;

    public int getBookingNum() {
        return bookingNum;
    }

    public void setBookingNum(int bookingNum) {
        this.bookingNum = bookingNum;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }
}
