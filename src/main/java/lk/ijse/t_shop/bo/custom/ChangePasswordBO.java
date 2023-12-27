package lk.ijse.t_shop.bo.custom;

import lk.ijse.t_shop.bo.SuperBO;

import java.sql.SQLException;

public interface ChangePasswordBO extends SuperBO {
    boolean changeNewPassword(String password) throws SQLException, ClassNotFoundException;
}
