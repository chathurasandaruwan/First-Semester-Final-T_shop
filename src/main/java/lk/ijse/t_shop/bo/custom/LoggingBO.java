package lk.ijse.t_shop.bo.custom;

import lk.ijse.t_shop.bo.SuperBO;
import lk.ijse.t_shop.dto.UserDto;

import java.sql.SQLException;

public interface LoggingBO extends SuperBO {
    UserDto getAllUserDetail() throws SQLException, ClassNotFoundException;
}
