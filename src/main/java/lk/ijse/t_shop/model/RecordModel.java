package lk.ijse.t_shop.model;

import lk.ijse.t_shop.db.DbConnection;
import lk.ijse.t_shop.dto.recordDto;
import lk.ijse.t_shop.dto.tailorDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RecordModel {
    public String genarateNextId() throws SQLException {
        Connection connection= DbConnection.getInstance().getConnection();
        String sql = "SELECT recId FROM record ORDER BY recId DESC LIMIT 1";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet =pstm.executeQuery();
        if (resultSet.next()){
            return splitRecId(resultSet.getNString(1));
        }
        return splitRecId(null);
    }
    private String splitRecId(String currentOrderId) {
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

    public boolean saveRecord(recordDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO record VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,dto.getRecordId());
        pstm.setString(2,dto.getType());
        pstm.setDouble(3,dto.getCrotchDep());
        pstm.setDouble(4,dto.getRice());
        pstm.setDouble(5,dto.getLegOpe());
        pstm.setDouble(6,dto.getKneeCirum());
        pstm.setDouble(7,dto.getThighCirum());
        pstm.setDouble(8,dto.getOutSeamL());
        pstm.setDouble(9,dto.getInseamL());
        pstm.setDouble(10,dto.getHipsCircum());
        pstm.setDouble(11,dto.getWaistCircum());
        pstm.setDouble(12,dto.getCuffCirum());
        pstm.setDouble(13,dto.getNeckCirum());
        pstm.setDouble(14,dto.getChestCirum());
        pstm.setDouble(15,dto.getShirtL());
        pstm.setDouble(16,dto.getShoulderWid());
        pstm.setDouble(17,dto.getSleeveL());
        pstm.setDouble(18,dto.getBicepCircum());
        pstm.setDouble(19,dto.getSleeveOp());
        pstm.setDouble(20,dto.getColler());
        pstm.setString(21,dto.getOrderId());
        pstm.setString(22,dto.getPrice());
        pstm.setString(23,dto.getCustId());
        return pstm.executeUpdate() > 0;
    }

    public List<recordDto> getAllRecord() throws SQLException {
        Connection connection= DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM record";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        ArrayList<recordDto> dtos = new ArrayList<>();
        while (resultSet.next()){
            dtos.add(
                    new recordDto(
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

    public recordDto searchRecord(String id) throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM record WHERE recId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);
        ResultSet resultSet= pstm.executeQuery();
        recordDto dto=null;
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
            dto = new recordDto(recId,type,Cd,rice,legOpen,kneeCrium,thighCirum,outSleam,inseamL,hiperC,waistC,Cuffc,neckC,chestC,shirtL,shoulderW,sleevL,bicepC,sleevO,coller,orderId,price,custId);
        }
        return dto;
    }

    public boolean updateRecord(recordDto dto) throws SQLException {
        String sql = "UPDATE record "
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
                + "WHERE recId = ?";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setDouble( 1,dto.getCrotchDep());
        pstm.setDouble(2,dto.getRice());
        pstm.setDouble(3,dto.getLegOpe());
        pstm.setDouble(4,dto.getKneeCirum());
        pstm.setDouble(5,dto.getThighCirum());
        pstm.setDouble(6,dto.getOutSeamL());
        pstm.setDouble(7,dto.getInseamL());
        pstm.setDouble(8,dto.getHipsCircum());
        pstm.setDouble(9,dto.getWaistCircum());
        pstm.setDouble(10, dto.getCuffCirum());
        pstm.setDouble(11, dto.getNeckCirum());
        pstm.setDouble(12, dto.getChestCirum());
        pstm.setDouble(13, dto.getShirtL());
        pstm.setDouble(14, dto.getShoulderWid());
        pstm.setDouble(15, dto.getSleeveL());
        pstm.setDouble(16, dto.getBicepCircum());
        pstm.setDouble(17, dto.getSleeveOp());
        pstm.setDouble(18, dto.getColler());
        pstm.setString(19,dto.getPrice());
        pstm.setString(20,dto.getRecordId());
        return pstm.executeUpdate()> 0;
    }

    public boolean deleteRecord(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql= "Delete from record WHERE recId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);
        return pstm.executeUpdate()>0;
    }
}
