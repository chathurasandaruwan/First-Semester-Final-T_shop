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
import lk.ijse.t_shop.bo.BOFactory;
import lk.ijse.t_shop.bo.custom.ForgotPasswordBO;
import lk.ijse.t_shop.bo.custom.impl.ForgotPasswordBOImpl;
import lk.ijse.t_shop.dao.custom.LoggingDAO;
import lk.ijse.t_shop.dao.custom.impl.LoggingDAOImpl;
import lk.ijse.t_shop.dto.UserDto;
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

    ForgotPasswordBO forgotPasswordBO = (ForgotPasswordBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.FORGOT_PASSWORD);

    @FXML
    void btnNextOnAction(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        String userName = textUserName.getText();
        String contactNo = textContactNo.getText();
        UserDto dto = forgotPasswordBO.getAllDetail();

        if (userName.equals(dto.getUsername())) {
            if (contactNo.equals(dto.getContactNo())) {

                Parent rootNode = FXMLLoader.load(getClass().getResource("/lk/ijse/t_shop/change_password_form.fxml"));

                changeRoot.getChildren().clear();
                changeRoot.getChildren().add(rootNode);

                Parent rootNode1 = FXMLLoader.load(getClass().getResource("/lk/ijse/t_shop/hideForm.fxml"));

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
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/lk/ijse/t_shop/login_form.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Logging page");
        stage.centerOnScreen();
    }

}
