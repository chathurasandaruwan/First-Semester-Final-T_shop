package lk.ijse.t_shop.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

import java.awt.event.MouseEvent;
import java.io.IOException;

public class loggingFromController {

    @FXML
    private AnchorPane rootLogIn;

    @FXML
    private JFXTextField textName;
    @FXML
    private ToggleButton btnToggle;


    @FXML
    private JFXPasswordField textPassword;

     String userName="chathura";
     String Password="12345";


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
}
