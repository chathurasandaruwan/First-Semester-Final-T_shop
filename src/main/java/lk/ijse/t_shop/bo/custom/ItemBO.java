package lk.ijse.t_shop.bo.custom;

import lk.ijse.t_shop.bo.SuperBO;
import lk.ijse.t_shop.dto.ItemDto;
import lk.ijse.t_shop.view.tdm.CartTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ItemBO extends SuperBO {
    ArrayList<ItemDto> getAllItem() throws SQLException, ClassNotFoundException;

    boolean saveItem(ItemDto dto) throws SQLException, ClassNotFoundException;

    boolean updateItem(ItemDto dto) throws SQLException, ClassNotFoundException;

    boolean deleteItem(String id) throws SQLException, ClassNotFoundException;

    String generateNextItemCode() throws SQLException, ClassNotFoundException;

    ItemDto searchItem(String newValue) throws SQLException, ClassNotFoundException;

}
