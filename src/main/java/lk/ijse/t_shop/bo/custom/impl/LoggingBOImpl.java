package lk.ijse.t_shop.bo.custom.impl;

import lk.ijse.t_shop.bo.custom.LoggingBO;
import lk.ijse.t_shop.dao.DAOFactory;
import lk.ijse.t_shop.dao.custom.LoggingDAO;
import lk.ijse.t_shop.dao.custom.impl.LoggingDAOImpl;
import lk.ijse.t_shop.dto.UserDto;

import java.sql.SQLException;

public class LoggingBOImpl implements LoggingBO {
    LoggingDAO loggingDAO = (LoggingDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.LOGGING);
    @Override
    public UserDto getAllUserDetail() throws SQLException, ClassNotFoundException {
        return loggingDAO.getAllInfo();
    }
}
