package lk.ijse.t_shop.dao.custom;

import lk.ijse.t_shop.dao.SupperDAO;
import lk.ijse.t_shop.entity.User;

import java.sql.SQLException;

public interface LoggingDAO extends SupperDAO {
    User getAllInfo() throws SQLException, ClassNotFoundException;
}
