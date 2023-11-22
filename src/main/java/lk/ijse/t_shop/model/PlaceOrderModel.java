package lk.ijse.t_shop.model;


import lk.ijse.t_shop.db.DbConnection;
import lk.ijse.t_shop.dto.PlaceOrderDto;
import lk.ijse.t_shop.dto.itemDto;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class PlaceOrderModel {
    private OrderModel orderModel = new OrderModel();
    private ItemModel itemModel = new ItemModel();
    private OrderDetailModel orderDetailModel = new OrderDetailModel();

    public boolean placeOrder(PlaceOrderDto placeOrderDto) throws SQLException {

        String orderId = placeOrderDto.getOrderId();
        String customerId = placeOrderDto.getCustomerId();
        LocalDate date = placeOrderDto.getDate();

        Connection connection = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isOrderSaved = orderModel.saveOrder(orderId, customerId, date);
            if (isOrderSaved) {
                boolean isUpdated = itemModel.updateItem(placeOrderDto.getCartTmList());
                if (isUpdated) {
                    boolean isOrderDetailSaved = orderDetailModel.saveOrderDetails(placeOrderDto.getOrderId(), placeOrderDto.getCartTmList());
                    if (isOrderDetailSaved) {
                        connection.commit();
                    }
                }
            }
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
        return true;
    }


}