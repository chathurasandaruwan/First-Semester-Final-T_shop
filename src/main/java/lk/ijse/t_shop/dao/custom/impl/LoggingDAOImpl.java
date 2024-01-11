package lk.ijse.t_shop.dao.custom.impl;

import lk.ijse.t_shop.dao.SQLUtil;
import lk.ijse.t_shop.dao.custom.LoggingDAO;
import lk.ijse.t_shop.dto.UserDto;
import lk.ijse.t_shop.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoggingDAOImpl implements LoggingDAO {
    @Override
    public User getAllInfo() throws SQLException, ClassNotFoundException {
        ResultSet resultSet= SQLUtil.execute("SELECT * from userInfo");
        User dto=null;
        if (resultSet.next()){
            String name = resultSet.getString(1);
            String password = resultSet.getString(2);
            String tel = resultSet.getString(3);
            dto=new User(name,password,tel);
        }
        return dto;
    }

}
