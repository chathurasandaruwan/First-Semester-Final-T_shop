package lk.ijse.t_shop.bo.custom;

import lk.ijse.t_shop.bo.SuperBO;
import lk.ijse.t_shop.dto.UserDto;

import java.sql.SQLException;

public interface ForgotPasswordBO extends SuperBO {

    UserDto getAllDetail() throws SQLException, ClassNotFoundException;
}
