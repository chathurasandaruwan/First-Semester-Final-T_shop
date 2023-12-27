package lk.ijse.t_shop.bo.custom;

import lk.ijse.t_shop.bo.SuperBO;
import lk.ijse.t_shop.dto.Raw_materialDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RawMaterialBO extends SuperBO {
    ArrayList<Raw_materialDto> getAllRawMaterial() throws SQLException, ClassNotFoundException;

    boolean saveRawMaterial(Raw_materialDto dto) throws SQLException, ClassNotFoundException;

    boolean updateRawMaterial(Raw_materialDto dto) throws SQLException, ClassNotFoundException;

    boolean deleteRawMaterial(String id) throws SQLException, ClassNotFoundException;

    String generateNextRawMaterialId() throws SQLException, ClassNotFoundException;

    Raw_materialDto searchRawMaterial(String newValue) throws SQLException, ClassNotFoundException;
}
