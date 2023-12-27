package lk.ijse.t_shop.dao.custom.impl;

import lk.ijse.t_shop.dao.SQLUtil;
import lk.ijse.t_shop.dao.custom.LoggingDAO;
import lk.ijse.t_shop.db.DbConnection;
import lk.ijse.t_shop.dto.UserDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoggingDAOImpl implements LoggingDAO {
    @Override
    public UserDto getAllInfo() throws SQLException, ClassNotFoundException {
        ResultSet resultSet= SQLUtil.execute("SELECT * from userInfo");
        UserDto dto=null;
        if (resultSet.next()){
            String name = resultSet.getString(1);
            String password = resultSet.getString(2);
            String tel = resultSet.getString(3);
            dto=new UserDto(name,password,tel);
        }
        return dto;
    }

}
