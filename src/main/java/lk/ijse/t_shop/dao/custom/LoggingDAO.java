package lk.ijse.t_shop.dao.custom;

import lk.ijse.t_shop.dao.SupperDAO;
import lk.ijse.t_shop.dto.UserDto;

import java.sql.SQLException;

public interface LoggingDAO extends SupperDAO {
    UserDto getAllInfo() throws SQLException, ClassNotFoundException;
}
