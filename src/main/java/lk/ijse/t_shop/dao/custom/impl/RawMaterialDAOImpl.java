package lk.ijse.t_shop.dao.custom.impl;

import lk.ijse.t_shop.dao.SQLUtil;
import lk.ijse.t_shop.dao.custom.RawMaterialDAO;
import lk.ijse.t_shop.dto.Raw_materialDto;
import lk.ijse.t_shop.entity.Raw_material;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RawMaterialDAOImpl implements RawMaterialDAO {

    @Override
    public ArrayList<Raw_material> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM rawMaterial");
        ArrayList<Raw_material> dtos = new ArrayList<>();
        while (resultSet.next()){
            dtos.add(
                    new Raw_material(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getInt(3)
                    )
            );
        }
        return dtos;
    }

    @Override
    public boolean save(Raw_material entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO rawMaterial VALUES(?, ?, ?)",entity.getRawID(),entity.getName(),String.valueOf(entity.getQuntity()));
    }

    @Override
    public boolean update(Raw_material entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE rawMaterial SET name =?, quntity =? WHERE rawId = ?",entity.getName(),String.valueOf(entity.getQuntity()),entity.getRawID());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("Delete from rawMaterial WHERE rawId = ?",id);
    }

    @Override
    public String splitId(String currentOrderId) {
        if(currentOrderId != null) {
            String[] split = currentOrderId.split("R0");

            int id = Integer.parseInt(split[1]);
            id++;
            String formattedNumericPart =String.format("%03d",id);
            return "R" + formattedNumericPart;
        } else {
            return "R001";
        }
    }

    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT rawId FROM rawMaterial ORDER BY rawId DESC LIMIT 1");
        if (resultSet.next()){
            return splitId(resultSet.getNString(1));
        }
        return splitId(null);
    }

    @Override
    public Raw_material search(String newValue) throws SQLException, ClassNotFoundException {
        ResultSet resultSet= SQLUtil.execute("SELECT * FROM rawMaterial WHERE rawId = ?",newValue);
        Raw_material dto=null;
        if (resultSet.next()){
            String rawId = resultSet.getString(1);
            String name = resultSet.getString(2);
            int qty = resultSet.getInt(3);
            dto=new Raw_material(rawId,name,qty);
        }
        return dto;
    }
}
