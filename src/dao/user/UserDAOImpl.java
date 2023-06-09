package dao.user;

import domain.Admin;
import domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {
    private static UserDAOImpl instance;
    public static UserDAOImpl getInstance() {
        if(instance==null) instance = new UserDAOImpl();
        return instance;
    }
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    @Override
    public User uIdPwd(String id, String pwd) {
        User user = null;
        try {
            conn = getConnect();
            stmt = conn.prepareStatement("select * from user where id = ? and pwd = ?");
            stmt.setString(1, id);
            stmt.setString(2, pwd);

            rs = stmt.executeQuery();
            rs.next();
            user = new User(rs.getString("id"),rs.getString("pwd"),rs.getString("name"));

        } catch (SQLException e) {
            System.out.println("User ID PWD Error");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            close(rs,stmt,conn);
        }
        return user;
    }
}