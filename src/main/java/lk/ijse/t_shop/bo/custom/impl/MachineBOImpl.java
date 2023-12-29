package lk.ijse.t_shop.bo.custom.impl;

import lk.ijse.t_shop.bo.custom.MachineBO;
import lk.ijse.t_shop.dao.DAOFactory;
import lk.ijse.t_shop.dao.custom.MachineDAO;
import lk.ijse.t_shop.dao.custom.TailorDAO;
import lk.ijse.t_shop.dao.custom.impl.MachineDAOImpl;
import lk.ijse.t_shop.dao.custom.impl.TailorDAOImpl;
import lk.ijse.t_shop.dto.CustomerDto;
import lk.ijse.t_shop.dto.MachineDto;
import lk.ijse.t_shop.dto.TailorDto;
import lk.ijse.t_shop.entity.Customer;
import lk.ijse.t_shop.entity.Machine;
import lk.ijse.t_shop.entity.Tailor;

import java.sql.SQLException;
import java.util.ArrayList;

public class MachineBOImpl implements MachineBO {
    TailorDAO tailorDAO = (TailorDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.TAILOR);
    MachineDAO machineDAO = (MachineDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.MACHINE);
    @Override
    public ArrayList<MachineDto> getAllMachine() throws SQLException, ClassNotFoundException {
        ArrayList<MachineDto> machineDtos = new ArrayList<>();
        ArrayList<Machine> machines = machineDAO.getAll();
        for (Machine machine : machines) {
            machineDtos.add(new MachineDto(machine.getMachineCode(),machine.getName(),machine.getMachineType(),machine.getTailerId()));
        }
        return machineDtos;
    }
    @Override
    public boolean saveMachine(MachineDto dto) throws SQLException, ClassNotFoundException {
        return machineDAO.save(new Machine(dto.getMachineCode(),dto.getName(),dto.getMachineType(),dto.getTailerId()));
    }
    @Override
    public boolean updateMachine(MachineDto dto) throws SQLException, ClassNotFoundException {
        return machineDAO.update(new Machine(dto.getMachineCode(),dto.getName(),dto.getMachineType(),dto.getTailerId()));
    }
    @Override
    public boolean deleteMachine(String id) throws SQLException, ClassNotFoundException {
        return machineDAO.delete(id);
    }
    @Override
    public String generateNextMachineId() throws SQLException, ClassNotFoundException {
        return machineDAO.generateNextId();
    }
    @Override
    public MachineDto searchMachine(String newValue) throws SQLException, ClassNotFoundException {
        Machine entity= machineDAO.search(newValue);
        return new MachineDto(entity.getMachineCode(),entity.getName(),entity.getMachineType(),entity.getTailerId());
    }
    @Override
    public ArrayList<TailorDto> getAllTailor() throws SQLException, ClassNotFoundException {
        ArrayList<TailorDto> tailorDtos = new ArrayList<>();
        ArrayList<Tailor> tailors = tailorDAO.getAll();
        for (Tailor tailor : tailors) {
            tailorDtos.add(new TailorDto(tailor.getTailerId(),tailor.getName(),tailor.getContacNo(),tailor.getItemCode()));
        }
        return tailorDtos;
    }
}
