package lk.ijse.t_shop.model;

import lk.ijse.t_shop.db.DbConnection;
import lk.ijse.t_shop.dto.customerDto;
import lk.ijse.t_shop.dto.tm.customerTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerModel {
    public String genarateCustId() throws SQLException {
        Connection connection= DbConnection.getInstance().getConnection();
        String sql = "SELECT custId FROM customer ORDER BY custId DESC LIMIT 1";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet =pstm.executeQuery();
        if (resultSet.next()){
            return splitCustomerId(resultSet.getNString(1));
        }
            return splitCustomerId(null);
    }
    private String splitCustomerId(String currentOrderId) {
        if(currentOrderId != null) {
            String[] split = currentOrderId.split("C0");

            int id = Integer.parseInt(split[1]); //01
            id++;
            String formattedNumericPart =String.format("%03d",id);
            return "C" + formattedNumericPart;
        } else {
            return "C001";
        }
    }
    public boolean saveCustomer(customerDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO customer VALUES(?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
         pstm.setString(1,dto.getCustId());
         pstm.setString(2,dto.getName());
         pstm.setString(3,dto.getAddress());
         pstm.setString(4,dto.getContacNo());

         return pstm.executeUpdate() > 0;
    }

    public customerDto searchCustomer(String id) throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM customer WHERE custId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);
        ResultSet resultSet= pstm.executeQuery();
        customerDto dto=null;
        if (resultSet.next()){
            String custId = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String tel = resultSet.getString(4);
            dto=new customerDto(custId,name,address,tel);
        }
        return dto;
    }
    public boolean deleteCustomer(String id) throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();
        String sql = "DELETE FROM customer WHERE custId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);
        return pstm.executeUpdate()>0;
    }
    public  boolean updateCustomer(customerDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE customer SET name = ?, address = ?, contacNo = ? WHERE custId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,dto.getName());
        pstm.setString(2,dto.getAddress());
        pstm.setString(3,dto.getContacNo());
        pstm.setString(4,dto.getCustId());

        return pstm.executeUpdate()> 0;
    }
    public List<customerDto> getAllCustomer() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql ="SELECT * FROM customer";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet=pstm.executeQuery();
        ArrayList<customerDto> dtos = new ArrayList<>();
             while (resultSet.next()){
                    dtos.add(
                        new customerDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4)
                        )
                    );
             }
             return dtos;
    }

}
