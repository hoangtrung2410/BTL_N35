package com.example.btl_35.viewController;

import com.example.btl_35.entity.Question;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.File;

public class guiquestion2 {
    @FXML
    private Button replay;
    @FXML
    private Label questionContent;
    @FXML
    private Label questionNumber;
    @FXML
    private CheckBox answer1;
    @FXML
    private CheckBox answer2;
    @FXML
    private CheckBox answer3;
    @FXML
    private ImageView imageView;
    @FXML
    private MediaView mediaView;
    private MediaPlayer mediaPlayer;
    private Question question;
    public void setQuestion(Question question){
        this.question = question;
        setContent(this.question.getMedia().getUrl());
    }

    private void setContent(String filePath){
        File contentFile = new File(filePath);
        String fileExtension = getFileExtension(contentFile);
        if(fileExtension.equalsIgnoreCase("mp4")){
            setVideo(contentFile);
        } else {
            setImage(contentFile);
        }
    }
    private void setImage(File contentFile){
        mediaView.setVisible(false);
        ImageView image = new ImageView(contentFile.toURI().toString());
        imageView.setImage(image.getImage());
    }
    private void setVideo(File contentFile){
        imageView.setVisible(false);
        replay.setVisible(true);
        javafx.scene.media.Media media = new javafx.scene.media.Media(contentFile.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        // Gán MediaPlayer vào MediaView để hiển thị video
        mediaView.setMediaPlayer(mediaPlayer);
        this.mediaPlayer = mediaPlayer;
    }

    private String getFileExtension(File file) {
        String extension = "";
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            extension = fileName.substring(dotIndex + 1).toLowerCase();
        }
        return extension;
    }
    @FXML
    void replayAction(ActionEvent event) {
        if (mediaPlayer != null) {
            mediaPlayer.seek(javafx.util.Duration.ZERO);
            mediaPlayer.play();
        }
    }
}