package lk.ijse.t_shop.model;

import lk.ijse.t_shop.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ChangePasswordModel {
    public boolean changePassword(String password) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE userInfo SET password =? WHERE name = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,password);
        pstm.setString(2,"chathura");
        return pstm.executeUpdate()>0;
    }
}
