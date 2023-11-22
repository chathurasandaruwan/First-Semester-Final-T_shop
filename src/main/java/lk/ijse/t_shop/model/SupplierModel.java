package lk.ijse.t_shop.model;

import lk.ijse.t_shop.db.DbConnection;
import lk.ijse.t_shop.dto.customerDto;
import lk.ijse.t_shop.dto.supplierDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierModel {

    public String genarateSupId() throws SQLException {
        Connection connection= DbConnection.getInstance().getConnection();
        String sql = "SELECT supId FROM supplier ORDER BY supId DESC LIMIT 1";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet =pstm.executeQuery();
        if (resultSet.next()){
            return splitSupId(resultSet.getNString(1));
        }
        return splitSupId(null);
    }

    private String splitSupId(String currentOrderId) {
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

    public boolean saveSupllier(supplierDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO supplier VALUES(?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,dto.getSupId());
        pstm.setString(2,dto.getName());
        pstm.setString(3,dto.getDescription());
        pstm.setString(4,dto.getContacNo());

        return pstm.executeUpdate() > 0;

    }
    public supplierDto searchCustomer(String id) throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM supplier WHERE supId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);
        ResultSet resultSet= pstm.executeQuery();
        supplierDto dto=null;
        if (resultSet.next()){
            String supId = resultSet.getString(1);
            String name = resultSet.getString(2);
            String description = resultSet.getString(3);
            String contacNo = resultSet.getString(4);
            dto=new supplierDto(supId,name,description,contacNo);
        }
        return dto;
    }

    public List<supplierDto> getAllSup() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql ="SELECT * FROM supplier";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet=pstm.executeQuery();
        ArrayList<supplierDto> dtos = new ArrayList<>();
        while (resultSet.next()){
            dtos.add(
                    new supplierDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4)
                    )
            );
        }
        return dtos;
    }

    public boolean deleteSupplier(String id) throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();
        String sql = "DELETE FROM supplier WHERE supId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);
        return pstm.executeUpdate()>0;
    }

    public boolean updateSupplier(supplierDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE supplier SET name = ?, description = ?, contacNo = ? WHERE supId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,dto.getName());
        pstm.setString(2,dto.getDescription());
        pstm.setString(3,dto.getContacNo());
        pstm.setString(4,dto.getSupId());

        return pstm.executeUpdate()> 0;
    }
}
