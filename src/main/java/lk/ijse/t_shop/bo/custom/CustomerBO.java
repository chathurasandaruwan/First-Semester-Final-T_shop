package lk.ijse.t_shop.bo.custom;

import lk.ijse.t_shop.bo.SuperBO;
import lk.ijse.t_shop.dto.CustomerDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
    ArrayList<CustomerDto> getAllCustomer() throws SQLException, ClassNotFoundException;

    boolean saveCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException;

    boolean updateCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException;

    boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;

    String generateNextCustomerId() throws SQLException, ClassNotFoundException;

    CustomerDto searchCustomer(String newValue) throws SQLException, ClassNotFoundException;
}
