package controller.admin;

import domain.Admin;
import dto.admin.resquest.LogIn;
import service.admin.AdminService;

import java.net.*;

public class AdminController {
    private AdminService adminService;
    public AdminController(){
        adminService = AdminService.getInstance();
    }
    public AdminController(AdminService adminService) { this.adminService = adminService; }

    public int logIn(String id, String pwd){
        LogIn lo = new LogIn();
        lo.setId(id);
        lo.setPwd(pwd);

        Admin admin = adminService.logIn(lo);
        if(admin==null)
            return 1;

        if (admin.getId().equals(id)&&admin.getPwd().equals(pwd)) {
            return 0;
        }else {
            return 1;
        }
    }

}
