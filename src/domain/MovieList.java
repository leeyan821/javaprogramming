package domain;

import java.sql.Date;
import java.sql.Timestamp;

public class MovieList {
    Integer movieListId;
    Integer movieNum; //영화 번호
    String theater; //극장명
    Date date; //날짜
    Timestamp time; //시간

    Integer room; //상영관 번호
}
