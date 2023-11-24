package lk.ijse.t_shop.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.t_shop.dto.itemDto;
import lk.ijse.t_shop.dto.raw_materialDto;
import lk.ijse.t_shop.dto.supplierDto;
import lk.ijse.t_shop.dto.tm.raw_materialTm;
import lk.ijse.t_shop.model.RawMaterialModel;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class RawMaterialFromController {

    @FXML
    private TextField textId;

    @FXML
    private TextField textName;

    @FXML
    private JFXTextField textQty;

    @FXML
    private TextField textSearch;

    @FXML
    private TableView<raw_materialTm> tableRawM;

    @FXML
    private TableColumn<?, ?> columnId;

    @FXML
    private TableColumn<?, ?> columnName;

    @FXML
    private TableColumn<?, ?> columnQty;

    private RawMaterialModel model=new RawMaterialModel();
    public void initialize(){
        genaratenextId();
        valuesFactory();
        selectAllMaterial();
    }
    public void valuesFactory(){
        columnId.setCellValueFactory(new PropertyValueFactory<>("rawID"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnQty.setCellValueFactory(new PropertyValueFactory<>("quntity"));
    }
    public void clearFiled() throws SQLException {
        textId.setText(model.genarateNextId());
        textName.setText("");
        textQty.setText("");
        textSearch.setText("");
    }
    public void genaratenextId(){
        try {
            String rawId = model.genarateNextId();
            textId.setText(rawId);
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
        String id = textId.getText();
        try {
            boolean isDelete = model.deleteMaterial(id);
            if (isDelete) {
                new Alert(Alert.AlertType.INFORMATION, "Delete Successful").show();
                clearFiled();
                selectAllMaterial();
            }else {
                new Alert(Alert.AlertType.ERROR,"Material not Found").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void btnSaveOnAction(ActionEvent event) {
        boolean isCorrect = validateInputs();
        String id = textId.getText();
        String name = textName.getText();
        int qty = Integer.parseInt(textQty.getText());

        var dto = new raw_materialDto(id,name,qty);

        if (isCorrect) {
            try {
                boolean isSaved = model.saveRawM(dto);
                if (isSaved) {
                    new Alert(Alert.AlertType.INFORMATION, "Material Saved Successful").show();
                    clearFiled();
                    selectAllMaterial();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                throw new RuntimeException(e);
            }
        }
    }
    private boolean validateInputs() {
        boolean matches = Pattern.compile("[R][0-9]{3,}").matcher(textId.getText()).matches();
        if (!matches){
            new Alert(Alert.AlertType.ERROR,"Invalid Material Id !!").show();
            return false;
        }
        boolean name = Pattern.matches("[A-Za-z]{4,}",textName.getText());
        if (!name){
            new Alert(Alert.AlertType.ERROR,"Invalid Name !!").show();
            return false;
        }
        boolean contactNo =Pattern.matches("[0-9]{1,}",textQty.getText());
        if (!contactNo){
            new Alert(Alert.AlertType.ERROR,"Invalid Qty !!").show();
            return false;
        }
        return true;
    }


    void  selectAllMaterial(){
        ObservableList<raw_materialTm> obList = FXCollections.observableArrayList();

        try {
            List<raw_materialDto> dtoList = model.getAllMaterial();

            for (raw_materialDto dto : dtoList) {
                obList.add(
                        new raw_materialTm(
                                dto.getRawID(),
                                dto.getName(),
                                dto.getQuntity()
                        )
                );
            }

            tableRawM.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        boolean isCorrect = validateInputs();
        String id = textId.getText();
        String name = textName.getText();
        int qty = Integer.parseInt(textQty.getText());

        var dto = new raw_materialDto(id,name,qty);
        if (isCorrect) {
            try {
                boolean isUpdate = model.updateMaterial(dto);
                if (isUpdate) {
                    new Alert(Alert.AlertType.INFORMATION, "Update Successfully").show();
                    clearFiled();
                    selectAllMaterial();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Material Not Found").show();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void textSearchOnAction(ActionEvent event) {
        String id=textSearch.getText();
        try {
            raw_materialDto dto = model.searchMaterial(id);
            if (dto!= null){
                textId.setText(dto.getRawID());
                textName.setText(dto.getName());
                textQty.setText(String.valueOf(dto.getQuntity()));

            }else {
                new Alert(Alert.AlertType.ERROR,"Material not found").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
