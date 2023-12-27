package lk.ijse.t_shop.bo.custom;

import lk.ijse.t_shop.bo.SuperBO;
import lk.ijse.t_shop.dto.MachineDto;
import lk.ijse.t_shop.dto.TailorDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MachineBO extends SuperBO {
    ArrayList<MachineDto> getAllMachine() throws SQLException, ClassNotFoundException;

    boolean saveMachine(MachineDto dto) throws SQLException, ClassNotFoundException;

    boolean updateMachine(MachineDto dto) throws SQLException, ClassNotFoundException;

    boolean deleteMachine(String id) throws SQLException, ClassNotFoundException;

    String generateNextMachineId() throws SQLException, ClassNotFoundException;

    MachineDto searchMachine(String newValue) throws SQLException, ClassNotFoundException;

    ArrayList<TailorDto> getAllTailor() throws SQLException, ClassNotFoundException;
}
