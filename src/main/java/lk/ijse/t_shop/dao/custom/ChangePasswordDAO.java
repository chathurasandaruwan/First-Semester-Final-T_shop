package lk.ijse.t_shop.dao.custom;

import lk.ijse.t_shop.dao.CrudDAO;
import lk.ijse.t_shop.dao.SupperDAO;
import lk.ijse.t_shop.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface ChangePasswordDAO extends SupperDAO {
    boolean changePassword(String password) throws SQLException, ClassNotFoundException;

}
