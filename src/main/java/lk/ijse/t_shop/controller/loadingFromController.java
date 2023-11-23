package lk.ijse.t_shop.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class loadingFromController {
    @FXML
    private Label lblChange;
    private final String[] textValues = {"Please wait.........","Check Licence Key........","Check Database Instance........"};
    private int currentIndex = 0;

    public void initialize(){
        setLblChange();
    }
    public void setLblChange(){
    Timeline timeline = new Timeline(new KeyFrame(
            Duration.seconds(3),
            event -> updateLabel()
    ));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
}

    private void updateLabel() {
        lblChange.setText(textValues[currentIndex]);
        currentIndex = (currentIndex + 1) % textValues.length;


        //lblChange.setText("Check Licence Key: " + System.currentTimeMillis());
    }
}
