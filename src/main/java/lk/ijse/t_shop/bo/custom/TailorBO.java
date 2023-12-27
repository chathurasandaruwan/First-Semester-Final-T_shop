package lk.ijse.t_shop.bo.custom;

import lk.ijse.t_shop.bo.SuperBO;
import lk.ijse.t_shop.dto.ItemDto;
import lk.ijse.t_shop.dto.TailorDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TailorBO extends SuperBO {

    ArrayList<TailorDto> getAllTailor() throws SQLException, ClassNotFoundException;

    boolean saveTailor(TailorDto dto) throws SQLException, ClassNotFoundException;

    boolean updateTailor(TailorDto dto) throws SQLException, ClassNotFoundException;

    boolean deleteTailor(String id) throws SQLException, ClassNotFoundException;

    String generateNextTailorId() throws SQLException, ClassNotFoundException;

    TailorDto searchTailor(String newValue) throws SQLException, ClassNotFoundException;

    ArrayList<ItemDto> getAllItem() throws SQLException, ClassNotFoundException;
}
