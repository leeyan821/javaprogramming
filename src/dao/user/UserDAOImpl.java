package dao.user;

import domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public void addUser(String id, String pwd, String name) {
        System.out.println(id+pwd+name);
        try {
            conn = getConnect();
            stmt = conn.prepareStatement("insert into user(id, pwd, name) values(?, ?, ?)");
            stmt.setString(1, id);
            stmt.setString(2, pwd);
            stmt.setString(3, name);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            close(rs,stmt,conn);
        }
    }

    @Override
    public List<String> getAllId() {
        List<String> re = new ArrayList<>();
        try {
            conn = getConnect();
            stmt = conn.prepareStatement("select id from user");
            rs = stmt.executeQuery();
            while(rs.next()){
                re.add(rs.getString("id"));
            }
        } catch (SQLException e) {
            System.out.println("User Get All Error");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            close(rs,stmt,conn);
        }
        return re;
    }

    @Override
    public List<User> getAll() {
        List<User> list = new ArrayList<>();
        try {
            conn = getConnect();
            stmt = conn.prepareStatement("select * from user");
            rs = stmt.executeQuery();
            while(rs.next()){
                list.add(new User(rs.getString("id"),rs.getString("pwd"),rs.getString("name")));
            }
        } catch (SQLException e) {
            System.out.println("User Get All Error");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            close(rs,stmt,conn);
        }
        return list;
    }

    @Override
    public void deleteUser(String id) {
        try {
            conn = getConnect();
            stmt = conn.prepareStatement("delete from user where id = ?");
            stmt.setString(1, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Delete Error");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            close(rs,stmt,conn);
        }
    }
}
