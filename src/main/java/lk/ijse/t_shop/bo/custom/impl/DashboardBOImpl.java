package lk.ijse.t_shop.bo.custom.impl;

import lk.ijse.t_shop.bo.custom.DashboardBO;
import lk.ijse.t_shop.dao.DAOFactory;
import lk.ijse.t_shop.dao.custom.CustomerDAO;
import lk.ijse.t_shop.dao.custom.OrderDAO;
import lk.ijse.t_shop.dao.custom.RecordDAO;
import lk.ijse.t_shop.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.t_shop.dao.custom.impl.OrderDAOImpl;
import lk.ijse.t_shop.dao.custom.impl.RecordDAOImpl;

import java.sql.SQLException;

public class DashboardBOImpl implements DashboardBO {
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ORDER);
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.CUSTOMER);
    RecordDAO recordDAO = (RecordDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.RECORD);
    @Override
    public String generateNextOrderId() throws SQLException, ClassNotFoundException {
        return orderDAO.generateNextId();
    }
    @Override
    public String generateNextRecordId() throws SQLException, ClassNotFoundException {
        return recordDAO.generateNextId();
    }
    @Override
    public String generateNextCustomerId() throws SQLException, ClassNotFoundException {
        return customerDAO.generateNextId();
    }
}
