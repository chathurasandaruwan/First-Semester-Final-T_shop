package lk.ijse.t_shop.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lk.ijse.t_shop.dto.userDto;
import lk.ijse.t_shop.model.LoggingModel;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.sql.SQLException;

public class ForgotPasswordController {

    @FXML
    private TextField textUserName;
    @FXML
    private Pane changeRoot;
    @FXML
    private Pane forgotRoot;

    @FXML
    private AnchorPane root;

    @FXML
    private TextField textContactNo;

    private LoggingModel loggingModel =new LoggingModel();

    @FXML
    void btnNextOnAction(ActionEvent event) throws IOException, SQLException {
        String userName = textUserName.getText();
        String contactNo = textContactNo.getText();
        userDto dto = loggingModel.getAllInfo();

        if (userName.equals(dto.getUsername())) {
            if (contactNo.equals(dto.getContactNo())) {

                Parent rootNode = FXMLLoader.load(getClass().getResource("/view/change_password_form.fxml"));

                changeRoot.getChildren().clear();
                changeRoot.getChildren().add(rootNode);

                Parent rootNode1 = FXMLLoader.load(getClass().getResource("/view/hideForm.fxml"));

                forgotRoot.getChildren().clear();
                forgotRoot.getChildren().add(rootNode1);
            }else {
                showErrorNotification("WRONG INPUT !!!","The contact number you entered is invalid");
            }
        }else {
            showErrorNotification("WRONG INPUT !!!","The User name you entered is invalid");
        }
    }

    private static void showErrorNotification(String title , String text){
        Notifications.create()
                .title(title)
                .text(text)
                .showError();

    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Logging page");
        stage.centerOnScreen();
    }

}
