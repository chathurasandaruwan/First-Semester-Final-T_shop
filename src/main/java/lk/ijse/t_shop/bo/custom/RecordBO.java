package lk.ijse.t_shop.bo.custom;

import lk.ijse.t_shop.bo.SuperBO;
import lk.ijse.t_shop.dto.CustomerDto;
import lk.ijse.t_shop.dto.RecordDto;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface RecordBO extends SuperBO {
    boolean placeRecord(RecordDto dto, LocalDate date) throws SQLException;

    ArrayList<RecordDto> getAllRecord() throws SQLException, ClassNotFoundException;

    boolean saveRecord(RecordDto dto) throws SQLException, ClassNotFoundException;

    boolean updateRecord(RecordDto dto) throws SQLException, ClassNotFoundException;

    boolean deleteRecord(String id) throws SQLException, ClassNotFoundException;

    String generateNextRecordId() throws SQLException, ClassNotFoundException;

    RecordDto searchRecord(String newValue) throws SQLException, ClassNotFoundException;

    ArrayList<CustomerDto> getAllCustomer() throws SQLException, ClassNotFoundException;

    String generateNextOrderId() throws SQLException, ClassNotFoundException;
}
