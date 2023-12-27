package lk.ijse.t_shop.bo.custom.impl;

import lk.ijse.t_shop.bo.custom.ItemBO;
import lk.ijse.t_shop.dao.DAOFactory;
import lk.ijse.t_shop.dao.custom.ItemDAO;
import lk.ijse.t_shop.dao.custom.impl.ItemDAOImpl;
import lk.ijse.t_shop.dto.ItemDto;
import lk.ijse.t_shop.view.tdm.CartTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBO {
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ITEM);
    @Override
    public ArrayList<ItemDto> getAllItem() throws SQLException, ClassNotFoundException {
        return itemDAO.getAll();
    }
    @Override
    public boolean saveItem(ItemDto dto) throws SQLException, ClassNotFoundException {
        return itemDAO.save(dto);
    }
    @Override
    public boolean updateItem(ItemDto dto) throws SQLException, ClassNotFoundException {
        return itemDAO.update(dto);
    }
    @Override
    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(id);
    }
    @Override
    public String generateNextItemCode() throws SQLException, ClassNotFoundException {
        return itemDAO.generateNextId();
    }
    @Override
    public ItemDto searchItem(String newValue) throws SQLException, ClassNotFoundException {
        return itemDAO.search(newValue);
    }
}
