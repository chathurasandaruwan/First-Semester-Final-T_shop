package lk.ijse.t_shop.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.t_shop.dto.customerDto;
import lk.ijse.t_shop.dto.recordDto;
import lk.ijse.t_shop.dto.tailorDto;
import lk.ijse.t_shop.model.CustomerModel;
import lk.ijse.t_shop.model.OrderModel;
import lk.ijse.t_shop.model.RecordModel;
import lk.ijse.t_shop.model.SaveRecordModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class RecordFromController {
    @FXML
    private JFXTextField textPayment;
    @FXML
    private JFXTextField textType;

    @FXML
    private JFXTextField textRecordId;

    @FXML
    private TableView<recordDto> tableRecord;

    @FXML
    private TableColumn<?, ?> columnRecordID;

    @FXML
    private TableColumn<?, ?> columnType;

    @FXML
    private TableColumn<?, ?> columnOrderID;

    @FXML
    private TableColumn<?, ?> columnCrotchDepth;

    @FXML
    private TableColumn<?, ?> columnRise;

    @FXML
    private TableColumn<?, ?> columnLegOpening;

    @FXML
    private TableColumn<?, ?> columnKneeCirumference;

    @FXML
    private TableColumn<?, ?> columnThighCirumference;

    @FXML
    private TableColumn<?, ?> columnInseamLength;

    @FXML
    private TableColumn<?, ?> columnOutSeamLength;

    @FXML
    private TableColumn<?, ?> columnHipsCircumference;

    @FXML
    private TableColumn<?, ?> columnWaistCircumferencr;

    @FXML
    private TableColumn<?, ?> columnCuffCirumference;

    @FXML
    private TableColumn<?, ?> columnNeckCirumference;

    @FXML
    private TableColumn<?, ?> columnChestCirumference;

    @FXML
    private TableColumn<?, ?> columnShirtLength;

    @FXML
    private TableColumn<?, ?> columnSleeveLength;

    @FXML
    private TableColumn<?, ?> columnBicepCircumference;

    @FXML
    private TableColumn<?, ?> columnSleeveOpening;

    @FXML
    private TableColumn<?, ?> columnShoulderWidth;

    @FXML
    private TableColumn<?, ?> columnColler;

    @FXML
    private JFXTextField textCrotchDep;

    @FXML
    private JFXTextField textRice;

    @FXML
    private JFXTextField textLegOpe;

    @FXML
    private JFXTextField textKneeCirum;

    @FXML
    private JFXTextField textThighCirum;

    @FXML
    private JFXTextField textInseamL;

    @FXML
    private JFXTextField textOutSeamL;

    @FXML
    private JFXTextField textHipsCircum;

    @FXML
    private JFXTextField textWaistCircum;

    @FXML
    private JFXTextField textCuffCirum;

    @FXML
    private JFXTextField textNeckCirum;

    @FXML
    private JFXTextField textChestCirum;

    @FXML
    private JFXTextField textShirtL;

    @FXML
    private JFXTextField textSleeveL;

    @FXML
    private JFXTextField textBicepCircum;

    @FXML
    private JFXTextField textSleeveOp;

    @FXML
    private JFXTextField textShoulderWid;

    @FXML
    private JFXTextField textColler;

    @FXML
    private JFXComboBox<String> combCust;

    @FXML
    private Label lableDate;

    @FXML
    private TextField textSearch;
    private OrderModel orderModel=new OrderModel();
    private RecordModel model = new RecordModel();
    private SaveRecordModel saveRecordModel = new SaveRecordModel();
    private CustomerModel customerModel= new CustomerModel();

    @FXML
    private TableColumn<?, ?> columnPrice;

    @FXML
    private Label lableOrderId;

    public void initialize() throws SQLException {
        genarateNextOrderId();
        genarateNextRecId();
        clearFiled();
        loadAllRecord();
        valuesFactory();
        setDate();
        loadCustomerIds();
    }
    public void valuesFactory(){
        columnRecordID.setCellValueFactory(new PropertyValueFactory<>("RecordId"));
        columnType.setCellValueFactory(new PropertyValueFactory<>("Type"));
        columnCrotchDepth.setCellValueFactory(new PropertyValueFactory<>("crotchDep"));
        columnRise.setCellValueFactory(new PropertyValueFactory<>("rice"));
        columnLegOpening.setCellValueFactory(new PropertyValueFactory<>("legOpe"));
        columnKneeCirumference.setCellValueFactory(new PropertyValueFactory<>("kneeCirum"));
        columnThighCirumference.setCellValueFactory(new PropertyValueFactory<>("thighCirum"));
        columnOutSeamLength.setCellValueFactory(new PropertyValueFactory<>("outSeamL"));
        columnInseamLength.setCellValueFactory(new PropertyValueFactory<>("inseamL"));
        columnHipsCircumference.setCellValueFactory(new PropertyValueFactory<>("hipsCircum"));
        columnWaistCircumferencr.setCellValueFactory(new PropertyValueFactory<>("waistCircum"));
        columnCuffCirumference.setCellValueFactory(new PropertyValueFactory<>("cuffCirum"));
        columnNeckCirumference.setCellValueFactory(new PropertyValueFactory<>("neckCirum"));
        columnChestCirumference.setCellValueFactory(new PropertyValueFactory<>("chestCirum"));
        columnShirtLength.setCellValueFactory(new PropertyValueFactory<>("shirtL"));
        columnShoulderWidth.setCellValueFactory(new PropertyValueFactory<>("shoulderWid"));
        columnSleeveLength.setCellValueFactory(new PropertyValueFactory<>("sleeveL"));
        columnBicepCircumference.setCellValueFactory(new PropertyValueFactory<>("bicepCircum"));
        columnSleeveOpening.setCellValueFactory(new PropertyValueFactory<>("sleeveOp"));
        columnColler.setCellValueFactory(new PropertyValueFactory<>("coller"));
        columnOrderID.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

    }
    public void setDate(){
        lableDate.setText(String.valueOf(LocalDate.now()));
    }
    private void loadCustomerIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<customerDto> idList = customerModel.getAllCustomer();

            for (customerDto dto : idList) {
                obList.add(dto.getCustId());
            }

            combCust.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void genarateNextOrderId(){
        try {
            String id = orderModel.genarateNextId();
            lableOrderId.setText(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void genarateNextRecId(){
        try {
            String id = model.genarateNextId();
            textRecordId.setText(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void clearFiled() throws SQLException {
        textRecordId.setText(model.genarateNextId());
        textType.setText("");
        textCrotchDep.setText("00");
        textRice.setText("00");
        textLegOpe.setText("00");
        textKneeCirum.setText("00");
        textThighCirum.setText("00");
        textOutSeamL.setText("00");
        textInseamL.setText("00");
        textHipsCircum.setText("00");
        textWaistCircum.setText("00");
        textCuffCirum.setText("00");
        textNeckCirum.setText("00");
        textChestCirum.setText("00");
        textShirtL.setText("00");
        textShoulderWid.setText("00");
        textSleeveL.setText("00");
        textBicepCircum.setText("00");
        textSleeveOp.setText("00");
        textColler.setText("00");
        textSearch.setText("");
        lableOrderId.setText(orderModel.genarateNextId());
        textPayment.setText("");
        combCust.setValue("");
    }
    @FXML
    void btnClearOnAction(ActionEvent event) throws SQLException {
            clearFiled();
    }
    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = textRecordId.getText();
        try {
            boolean isDelete = model.deleteRecord(id);
            if (isDelete) {
                new Alert(Alert.AlertType.INFORMATION, "Delete Successful").show();
                clearFiled();
                loadAllRecord();
            }else {
                new Alert(Alert.AlertType.ERROR,"Record not Found").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
       String id= textRecordId.getText();
       String type =textType.getText();
       double Cd = Double.parseDouble(textCrotchDep.getText());
       double rice = Double.parseDouble(textRice.getText());
       double legOpen = Double.parseDouble(textLegOpe.getText());
       double kneeCrium = Double.parseDouble(textKneeCirum.getText());
       double thighCirum = Double.parseDouble(textThighCirum.getText());
       double outSleam = Double.parseDouble(textOutSeamL.getText());
       double inseamL = Double.parseDouble(textInseamL.getText());
       double hiperC = Double.parseDouble(textHipsCircum.getText());
       double waistC = Double.parseDouble(textWaistCircum.getText());
       double Cuffc = Double.parseDouble(textCuffCirum.getText());
       double neckC = Double.parseDouble(textNeckCirum.getText());
       double chestC = Double.parseDouble(textChestCirum.getText());
       double shirtL = Double.parseDouble(textShirtL.getText());
       double shoulderW = Double.parseDouble(textShoulderWid.getText());
       double sleevL = Double.parseDouble(textSleeveL.getText());
       double bicepC = Double.parseDouble(textBicepCircum.getText());
       double sleevO = Double.parseDouble(textSleeveOp.getText());
       double coller = Double.parseDouble(textColler.getText());
       String orderId = lableOrderId.getText();
       String price = textPayment.getText();
       LocalDate date = LocalDate.parse(lableDate.getText());
       String customerId = combCust.getValue();
        var dto = new recordDto(id,type,Cd,rice,legOpen,kneeCrium,thighCirum,outSleam,inseamL,hiperC,waistC,Cuffc,neckC,chestC,shirtL,shoulderW,sleevL,bicepC,sleevO,coller,orderId,price,customerId);

        try {
            boolean isSaved = saveRecordModel.saveRecord(dto,date);
            if (isSaved){
                new Alert(Alert.AlertType.INFORMATION,"Record Saved Successful").show();
                clearFiled();
                loadAllRecord();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            throw new RuntimeException(e);
        }
    }
    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id= textRecordId.getText();
        String type =textType.getText();
        double Cd = Double.parseDouble(textCrotchDep.getText());
        double rice = Double.parseDouble(textRice.getText());
        double legOpen = Double.parseDouble(textLegOpe.getText());
        double kneeCrium = Double.parseDouble(textKneeCirum.getText());
        double thighCirum = Double.parseDouble(textThighCirum.getText());
        double outSleam = Double.parseDouble(textOutSeamL.getText());
        double inseamL = Double.parseDouble(textInseamL.getText());
        double hiperC = Double.parseDouble(textHipsCircum.getText());
        double waistC = Double.parseDouble(textWaistCircum.getText());
        double Cuffc = Double.parseDouble(textCuffCirum.getText());
        double neckC = Double.parseDouble(textNeckCirum.getText());
        double chestC = Double.parseDouble(textChestCirum.getText());
        double shirtL = Double.parseDouble(textShirtL.getText());
        double shoulderW = Double.parseDouble(textShoulderWid.getText());
        double sleevL = Double.parseDouble(textSleeveL.getText());
        double bicepC = Double.parseDouble(textBicepCircum.getText());
        double sleevO = Double.parseDouble(textSleeveOp.getText());
        double coller = Double.parseDouble(textColler.getText());
        String orderId = lableOrderId.getText();
        String price = textPayment.getText();
        LocalDate date = LocalDate.parse(lableDate.getText());
        String customerId = combCust.getValue();
        var dto = new recordDto(id,type,Cd,rice,legOpen,kneeCrium,thighCirum,outSleam,inseamL,hiperC,waistC,Cuffc,neckC,chestC,shirtL,shoulderW,sleevL,bicepC,sleevO,coller,orderId,price,customerId);

        try {
            boolean isUpdate = model.updateRecord(dto);
            if (isUpdate){
                new Alert(Alert.AlertType.INFORMATION,"Update Successfully").show();
                clearFiled();
                loadAllRecord();
            }else {
                new Alert(Alert.AlertType.ERROR,"Record Not Found").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void textSearchOnAction(ActionEvent event) {
        String id=textSearch.getText();
        try {
            recordDto dto = model.searchRecord(id);
            if (dto!= null){
                textRecordId.setText(dto.getRecordId());
                textType.setText(dto.getType());
                textCrotchDep.setText(String.valueOf(dto.getCrotchDep()));
                textRice.setText(String.valueOf(dto.getRice()));
                textLegOpe.setText(String.valueOf(dto.getLegOpe()));
                textKneeCirum.setText(String.valueOf(dto.getKneeCirum()));
                textThighCirum.setText(String.valueOf(dto.getThighCirum()));
                textOutSeamL.setText(String.valueOf(dto.getOutSeamL()));
                textInseamL.setText(String.valueOf(dto.getInseamL()));
                textHipsCircum.setText(String.valueOf(dto.getHipsCircum()));
                textWaistCircum.setText(String.valueOf(dto.getWaistCircum()));
                textCuffCirum.setText(String.valueOf(dto.getCuffCirum()));
                textNeckCirum.setText(String.valueOf(dto.getNeckCirum()));
                textChestCirum.setText(String.valueOf(dto.getChestCirum()));
                textShirtL.setText(String.valueOf(dto.getShirtL()));
                textShoulderWid.setText(String.valueOf(dto.getShoulderWid()));
                textSleeveL.setText(String.valueOf(dto.getSleeveL()));
                textBicepCircum.setText(String.valueOf(dto.getBicepCircum()));
                textSleeveOp.setText(String.valueOf(dto.getSleeveOp()));
                textColler.setText(String.valueOf(dto.getColler()));
                lableOrderId.setText(dto.getOrderId());
                textPayment.setText(dto.getPrice());
                combCust.setValue(dto.getCustId());

            }else {
                new Alert(Alert.AlertType.ERROR,"Record not found").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    void loadAllRecord(){
        ObservableList<recordDto> obList = FXCollections.observableArrayList();
        try {
            List<recordDto> dtoList = model.getAllRecord();
            for (recordDto dto : dtoList) {
                obList.add(
                        new recordDto(
                                dto.getRecordId(),
                                dto.getType(),
                                dto.getCrotchDep(),
                                dto.getRice(),
                                dto.getLegOpe(),
                                dto.getKneeCirum(),
                                dto.getThighCirum(),
                                dto.getOutSeamL(),
                                dto.getInseamL(),
                                dto.getHipsCircum(),
                                dto.getWaistCircum(),
                                dto.getCuffCirum(),
                                dto.getNeckCirum(),
                                dto.getChestCirum(),
                                dto.getShirtL(),
                                dto.getShoulderWid(),
                                dto.getSleeveL(),
                                dto.getBicepCircum(),
                                dto.getSleeveOp(),
                                dto.getColler(),
                                dto.getOrderId(),
                                dto.getPrice(),
                                dto.getCustId()
                        )
                );
            }

            tableRecord.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
