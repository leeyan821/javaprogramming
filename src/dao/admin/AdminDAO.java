package dao.admin;

import dao.DAO;
import domain.Admin;

public interface AdminDAO extends DAO {
    public Admin idPwd(String id, String pwd);
}
