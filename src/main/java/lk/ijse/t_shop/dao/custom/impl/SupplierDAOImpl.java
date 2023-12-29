package lk.ijse.t_shop.dao.custom.impl;

import lk.ijse.t_shop.dao.SQLUtil;
import lk.ijse.t_shop.dao.custom.SupplierDAO;
import lk.ijse.t_shop.dto.SupplierDto;
import lk.ijse.t_shop.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOImpl implements SupplierDAO {
    @Override
    public ArrayList<Supplier> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet= SQLUtil.execute("SELECT * FROM supplier");
        ArrayList<Supplier> dtos = new ArrayList<>();
        while (resultSet.next()){
            dtos.add(
                    new Supplier(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4)
                    )
            );
        }
        return dtos;
    }

    @Override
    public boolean save(Supplier entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO supplier VALUES(?, ?, ?, ?)",entity.getSupId(),entity.getName(),entity.getDescription(),entity.getContacNo());
    }

    @Override
    public boolean update(Supplier entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE supplier SET name = ?, description = ?, contacNo = ? WHERE supId = ?",entity.getName(),entity.getDescription(),entity.getContacNo(),entity.getSupId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM supplier WHERE supId = ?",id);
    }

    @Override
    public String splitId(String currentOrderId) {
        if(currentOrderId != null) {
            String[] split = currentOrderId.split("S0");

            int id = Integer.parseInt(split[1]); //01
            id++;
            String formattedNumericPart =String.format("%03d",id);
            return "S" + formattedNumericPart;
        } else {
            return "S001";
        }
    }

    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT supId FROM supplier ORDER BY supId DESC LIMIT 1");
        if (resultSet.next()){
            return splitId(resultSet.getNString(1));
        }
        return splitId(null);
    }

    @Override
    public Supplier search(String newValue) throws SQLException, ClassNotFoundException {
        ResultSet resultSet= SQLUtil.execute("SELECT * FROM supplier WHERE supId = ?",newValue);
        Supplier dto=null;
        if (resultSet.next()){
            String supId = resultSet.getString(1);
            String name = resultSet.getString(2);
            String description = resultSet.getString(3);
            String contacNo = resultSet.getString(4);
            dto=new Supplier(supId,name,description,contacNo);
        }
        return dto;
    }
}
