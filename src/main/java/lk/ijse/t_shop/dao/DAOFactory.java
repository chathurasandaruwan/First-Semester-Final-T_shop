package lk.ijse.t_shop.dao;

import lk.ijse.t_shop.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }
    public static DAOFactory getDaoFactory(){
        return daoFactory == null ? daoFactory = new DAOFactory() : daoFactory;
    }
    public enum DAOType{
        CHANGE_PASSWORD,CUSTOMER,ITEM,LOGGING,MACHINE,ORDER,ORDER_DETAIL,RAW_MATERIAL,RECORD,SUPPLIER,TAILOR
    }
    public SupperDAO getDAO(DAOType daoType){
        switch (daoType){
            case CHANGE_PASSWORD:
                return new ChangePasswordDAOImpl();
            case CUSTOMER:
                return new CustomerDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            case LOGGING:
                return new LoggingDAOImpl();
            case MACHINE:
                return new MachineDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case ORDER_DETAIL:
                return new OrderDetailDAOImpl();
            case RAW_MATERIAL:
                return new RawMaterialDAOImpl();
            case RECORD:
                return new RecordDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();
            case TAILOR:
                return new TailorDAOImpl();
            default:
                return null;
        }
    }
}
