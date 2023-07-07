package com.example.btl_35.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class gui51Error {

    @FXML
    private Button error;

    @FXML
    void error(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("gui51.fxml"));
            Parent gui51Parent = loader.load();
            Scene gui51Scene = new Scene(gui51Parent);
            Stage stage = (Stage) error.getScene().getWindow();
            stage.setScene(gui51Scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
