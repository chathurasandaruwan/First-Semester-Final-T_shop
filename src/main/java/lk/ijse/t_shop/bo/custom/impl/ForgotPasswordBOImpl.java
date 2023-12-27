package lk.ijse.t_shop.bo.custom.impl;

import lk.ijse.t_shop.bo.custom.ForgotPasswordBO;
import lk.ijse.t_shop.dao.DAOFactory;
import lk.ijse.t_shop.dao.custom.LoggingDAO;
import lk.ijse.t_shop.dao.custom.impl.LoggingDAOImpl;
import lk.ijse.t_shop.dto.UserDto;

import java.sql.SQLException;

public class ForgotPasswordBOImpl implements ForgotPasswordBO {
    LoggingDAO loggingDAO = (LoggingDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.LOGGING);
    @Override
    public UserDto getAllDetail() throws SQLException, ClassNotFoundException {
        return loggingDAO.getAllInfo();
    }
}
