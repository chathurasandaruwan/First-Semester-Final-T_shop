package lk.ijse.t_shop.dao.custom;

import lk.ijse.t_shop.dao.CrudDAO;
import lk.ijse.t_shop.dto.ItemDto;
import lk.ijse.t_shop.entity.Item;
import lk.ijse.t_shop.view.tdm.CartTm;

import java.sql.SQLException;
import java.util.List;

public interface ItemDAO extends CrudDAO<Item> {
    boolean updateItem(List<CartTm> cartTmList) throws SQLException, ClassNotFoundException;

    boolean updateQty(String code, int qty) throws SQLException, ClassNotFoundException;
}
