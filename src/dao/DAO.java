package dao;

import java.sql.*;

public interface DAO {
    default Connection getConnect() throws ClassNotFoundException, SQLException {
        Connection con = null;

        Class.forName("com.mysql.cj.jdbc.Driver");
        String server = "127.0.0.1:3306/javaprogramming";// 서버 주소
        String user_name = "root"; //  접속자 id
        String password = "1234"; // 접속자 pw

        con = DriverManager.getConnection("jdbc:mysql://" + server + "?allowPublicRetrieval=true&useSSL=false", user_name, password);

        return con;
    }

    default void close(ResultSet rs, PreparedStatement stmt, Connection conn){
        if (rs != null) {
            try {
                if (!rs.isClosed())
                    rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                rs = null;
            }
        }

        if (stmt != null) {
            try {
                if (!stmt.isClosed())
                    stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                stmt = null;
            }
        }
        if (conn != null) {
            try {
                if (!conn.isClosed())
                    conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conn = null;
            }
        }
    }

}
