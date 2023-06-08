package dao.user;

import dao.DAO;
import domain.User;

public interface UserDAO extends DAO{
    public User uIdPwd(String id, String pwd);
}
