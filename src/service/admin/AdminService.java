package service.admin;

import dao.admin.AdminDAO;
import dao.admin.AdminDAOImpl;
import domain.Admin;
import dto.admin.resquest.LogIn;

public class AdminService {
    private AdminDAO dao;
    private static AdminService service;
    private AdminService(){
        dao = AdminDAOImpl.getInstance();
    }
    public static AdminService getInstance(){
        if(service == null) service = new AdminService();
        return service;
    }

    public Admin logIn(LogIn request) {
        Admin admin = dao.idPwd(request.getId(), request.getPwd());
        return admin;
    }
}
