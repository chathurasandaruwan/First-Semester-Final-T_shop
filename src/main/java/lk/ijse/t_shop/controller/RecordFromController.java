package lk.ijse.t_shop.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.t_shop.bo.BOFactory;
import lk.ijse.t_shop.bo.custom.RecordBO;
import lk.ijse.t_shop.bo.custom.impl.RecordBOImpl;
import lk.ijse.t_shop.dao.custom.CustomerDAO;
import lk.ijse.t_shop.dao.custom.OrderDAO;
import lk.ijse.t_shop.dao.custom.RecordDAO;
import lk.ijse.t_shop.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.t_shop.dao.custom.impl.OrderDAOImpl;
import lk.ijse.t_shop.dao.custom.impl.RecordDAOImpl;
import lk.ijse.t_shop.db.DbConnection;
import lk.ijse.t_shop.dto.CustomerDto;
import lk.ijse.t_shop.dto.OrderDto;
import lk.ijse.t_shop.dto.RecordDto;
import lk.ijse.t_shop.view.tdm.RecordTm;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

public class RecordFromController {
    @FXML
    private JFXTextField textPayment;
    @FXML
    private JFXTextField textType;

    @FXML
    private JFXTextField textRecordId;

    @FXML
    private TableView<RecordTm> tableRecord;

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

    @FXML
    private TableColumn<?, ?> columnPrice;

    @FXML
    private Label lableOrderId;
    RecordBO recordBO = (RecordBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.RECORD);

