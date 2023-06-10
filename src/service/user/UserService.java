package service.user;

import dao.user.UserDAO;
import dao.user.UserDAOImpl;
import domain.User;
import dto.user.GetIdPwd;

import java.util.List;

public class UserService {
    private UserDAO dao;
    private static UserService service;
    private UserService(){dao= UserDAOImpl.getInstance();}
    public static UserService getInstance(){
        if(service==null) service = new UserService();
        return service;
    }

    public User userLogIn(GetIdPwd request){
        User user = dao.uIdPwd(request.getId(), request.getPwd());
        return user;
    }

    public List<String> getAllId(){return dao.getAllId();}

    public void addUser(String id, String pwd, String name){
        dao.addUser(id,pwd,name);
    }

    public List<User> getAll() {
        return dao.getAll();
    }

    public void deleteUser(String id) {
        dao.deleteUser(id);
    }
}
