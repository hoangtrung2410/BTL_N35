package com.example.btl_35.viewController;

import com.example.btl_35.dao.AnswerDao;
import com.example.btl_35.dao.QuestionDao;
import com.example.btl_35.entity.Answer;
import com.example.btl_35.entity.Question;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class gui32b {
    @FXML
    private AnchorPane anchorInfoPane;
    @FXML
    private ScrollPane scrollPane;
    private Question currentQuestion; // for saving
    private List<Answer> answerList;

    // new saveImport solution
    private void setAnswerList(Question question) {
        this.answerList = new ArrayList<>(question.getAnswers());
        this.answerCount = answerList.size();
        for (int i = 0; i < 3; i++) {
            if (i < answerCount) {
                defaultAnswers.add(answerList.get(i));
            } else {
                defaultAnswers.add(null);
            }
        }
        System.out.println(answerCount);
    }

    private int answerCount;
    private List<Answer> defaultAnswers = new ArrayList<>(3);

    //
    public void setCurrentQuestion(Question question) {
        this.currentQuestion = question;
    }

    @FXML
    private Button cancel2;
    @FXML
    private Button savechanges2;
    @FXML
    private Button savechangesandcontinue;
    @FXML
    private TextField questionNameField;
    @FXML
    private TextField questionCategoryField;
    @FXML
    private TextArea questionTextField;
    @FXML
    private TextField questionMarkField;
    @FXML
    private Button returngui11;
    @FXML
    private SVGPath chamthan1;
    @FXML
    private SVGPath chamthan2;
    @FXML
    private SVGPath chamthan3;
    // answer field
    @FXML
    private TextField answerChoice1;
    @FXML
    private ComboBox<String> answerGrade1;
    @FXML
    private TextField answerChoice2;
    @FXML
    private ComboBox<String> answerGrade2;
    @FXML
    private TextField answerChoice3;
    @FXML
    private ComboBox<String> answerGrade3;

    // call in previous GUI
    private void setQuestionName(String questionName) {
        questionNameField.setText(questionName == null ? "" : questionName);
    }

    private void setQuestionCategory(String questionCategory) {
        questionCategoryField.setText(questionCategory);
    }

    private void setQuestionText(String questionText) {
        questionTextField.setText(questionText);
    }

    private void setQuestionMark(Integer questionMark) {
        if (questionMark != null) {
            questionMarkField.setText(questionMark.toString());
        } else {
            questionMarkField.setText("");
        }
    }

    public void setQuestionInfo() {
        setQuestionName(currentQuestion.getName());
        setQuestionMark(currentQuestion.getMark());
        setQuestionCategory(currentQuestion.getCategory().getName());
        setQuestionText(currentQuestion.getText());
    }

    // set textfield for the answer
    private void setAnswerChoice(Answer answer, TextField answerChoice) {
        answerChoice.setText(answer.getChoice());
    }

    // set combobox label for the answer
    private void setAnswerGrade(Answer answer, ComboBox<String> gradeChoice) {
        if (answer.getGrade() != null) {
            String answerGrade = String.format("%.0f%%", answer.getGrade() * 100);
            gradeChoice.setValue(answerGrade);
        } else {
            gradeChoice.setValue("");
        }
    }

    // set the combo box for the answer
    private void setAnswerBox() {
        ObservableList<String> gradeList = FXCollections.observableArrayList(
                "100%", "90%", "83.33333%", "80%", "75%", "70%", "66,.66667%", "60%", "50%", "40%", "33.33333%", "30%", "25%", "20%", "16.66667%", "14.28571%", "12.5%", "11.11111%", "10%",
                "5%", "0%", "-5%", "-10%", "-11.11111%", "-12.5%", "-14.28571%", "-15%", "-16.66667%", "-20%", "-25%", "-30%", "-33.33333%", "-40%",
                "-50%", "-60%", "-70%", "-75%", "-80%", "-83.33333%"
        );
        answerGrade1.setItems(gradeList);
        answerGrade2.setItems(gradeList);
        answerGrade3.setItems(gradeList);
    }

    // IF ELSE TO DIE
    public void setAnswersInfo() {
        setAnswerList(currentQuestion);
        int questionCount = answerList.size();
        if (questionCount == 1) {
            setAnswerGrade(answerList.get(0), answerGrade1);
            setAnswerChoice(answerList.get(0), answerChoice1);
        } else if (questionCount == 2) {
            setAnswerGrade(answerList.get(0), answerGrade1);
            setAnswerChoice(answerList.get(0), answerChoice1);
            setAnswerGrade(answerList.get(1), answerGrade2);
            setAnswerChoice(answerList.get(1), answerChoice2);
        } else if (questionCount == 3) {
            setAnswerGrade(answerList.get(0), answerGrade1);
            setAnswerChoice(answerList.get(0), answerChoice1);
            setAnswerGrade(answerList.get(1), answerGrade2);
            setAnswerChoice(answerList.get(1), answerChoice2);
            setAnswerGrade(answerList.get(2), answerGrade3);
            setAnswerChoice(answerList.get(2), answerChoice3);
        } else {
            System.out.println("Loi hien thi");
            System.out.println("So cau hoi: " + questionCount);
        }
    }

    @FXML
    void returngui11(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("gui11.fxml"));
            Parent gui11Parent = loader.load();
            Scene gui11Scene = new Scene(gui11Parent);
            Stage stage = (Stage) returngui11.getScene().getWindow();
            stage.setScene(gui11Scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void cancel2(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("gui21.fxml"));
            Parent gui21Parent = loader.load();
            Scene gui21Scene = new Scene(gui21Parent);
            Stage stage = (Stage) cancel2.getScene().getWindow();
            stage.setScene(gui21Scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void savechanges2(ActionEvent event) {
        try {
            // save first
            savechangesandcontinue(event);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("gui21.fxml"));
            Parent gui21Parent = loader.load();
            Scene gui21Scene = new Scene(gui21Parent);
            Stage stage = (Stage) savechanges2.getScene().getWindow();
            stage.setScene(gui21Scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void savechangesandcontinue(ActionEvent event) {
        saveInfo();
        System.out.println("question updated success");
    }

    // for 2 buttons save / save and continue
    public void saveInfo() {
        if (defaultAnswers.get(0) != null) {
            AnswerDao.getInstance().delete(defaultAnswers.get(0).getId());
        }
        if (defaultAnswers.get(1) != null) {
            AnswerDao.getInstance().delete(defaultAnswers.get(1).getId());

        }
        if (defaultAnswers.get(2) != null) {
            AnswerDao.getInstance().delete(defaultAnswers.get(2).getId());
        }
        Set<Answer> answers = new HashSet<>();
        if (!answerChoice1.getText().isEmpty()) {
            Answer da1 = createAnswer(answerChoice1.getText(), answerGrade1.getValue());
            da1.setQuestion(currentQuestion);
            answers.add(da1);
        }
        if (!answerChoice2.getText().isEmpty()) {
            Answer da2 = createAnswer(answerChoice2.getText(), answerGrade2.getValue());
            da2.setQuestion(currentQuestion);
            answers.add(da2);
        }
        if (!answerChoice3.getText().isEmpty()) {
            Answer da3 = createAnswer(answerChoice3.getText(), answerGrade3.getValue());
            da3.setQuestion(currentQuestion);
            answers.add(da3);
        }
        int int_value = Integer.parseInt(questionMarkField.getText());
        currentQuestion.setMark(int_value);

        currentQuestion.setAnswers(answers);
        QuestionDao.getInstance().update(currentQuestion);


    }

    public static Answer createAnswer(String choiceText, String gradeInput) {
        Answer answer = new Answer();
        answer.setChoice(choiceText);
        String numberString = gradeInput.replaceAll("%", "");
        double number = Double.parseDouble(numberString) / 100.0;
        answer.setGrade(number);
        boolean boolValue = (number > 0);
        answer.setIs_choice(boolValue);
        return answer;
    }

    @FXML
    void initialize() {
        // Listen to changes in the quiz name field
        questionNameField.textProperty().addListener((observable, oldValue, newValue) -> {
            disablechamthan1(newValue.isEmpty());
        });
        // Listen to changes in the quiz name field
        questionTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            disablechamthan2(newValue.isEmpty());
        });
        // Listen to changes in the quiz name field
        questionMarkField.textProperty().addListener((observable, oldValue, newValue) -> {
            disablechamthan3(newValue.isEmpty());
        });
        setAnswerBox();
        scrollPane.widthProperty().addListener((observable, oldVal, newVal) -> {
            anchorInfoPane.setPrefWidth(newVal.doubleValue());
        });
    }

    private void disablechamthan1(boolean disable) {
        chamthan1.setVisible(disable);
    }

    private void disablechamthan2(boolean disable) {
        chamthan2.setVisible(disable);
    }

    private void disablechamthan3(boolean disable) {
        chamthan3.setVisible(disable);
    }
}
