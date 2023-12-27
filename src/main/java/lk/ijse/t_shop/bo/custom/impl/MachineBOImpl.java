package lk.ijse.t_shop.bo.custom.impl;

import lk.ijse.t_shop.bo.custom.MachineBO;
import lk.ijse.t_shop.dao.DAOFactory;
import lk.ijse.t_shop.dao.custom.MachineDAO;
import lk.ijse.t_shop.dao.custom.TailorDAO;
import lk.ijse.t_shop.dao.custom.impl.MachineDAOImpl;
import lk.ijse.t_shop.dao.custom.impl.TailorDAOImpl;
import lk.ijse.t_shop.dto.MachineDto;
import lk.ijse.t_shop.dto.TailorDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class MachineBOImpl implements MachineBO {
    TailorDAO tailorDAO = (TailorDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.TAILOR);
    MachineDAO machineDAO = (MachineDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.MACHINE);
    @Override
    public ArrayList<MachineDto> getAllMachine() throws SQLException, ClassNotFoundException {
        return machineDAO.getAll();
    }
    @Override
    public boolean saveMachine(MachineDto dto) throws SQLException, ClassNotFoundException {
        return machineDAO.save(dto);
    }
    @Override
    public boolean updateMachine(MachineDto dto) throws SQLException, ClassNotFoundException {
        return machineDAO.update(dto);
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
        return machineDAO.search(newValue);
    }
    @Override
    public ArrayList<TailorDto> getAllTailor() throws SQLException, ClassNotFoundException {
        return tailorDAO.getAll();
    }
}
