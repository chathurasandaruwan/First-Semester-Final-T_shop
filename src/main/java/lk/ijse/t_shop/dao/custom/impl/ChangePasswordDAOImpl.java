package lk.ijse.t_shop.dao.custom.impl;

import lk.ijse.t_shop.dao.SQLUtil;
import lk.ijse.t_shop.dao.custom.ChangePasswordDAO;
import java.sql.SQLException;

public class ChangePasswordDAOImpl implements ChangePasswordDAO {
    @Override
    public boolean changePassword(String password) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE userInfo SET password =? WHERE name = ?",password,"chathura");
    }

}
