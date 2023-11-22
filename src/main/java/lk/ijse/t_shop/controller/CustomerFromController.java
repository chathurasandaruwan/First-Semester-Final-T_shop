package lk.ijse.t_shop.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.t_shop.dto.customerDto;
import lk.ijse.t_shop.dto.tm.customerTm;
import lk.ijse.t_shop.model.CustomerModel;

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

    private CustomerModel model = new CustomerModel();

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
    public void clearFiled() throws SQLException {
       lblcustomerId.setText(model.genarateCustId());
       textName.setText("");
       textAddress.setText("");
       texTel.setText("");
       textSearch.setText("");
   }
    public void genarateNextCustId(){
        try {
            String custId= model.genarateCustId();
            lblcustomerId.setText(custId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void btnClearOnAction(ActionEvent event) throws SQLException {
        clearFiled();
    }
    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = lblcustomerId.getText();
        try {
            boolean isDelete = model.deleteCustomer(id);
            if (isDelete) {
                new Alert(Alert.AlertType.INFORMATION, "Delete Successful").show();
                clearFiled();
                selectAllFromCustomer();
            }else {
                new Alert(Alert.AlertType.ERROR,"Customer not Found").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String cutId = lblcustomerId.getText();
        String name = textName.getText();
        String address = textAddress.getText();
        String contactNo = texTel.getText();
        var dto = new customerDto(cutId,name,address,contactNo);
        boolean isCorrect =validateCustomer(dto);
        if (isCorrect) {
            try {
                boolean isSaved = model.saveCustomer(dto);
                if (isSaved) {
                    new Alert(Alert.AlertType.INFORMATION, "Customer Saved Successful").show();
                    clearFiled();
                    selectAllFromCustomer();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                throw new RuntimeException(e);
            }
        }
    }
    private boolean validateCustomer(customerDto dto) {
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
        boolean contactNo =Pattern.matches("[0-9]{10,}",dto.getContacNo());
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

        var dto = new customerDto(id,name,address,tel);
        try {
            boolean isUpdate = model.updateCustomer(dto);
            if (isUpdate){
                new Alert(Alert.AlertType.INFORMATION,"Update Successfully").show();
                clearFiled();
                selectAllFromCustomer();
            }else {
                new Alert(Alert.AlertType.ERROR,"Customer Not Found").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void textSearchOnAction(ActionEvent event){
        String id=textSearch.getText();
        try {
            customerDto dto = model.searchCustomer(id);
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
        }
    }
    void selectAllFromCustomer(){
       // var model = new customerDto();
        ObservableList<customerTm> obList = FXCollections.observableArrayList();

        try {
            List<customerDto> dtoList = model.getAllCustomer();

            for (customerDto dto : dtoList) {
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
        }

    }

}