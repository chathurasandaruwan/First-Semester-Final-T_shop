package lk.ijse.t_shop.bo;

import lk.ijse.t_shop.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }
    public static BOFactory getBoFactory(){
        return boFactory == null ? boFactory = new BOFactory() : boFactory;
    }
    public enum BOType{
        CHANGE_PASSWORD,CUSTOMER,DASHBOARD,FORGOT_PASSWORD,ITEM,LOGGING,MACHINE,ORDERS,RAW_MATERIAL,RECORD,SUPPLIER,TAILOR
    }
    public SuperBO getBO(BOType boType){
        switch (boType){
            case CHANGE_PASSWORD:
                return new ChangePasswordBOImpl();
            case CUSTOMER:
                return new CustomerBOImpl();
            case DASHBOARD:
                return new DashboardBOImpl();
            case FORGOT_PASSWORD:
                return new ForgotPasswordBOImpl();
            case ITEM:
                return new ItemBOImpl();
            case LOGGING:
                return new LoggingBOImpl();
            case MACHINE:
                return new MachineBOImpl();
            case ORDERS:
                return new OrderBOImpl();
            case RAW_MATERIAL:
                return new RawMaterialBOImpl();
            case RECORD:
                return new RecordBOImpl();
            case SUPPLIER:
                return new SupplierBOImpl();
            case TAILOR:
                return new TailorBOImpl();
            default:
                return null;
        }
    }

}
