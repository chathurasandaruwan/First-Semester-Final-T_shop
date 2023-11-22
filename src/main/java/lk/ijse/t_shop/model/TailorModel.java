package lk.ijse.t_shop.model;

import lk.ijse.t_shop.db.DbConnection;
import lk.ijse.t_shop.dto.raw_materialDto;
import lk.ijse.t_shop.dto.tailorDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TailorModel {

    public String genarateNextId() throws SQLException {
        Connection connection= DbConnection.getInstance().getConnection();
        String sql = "SELECT tailerId FROM tailer ORDER BY tailerId DESC LIMIT 1";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet =pstm.executeQuery();
        if (resultSet.next()){
            return splitTailorId(resultSet.getNString(1));
        }
        return splitTailorId(null);
    }
    private String splitTailorId(String currentOrderId) {
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

    public boolean saveTailor(tailorDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO tailer VALUES(?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,dto.getTailerId());
        pstm.setString(2,dto.getName());
        pstm.setInt(3,dto.getContacNo());
        pstm.setString(4,dto.getItemCode());
        return pstm.executeUpdate() > 0;

    }

    public List<tailorDto> getAllTailor() throws SQLException {
        Connection connection= DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM tailer";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        ArrayList<tailorDto> dtos = new ArrayList<>();
        while (resultSet.next()){
            dtos.add(
                    new tailorDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getInt(3),
                            resultSet.getString(4)
                    )
            );
        }
        return dtos;
    }

    public tailorDto searchTailer(String id) throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM tailer WHERE tailerId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);
        ResultSet resultSet= pstm.executeQuery();
        tailorDto dto=null;
        if (resultSet.next()){
            String Id = resultSet.getString(1);
            String name = resultSet.getString(2);
            int qty = resultSet.getInt(3);
            String ItemCode = resultSet.getString(4);
            dto=new tailorDto(Id,name,qty,ItemCode);
        }
        return dto;
    }

    public boolean updateTailor(tailorDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE tailer SET name =?, contacNo =?, itemCode =? WHERE tailerId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,dto.getName());
        pstm.setString(2,String.valueOf(dto.getContacNo()));
        pstm.setString(3,dto.getItemCode());
        pstm.setString(4,dto.getTailerId());

        return pstm.executeUpdate()> 0;

    }

    public boolean deleteTailor(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql= "Delete from tailer WHERE tailerId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);
        return pstm.executeUpdate()>0;
    }
}
