package lk.ijse.t_shop.bo.custom;

import lk.ijse.t_shop.bo.SuperBO;
import lk.ijse.t_shop.dto.SupplierDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBO extends SuperBO {
    ArrayList<SupplierDto> getAllSupplier() throws SQLException, ClassNotFoundException;

    boolean saveSupplier(SupplierDto dto) throws SQLException, ClassNotFoundException;

    boolean updateSupplier(SupplierDto dto) throws SQLException, ClassNotFoundException;

    boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException;

    String generateNextSupplierId() throws SQLException, ClassNotFoundException;

    SupplierDto searchSupplier(String newValue) throws SQLException, ClassNotFoundException;
}
