package lk.ijse.t_shop.model;

import lk.ijse.t_shop.db.DbConnection;

import java.sql.*;
import java.time.LocalDate;

public class OrderModel {
    public String genarateNextId() throws SQLException {
        Connection connection= DbConnection.getInstance().getConnection();
        String sql = "SELECT orderId FROM orders ORDER BY orderId DESC LIMIT 1";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet =pstm.executeQuery();
        if (resultSet.next()){
            return splitOrderId(resultSet.getNString(1));
        }
        return splitOrderId(null);
    }
    private String splitOrderId(String currentOrderId) {
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
    public boolean saveOrder(String orderId, String customerId, LocalDate date) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO orders VALUES(?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, orderId);
        pstm.setDate(2, Date.valueOf(date));
        pstm.setString(3, customerId);

        return pstm.executeUpdate() > 0;
    }
   /* public ResultSet getOrderId() throws SQLException {
        Connection connection =DbConnection.getInstance().getConnection();
        String sql = "select orderId from orders";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet=pstm.executeQuery();
        return resultSet;
    }*/
}
