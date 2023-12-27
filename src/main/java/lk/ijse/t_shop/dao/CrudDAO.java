package lk.ijse.t_shop.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> extends SupperDAO {
    ArrayList<T> getAll() throws SQLException, ClassNotFoundException;

    boolean save(T dto) throws SQLException, ClassNotFoundException;

    boolean update(T dto) throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;
   String splitId(String currentOrderId);

    String generateNextId() throws SQLException, ClassNotFoundException;

    T search(String newValue) throws SQLException, ClassNotFoundException;
}
