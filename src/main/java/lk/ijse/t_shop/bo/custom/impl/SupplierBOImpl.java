package lk.ijse.t_shop.bo.custom.impl;

import lk.ijse.t_shop.bo.custom.SupplierBO;
import lk.ijse.t_shop.dao.DAOFactory;
import lk.ijse.t_shop.dao.custom.SupplierDAO;
import lk.ijse.t_shop.dao.custom.impl.SupplierDAOImpl;
import lk.ijse.t_shop.dto.SupplierDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBO {
    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.SUPPLIER);
    @Override
    public ArrayList<SupplierDto> getAllSupplier() throws SQLException, ClassNotFoundException {
        return supplierDAO.getAll();
    }
    @Override
    public boolean saveSupplier(SupplierDto dto) throws SQLException, ClassNotFoundException {
        return supplierDAO.save(dto);
    }
    @Override
    public boolean updateSupplier(SupplierDto dto) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(dto);
    }
    @Override
    public boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(id);
    }
    @Override
    public String generateNextSupplierId() throws SQLException, ClassNotFoundException {
        return supplierDAO.generateNextId();
    }
    @Override
    public SupplierDto searchSupplier(String newValue) throws SQLException, ClassNotFoundException {
        return supplierDAO.search(newValue);
    }
}
