package dao.user;

public class UserDAO implements UserDAOIn {
    private static UserDAO instance;
    public static UserDAO getInstance() {
        if(instance==null) instance = new UserDAO();
        return instance;
    }
}
