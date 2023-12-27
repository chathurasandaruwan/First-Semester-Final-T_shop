package lk.ijse.t_shop.dao.custom.impl;

import lk.ijse.t_shop.dao.SQLUtil;
import lk.ijse.t_shop.dao.custom.TailorDAO;
import lk.ijse.t_shop.dto.TailorDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TailorDAOImpl implements TailorDAO {

    @Override
    public ArrayList<TailorDto> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM tailer");
        ArrayList<TailorDto> dtos = new ArrayList<>();
        while (resultSet.next()){
            dtos.add(
                    new TailorDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getInt(3),
                            resultSet.getString(4)
                    )
            );
        }
        return dtos;
    }

    @Override
    public boolean save(TailorDto dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO tailer VALUES(?, ?, ?, ?)",dto.getTailerId(),dto.getName(),dto.getContacNo(),dto.getItemCode());
    }

    @Override
    public boolean update(TailorDto dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE tailer SET name =?, contacNo =?, itemCode =? WHERE tailerId = ?",dto.getName(),dto.getContacNo(),dto.getItemCode(),dto.getTailerId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("Delete from tailer WHERE tailerId = ?",id);
    }

    @Override
    public String splitId(String currentOrderId) {
        if(currentOrderId != null) {
            String[] split = currentOrderId.split("T0");

            int id = Integer.parseInt(split[1]);
            id++;
            String formattedNumericPart =String.format("%03d",id);
            return "T" + formattedNumericPart;
        } else {
            return "T001";
        }
    }

    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT tailerId FROM tailer ORDER BY tailerId DESC LIMIT 1");
        if (resultSet.next()){
            return splitId(resultSet.getNString(1));
        }
        return splitId(null);
    }

    @Override
    public TailorDto search(String newValue) throws SQLException, ClassNotFoundException {
        ResultSet resultSet= SQLUtil.execute("SELECT * FROM tailer WHERE tailerId = ?",newValue);
        TailorDto dto=null;
        if (resultSet.next()){
            String Id = resultSet.getString(1);
            String name = resultSet.getString(2);
            int qty = resultSet.getInt(3);
            String ItemCode = resultSet.getString(4);
            dto=new TailorDto(Id,name,qty,ItemCode);
        }
        return dto;
    }
}
