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
import lk.ijse.t_shop.dto.ItemDto;
import lk.ijse.t_shop.dto.OrderDto;
import lk.ijse.t_shop.dto.RecordDto;
import lk.ijse.t_shop.entity.Customer;
import lk.ijse.t_shop.entity.Item;
import lk.ijse.t_shop.entity.Order;
import lk.ijse.t_shop.entity.Record;

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

            boolean isOrderSaved = orderDAO.save(new Order(orderId,custId, date));
            if (isOrderSaved){
                boolean isRecSaved =recordDAO.save(new Record(dto.getRecordId(),
                        dto.getType(),dto.getCrotchDep(),dto.getRice(),dto.getLegOpe(),dto.getKneeCirum(),dto.getThighCirum(),dto.getOutSeamL(),dto.getInseamL(),
                        dto.getHipsCircum(),dto.getWaistCircum(),dto.getCuffCirum(),dto.getNeckCirum(),dto.getChestCirum(),dto.getShirtL(),dto.getShoulderWid(),
                        dto.getSleeveL(),dto.getBicepCircum(),dto.getSleeveOp(),dto.getColler(),dto.getOrderId(),dto.getPrice(),dto.getCustId()));
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
        ArrayList<RecordDto> recordDtos = new ArrayList<>();
        ArrayList<Record> records = recordDAO.getAll();
        for (Record record : records) {
            recordDtos.add(new RecordDto(record.getRecordId(),
                    record.getType(),record.getCrotchDep(),record.getRice(),record.getLegOpe(),record.getKneeCirum(),record.getThighCirum(),record.getOutSeamL(),record.getInseamL(),
                    record.getHipsCircum(),record.getWaistCircum(),record.getCuffCirum(),record.getNeckCirum(),record.getChestCirum(),record.getShirtL(),record.getShoulderWid(),
                    record.getSleeveL(),record.getBicepCircum(),record.getSleeveOp(),record.getColler(),record.getOrderId(),record.getPrice(),record.getCustId()));
        }
        return recordDtos;
    }
    @Override
    public boolean saveRecord(RecordDto dto) throws SQLException, ClassNotFoundException {
        return recordDAO.save(new Record(dto.getRecordId(),
                dto.getType(),dto.getCrotchDep(),dto.getRice(),dto.getLegOpe(),dto.getKneeCirum(),dto.getThighCirum(),dto.getOutSeamL(),dto.getInseamL(),
                dto.getHipsCircum(),dto.getWaistCircum(),dto.getCuffCirum(),dto.getNeckCirum(),dto.getChestCirum(),dto.getShirtL(),dto.getShoulderWid(),
                dto.getSleeveL(),dto.getBicepCircum(),dto.getSleeveOp(),dto.getColler(),dto.getOrderId(),dto.getPrice(),dto.getCustId()));
    }
    @Override
    public boolean updateRecord(RecordDto dto) throws SQLException, ClassNotFoundException {
        return recordDAO.update(new Record(dto.getCrotchDep(),dto.getRice(),dto.getLegOpe(),dto.getKneeCirum(),dto.getThighCirum(),dto.getOutSeamL(),dto.getInseamL(),
                dto.getHipsCircum(),dto.getWaistCircum(),dto.getCuffCirum(),dto.getNeckCirum(),dto.getChestCirum(),dto.getShirtL(),dto.getShoulderWid(),
                dto.getSleeveL(),dto.getBicepCircum(),dto.getSleeveOp(),dto.getColler(),dto.getPrice(),dto.getRecordId()));
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
        Record dto= recordDAO.search(newValue);
        return new RecordDto(dto.getRecordId(),
                dto.getType(),dto.getCrotchDep(),dto.getRice(),dto.getLegOpe(),dto.getKneeCirum(),dto.getThighCirum(),dto.getOutSeamL(),dto.getInseamL(),
                dto.getHipsCircum(),dto.getWaistCircum(),dto.getCuffCirum(),dto.getNeckCirum(),dto.getChestCirum(),dto.getShirtL(),dto.getShoulderWid(),
                dto.getSleeveL(),dto.getBicepCircum(),dto.getSleeveOp(),dto.getColler(),dto.getOrderId(),dto.getPrice(),dto.getCustId());
    }
    @Override
    public ArrayList<CustomerDto> getAllCustomer() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDto> customerDTOS = new ArrayList<>();
        ArrayList<Customer> customers = customerDAO.getAll();
        for (Customer customer : customers) {
            customerDTOS.add(new CustomerDto(customer.getCustId(),customer.getName(),customer.getAddress(),customer.getContacNo()));
        }
        return customerDTOS;
    }
    @Override
    public String generateNextOrderId() throws SQLException, ClassNotFoundException {
        return orderDAO.generateNextId();
    }
}
