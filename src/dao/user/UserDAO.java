package dao.user;

import dao.DAO;
import domain.User;

import java.util.List;

public interface UserDAO extends DAO{
    public User uIdPwd(String id, String pwd);
    public void addUser(String id, String pwd, String name);
    public List<String> getAllId();

    List<User> getAll();

    void deleteUser(String id);

    Integer checkDelete(String id);
}
