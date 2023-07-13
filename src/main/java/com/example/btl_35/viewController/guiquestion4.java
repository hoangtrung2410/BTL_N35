//package com.example.btl_35.viewController;
//
//import com.example.btl_35.entity.Answer;
//import com.example.btl_35.entity.Question;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.control.Button;
//import javafx.scene.control.CheckBox;
//import javafx.scene.control.Label;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.media.MediaPlayer;
//import javafx.scene.media.MediaView;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.List;
//
//public class guiquestion4 {
//
//    @FXML
//    private CheckBox answer1;
//
//    @FXML
//    private CheckBox answer2;
//
//    @FXML
//    private CheckBox answer3;
//
//    @FXML
//    private CheckBox answer4;
//
//    @FXML
//    private ImageView image1;
//
//    @FXML
//    private ImageView image2;
//
//    @FXML
//    private ImageView image3;
//
//    @FXML
//    private ImageView image4;
//
//    @FXML
//    private ImageView imageView;
//
//    @FXML
//    private MediaView mediaView;
//
//    @FXML
//    private AnchorPane parentPane;
//
//    @FXML
//    private Label questionContent;
//
//    @FXML
//    private Label questionNumber;
//    private List<Answer> answerList;
//    private int answerCount;
//    private Question question;
//    public void setQuestion(Question question){
//        this.question = question;
//        setContent(this.question.getMedia().getUrl());
//        this.answerList = new ArrayList<>(question.getAnswers());
//        this.answerCount=answerList.size();
//    }
//
//    private void setContent(String filePath){
//        File contentFile = new File(filePath);
//        String fileExtension = getFileExtension(contentFile);
//        if(fileExtension.equalsIgnoreCase("mp4")){
//            setVideo(contentFile);
//        } else {
//            setImage(contentFile);
//        }
//    }
//    private void setImage(File contentFile){
//        mediaView.setVisible(false);
//        ImageView image = new ImageView(contentFile.toURI().toString());
//        imageView.setImage(image.getImage());
//    }
//    private void setVideo(File contentFile){
//        imageView.setVisible(false);
////        replay.setVisible(true);
//        javafx.scene.media.Media media = new javafx.scene.media.Media(contentFile.toURI().toString());
//        MediaPlayer mediaPlayer = new MediaPlayer(media);
//        // Gán MediaPlayer vào MediaView để hiển thị video
//        mediaView.setMediaPlayer(mediaPlayer);
////        this.mediaPlayer = mediaPlayer;
//    }
//
//    private String getFileExtension(File file) {
//        String extension = "";
//        String fileName = file.getName();
//        int dotIndex = fileName.lastIndexOf('.');
//        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
//            extension = fileName.substring(dotIndex + 1).toLowerCase();
//        }
//        return extension;
//    }
////    @FXML
////    void replayAction(ActionEvent event) {
////        if (mediaPlayer != null) {
////            mediaPlayer.seek(javafx.util.Duration.ZERO);
////            mediaPlayer.play();
////        }
////    }
//public void setImageViews(List<Answer> answers){
//    List<ImageView> imageviews = new ArrayList<>();
//    imageviews.add(image1);
//    imageviews.add(image2);
//    imageviews.add(image3);
//    imageviews.add(image4);
//    for(int i =0 ; i<answers.size();i++){
//        setAnswerImageView(answers.get(i), imageviews.get(i));
//    }
//
//}
//
//    private void setAnswerImageView(Answer answer, ImageView imageView) {
//        String imageUrl = answer.getMedia().getUrl();
//        File contentFile = new File(imageUrl);
//        if (imageUrl != null && !imageUrl.isEmpty()) {
//            try {
//                ImageView image = new ImageView(contentFile.toURI().toString());
//                imageView.setImage(image.getImage());
//            } catch (IllegalArgumentException e) {
//                // Handle the case where the URL is invalid or the resource could not be found
//                System.err.println("Failed to load image at URL: " + imageUrl);
//                e.printStackTrace();
//            }
//        } else {
//            imageView.setImage(null);
//        }
//    }
//}
package com.example.btl_35.viewController;

import com.example.btl_35.entity.Answer;
import com.example.btl_35.entity.Question;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class guiquestion4 {
    @FXML
    private Button replay;
    @FXML
    private CheckBox answer1;

    @FXML
    private CheckBox answer2;

    @FXML
    private CheckBox answer3;

    @FXML
    private CheckBox answer4;

    @FXML
    private ImageView image1;

    @FXML
    private ImageView image2;

    @FXML
    private ImageView image3;

    @FXML
    private ImageView image4;

    @FXML
    private ImageView imageView;

    @FXML
    private MediaView mediaView;
    private MediaPlayer mediaPlayer;

    @FXML
    private AnchorPane parentPane;

    @FXML
    private Label questionContent;

    @FXML
    private Label questionNumber;

    private List<Answer> answerList;
    private int answerCount;
    private Question question;

    public void setQuestion(Question question) {
        this.question = question;
        setContent(this.question.getMedia().getUrl());
        this.answerList = new ArrayList<>(question.getAnswers());
        this.answerCount = answerList.size();
    }
    private void setContent(String filePath) {
        File contentFile = new File(filePath);
        String fileExtension = getFileExtension(contentFile);
        if (fileExtension.equalsIgnoreCase("mp4")) {
            setVideo(contentFile);
        } else {
            setImage(contentFile);
        }
    }

    private void setImage(File contentFile) {
        mediaView.setVisible(false);
        Image image = new Image(contentFile.toURI().toString());
        imageView.setImage(image);
    }

    private void setVideo(File contentFile) {
        imageView.setVisible(false);
        replay.setVisible(true);
        Media media = new Media(contentFile.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
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
    public void setImageViews(List<Answer> answers){
        List<ImageView> imageviews = new ArrayList<>();
        imageviews.add(image1);
        imageviews.add(image2);
        imageviews.add(image3);
        imageviews.add(image4);
        for(int i =0 ; i<answers.size();i++){
            setAnswerImageView(answers.get(i), imageviews.get(i));
        }
    }


    private void setAnswerImageView(Answer answer, ImageView imageView) {
        String imageUrl = answer.getMedia().getUrl();
        File contentFile = new File(imageUrl);
        if (imageUrl != null && !imageUrl.isEmpty()) {
            try {
                ImageView image = new ImageView(contentFile.toURI().toString());
                imageView.setImage(image.getImage());
            } catch (IllegalArgumentException e) {
                // Handle the case where the URL is invalid or the resource could not be found
                System.err.println("Failed to load image at URL: " + imageUrl);
                e.printStackTrace();
            }
        } else {
            imageView.setImage(null);
        }
    }
    @FXML
    void replayAction(ActionEvent event) {
        if (mediaPlayer != null) {
            mediaPlayer.seek(javafx.util.Duration.ZERO);
            mediaPlayer.play();
        }
    }


}
