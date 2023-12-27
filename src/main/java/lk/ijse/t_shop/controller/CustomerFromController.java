package lk.ijse.t_shop.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.t_shop.bo.BOFactory;
import lk.ijse.t_shop.bo.custom.CustomerBO;
import lk.ijse.t_shop.bo.custom.impl.CustomerBOImpl;
import lk.ijse.t_shop.dao.custom.CustomerDAO;
import lk.ijse.t_shop.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.t_shop.dto.CustomerDto;
import lk.ijse.t_shop.view.tdm.customerTm;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class CustomerFromController {

    @FXML
    private TableView<customerTm> tblCust;

    @FXML
    private TableColumn<?, ?> columnId;

    @FXML
    private TableColumn<?, ?> columnName;

    @FXML
    private TableColumn<?, ?> columnAdd;

    @FXML
    private TableColumn<?, ?> columntel;

    @FXML
    private TextField texTel;

    @FXML
    private TextField textName;

    @FXML
    private TextField textAddress;

    @FXML
    private Label lblcustomerId;

    @FXML
    private TextField textSearch;
    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.CUSTOMER);
    public void initialize() {
        genarateNextCustId();
        selectAllFromCustomer();
        setvaluesFactory();
    }
    private void setvaluesFactory() {
        columnId.setCellValueFactory(new PropertyValueFactory<>("custId"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnAdd.setCellValueFactory(new PropertyValueFactory<>("address"));
        columntel.setCellValueFactory(new PropertyValueFactory<>("contacNo"));
    }
    public void clearFiled() throws SQLException, ClassNotFoundException {
       lblcustomerId.setText(customerBO.generateNextCustomerId());
       textName.setText("");
       textAddress.setText("");
       texTel.setText("");
       textSearch.setText("");
   }
    public void genarateNextCustId(){
        try {
            String custId= customerBO.generateNextCustomerId();
            lblcustomerId.setText(custId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void btnClearOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        clearFiled();
    }
    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = lblcustomerId.getText();
        try {
            boolean isDelete = customerBO.deleteCustomer(id);
            if (isDelete) {
                new Alert(Alert.AlertType.INFORMATION, "Delete Successful").show();
                clearFiled();
                selectAllFromCustomer();
            }else {
                new Alert(Alert.AlertType.ERROR,"Customer not Found").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String cutId = lblcustomerId.getText();
        String name = textName.getText();
        String address = textAddress.getText();
        String contactNo = texTel.getText();
        var dto = new CustomerDto(cutId,name,address,contactNo);
        boolean isCorrect =validateCustomer(dto);
        if (isCorrect) {
            try {
                boolean isSaved = customerBO.saveCustomer(dto);
                if (isSaved) {
                    new Alert(Alert.AlertType.INFORMATION, "Customer Saved Successful").show();
                    clearFiled();
                    selectAllFromCustomer();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private boolean validateCustomer(CustomerDto dto) {
        boolean matches = Pattern.compile("[C][0-9]{3,}").matcher(dto.getCustId()).matches();
        if (!matches){
            new Alert(Alert.AlertType.ERROR,"Invalid Customer !!").show();
            return false;
        }
        boolean name = Pattern.matches("[A-Za-z]{4,}",dto.getName());
        if (!name){
            new Alert(Alert.AlertType.ERROR,"Invalid Name !!").show();
            return false;
        }
        boolean Address = Pattern.matches("[A-Za-z]{5,}",dto.getAddress());
        if (!Address){
            new Alert(Alert.AlertType.ERROR,"Invalid Address !!").show();
            return false;
        }
        boolean contactNo =Pattern.matches("^(?:0|94|\\+94|0094)?(?:(11|21|23|24|25|26|27|31|32|33|34|35|36|37|38|41|45|47|51|52|54|55|57|63|65|66|67|81|91)(0|2|3|4|5|7|9)|7(0|1|2|4|5|6|7|8)\\d)\\d{6}$",dto.getContacNo());
        if (!contactNo){
            new Alert(Alert.AlertType.ERROR,"Invalid Contact No !!").show();
            return false;
        }
        return true;
    }
    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = lblcustomerId.getText();
        String name = textName.getText();
        String address = textAddress.getText();
        String tel = texTel.getText();

        var dto = new CustomerDto(id,name,address,tel);
        boolean isCorrect = validateCustomer(dto);
        if (isCorrect) {
            try {
                boolean isUpdate = customerBO.updateCustomer(dto);
                if (isUpdate) {
                    new Alert(Alert.AlertType.INFORMATION, "Update Successfully").show();
                    clearFiled();
                    selectAllFromCustomer();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Customer Not Found").show();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @FXML
    void textSearchOnAction(ActionEvent event){
        String id=textSearch.getText();
        try {
            CustomerDto dto = customerBO.searchCustomer(id);
            if (dto!= null){
                lblcustomerId.setText(dto.getCustId());
                textName.setText(dto.getName());
                textAddress.setText(dto.getAddress());
                texTel.setText(dto.getContacNo());
            }else {
                new Alert(Alert.AlertType.ERROR,"Customer not found").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    void selectAllFromCustomer(){
        ObservableList<customerTm> obList = FXCollections.observableArrayList();

        try {
            List<CustomerDto> dtoList = customerBO.getAllCustomer();

            for (CustomerDto dto : dtoList) {
                obList.add(
                        new customerTm(
                                dto.getCustId(),
                                dto.getName(),
                                dto.getAddress(),
                                dto.getContacNo()
                        )
                );
            }

            tblCust.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}