    public void initialize() throws SQLException, ClassNotFoundException {
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
            List<CustomerDto> idList = recordBO.getAllCustomer();

            for (CustomerDto dto : idList) {
                obList.add(dto.getCustId());
            }

            combCust.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void genarateNextOrderId(){
        try {
            String id = recordBO.generateNextOrderId();
            lableOrderId.setText(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void genarateNextRecId(){
        try {
            String id = recordBO.generateNextRecordId();
            textRecordId.setText(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void clearFiled() throws SQLException, ClassNotFoundException {
        textRecordId.setText(recordBO.generateNextRecordId());
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
        lableOrderId.setText(recordBO.generateNextOrderId());
        textPayment.setText("");
        combCust.setValue("");
    }
    @FXML
    void btnClearOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
            clearFiled();
    }
    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = textRecordId.getText();
        try {
            boolean isDelete = recordBO.deleteRecord(id);
            if (isDelete) {
                new Alert(Alert.AlertType.INFORMATION, "Delete Successful").show();
                clearFiled();
                loadAllRecord();
            } else {
                new Alert(Alert.AlertType.ERROR, "Record not Found").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
      if (validateInputs()) {
          String id = textRecordId.getText();
          String type = textType.getText();
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
          var dto = new RecordDto(id, type, Cd, rice, legOpen, kneeCrium, thighCirum, outSleam, inseamL, hiperC, waistC, Cuffc, neckC, chestC, shirtL, shoulderW, sleevL, bicepC, sleevO, coller, orderId, price, customerId);

          try {
              boolean isSaved = recordBO.placeRecord(dto, date);
              if (isSaved) {
                  new Alert(Alert.AlertType.INFORMATION, "Record Saved Successful").show();
                  clearFiled();
                  loadAllRecord();
              }
          } catch (SQLException e) {
              new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
              throw new RuntimeException(e);
          } catch (ClassNotFoundException e) {
              throw new RuntimeException(e);
          }
      }
    }
    private boolean validateInputs() {
        boolean id = Pattern.matches("[R][0-9]{3,}", textRecordId.getText());
        if (!id) {
            new Alert(Alert.AlertType.ERROR, "Invalid Id !!").show();
            return false;
        }
        boolean type = Pattern.matches("[A-Za-z]{4,}", textType.getText());
        if (!type) {
            new Alert(Alert.AlertType.ERROR, "Invalid Type !!").show();
            return false;
        }
        boolean CrotchDep = Pattern.matches("[0-9.]{1,}", textCrotchDep.getText());
        if (!CrotchDep) {
            new Alert(Alert.AlertType.ERROR, "Invalid CrotchDept !!").show();
            return false;
        }
        boolean rice = Pattern.matches("[0-9.]{1,}", textRice.getText());
        if (!rice) {
            new Alert(Alert.AlertType.ERROR, "Invalid Rice !!").show();
            return false;
        }
        boolean legOp = Pattern.matches("[0-9.]{1,}", textLegOpe.getText());
        if (!legOp) {
            new Alert(Alert.AlertType.ERROR, "Invalid Leg Open !!").show();
            return false;
        }
        boolean KC = Pattern.matches("[0-9.]{1,}", textKneeCirum.getText());
        if (!KC) {
            new Alert(Alert.AlertType.ERROR, "Invalid KneeCrim !!").show();
            return false;
        }
        boolean Tc = Pattern.matches("[0-9.]{1,}", textThighCirum.getText());
        if (!Tc) {
            new Alert(Alert.AlertType.ERROR, "Invalid ThighCrim!!").show();
            return false;
        }
        boolean Os = Pattern.matches("[0-9.]{1,}", textOutSeamL.getText());
        if (!Os) {
            new Alert(Alert.AlertType.ERROR, "Invalid OutSeamL !!").show();
            return false;
        }
        boolean IL = Pattern.matches("[0-9.]{1,}", textInseamL.getText());
        if (!IL) {
            new Alert(Alert.AlertType.ERROR, "Invalid InseamL !!").show();
            return false;
        }
        boolean HC = Pattern.matches("[0-9.]{1,}", textHipsCircum.getText());
        if (!HC) {
            new Alert(Alert.AlertType.ERROR, "Invalid HipsCircum !!").show();
            return false;
        }
        boolean Wc = Pattern.matches("[0-9.]{1,}", textWaistCircum.getText());
        if (!Wc) {
            new Alert(Alert.AlertType.ERROR, "Invalid WaistCircum !!").show();
            return false;
        }
        boolean CC = Pattern.matches("[0-9.]{1,}", textCuffCirum.getText());
        if (!CC) {
            new Alert(Alert.AlertType.ERROR, "Invalid CuffCirum !!").show();
            return false;
        }
        boolean Nc = Pattern.matches("[0-9.]{1,}", textNeckCirum.getText());
        if (!Nc) {
            new Alert(Alert.AlertType.ERROR, "Invalid NeckCirum !!").show();
            return false;
        }
        boolean Ccri = Pattern.matches("[0-9.]{1,}", textChestCirum.getText());
        if (!Ccri) {
            new Alert(Alert.AlertType.ERROR, "Invalid ChestCirum !!").show();
            return false;
        }
        boolean Sl = Pattern.matches("[0-9.]{1,}", textShirtL.getText());
        if (!Sl) {
            new Alert(Alert.AlertType.ERROR, "Invalid ShirtL !!").show();
            return false;
        }

        boolean Sw = Pattern.matches("[0-9.]{1,}", textShoulderWid.getText());
        if (!Sw) {
            new Alert(Alert.AlertType.ERROR, "Invalid ShoulderWid !!").show();
            return false;
        }

        boolean sl = Pattern.matches("[0-9.]{1,}", textSleeveL.getText());
        if (!sl) {
            new Alert(Alert.AlertType.ERROR, "Invalid SleeveL !!").show();
            return false;
        }

        boolean Bc = Pattern.matches("[0-9.]{1,}", textBicepCircum.getText());
        if (!Bc) {
            new Alert(Alert.AlertType.ERROR, "Invalid BicepCircum !!").show();
            return false;
        }

        boolean So = Pattern.matches("[0-9.]{1,}", textSleeveOp.getText());
        if (!So) {
            new Alert(Alert.AlertType.ERROR, "Invalid SleeveOp !!").show();
            return false;
        }

        boolean Coller = Pattern.matches("[0-9.]{1,}", textColler.getText());
        if (!Coller) {
            new Alert(Alert.AlertType.ERROR, "Invalid Coller !!").show();
            return false;
        }
        boolean price = Pattern.matches("[0-9]{1,}", textPayment.getText());
        if (!price) {
            new Alert(Alert.AlertType.ERROR, "Invalid Price!!").show();
            return false;
        }
        return true;
    }




        @FXML
    void btnUpdateOnAction(ActionEvent event) {
        boolean isCorrect = validateInputs();
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
      //  LocalDate date = LocalDate.parse(lableDate.getText());
        String customerId = combCust.getValue();
        var dto = new RecordDto(id,type,Cd,rice,legOpen,kneeCrium,thighCirum,outSleam,inseamL,hiperC,waistC,Cuffc,neckC,chestC,shirtL,shoulderW,sleevL,bicepC,sleevO,coller,orderId,price,customerId);
        if (isCorrect) {
            try {
                boolean isUpdate = recordBO.updateRecord(dto);
                if (isUpdate) {
                    new Alert(Alert.AlertType.INFORMATION, "Update Successfully").show();
                    clearFiled();
                    loadAllRecord();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Record Not Found").show();
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
        String id=textSearch.getText();
        try {
            RecordDto dto = recordBO.searchRecord(id);
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
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    void loadAllRecord(){
        ObservableList<RecordTm> obList = FXCollections.observableArrayList();
        try {
            List<RecordDto> dtoList = recordBO.getAllRecord();
            for (RecordDto dto : dtoList) {
                obList.add(
                        new RecordTm(
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
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
