package lk.ijse.t_shop.dao.custom.impl;

import lk.ijse.t_shop.dao.SQLUtil;
import lk.ijse.t_shop.dao.custom.OrderDAO;
import lk.ijse.t_shop.db.DbConnection;
import lk.ijse.t_shop.dto.OrderDto;
import lk.ijse.t_shop.entity.Order;

import java.sql.*;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public ArrayList<Order> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Order entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO orders VALUES(?, ?, ?)",entity.getOrderId(),Date.valueOf(entity.getDate()),entity.getCustomerId());
    }

    @Override
    public boolean update(Order entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String splitId(String currentOrderId) {
        if(currentOrderId != null) {
            String[] split = currentOrderId.split("O0");

            int id = Integer.parseInt(split[1]);
            id++;
            String formattedNumericPart =String.format("%03d",id);
            return "O" + formattedNumericPart;
        } else {
            return "O001";
        }
    }

    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT orderId FROM orders ORDER BY orderId DESC LIMIT 1");
        if (resultSet.next()){
            return splitId(resultSet.getNString(1));
        }
        return splitId(null);
    }

    @Override
    public Order search(String newValue) throws SQLException, ClassNotFoundException {
        return null;
    }
}
