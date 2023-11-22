package lk.ijse.t_shop.model;

import lk.ijse.t_shop.db.DbConnection;
import lk.ijse.t_shop.dto.machineDto;
import lk.ijse.t_shop.dto.tailorDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class MachineModel {
    public String genarateNextId() throws SQLException {
        Connection connection= DbConnection.getInstance().getConnection();
        String sql = "SELECT machineCode FROM machine ORDER BY machineCode DESC LIMIT 1";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet =pstm.executeQuery();
        if (resultSet.next()){
            return splitMachineCode(resultSet.getNString(1));
        }
        return splitMachineCode(null);
    }
    private String splitMachineCode(String currentOrderId) {
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

    public boolean saveMachine(machineDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO machine VALUES(?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,dto.getMachineCode());
        pstm.setString(2,dto.getName());
        pstm.setString(3,dto.getMachineType());
        pstm.setString(4,dto.getTailerId());
        return pstm.executeUpdate() > 0;
    }

    public List<machineDto> getAllMachine() throws SQLException {
        Connection connection= DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM machine";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        ArrayList<machineDto> dtos = new ArrayList<>();
        while (resultSet.next()){
            dtos.add(
                    new machineDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4)
                    )
            );
        }
        return dtos;
    }

    public machineDto searchMachine(String code) throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM machine WHERE machineCode = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,code);
        ResultSet resultSet= pstm.executeQuery();
        machineDto dto=null;
        if (resultSet.next()){
            String Code = resultSet.getString(1);
            String name = resultSet.getString(2);
            String type = resultSet.getString(3);
            String tId = resultSet.getString(4);
            dto=new machineDto(Code,name,type,tId);
        }
        return dto;
    }

    public boolean updateMachine(machineDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE machine SET name =?, machineType =?, tailerId =? WHERE machineCode = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,dto.getName());
        pstm.setString(2,dto.getMachineType());
        pstm.setString(3,dto.getTailerId());
        pstm.setString(4,dto.getMachineCode());

        return pstm.executeUpdate()> 0;
    }

    public boolean deleteMachine(String code) throws SQLException {
     Connection connection = DbConnection.getInstance().getConnection();
        String sql ="DELETE FROM machine where machineCode = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,code);
        return pstm.executeUpdate()>0;
    }
}
