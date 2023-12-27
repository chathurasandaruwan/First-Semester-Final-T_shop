package lk.ijse.t_shop.dao.custom.impl;

import lk.ijse.t_shop.dao.SQLUtil;
import lk.ijse.t_shop.dao.custom.RecordDAO;
import lk.ijse.t_shop.dto.RecordDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RecordDAOImpl implements RecordDAO {
    @Override
    public ArrayList<RecordDto> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM record");
        ArrayList<RecordDto> dtos = new ArrayList<>();
        while (resultSet.next()){
            dtos.add(
                    new RecordDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getDouble(3),
                            resultSet.getDouble(4),
                            resultSet.getDouble(5),
                            resultSet.getDouble(6),
                            resultSet.getDouble(7),
                            resultSet.getDouble(8),
                            resultSet.getDouble(9),
                            resultSet.getDouble(10),
                            resultSet.getDouble(11),
                            resultSet.getDouble(12),
                            resultSet.getDouble(13),
                            resultSet.getDouble(14),
                            resultSet.getDouble(15),
                            resultSet.getDouble(16),
                            resultSet.getDouble(17),
                            resultSet.getDouble(18),
                            resultSet.getDouble(19),
                            resultSet.getDouble(20),
                            resultSet.getString(21),
                            resultSet.getString(22),
                            resultSet.getString(23)
                    )
            );
        }
        return dtos;
    }

    @Override
    public boolean save(RecordDto dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO record VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",dto.getRecordId(),
                dto.getType(),dto.getCrotchDep(),dto.getRice(),dto.getLegOpe(),dto.getKneeCirum(),dto.getThighCirum(),dto.getOutSeamL(),dto.getInseamL(),
                dto.getHipsCircum(),dto.getWaistCircum(),dto.getCuffCirum(),dto.getNeckCirum(),dto.getChestCirum(),dto.getShirtL(),dto.getShoulderWid(),
                dto.getSleeveL(),dto.getBicepCircum(),dto.getSleeveOp(),dto.getColler(),dto.getOrderId(),dto.getPrice(),dto.getCustId());
    }

    @Override
    public boolean update(RecordDto dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute( "UPDATE record "
                + "SET "
                + "crotchDepth = ?, "
                + "Rise = ?, "
                + "LegOpening = ?, "
                + "KneeCirumference = ?, "
                + "ThighCirumference = ?, "
                + "OutSeamLength = ?, "
                + "InseamLength = ?, "
                + "HipsCircumference = ?, "
                + "WaistCircumferencr = ?, "
                + "CuffCirumference = ?, "
                + "NeckCirumference = ?, "
                + "ChestCirumference = ?, "
                + "ShirtLength = ?, "
                + "ShoulderWidth = ?, "
                + "SleeveLength = ?, "
                + "BicepCircumference = ?, "
                + "SleeveOpening = ?, "
                + "Coller = ?, "
                + "payment = ? "
                + "WHERE recId = ?",dto.getCrotchDep(),dto.getRice(),dto.getLegOpe(),dto.getKneeCirum(),dto.getThighCirum(),dto.getOutSeamL(),dto.getInseamL(),
                dto.getHipsCircum(),dto.getWaistCircum(),dto.getCuffCirum(),dto.getNeckCirum(),dto.getChestCirum(),dto.getShirtL(),dto.getShoulderWid(),
                dto.getSleeveL(),dto.getBicepCircum(),dto.getSleeveOp(),dto.getColler(),dto.getPrice(),dto.getRecordId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("Delete from record WHERE recId = ?",id);
    }

    @Override
    public String splitId(String currentOrderId) {
        if(currentOrderId != null) {
            String[] split = currentOrderId.split("R0");

            int id = Integer.parseInt(split[1]);
            id++;
            String formattedNumericPart =String.format("%03d",id);
            return "R" +formattedNumericPart;
        } else {
            return "R001";
        }
    }

    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT recId FROM record ORDER BY recId DESC LIMIT 1");
        if (resultSet.next()){
            return splitId(resultSet.getNString(1));
        }
        return splitId(null);
    }

    @Override
    public RecordDto search(String newValue) throws SQLException, ClassNotFoundException {
        ResultSet resultSet= SQLUtil.execute("SELECT * FROM record WHERE recId = ?",newValue);
        RecordDto dto=null;
        if (resultSet.next()){
            String recId= resultSet.getString(1);
            String type =resultSet.getString(2);
            double Cd = resultSet.getDouble(3);
            double rice = resultSet.getDouble(4);
            double legOpen = resultSet.getDouble(5);
            double kneeCrium = resultSet.getDouble(6);
            double thighCirum = resultSet.getDouble(7);
            double outSleam = resultSet.getDouble(8);
            double inseamL = resultSet.getDouble(9);
            double hiperC = resultSet.getDouble(10);
            double waistC = resultSet.getDouble(11);
            double Cuffc = resultSet.getDouble(12);
            double neckC = resultSet.getDouble(13);
            double chestC = resultSet.getDouble(14);
            double shirtL = resultSet.getDouble(15);
            double shoulderW = resultSet.getDouble(16);
            double sleevL = resultSet.getDouble(17);
            double bicepC = resultSet.getDouble(18);
            double sleevO = resultSet.getDouble(19);
            double coller = resultSet.getDouble(20);
            String orderId = resultSet.getString(21);
            String price = resultSet.getString(22);
            String custId = resultSet.getString(23);
            dto = new RecordDto(recId,type,Cd,rice,legOpen,kneeCrium,thighCirum,outSleam,inseamL,hiperC,waistC,Cuffc,neckC,chestC,shirtL,shoulderW,sleevL,bicepC,sleevO,coller,orderId,price,custId);
        }
        return dto;
    }
}
