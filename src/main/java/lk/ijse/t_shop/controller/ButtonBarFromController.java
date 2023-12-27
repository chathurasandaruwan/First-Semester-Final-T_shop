package lk.ijse.t_shop.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;

import static javafx.scene.control.ButtonType.NO;
import static javafx.scene.control.ButtonType.YES;

public class ButtonBarFromController implements Serializable {

    @FXML
    private AnchorPane root;

    public void initialize() throws IOException {
        Parent rootNode =FXMLLoader.load(getClass().getResource("/lk/ijse/t_shop/dashboard_form.fxml"));
        root.getChildren().clear();
        root.getChildren().add(rootNode);
    }
    @FXML
    void btnCustomerOnAction(ActionEvent event) throws IOException {
        Parent rootNode =FXMLLoader.load(getClass().getResource("/lk/ijse/t_shop/customer_form.fxml"));
        this.root.getChildren().clear();
        this.root.getChildren().add(rootNode);
    }

    @FXML
    void btnHomeOnAction(ActionEvent event) throws IOException {
        Parent rootNode =FXMLLoader.load(getClass().getResource("/lk/ijse/t_shop/dashboard_form.fxml"));
        this.root.getChildren().clear();
        this.root.getChildren().add(rootNode);
    }

    @FXML
    void btnItemOnAction(ActionEvent event) throws IOException {
        Parent rootNode =FXMLLoader.load(getClass().getResource("/lk/ijse/t_shop/item_form.fxml"));
        this.root.getChildren().clear();
        this.root.getChildren().add(rootNode);
    }

    @FXML
    void btnLogOutOnAction(ActionEvent event) throws IOException {
        Optional <ButtonType> type=new Alert(Alert.AlertType.INFORMATION,"Do you want Log Out",NO,YES).showAndWait();
            if(type.orElse(NO)==YES){
                AnchorPane anchorPane =FXMLLoader.load(getClass().getResource("/lk/ijse/t_shop/login_form.fxml"));
                Scene scene=new Scene(anchorPane);

                Stage stage=(Stage)root.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Logging page");
                stage.centerOnScreen();
            }
    }

    @FXML
    void btnMachineOnAction(ActionEvent event) throws IOException {
        Parent rootNode =FXMLLoader.load(getClass().getResource("/lk/ijse/t_shop/machine_form.fxml"));
        this.root.getChildren().clear();
        this.root.getChildren().add(rootNode);
    }

    @FXML
    void btnOrderOnAction(ActionEvent event) throws IOException {
        Parent rootNode =FXMLLoader.load(getClass().getResource("/lk/ijse/t_shop/order_form.fxml"));
        this.root.getChildren().clear();
        this.root.getChildren().add(rootNode);
    }

    @FXML
    void btnRawMaterialsOnAction(ActionEvent event) throws IOException {
        Parent rootNode =FXMLLoader.load(getClass().getResource("/lk/ijse/t_shop/raw_material_form.fxml"));
        this.root.getChildren().clear();
        this.root.getChildren().add(rootNode);
    }

    @FXML
    void btnRecordOnAction(ActionEvent event) throws IOException {
        Parent rootNode =FXMLLoader.load(getClass().getResource("/lk/ijse/t_shop/record_form.fxml"));
        this.root.getChildren().clear();
        this.root.getChildren().add(rootNode);
    }

    @FXML
    void btnSupplierOnAction(ActionEvent event) throws IOException {
        Parent rootNode =FXMLLoader.load(getClass().getResource("/lk/ijse/t_shop/supplier_form.fxml"));
        this.root.getChildren().clear();
        this.root.getChildren().add(rootNode);
    }

    @FXML
    void btnTailorOnAction(ActionEvent event) throws IOException {
        Parent rootNode =FXMLLoader.load(getClass().getResource("/lk/ijse/t_shop/tailor_form.fxml"));
        this.root.getChildren().clear();
        this.root.getChildren().add(rootNode);
    }

}
