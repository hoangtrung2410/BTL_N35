package com.example.btl_35.viewController;

import com.example.btl_35.entity.Answer;
import com.example.btl_35.entity.Question;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class guiquestion3 {
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
    private CheckBox answer4;

    @FXML
    private ImageView image1;

    @FXML
    private ImageView image2;

    @FXML
    private ImageView image3;

    @FXML
    private ImageView image4;
    private List<Answer> answerList;
    private int answerCount;

    @FXML
    private AnchorPane parentPane;
    //không có hình ảnh question , có hình ảnh đáp án
    private Question question;
    public void setQuestion(Question question){
        this.question = question;
        this.answerList = new ArrayList<>(question.getAnswers());
        this.answerCount=answerList.size();
//        setContent(this.question.getMedia().getUrl());
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

}