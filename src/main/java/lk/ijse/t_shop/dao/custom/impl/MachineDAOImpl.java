package lk.ijse.t_shop.dao.custom.impl;

import lk.ijse.t_shop.dao.SQLUtil;
import lk.ijse.t_shop.dao.custom.MachineDAO;
import lk.ijse.t_shop.entity.Machine;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MachineDAOImpl implements MachineDAO {
    @Override
    public ArrayList<Machine> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM machine");
        ArrayList<Machine> dtos = new ArrayList<>();
        while (resultSet.next()){
            dtos.add(
                    new Machine(
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
    public boolean save(Machine entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO machine VALUES(?, ?, ?, ?)",entity.getMachineCode(),entity.getName(),entity.getMachineType(),entity.getTailerId());
    }

    @Override
    public boolean update(Machine entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE machine SET name =?, machineType =?, tailerId =? WHERE machineCode = ?",entity.getName(),entity.getMachineType(),entity.getTailerId(),entity.getMachineCode());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM machine where machineCode = ?",id);
    }

    @Override
    public String splitId(String currentOrderId) {
        if(currentOrderId != null) {
            String[] split = currentOrderId.split("M0");

            int id = Integer.parseInt(split[1]);
            id++;
            String formattedNumericPart =String.format("%03d",id);
            return "M" + formattedNumericPart;
        } else {
            return "M001";
        }
    }

    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT machineCode FROM machine ORDER BY machineCode DESC LIMIT 1");
        if (resultSet.next()){
            return splitId(resultSet.getNString(1));
        }
        return splitId(null);
    }

    @Override
    public Machine search(String newValue) throws SQLException, ClassNotFoundException {
        ResultSet resultSet= SQLUtil.execute("SELECT * FROM machine WHERE machineCode = ?",newValue);
        Machine dto=null;
        if (resultSet.next()){
            String Code = resultSet.getString(1);
            String name = resultSet.getString(2);
            String type = resultSet.getString(3);
            String tId = resultSet.getString(4);
            dto=new Machine(Code,name,type,tId);
        }
        return dto;
    }
}
