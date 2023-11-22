package lk.ijse.t_shop.model;

import lk.ijse.t_shop.db.DbConnection;
import lk.ijse.t_shop.dto.raw_materialDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RawMaterialModel {
    public String genarateNextId() throws SQLException {
        Connection connection= DbConnection.getInstance().getConnection();
        String sql = "SELECT rawId FROM rawMaterial ORDER BY rawId DESC LIMIT 1";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet =pstm.executeQuery();
        if (resultSet.next()){
            return splitRawId(resultSet.getNString(1));
        }
        return splitRawId(null);
    }
    private String splitRawId(String currentOrderId) {
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

    public boolean saveRawM(raw_materialDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO rawMaterial VALUES(?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,dto.getRawID());
        pstm.setString(2,dto.getName());
        pstm.setString(3, String.valueOf(dto.getQuntity()));
        return pstm.executeUpdate() > 0;
    }

    public List<raw_materialDto> getAllMaterial() throws SQLException {
        Connection connection= DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM rawMaterial";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        ArrayList<raw_materialDto> dtos = new ArrayList<>();
       while (resultSet.next()){
           dtos.add(
                   new raw_materialDto(
                           resultSet.getString(1),
                           resultSet.getString(2),
                           resultSet.getInt(3)
                   )
           );
       }
        return dtos;
    }

    public raw_materialDto searchMaterial(String id) throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM rawMaterial WHERE rawId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);
        ResultSet resultSet= pstm.executeQuery();
        raw_materialDto dto=null;
        if (resultSet.next()){
            String rawId = resultSet.getString(1);
            String name = resultSet.getString(2);
           int qty = resultSet.getInt(3);
            dto=new raw_materialDto(rawId,name,qty);
        }
        return dto;
    }

    public boolean updateMaterial(raw_materialDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE rawMaterial SET name =?, quntity =? WHERE rawId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,dto.getName());
        pstm.setString(2,String.valueOf(dto.getQuntity()));
        pstm.setString(3,dto.getRawID());

        return pstm.executeUpdate()> 0;
    }

    public boolean deleteMaterial(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql= "Delete from rawMaterial WHERE rawId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);
        return pstm.executeUpdate()>0;
    }
}
