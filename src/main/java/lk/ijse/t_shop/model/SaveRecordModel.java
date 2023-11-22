package lk.ijse.t_shop.model;

import lk.ijse.t_shop.db.DbConnection;
import lk.ijse.t_shop.dto.recordDto;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

public class SaveRecordModel {
    private  RecordModel recordModel= new RecordModel();
    private  OrderModel orderModel = new OrderModel();
    public boolean saveRecord(recordDto dto, LocalDate date) throws SQLException{
        String orderId = dto.getOrderId();
        String custId = dto.getCustId();
        /*LocalDate localDate = date;*/
        Connection connection =null;
        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isOrderSaved = orderModel.saveOrder(orderId,custId, date);
            if (isOrderSaved){
                boolean isRecSaved =recordModel.saveRecord(dto);
                if (isRecSaved){
                    connection.commit();
                }
            }
            connection.rollback();
        }finally {
            connection.setAutoCommit(true);
        }
        return true;

    }
}
