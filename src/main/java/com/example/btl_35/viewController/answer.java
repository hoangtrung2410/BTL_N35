package com.example.btl_35.viewController;

import com.example.btl_35.entity.Answer;
import com.example.btl_35.entity.Question;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

public class answer {
    @FXML
    TextField answerTextField;
    public void setCurrentQuestion(Question question){
        StringBuilder text = new StringBuilder("Đáp án của câu hỏi là: ");
        List<Answer> answers = new ArrayList<>(question.getAnswers());
        int answerCount = 0;
        for(Answer answer : answers){
            if(answer.isIs_choice()){
                text.append(answerCount > 0 ? ", "+answer.getChoice() : answer.getChoice());
                answerCount++;
            }
        }
        answerTextField.setText(String.valueOf(text));
    }
}
