package lk.ijse.t_shop.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.t_shop.dto.userDto;
import lk.ijse.t_shop.model.LoggingModel;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.sql.SQLException;

public class loggingFromController {
    private LoggingModel loggingModel=new LoggingModel();
    @FXML
    private AnchorPane rootLogIn;

    @FXML
    private JFXTextField textName;
    @FXML
    private ToggleButton btnToggle;


    @FXML
    private JFXPasswordField textPassword;
    userDto dto=loggingModel.getAllInfo();
     String userName=dto.getUsername();
     String Password=dto.getPassword();

    public loggingFromController() throws SQLException {
    }


    @FXML
    void btnSingInOnAction(ActionEvent event) throws IOException {
       String name = textName.getText();
       String password = textPassword.getText();

       if (name.equals(userName)) {
           if (password.equals(Password)) {
               AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/button_bar_form.fxml"));
               Scene scene = new Scene(anchorPane);

               Stage stage = (Stage) rootLogIn.getScene().getWindow();
               stage.setScene(scene);
               stage.setTitle("Men's tailor Shop");
               stage.centerOnScreen();
           }else {
               showErrorNotification("WRONG INPUT !!!","The Password you entered is invalid");
           }
       }else {
          showErrorNotification("WRONG INPUT !!!","The User Name you entered is invalid");
       }
    }

    @FXML
    void textPasswordOnAction(ActionEvent event) throws IOException {
        btnSingInOnAction(event);
    }
    private static void showErrorNotification(String title , String text){
        Notifications.create()
                .title(title)
                .text(text)
                .showError();

    }
    @FXML
    void btnToggleOnAction(ActionEvent event) {
        if (btnToggle.isSelected()) {
            textPassword.setPromptText(textPassword.getText());
            textPassword.setText("");
        } else {
            textPassword.setText(textPassword.getPromptText());
            textPassword.setPromptText("");
        }
    }
    @FXML
    void btnForgetPasswordOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/forgotPassword_form.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) rootLogIn.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("ForgetPassword");
        stage.centerOnScreen();

    }
}
