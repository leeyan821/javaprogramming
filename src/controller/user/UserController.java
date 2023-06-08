package controller.user;

import domain.User;
import dto.user.GetIdPwd;
import service.user.UserService;

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
}
