package lk.ijse.t_shop.model;

import lk.ijse.t_shop.db.DbConnection;
import lk.ijse.t_shop.dto.customerDto;
import lk.ijse.t_shop.dto.itemDto;
import lk.ijse.t_shop.dto.tm.CartTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemModel {
    public String genarateItemCode() throws SQLException {
        Connection connection= DbConnection.getInstance().getConnection();
        String sql = "SELECT itemCode FROM item ORDER BY itemCode DESC LIMIT 1";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet =pstm.executeQuery();
        if (resultSet.next()){
            return splitItemCode(resultSet.getNString(1));
        }
        return splitItemCode(null);
    }
    private String splitItemCode(String currentOrderId) {
        if(currentOrderId != null) {
            String[] split = currentOrderId.split("I0");

            int id = Integer.parseInt(split[1]);
            id++;
            String formattedNumericPart =String.format("%03d",id);
            return "I" + formattedNumericPart;
        } else {
            return "I001";
        }
    }

    public boolean saveItem(itemDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO item VALUES(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,dto.getItemCode());
        pstm.setString(2,dto.getType());
        pstm.setDouble(3,dto.getPrice());
        pstm.setInt(4,dto.getQuntity());
        pstm.setDouble(5,dto.getDiscountPercentage());
        pstm.setString(6,dto.getSize());
        pstm.setString(7,dto.getColor());

        return pstm.executeUpdate() > 0;
    }
    public List<itemDto> getAllItem() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql ="SELECT * FROM item";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet=pstm.executeQuery();
        ArrayList<itemDto> dtos = new ArrayList<>();
        while (resultSet.next()){
            dtos.add(
                    new itemDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getDouble(3),
                            resultSet.getInt(4),
                            resultSet.getDouble(5),
                            resultSet.getString(6),
                            resultSet.getString(7)
                    )
            );
        }
        return dtos;
    }

    public itemDto searchItem(String code) throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM item WHERE itemCode = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,code);
        ResultSet resultSet= pstm.executeQuery();
        itemDto dto=null;
        if (resultSet.next()){
            String itemCode = resultSet.getString(1);
            String type = resultSet.getString(2);
            double price = resultSet.getDouble(3);
            int quntity = resultSet.getInt(4);
            double discount= resultSet.getDouble(5);
            String size = resultSet.getString(6);
            String color = resultSet.getString(7);
            dto=new itemDto(itemCode,type,price,quntity,discount,size,color);
        }
        return dto;
    }

    public boolean updateItem(itemDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE item SET type = ?, price = ?, quntity = ?, discountPercentage = ?, size = ?, color = ? WHERE itemCode = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,dto.getType());
        pstm.setDouble(2,dto.getPrice());
        pstm.setInt(3, dto.getQuntity());
        pstm.setDouble(4, dto.getDiscountPercentage());
        pstm.setString(5,dto.getSize());
        pstm.setString(6,dto.getColor());
        pstm.setString(7,dto.getItemCode());


        return pstm.executeUpdate()> 0;
    }

    public boolean deleteItem(String code) throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();
        String sql = "DELETE FROM item WHERE itemCode = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,code);
        return pstm.executeUpdate()>0;
    }
    public boolean updateItem(List<CartTm> cartTmList) throws SQLException {
        for(CartTm tm : cartTmList) {
            System.out.println("Item: " + tm);
            if(!updateQty(tm.getCode(), tm.getQty())) {
                return false;
            }
        }
        return true;
    }
    public boolean updateQty(String code, int qty) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE item SET quntity = quntity - ? WHERE itemCode = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setInt(1, qty);
        pstm.setString(2, code);

        return pstm.executeUpdate() > 0; //false
    }
}

