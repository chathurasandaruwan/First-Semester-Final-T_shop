package lk.ijse.t_shop.dao.custom.impl;

import lk.ijse.t_shop.dao.SQLUtil;
import lk.ijse.t_shop.dao.custom.OrderDetailDAO;
import lk.ijse.t_shop.dto.PlaceOrderDto;
import lk.ijse.t_shop.entity.PlaceOrder;
import lk.ijse.t_shop.view.tdm.CartTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailDAO {


    @Override
    public ArrayList<PlaceOrder> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(PlaceOrder entity) throws SQLException, ClassNotFoundException {
        String orderId = entity.getOrderId();
        List<CartTm> cartTmList = entity.getCartTmList();
        for(CartTm tm : cartTmList) {
            if(!saveOrderDetail(orderId, tm)) {
                return false;
            }
        }
        return true;
    }
    @Override
    public boolean saveOrderDetail(String orderId, CartTm tm) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO orderInfo VALUES(?, ?, ?, ?)",orderId,tm.getCode(),tm.getQty(),tm.getPrice());
    }

    @Override
    public boolean update(PlaceOrder entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String splitId(String currentOrderId) {
        return null;
    }

    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public PlaceOrder search(String newValue) throws SQLException, ClassNotFoundException {
        return null;
    }
}
