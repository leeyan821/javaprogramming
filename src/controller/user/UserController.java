package controller.user;

import domain.User;
import dto.user.GetIdPwd;
import service.user.UserService;

import java.util.ArrayList;
import java.util.List;

public class UserController {
    private UserService userService;
    public UserController() {userService = UserService.getInstance();}
    public int userLogIn(String id, String pwd){
        GetIdPwd lo = new GetIdPwd();
        lo.setId(id);
        lo.setPwd(pwd);

        User user = userService.userLogIn(lo);
        if(user==null)
            return 1;

        if (user.getId().equals(id)&&user.getPwd().equals(pwd)) {
            return 0;
        }else {
            return 1;
        }
    }

    public Integer checkId(String id){
        for (String a:userService.getAllId()) {
            if(a.equals(id)) return 0;
        }
        return 1;
    }
    public void addUser(String id, String pwd, String name){
        userService.addUser(id,pwd,name);
    }

    public List<dto.user.User> getAll() {
        List<User> list = userService.getAll();
        List<dto.user.User> re = new ArrayList<>();
        for (User a:list) {
            re.add(new dto.user.User(a.getId(),a.getPwd(),a.getName()));
        }
        return re;
    }

    public void deleteUser(String id) {
        userService.deleteUser(id);
    }
}
