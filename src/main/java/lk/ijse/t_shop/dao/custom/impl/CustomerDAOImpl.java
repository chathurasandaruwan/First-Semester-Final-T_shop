package lk.ijse.t_shop.dao.custom.impl;

import lk.ijse.t_shop.dao.SQLUtil;
import lk.ijse.t_shop.dao.custom.CustomerDAO;
import lk.ijse.t_shop.dto.CustomerDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public ArrayList<CustomerDto> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet=SQLUtil.execute("SELECT * FROM customer");
        ArrayList<CustomerDto> dtos = new ArrayList<>();
        while (resultSet.next()){
            dtos.add(
                    new CustomerDto(
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
    public boolean save(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO customer VALUES(?, ?, ?, ?)",dto.getCustId(),dto.getName(),dto.getAddress(),dto.getContacNo());
    }

    @Override
    public boolean update(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE customer SET name = ?, address = ?, contacNo = ? WHERE custId = ?",dto.getName(),dto.getAddress(),dto.getContacNo(),dto.getCustId());
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
    public CustomerDto search(String newValue) throws SQLException, ClassNotFoundException {
        ResultSet resultSet= SQLUtil.execute("SELECT * FROM customer WHERE custId = ?",newValue);
        CustomerDto dto=null;
        if (resultSet.next()){
            String custId = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String tel = resultSet.getString(4);
            dto=new CustomerDto(custId,name,address,tel);
        }
        return dto;

    }
}
