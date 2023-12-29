package lk.ijse.t_shop.dao.custom.impl;

import lk.ijse.t_shop.dao.SQLUtil;
import lk.ijse.t_shop.dao.custom.CustomerDAO;
import lk.ijse.t_shop.dto.CustomerDto;
import lk.ijse.t_shop.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet=SQLUtil.execute("SELECT * FROM customer");
        ArrayList<Customer> dtos = new ArrayList<>();
        while (resultSet.next()){
            dtos.add(
                    new Customer(
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
    public boolean save(Customer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO customer VALUES(?, ?, ?, ?)",entity.getCustId(),entity.getName(),entity.getAddress(),entity.getContacNo());
    }

    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE customer SET name = ?, address = ?, contacNo = ? WHERE custId = ?",entity.getName(),entity.getAddress(),entity.getContacNo(),entity.getCustId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM customer WHERE custId = ?",id);
    }
    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT custId FROM customer ORDER BY custId DESC LIMIT 1");
        if (resultSet.next()){
            return splitId(resultSet.getNString(1));
        }
        return splitId(null);
    }
    @Override
    public String splitId(String currentOrderId) {
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

    @Override
    public Customer search(String newValue) throws SQLException, ClassNotFoundException {
        ResultSet resultSet= SQLUtil.execute("SELECT * FROM customer WHERE custId = ?",newValue);
        Customer dto=null;
        if (resultSet.next()){
            String custId = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String tel = resultSet.getString(4);
            dto=new Customer(custId,name,address,tel);
        }
        return dto;

    }
}
