package lk.ijse.t_shop.bo.custom.impl;

import lk.ijse.t_shop.bo.custom.RawMaterialBO;
import lk.ijse.t_shop.dao.DAOFactory;
import lk.ijse.t_shop.dao.custom.RawMaterialDAO;
import lk.ijse.t_shop.dao.custom.impl.RawMaterialDAOImpl;
import lk.ijse.t_shop.dto.Raw_materialDto;
import lk.ijse.t_shop.entity.Raw_material;

import java.sql.SQLException;
import java.util.ArrayList;

public class RawMaterialBOImpl implements RawMaterialBO {
    RawMaterialDAO rawMaterialDAO = (RawMaterialDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.RAW_MATERIAL);
    @Override
    public ArrayList<Raw_materialDto> getAllRawMaterial() throws SQLException, ClassNotFoundException {
        ArrayList<Raw_materialDto> rawMaterialDtos = new ArrayList<>();
        ArrayList<Raw_material> rawMaterials= rawMaterialDAO.getAll();
        for (Raw_material rawMaterial : rawMaterials) {
            rawMaterialDtos.add(new Raw_materialDto(rawMaterial.getRawID(),rawMaterial.getName(),rawMaterial.getQuntity()));
        }
        return rawMaterialDtos;
    }
    @Override
    public boolean saveRawMaterial(Raw_materialDto dto) throws SQLException, ClassNotFoundException {
        return rawMaterialDAO.save(new Raw_material(dto.getRawID(),dto.getName(),dto.getQuntity()));
    }
    @Override
    public boolean updateRawMaterial(Raw_materialDto dto) throws SQLException, ClassNotFoundException {
        return rawMaterialDAO.update(new Raw_material(dto.getRawID(),dto.getName(),dto.getQuntity()));
    }
    @Override
    public boolean deleteRawMaterial(String id) throws SQLException, ClassNotFoundException {
        return rawMaterialDAO.delete(id);
    }
    @Override
    public String generateNextRawMaterialId() throws SQLException, ClassNotFoundException {
        return rawMaterialDAO.generateNextId();
    }
    @Override
    public Raw_materialDto searchRawMaterial(String newValue) throws SQLException, ClassNotFoundException {
       Raw_material rawMaterial = rawMaterialDAO.search(newValue);
       return new Raw_materialDto(rawMaterial.getRawID(),rawMaterial.getName(),rawMaterial.getQuntity());
    }
}
