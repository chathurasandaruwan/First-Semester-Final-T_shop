package lk.ijse.t_shop.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;

import static javafx.scene.control.ButtonType.NO;
import static javafx.scene.control.ButtonType.YES;

public class ButtonBarFromController implements Serializable {

    @FXML
    private AnchorPane root;
    @FXML
    private JFXButton btnHome;

    @FXML
    private JFXButton btnItem;

    @FXML
    private JFXButton btnOrder;

    @FXML
    private JFXButton btnTailor;

    @FXML
    private JFXButton btnRecord;

    @FXML
    private JFXButton btnSupplier;

    @FXML
    private JFXButton btnMachine;

    @FXML
    private JFXButton btnCustomer;

    @FXML
    private JFXButton btnRawMaterials;

    @FXML
    private JFXButton btnLogOut;

    public void initialize() throws IOException {
        Parent rootNode =FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        root.getChildren().clear();
        root.getChildren().add(rootNode);
    }
    @FXML
    void btnCustomerOnAction(ActionEvent event) throws IOException {
        Parent rootNode =FXMLLoader.load(getClass().getResource("/view/customer_form.fxml"));
        this.root.getChildren().clear();
        this.root.getChildren().add(rootNode);
    }

    @FXML
    void btnHomeOnAction(ActionEvent event) throws IOException {
        Parent rootNode =FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        this.root.getChildren().clear();
        this.root.getChildren().add(rootNode);
    }

    @FXML
    void btnItemOnAction(ActionEvent event) throws IOException {
        Parent rootNode =FXMLLoader.load(getClass().getResource("/view/item_form.fxml"));
        this.root.getChildren().clear();
        this.root.getChildren().add(rootNode);
    }

    @FXML
    void btnLogOutOnAction(ActionEvent event) throws IOException {
        Optional <ButtonType> type=new Alert(Alert.AlertType.INFORMATION,"Do you want Log Out",NO,YES).showAndWait();
            if(type.orElse(NO)==YES){
                AnchorPane anchorPane =FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
                Scene scene=new Scene(anchorPane);

                Stage stage=(Stage)root.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Logging page");
                stage.centerOnScreen();
            }
    }

    @FXML
    void btnMachineOnAction(ActionEvent event) throws IOException {
        Parent rootNode =FXMLLoader.load(getClass().getResource("/view/machine_form.fxml"));
        this.root.getChildren().clear();
        this.root.getChildren().add(rootNode);
    }

    @FXML
    void btnOrderOnAction(ActionEvent event) throws IOException {
        Parent rootNode =FXMLLoader.load(getClass().getResource("/view/order_form.fxml"));
        this.root.getChildren().clear();
        this.root.getChildren().add(rootNode);
    }

    @FXML
    void btnRawMaterialsOnAction(ActionEvent event) throws IOException {
        Parent rootNode =FXMLLoader.load(getClass().getResource("/view/raw_material_form.fxml"));
        this.root.getChildren().clear();
        this.root.getChildren().add(rootNode);
    }

    @FXML
    void btnRecordOnAction(ActionEvent event) throws IOException {
        Parent rootNode =FXMLLoader.load(getClass().getResource("/view/record_form.fxml"));
        this.root.getChildren().clear();
        this.root.getChildren().add(rootNode);
    }

    @FXML
    void btnSupplierOnAction(ActionEvent event) throws IOException {
        Parent rootNode =FXMLLoader.load(getClass().getResource("/view/supplier_form.fxml"));
        this.root.getChildren().clear();
        this.root.getChildren().add(rootNode);
    }

    @FXML
    void btnTailorOnAction(ActionEvent event) throws IOException {
        Parent rootNode =FXMLLoader.load(getClass().getResource("/view/tailor_form.fxml"));
        this.root.getChildren().clear();
        this.root.getChildren().add(rootNode);
    }

}
