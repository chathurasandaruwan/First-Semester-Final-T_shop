package lk.ijse.t_shop.bo.custom.impl;

import lk.ijse.t_shop.bo.custom.OrderBO;
import lk.ijse.t_shop.dao.DAOFactory;
import lk.ijse.t_shop.dao.custom.CustomerDAO;
import lk.ijse.t_shop.dao.custom.ItemDAO;
import lk.ijse.t_shop.dao.custom.OrderDAO;
import lk.ijse.t_shop.dao.custom.OrderDetailDAO;
import lk.ijse.t_shop.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.t_shop.dao.custom.impl.ItemDAOImpl;
import lk.ijse.t_shop.dao.custom.impl.OrderDAOImpl;
import lk.ijse.t_shop.dao.custom.impl.OrderDetailDAOImpl;
import lk.ijse.t_shop.db.DbConnection;
import lk.ijse.t_shop.dto.CustomerDto;
import lk.ijse.t_shop.dto.ItemDto;
import lk.ijse.t_shop.dto.OrderDto;
import lk.ijse.t_shop.dto.PlaceOrderDto;
import lk.ijse.t_shop.entity.Customer;
import lk.ijse.t_shop.entity.Item;
import lk.ijse.t_shop.entity.Order;
import lk.ijse.t_shop.entity.PlaceOrder;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class OrderBOImpl implements OrderBO {
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ORDER);
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ITEM);
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.CUSTOMER);
    OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ORDER_DETAIL);
    @Override
    public boolean placeOrder(PlaceOrderDto placeOrderDto) throws SQLException {

        String orderId = placeOrderDto.getOrderId();
        String customerId = placeOrderDto.getCustomerId();
        LocalDate date = placeOrderDto.getDate();

        Connection connection = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isOrderSaved = orderDAO.save(new Order(orderId, customerId, date));
            if (isOrderSaved) {
                boolean isUpdated = itemDAO.updateItem(placeOrderDto.getCartTmList());
                if (isUpdated) {
                    boolean isOrderDetailSaved = orderDetailDAO.save(new PlaceOrder(placeOrderDto.getOrderId(), placeOrderDto.getDate(), placeOrderDto.getCustomerId(), placeOrderDto.getCartTmList()));
                    if (isOrderDetailSaved) {
                        connection.commit();
                    }
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
    public String generateNextOrderId() throws SQLException, ClassNotFoundException {
        return orderDAO.generateNextId();
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
    public CustomerDto searchCustomer(String newValue) throws SQLException, ClassNotFoundException {
        Customer customer = customerDAO.search(newValue);
        return new CustomerDto(customer.getCustId(),customer.getName(),customer.getAddress(),customer.getContacNo());
    }
    @Override
    public ArrayList<ItemDto> getAllItem() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDto> itemDtos = new ArrayList<>();
        ArrayList<Item> items = itemDAO.getAll();
        for (Item item : items) {
            itemDtos.add(new ItemDto(item.getItemCode(),item.getType(),item.getPrice(),item.getQuntity(),item.getDiscountPercentage(),item.getSize(),item.getColor()));
        }
        return itemDtos;
    }
    @Override
    public ItemDto searchItem(String newValue) throws SQLException, ClassNotFoundException {
        Item entity = itemDAO.search(newValue);
        return new ItemDto(entity.getItemCode(),entity.getType(),entity.getPrice(),entity.getQuntity(),entity.getDiscountPercentage(),entity.getSize(),entity.getColor());
    }

}
