package lk.ijse.t_shop.bo.custom.impl;

import lk.ijse.t_shop.bo.custom.TailorBO;
import lk.ijse.t_shop.dao.DAOFactory;
import lk.ijse.t_shop.dao.custom.ItemDAO;
import lk.ijse.t_shop.dao.custom.TailorDAO;
import lk.ijse.t_shop.dao.custom.impl.ItemDAOImpl;
import lk.ijse.t_shop.dao.custom.impl.TailorDAOImpl;
import lk.ijse.t_shop.dto.ItemDto;
import lk.ijse.t_shop.dto.TailorDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class TailorBOImpl implements TailorBO {
    TailorDAO tailorDAO = (TailorDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.TAILOR);
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ITEM);
    @Override
    public ArrayList<TailorDto> getAllTailor() throws SQLException, ClassNotFoundException {
        return tailorDAO.getAll();
    }
    @Override
    public boolean saveTailor(TailorDto dto) throws SQLException, ClassNotFoundException {
        return tailorDAO.save(dto);
    }
    @Override
    public boolean updateTailor(TailorDto dto) throws SQLException, ClassNotFoundException {
        return tailorDAO.update(dto);
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
        return tailorDAO.search(newValue);
    }
    @Override
    public ArrayList<ItemDto> getAllItem() throws SQLException, ClassNotFoundException {
        return itemDAO.getAll();
    }
}
