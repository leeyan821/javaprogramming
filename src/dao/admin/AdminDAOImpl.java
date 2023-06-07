package dao.admin;

import domain.Admin;

import java.net.CookieManager;
import java.sql.*;

public class AdminDAOImpl implements AdminDAO {
    private static AdminDAOImpl instance;
    private AdminDAOImpl() {}
    public static AdminDAOImpl getInstance(){
        if(instance==null) instance = new AdminDAOImpl();
        return instance;
    }

    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;
    @Override
    public Admin idPwd(String adminId, String adminPwd) {
        Admin admin = null;
        try {
            conn = getConnect();
            stmt = conn.prepareStatement("select * from admin where id = ? and pwd = ?");
            stmt.setString(1, adminId);
            stmt.setString(2, adminPwd);

            rs = stmt.executeQuery();
            rs.next();
            admin = new Admin(rs.getString("id"),rs.getString("pwd"),rs.getString("name"));

        } catch (SQLException e) {
            System.out.println("ID PWD Error");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            close(rs,stmt,conn);
        }
        return admin;
    }
}
