package lk.ijse.t_shop.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lk.ijse.t_shop.model.ChangePasswordModel;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import java.util.regex.Pattern;

import static javafx.scene.control.ButtonType.NO;
import static javafx.scene.control.ButtonType.YES;

public class ChangePasswordFromController {

    @FXML
    private JFXTextField textNewPassword;

    @FXML
    private Pane changeRoot;

    @FXML
    private JFXTextField textConfirmPassword;

    @FXML
    private Button btnChangePassword;
    private ChangePasswordModel changePasswordModel=new ChangePasswordModel();

    @FXML
    void btnChangePasswordOnAction(ActionEvent event) throws IOException, SQLException {
        boolean isCorrect = validateInputs();
        if (isCorrect) {
            String newPassword = textNewPassword.getText();
            String confirmPassword = textConfirmPassword.getText();
            if (newPassword.equals(confirmPassword)) {
                Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Do you want to change Password", NO, YES).showAndWait();
                if (type.orElse(NO) == YES) {
                    boolean isChange = changePasswordModel.changePassword(newPassword);
                    if (isChange) {
                        showInformationNotification("Successful", "Password change Successful");
                        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
                        Scene scene = new Scene(anchorPane);

                        Stage stage = (Stage) changeRoot.getScene().getWindow();
                        stage.setScene(scene);
                        stage.setTitle("Logging page");
                        stage.centerOnScreen();
                    }
                }
            } else {
                showErrorNotification("WRONG INPUT", "Password not matched !");
            }
        }
    }
    private boolean validateInputs() {
        boolean password = Pattern.matches("[A-Za-z0-9.@]{8,}",textNewPassword.getText());
        if (!password){
            showErrorNotification("INVALID INPUT !!!","password need more than '8' characters!!");
            return false;
        }
        return true;
    }
    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) changeRoot.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Logging page");
        stage.centerOnScreen();
    }
    private static void showErrorNotification(String title , String text){
        Notifications.create()
                .title(title)
                .text(text)
                .showError();

    }
    private static void showInformationNotification(String title , String text){
        Notifications.create()
                .title(title)
                .text(text)
                .showInformation();

    }


}
