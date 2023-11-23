package lk.ijse.t_shop;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class AppInitializer extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/loading_form.fxml"));
        Scene scene = new Scene(rootNode);
        stage.setScene(scene);
        stage.setTitle("LOADING .............");
        stage.centerOnScreen();

        stage.show();

        Timeline timeline = new Timeline(new KeyFrame(
                Duration.seconds(10),
                event -> {
                    try {
                        closeApplication(rootNode,stage);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        ));
        timeline.play();
    }

    private void closeApplication(AnchorPane rootNode,Stage stage) throws IOException {
        rootNode = FXMLLoader.load(this.getClass().getResource("/view/login_form.fxml"));
        Scene scene = new Scene(rootNode);
        stage.setScene(scene);
        stage.setTitle("Logging Page");
        stage.centerOnScreen();
    }
}















/*
 stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/login_form.fxml"))));
         stage.centerOnScreen();
         stage.setTitle("Logging Page");
         stage.show();*/
