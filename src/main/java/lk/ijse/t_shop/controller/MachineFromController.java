package lk.ijse.t_shop.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.t_shop.dto.machineDto;
import lk.ijse.t_shop.dto.raw_materialDto;
import lk.ijse.t_shop.dto.tailorDto;
import lk.ijse.t_shop.model.MachineModel;
import lk.ijse.t_shop.model.TailorModel;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class MachineFromController {

    @FXML
    private TextField textCode;

    @FXML
    private TextField textName;

    @FXML
    private TextField textType;

    @FXML
    private ComboBox<String> comTailerId;

    @FXML
    private TextField textSearch;

    @FXML
    private TableView<machineDto> tableMachine;

    @FXML
    private TableColumn<?, ?> columnId;

    @FXML
    private TableColumn<?, ?> columnName;

    @FXML
    private TableColumn<?, ?> columnType;

    @FXML
    private TableColumn<?, ?> columnTId;

    private TailorModel tailorModel=new TailorModel();
    private MachineModel model=new MachineModel();

    public void initialize(){
        loadTailorIds();
        genarateNextMachineId();
       selectAllMachine();
        valuesFactory();
    }

    private void genarateNextMachineId() {
        try {
            String id = model.genarateNextId();
            textCode.setText(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void valuesFactory(){
        columnId.setCellValueFactory(new PropertyValueFactory<>("machineCode"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnType.setCellValueFactory(new PropertyValueFactory<>("machineType"));
        columnTId.setCellValueFactory(new PropertyValueFactory<>("tailerId"));

    }
    private void loadTailorIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<tailorDto> idList = tailorModel.getAllTailor();

            for (tailorDto dto : idList) {
                obList.add(dto.getTailerId());
            }
            comTailerId.setItems(obList);
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
            String code = textCode.getText();
        try {
            boolean isDelete = model.deleteMachine(code);
            if (isDelete) {
                new Alert(Alert.AlertType.INFORMATION, "Delete Successful").show();
                clearFiled();
                selectAllMachine();
            }else {
                new Alert(Alert.AlertType.ERROR,"Machine not Found").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String code= textCode.getText();
        String name = textName.getText();
        String type =textType.getText();
        String tailerId = comTailerId.getValue();
        var dto =new machineDto(code,name,type,tailerId);
        boolean isCorrect = validateInputs(dto);
        if (isCorrect) {
            try {
                boolean isSaved = model.saveMachine(dto);
                if (isSaved) {
                    new Alert(Alert.AlertType.INFORMATION, "Machine Saved Successful").show();
                    clearFiled();
                    selectAllMachine();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                throw new RuntimeException(e);
            }
        }
    }
    private boolean validateInputs(machineDto dto) {
        boolean matches = Pattern.compile("[M][0-9]{3,}").matcher(dto.getMachineCode()).matches();
        if (!matches){
            new Alert(Alert.AlertType.ERROR,"Invalid Machine Id !!").show();
            return false;
        }
        boolean name = Pattern.matches("[A-Za-z]{4,}",dto.getName());
        if (!name){
            new Alert(Alert.AlertType.ERROR,"Invalid Name !!").show();
            return false;
        }
        boolean type =Pattern.matches("[A-Za-z]{4,}",dto.getMachineType());
        if (!type){
            new Alert(Alert.AlertType.ERROR,"Invalid Type !!").show();
            return false;
        }
        return true;
    }
    public void selectAllMachine() {
        ObservableList<machineDto> obList = FXCollections.observableArrayList();

        try {
            List<machineDto> dtoList = model.getAllMachine();

            for (machineDto dto : dtoList) {
                obList.add(
                        new machineDto(
                                dto.getMachineCode(),
                                dto.getName(),
                                dto.getMachineType(),
                                dto.getTailerId()
                        )
                );
            }

            tableMachine.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearFiled() throws SQLException {
        textCode.setText(model.genarateNextId());
        textName.setText("");
        textType.setText("");
        comTailerId.setValue("");
        textSearch.setText("");
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String code = textCode.getText();
        String name = textName.getText();
       String type = textType.getText();
        String tId = comTailerId.getValue();

        var dto = new machineDto(code,name,type,tId);
        try {
            boolean isUpdate = model.updateMachine(dto);
            if (isUpdate){
                new Alert(Alert.AlertType.INFORMATION,"Update Successfully").show();
                clearFiled();
                selectAllMachine();
            }else {
                new Alert(Alert.AlertType.ERROR,"Machine Not Found").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void textSearchOnAction(ActionEvent event) {
            String code = textSearch.getText();
        try {
            machineDto dto = model.searchMachine(code);
            if (dto!= null){
                textCode.setText(dto.getMachineCode());
                textName.setText(dto.getName());
                textType.setText(dto.getMachineType());
                comTailerId.setValue(dto.getTailerId());

            }else {
                new Alert(Alert.AlertType.ERROR,"Machine not found").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}