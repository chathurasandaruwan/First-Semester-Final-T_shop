package lk.ijse.t_shop.bo.custom.impl;

import lk.ijse.t_shop.bo.custom.ItemBO;
import lk.ijse.t_shop.dao.DAOFactory;
import lk.ijse.t_shop.dao.custom.ItemDAO;
import lk.ijse.t_shop.dao.custom.impl.ItemDAOImpl;
import lk.ijse.t_shop.dto.CustomerDto;
import lk.ijse.t_shop.dto.ItemDto;
import lk.ijse.t_shop.entity.Customer;
import lk.ijse.t_shop.entity.Item;
import lk.ijse.t_shop.view.tdm.CartTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBO {
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ITEM);
    @Override
    public ArrayList<ItemDto> getAllItem() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDto> itemDtos = new ArrayList<>();
        ArrayList<Item> items = itemDAO.getAll();
        for (Item item : items) {
            itemDtos.add(new ItemDto(item.getItemCode(),item.getType(),item.getPrice(),item.getQuntity(),item.getDiscountPercentage(),item.getSize(),item.getColor()));
        }
        return itemDtos;
    }
    @Override
    public boolean saveItem(ItemDto dto) throws SQLException, ClassNotFoundException {
        return itemDAO.save(new Item(dto.getItemCode(),dto.getType(),dto.getPrice(),dto.getQuntity(),dto.getDiscountPercentage(),dto.getSize(),dto.getColor()));
    }
    @Override
    public boolean updateItem(ItemDto dto) throws SQLException, ClassNotFoundException {
        return itemDAO.update(new Item(dto.getItemCode(),dto.getType(),dto.getPrice(),dto.getQuntity(),dto.getDiscountPercentage(),dto.getSize(),dto.getColor()));
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
       Item entity = itemDAO.search(newValue);
       return new ItemDto(entity.getItemCode(),entity.getType(),entity.getPrice(),entity.getQuntity(),entity.getDiscountPercentage(),entity.getSize(),entity.getColor());
    }
}
