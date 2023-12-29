package lk.ijse.t_shop.dao.custom.impl;

import lk.ijse.t_shop.dao.SQLUtil;
import lk.ijse.t_shop.dao.custom.ItemDAO;
import lk.ijse.t_shop.dto.ItemDto;
import lk.ijse.t_shop.entity.Item;
import lk.ijse.t_shop.view.tdm.CartTm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet=SQLUtil.execute("SELECT * FROM item");
        ArrayList<Item> dtos = new ArrayList<>();
        while (resultSet.next()){
            dtos.add(
                    new Item(
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

    @Override
    public boolean save(Item entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO item VALUES(?, ?, ?, ?, ?, ?, ?)",entity.getItemCode(),entity.getType(),entity.getPrice(),entity.getQuntity(),entity.getDiscountPercentage(),entity.getSize(),entity.getColor());
    }

    @Override
    public boolean update(Item entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE item SET type = ?, price = ?, quntity = ?, discountPercentage = ?, size = ?, color = ? WHERE itemCode = ?",entity.getType(),entity.getPrice(),entity.getQuntity(),entity.getDiscountPercentage(),entity.getSize(),entity.getColor(),entity.getItemCode());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM item WHERE itemCode = ?",id);
    }

    @Override
    public String splitId(String currentOrderId) {
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
    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT itemCode FROM item ORDER BY itemCode DESC LIMIT 1");
        if (resultSet.next()){
            return splitId(resultSet.getNString(1));
        }
        return splitId(null);
    }

    @Override
    public Item search(String newValue) throws SQLException, ClassNotFoundException {
        ResultSet resultSet= SQLUtil.execute("SELECT * FROM item WHERE itemCode = ?",newValue);
        Item entity=null;
        if (resultSet.next()){
            String itemCode = resultSet.getString(1);
            String type = resultSet.getString(2);
            double price = resultSet.getDouble(3);
            int quntity = resultSet.getInt(4);
            double discount= resultSet.getDouble(5);
            String size = resultSet.getString(6);
            String color = resultSet.getString(7);
            entity=new Item(itemCode,type,price,quntity,discount,size,color);
        }
        return entity;
    }
    @Override
    public boolean updateItem(List<CartTm> cartTmList) throws SQLException, ClassNotFoundException {
        for(CartTm tm : cartTmList) {
            if(!updateQty(tm.getCode(), tm.getQty())) {
                return false;
            }
        }
        return true;
    }
    @Override
    public boolean updateQty(String code, int qty) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE item SET quntity = quntity - ? WHERE itemCode = ?",qty,code);
    }
}
