package lk.ijse.t_shop.bo.custom.impl;

import lk.ijse.t_shop.bo.custom.CustomerBO;
import lk.ijse.t_shop.dao.DAOFactory;
import lk.ijse.t_shop.dao.custom.CustomerDAO;
import lk.ijse.t_shop.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.t_shop.dto.CustomerDto;
import lk.ijse.t_shop.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.CUSTOMER);
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
    public  boolean saveCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return customerDAO.save(new Customer(dto.getCustId(),dto.getName(),dto.getAddress(),dto.getContacNo()));
    }
    @Override
    public boolean updateCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(dto.getCustId(),dto.getName(),dto.getAddress(),dto.getContacNo()));
    }
    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }
    @Override
    public String generateNextCustomerId() throws SQLException, ClassNotFoundException {
        return customerDAO.generateNextId();
    }
    @Override
    public CustomerDto searchCustomer(String newValue) throws SQLException, ClassNotFoundException {
       Customer customer = customerDAO.search(newValue);
       return new CustomerDto(customer.getCustId(),customer.getName(),customer.getAddress(),customer.getContacNo());
    }
}
