package domain;

public class Movie {
    Integer movieNum;
    String movieName;
    Integer purchase; //팔린 수

    public Movie(Integer movieNum, String movieName, Integer purchase) {
        this.movieNum = movieNum;
        this.movieName = movieName;
        this.purchase = purchase;
    }

    public Integer getMovieNum() {
        return movieNum;
    }

    public String getMovieName() {
        return movieName;
    }

    public Integer getPurchase() {
        return purchase;
    }
}
