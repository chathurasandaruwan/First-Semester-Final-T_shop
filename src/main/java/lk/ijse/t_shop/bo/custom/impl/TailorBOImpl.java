package lk.ijse.t_shop.bo.custom.impl;

import lk.ijse.t_shop.bo.custom.TailorBO;
import lk.ijse.t_shop.dao.DAOFactory;
import lk.ijse.t_shop.dao.custom.ItemDAO;
import lk.ijse.t_shop.dao.custom.TailorDAO;
import lk.ijse.t_shop.dao.custom.impl.ItemDAOImpl;
import lk.ijse.t_shop.dao.custom.impl.TailorDAOImpl;
import lk.ijse.t_shop.dto.ItemDto;
import lk.ijse.t_shop.dto.TailorDto;
import lk.ijse.t_shop.entity.Item;
import lk.ijse.t_shop.entity.Tailor;

import java.sql.SQLException;
import java.util.ArrayList;

public class TailorBOImpl implements TailorBO {
    TailorDAO tailorDAO = (TailorDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.TAILOR);
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ITEM);
    @Override
    public ArrayList<TailorDto> getAllTailor() throws SQLException, ClassNotFoundException {
        ArrayList<TailorDto> tailorDtos = new ArrayList<>();
        ArrayList<Tailor> tailors = tailorDAO.getAll();
        for (Tailor tailor : tailors) {
            tailorDtos.add(new TailorDto(tailor.getTailerId(),tailor.getName(),tailor.getContacNo(),tailor.getItemCode()));
        }
        return tailorDtos;
    }
    @Override
    public boolean saveTailor(TailorDto dto) throws SQLException, ClassNotFoundException {
        return tailorDAO.save(new Tailor(dto.getTailerId(),dto.getName(),dto.getContacNo(),dto.getItemCode()));
    }
    @Override
    public boolean updateTailor(TailorDto dto) throws SQLException, ClassNotFoundException {
        return tailorDAO.update(new Tailor(dto.getTailerId(),dto.getName(),dto.getContacNo(),dto.getItemCode()));
    }
    @Override
    public boolean deleteTailor(String id) throws SQLException, ClassNotFoundException {
        return tailorDAO.delete(id);
    }
    @Override
    public String generateNextTailorId() throws SQLException, ClassNotFoundException {
        return tailorDAO.generateNextId();
    }
    @Override
    public TailorDto searchTailor(String newValue) throws SQLException, ClassNotFoundException {
        Tailor tailor= tailorDAO.search(newValue);
        return new TailorDto(tailor.getTailerId(),tailor.getName(),tailor.getContacNo(),tailor.getItemCode());
    }
    @Override
    public ArrayList<ItemDto> getAllItem() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDto> itemDtos = new ArrayList<>();
        ArrayList<Item> items = itemDAO.getAll();
        for (Item item : items) {
            itemDtos.add(new ItemDto(item.getItemCode(),item.getType(),item.getPrice(),item.getQuntity(),item.getDiscountPercentage(),item.getSize(),item.getColor()));
        }
        return itemDtos;
    }
}
