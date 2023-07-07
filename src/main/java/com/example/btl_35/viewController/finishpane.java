package com.example.btl_35.viewController;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class finishpane {
    @FXML
    private Label FinishTime;

    @FXML
    private Label StartTime;

    @FXML
    private Label TimeTaken;

    @FXML
    private Label gradeLabel;

    @FXML
    private Label markLabel;

    public void setInfo(String finishTime, String startTime, String timeTaken, String grade, String mark){
        FinishTime.setText(finishTime);
        StartTime.setText(startTime);
        TimeTaken.setText(timeTaken);
        gradeLabel.setText(grade);
        markLabel.setText(mark);
    }
}
