package lk.ijse.t_shop.bo.custom.impl;

import lk.ijse.t_shop.bo.custom.ChangePasswordBO;
import lk.ijse.t_shop.dao.DAOFactory;
import lk.ijse.t_shop.dao.custom.ChangePasswordDAO;

import java.sql.SQLException;

public class ChangePasswordBOImpl implements ChangePasswordBO {
    ChangePasswordDAO changePasswordDAO = (ChangePasswordDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.CHANGE_PASSWORD);
    @Override
    public boolean changeNewPassword(String password) throws SQLException, ClassNotFoundException {
        return changePasswordDAO.changePassword(password);
    }
}
