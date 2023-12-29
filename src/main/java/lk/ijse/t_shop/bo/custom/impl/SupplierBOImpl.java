package lk.ijse.t_shop.bo.custom.impl;

import lk.ijse.t_shop.bo.custom.SupplierBO;
import lk.ijse.t_shop.dao.DAOFactory;
import lk.ijse.t_shop.dao.custom.SupplierDAO;
import lk.ijse.t_shop.dao.custom.impl.SupplierDAOImpl;
import lk.ijse.t_shop.dto.ItemDto;
import lk.ijse.t_shop.dto.SupplierDto;
import lk.ijse.t_shop.entity.Item;
import lk.ijse.t_shop.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBO {
    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.SUPPLIER);
    @Override
    public ArrayList<SupplierDto> getAllSupplier() throws SQLException, ClassNotFoundException {
        ArrayList<SupplierDto> supplierDtos = new ArrayList<>();
        ArrayList<Supplier> suppliers = supplierDAO.getAll();
        for (Supplier supplier : suppliers) {
            supplierDtos.add(new SupplierDto(supplier.getSupId(),supplier.getName(),supplier.getDescription(),supplier.getContacNo()));
        }
        return supplierDtos;
    }
    @Override
    public boolean saveSupplier(SupplierDto dto) throws SQLException, ClassNotFoundException {
        return supplierDAO.save(new Supplier(dto.getSupId(),dto.getName(),dto.getDescription(),dto.getContacNo()));
    }
    @Override
    public boolean updateSupplier(SupplierDto dto) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(new Supplier(dto.getSupId(),dto.getName(),dto.getDescription(),dto.getContacNo()));
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
        Supplier entity= supplierDAO.search(newValue);
        return new SupplierDto(entity.getSupId(),entity.getName(),entity.getDescription(),entity.getContacNo());
    }
}
