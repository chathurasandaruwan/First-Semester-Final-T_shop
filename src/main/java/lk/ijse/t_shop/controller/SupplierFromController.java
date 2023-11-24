package lk.ijse.t_shop.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.t_shop.dto.customerDto;
import lk.ijse.t_shop.dto.supplierDto;
import lk.ijse.t_shop.dto.tailorDto;
import lk.ijse.t_shop.dto.tm.supplierTm;
import lk.ijse.t_shop.model.SupplierModel;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class SupplierFromController {
    @FXML
    private TextField textsearch;

    @FXML
    private TextField textSupId;

    @FXML
    private TextField textName;

    @FXML
    private TextField textDesc;

    @FXML
    private TextField textContactNo;

    @FXML
    private TableView<supplierTm> tableSupplier;

    @FXML
    private TableColumn<?, ?> columnId;

    @FXML
    private TableColumn<?, ?> columnName;

    @FXML
    private TableColumn<?, ?> columnDesc;

    @FXML
    private TableColumn<?, ?> ColumnContactNo;
    private SupplierModel model= new SupplierModel();
   public void initialize(){
       genarateNextSupId();
       SelectAllSup();
       setvaluesFactory();
   }
    private void setvaluesFactory() {
        columnId.setCellValueFactory(new PropertyValueFactory<>("supId"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        columnDesc.setCellValueFactory(new PropertyValueFactory<>("Description"));
        ColumnContactNo.setCellValueFactory(new PropertyValueFactory<>("contacNo"));
    }
    public void genarateNextSupId(){
       try {
           String supId= model.genarateSupId();
           textSupId.setText(supId);
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }

    }
    public void clearFiled() throws SQLException {
        textSupId.setText(model.genarateSupId());
        textName.setText("");
        textDesc.setText("");
        textContactNo.setText("");
        textsearch.setText("");
    }
    @FXML
    void btnClearOnAction(ActionEvent event) throws SQLException {
        clearFiled();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        boolean isCorrect = validateInputs();
        String id = textSupId.getText();
        String name = textName.getText();
        String address = textDesc.getText();
        String tel = textContactNo.getText();
        if (isCorrect) {
            var dto = new supplierDto(id, name, address, tel);
            try {
                boolean isUpdate = model.updateSupplier(dto);
                if (isUpdate) {
                    new Alert(Alert.AlertType.INFORMATION, "Update Successfully").show();
                    clearFiled();
                    SelectAllSup();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Customer Not Found").show();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = textSupId.getText();
        try {
            boolean isDelete = model.deleteSupplier(id);
            if (isDelete) {
                new Alert(Alert.AlertType.INFORMATION, "Delete Successful").show();
                clearFiled();
                SelectAllSup();
            }else {
                new Alert(Alert.AlertType.ERROR,"Supplier not Found").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private boolean validateInputs() {
        boolean matches = Pattern.compile("[S][0-9]{3,}").matcher(textSupId.getText()).matches();
        if (!matches){
            new Alert(Alert.AlertType.ERROR,"Invalid Supplier Id !!").show();
            return false;
        }
        boolean name = Pattern.matches("[A-Za-z]{4,}",textName.getText());
        if (!name){
            new Alert(Alert.AlertType.ERROR,"Invalid Name !!").show();
            return false;
        }
        boolean desc =Pattern.matches("[A-Za-z]{4,}",textDesc.getText());
        if (!desc){
            new Alert(Alert.AlertType.ERROR,"Invalid Contact No !!").show();
            return false;
        }
        boolean contactNo =Pattern.matches("^(?:0|94|\\+94|0094)?(?:(11|21|23|24|25|26|27|31|32|33|34|35|36|37|38|41|45|47|51|52|54|55|57|63|65|66|67|81|91)(0|2|3|4|5|7|9)|7(0|1|2|4|5|6|7|8)\\d)\\d{6}$",textContactNo.getText());
        if (!contactNo){
            new Alert(Alert.AlertType.ERROR,"Invalid Contact No !!").show();
            return false;
        }
        return true;
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        boolean isCorrect = validateInputs();
       String supId = textSupId.getText();
        String name = textName.getText();
        String description = textDesc.getText();
        String contacNo = textContactNo.getText();
        var dto = new supplierDto(supId,name,description,contacNo);
        if (isCorrect) {
            try {
                boolean isSaved = model.saveSupllier(dto);
                if (isSaved) {
                    new Alert(Alert.AlertType.INFORMATION, "Supplier Saved Successful").show();
                    clearFiled();
                    SelectAllSup();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                throw new RuntimeException(e);
            }
        }
    }
    void SelectAllSup(){
        ObservableList<supplierTm> obList = FXCollections.observableArrayList();

        try {
            List <supplierDto> dtoList = model.getAllSup();

            for (supplierDto dto : dtoList) {
                obList.add(
                        new supplierTm(
                                dto.getSupId(),
                                dto.getName(),
                                dto.getDescription(),
                                dto.getContacNo()
                        )
                );
            }

            tableSupplier.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    void textSerchOnAction(ActionEvent event) {
       String supId=textsearch.getText();
       try {
           supplierDto dto = model.searchCustomer(supId);
           if (dto!=null){
               textSupId.setText(dto.getSupId());
               textName.setText(dto.getName());
               textDesc.setText(dto.getDescription());
               textContactNo.setText(dto.getContacNo());
           }else {
               new Alert(Alert.AlertType.ERROR,"Supplier Not Found").show();
           }
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
    }
}