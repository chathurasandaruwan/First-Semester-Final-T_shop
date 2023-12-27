package lk.ijse.t_shop.bo.custom;

import lk.ijse.t_shop.bo.SuperBO;

import java.sql.SQLException;

public interface DashboardBO extends SuperBO {
    String generateNextOrderId() throws SQLException, ClassNotFoundException;

    String generateNextRecordId() throws SQLException, ClassNotFoundException;

    String generateNextCustomerId() throws SQLException, ClassNotFoundException;
}
