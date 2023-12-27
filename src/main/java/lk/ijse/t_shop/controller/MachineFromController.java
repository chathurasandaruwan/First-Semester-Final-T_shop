package lk.ijse.t_shop.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.t_shop.bo.BOFactory;
import lk.ijse.t_shop.bo.custom.MachineBO;
import lk.ijse.t_shop.bo.custom.impl.MachineBOImpl;
import lk.ijse.t_shop.dto.MachineDto;
import lk.ijse.t_shop.dto.TailorDto;
import lk.ijse.t_shop.view.tdm.MachineTm;

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
    private TableView<MachineTm> tableMachine;

    @FXML
    private TableColumn<?, ?> columnId;

    @FXML
    private TableColumn<?, ?> columnName;

    @FXML
    private TableColumn<?, ?> columnType;

    @FXML
    private TableColumn<?, ?> columnTId;
    MachineBO machineBO = (MachineBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.MACHINE);

    public void initialize(){
        loadTailorIds();
        genarateNextMachineId();
       selectAllMachine();
        valuesFactory();
    }

    private void genarateNextMachineId() {
        try {
            String id = machineBO.generateNextMachineId();
            textCode.setText(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
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
            List<TailorDto> idList = machineBO.getAllTailor();

            for (TailorDto dto : idList) {
                obList.add(dto.getTailerId());
            }
            comTailerId.setItems(obList);
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
            String code = textCode.getText();
        try {
            boolean isDelete = machineBO.deleteMachine(code);
            if (isDelete) {
                new Alert(Alert.AlertType.INFORMATION, "Delete Successful").show();
                clearFiled();
                selectAllMachine();
            }else {
                new Alert(Alert.AlertType.ERROR,"Machine not Found").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String code= textCode.getText();
        String name = textName.getText();
        String type =textType.getText();
        String tailerId = comTailerId.getValue();
        var dto =new MachineDto(code,name,type,tailerId);
        boolean isCorrect = validateInputs(dto);
        if (isCorrect) {
            try {
                boolean isSaved = machineBO.saveMachine(dto);
                if (isSaved) {
                    new Alert(Alert.AlertType.INFORMATION, "Machine Saved Successful").show();
                    clearFiled();
                    selectAllMachine();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private boolean validateInputs(MachineDto dto) {
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
        ObservableList<MachineTm> obList = FXCollections.observableArrayList();

        try {
            List<MachineDto> dtoList = machineBO.getAllMachine();

            for (MachineDto dto : dtoList) {
                obList.add(
                        new MachineTm(
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
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearFiled() throws SQLException, ClassNotFoundException {
        textCode.setText(machineBO.generateNextMachineId());
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

        var dto = new MachineDto(code,name,type,tId);
        boolean isCorrect = validateInputs(dto);
        if (isCorrect) {
            try {
                boolean isUpdate = machineBO.updateMachine(dto);
                if (isUpdate) {
                    new Alert(Alert.AlertType.INFORMATION, "Update Successfully").show();
                    clearFiled();
                    selectAllMachine();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Machine Not Found").show();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void textSearchOnAction(ActionEvent event) {
            String code = textSearch.getText();
        try {
            MachineDto dto = machineBO.searchMachine(code);
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
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}