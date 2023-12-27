package lk.ijse.t_shop.bo.custom.impl;

import lk.ijse.t_shop.bo.custom.RecordBO;
import lk.ijse.t_shop.dao.DAOFactory;
import lk.ijse.t_shop.dao.custom.CustomerDAO;
import lk.ijse.t_shop.dao.custom.OrderDAO;
import lk.ijse.t_shop.dao.custom.RecordDAO;
import lk.ijse.t_shop.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.t_shop.dao.custom.impl.OrderDAOImpl;
import lk.ijse.t_shop.dao.custom.impl.RecordDAOImpl;
import lk.ijse.t_shop.db.DbConnection;
import lk.ijse.t_shop.dto.CustomerDto;
import lk.ijse.t_shop.dto.OrderDto;
import lk.ijse.t_shop.dto.RecordDto;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class RecordBOImpl implements RecordBO {
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ORDER);
    RecordDAO recordDAO = (RecordDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.RECORD);
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.CUSTOMER);
    @Override
    public boolean placeRecord(RecordDto dto, LocalDate date) throws SQLException{
        String orderId = dto.getOrderId();
        String custId = dto.getCustId();
        /*LocalDate localDate = date;*/
        Connection connection =null;
        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isOrderSaved = orderDAO.save(new OrderDto(orderId,custId, date));
            if (isOrderSaved){
                boolean isRecSaved =recordDAO.save(dto);
                if (isRecSaved){
                    connection.commit();
                }
            }
            connection.rollback();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            connection.setAutoCommit(true);
        }
        return true;

    }
    @Override
    public ArrayList<RecordDto> getAllRecord() throws SQLException, ClassNotFoundException {
        return recordDAO.getAll();
    }
    @Override
    public boolean saveRecord(RecordDto dto) throws SQLException, ClassNotFoundException {
        return recordDAO.save(dto);
    }
    @Override
    public boolean updateRecord(RecordDto dto) throws SQLException, ClassNotFoundException {
        return recordDAO.update(dto);
    }
    @Override
    public boolean deleteRecord(String id) throws SQLException, ClassNotFoundException {
        return recordDAO.delete(id);
    }
    @Override
    public String generateNextRecordId() throws SQLException, ClassNotFoundException {
        return recordDAO.generateNextId();
    }
    @Override
    public RecordDto searchRecord(String newValue) throws SQLException, ClassNotFoundException {
        return recordDAO.search(newValue);
    }
    @Override
    public ArrayList<CustomerDto> getAllCustomer() throws SQLException, ClassNotFoundException {
        return customerDAO.getAll();
    }
    @Override
    public String generateNextOrderId() throws SQLException, ClassNotFoundException {
        return orderDAO.generateNextId();
    }
}
