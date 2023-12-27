package lk.ijse.t_shop.bo.custom;

import lk.ijse.t_shop.bo.SuperBO;
import lk.ijse.t_shop.dto.CustomerDto;
import lk.ijse.t_shop.dto.ItemDto;
import lk.ijse.t_shop.dto.PlaceOrderDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderBO extends SuperBO {
    boolean placeOrder(PlaceOrderDto placeOrderDto) throws SQLException;

    String generateNextOrderId() throws SQLException, ClassNotFoundException;

    ArrayList<CustomerDto> getAllCustomer() throws SQLException, ClassNotFoundException;

    CustomerDto searchCustomer(String newValue) throws SQLException, ClassNotFoundException;

    ArrayList<ItemDto> getAllItem() throws SQLException, ClassNotFoundException;

    ItemDto searchItem(String newValue) throws SQLException, ClassNotFoundException;
}
