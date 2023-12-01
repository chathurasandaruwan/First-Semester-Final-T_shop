package lk.ijse.t_shop.model;

import lk.ijse.t_shop.db.DbConnection;
import lk.ijse.t_shop.dto.userDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoggingModel {
    public userDto getAllInfo() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "select * from userInfo";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet=pstm.executeQuery();
        userDto dto=null;
        if (resultSet.next()){
            String name = resultSet.getString(1);
            String password = resultSet.getString(2);
            String tel = resultSet.getString(3);
            dto=new userDto(name,password,tel);
        }
        return dto;
    }
}
