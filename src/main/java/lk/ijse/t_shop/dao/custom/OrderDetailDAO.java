package lk.ijse.t_shop.dao.custom;

import lk.ijse.t_shop.dao.CrudDAO;
import lk.ijse.t_shop.dto.PlaceOrderDto;
import lk.ijse.t_shop.view.tdm.CartTm;

import java.sql.SQLException;

public interface OrderDetailDAO extends CrudDAO<PlaceOrderDto> {
    boolean saveOrderDetail(String orderId, CartTm tm) throws SQLException, ClassNotFoundException;
}
