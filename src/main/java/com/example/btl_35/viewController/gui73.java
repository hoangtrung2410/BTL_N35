package com.example.btl_35.viewController;

import com.example.btl_35.entity.Answer;
import com.example.btl_35.entity.Question;
import com.example.btl_35.entity.Quiz;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class gui73 {

    @FXML
    private GridPane gridPane;
    private Map<CheckBox, Answer> map = new HashMap<>();
    private String quizName;
    private Quiz currentQuiz;
    public void setQuiz(Quiz quiz){
        this.currentQuiz = quiz;
    }
    private int remainingSeconds;
    private LocalTime startTime;
    public void setUpQuiz(LocalTime startTime){
        timeLimitSetup(currentQuiz);
        setQuizName(currentQuiz.getName());
        if (currentQuiz.getLimit_Time() != null) {
            this.remainingSeconds = currentQuiz.getLimit_Time().toSecondOfDay(); // set thoi gian}
        } else {
            this.remainingSeconds = 60;
        }

        this.questions = new ArrayList<>(currentQuiz.getListQuestionQuiz());
        // goi ham initializeQuestion
        initializeQuestions();

        // set start time cua quiz
        this.startTime = startTime;
    }
    private void setQuizName(String quizName) {
        this.quizName = quizName;
        quiz.setText(quizName);
    }
    private List<Question> questions;
    @FXML
    private Button quiz;
    @FXML
    private VBox questionContainer;
    @FXML
    private Label quiz1;
    @FXML
    public Label quiztime;
    @FXML
    private ScrollPane quizPane;
    @FXML
    private AnchorPane anchorInfoPane;
    @FXML
    private Button finishQuiz;
    @FXML
    private Button backHome;

    private Timeline timeline;

    public void initialize() {
        backHome.setVisible(false);
        quizPane.widthProperty().addListener((observable, oldVal, newVal) ->{
            anchorInfoPane.setPrefWidth(newVal.doubleValue());
            if(questionContainer.getChildren().size() > 0){
                for(Node pane : questionContainer.getChildren()){
                    AnchorPane anchorPane = (AnchorPane) pane;
                    anchorPane.setPrefWidth(newVal.doubleValue()-15);
                }
            }
        });
    }

    private void initializeQuestions() {
        // set up cho tung cau hoi
        for (int i = 0; i < questions.size(); i++) {
            setUpQuestion(i);
            Button button = new Button("" + (i + 1));
            button.setPrefWidth(27);
            button.setPrefHeight(27);
            button.setBackground(null);
            BorderStroke borderStroke = new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null);
            Border border = new Border(borderStroke);
            button.setBorder(border);
            button.setFont(new Font(8));
            button.setOnAction(event -> {
                int questionIndex = Integer.parseInt(((Button)event.getSource()).getText()) - 1;

                // get the question pane at the specified index
                Node questionPane = questionContainer.getChildren().get(questionIndex);

                // get the height of the view above the question pane
                double viewHeight = quizPane.getChildrenUnmodifiable().get(0).lookup("#markLabel") == null ? 20.0 : 240.0;

                // convert the coordinates of the question pane to the coordinates of the scroll pane
                Bounds questionBounds = questionPane.localToParent(questionPane.getBoundsInLocal());
                Bounds scrollBounds = quizPane.getContent().localToParent(quizPane.getBoundsInLocal());
                double questionY = questionBounds.getMinY() - scrollBounds.getMinY() + viewHeight;

                // scroll to the top of the question pane
                double vvalue = questionY / quizPane.getContent().getBoundsInLocal().getHeight();
                quizPane.setVvalue(vvalue);
            });
            gridPane.add(button, i % 5, i / 5);
        }
    }

    @FXML
    void finish(ActionEvent event) {
        finish2();
    }
//    private Double calculateMark(Answer Answer1, Answer Answer2, Answer Answer3,Answer Answer4, Question question){
//        double grade1 = (Answer1 == null ? 0.0 : Answer1.getGrade());
//        double grade2 = (Answer2 == null ? 0.0 : Answer2.getGrade());
//        double grade3 = (Answer3 == null ? 0.0 : Answer3.getGrade());
//        double grade4 = (Answer4 == null ? 0.0 : Answer4.getGrade());
//        double grade;
//        if(grade1 >= 0 && grade2 >= 0 && grade3 >= 0 && grade4 >= 0){
//            grade = grade1 + grade2 + grade3 + grade4;
//        } else {
//            grade = (grade1 < 0 ? grade1 : 0.0) + (grade2 < 0 ? grade2 : 0.0) + (grade3 < 0 ? grade3 : 0.0)+ (grade4 < 0 ? grade4 : 0.0);
//        }
//        return (question.getMark() == null ? 1.00 : question.getMark()) * grade;
//    }

    private Double calculateMark(Answer Answer1, Answer Answer2, Answer Answer3,Answer Answer4, Question question){

        List<Answer> answers = new ArrayList<>(4);

        answers.add(Answer1); answers.add(Answer2); answers.add(Answer3); answers.add(Answer4);

        double grade1 = (Answer1 == null ? 0.0 : Answer1.getGrade());

        double grade2 = (Answer2 == null ? 0.0 : Answer2.getGrade());

        double grade3 = (Answer3 == null ? 0.0 : Answer3.getGrade());

        double grade4 = (Answer4 == null ? 0.0 : Answer4.getGrade());

        double grade = 0.0;

        int wrongAnswers = 0;

        for(Answer answer : answers){

            if (answer != null && answer.getGrade() <= 0){

                wrongAnswers += 1;

            }

        }

        if (wrongAnswers == 0){

            for(Answer answer : answers){

                if (answer != null){

                    grade += answer.getGrade();

                }

            }

        } else {

            for(Answer answer : answers){

                if (answer != null && answer.getGrade() <= 0){

                    grade += answer.getGrade();

                }
            }
        }
        return (question.getMark() == null ? 1.00 : question.getMark()) * grade;
    }
    private Answer processCheckBox(CheckBox checkBox){
        Answer answer = null;
        if (map.get(checkBox) != null && checkBox.isSelected()){
            answer = map.get(checkBox);
        }
        return answer;
    }
    private void setUpQuestion(int i) {
        List<Answer> answers = new ArrayList<>(questions.get(i).getAnswers());

//        if (questions.get(i).getMedia() == null && answers.get(1).getMedia() == null ) {
//            FXMLLoader questionLoader = new FXMLLoader(getClass().getResource("guiquestion.fxml"));
//            AnchorPane questionPane1 = makeDefaultGUI(i, questionLoader);
//            questionContainer.getChildren().add(questionPane1);
//        } else if (questions.get(i).getMedia() != null && answers.get(1).getMedia() == null ){
//            FXMLLoader questionLoader = new FXMLLoader(getClass().getResource("guiquestion2.fxml"));
//            AnchorPane questionPane2 = makeDefaultGUI(i, questionLoader);
//            insertMedia(questions.get(i), questionPane2, questionLoader);
//            questionContainer.getChildren().add(questionPane2);
//        }
//        else if  (questions.get(i).getMedia() == null && answers.get(1).getMedia() != null ){
//            FXMLLoader questionLoader = new FXMLLoader(getClass().getResource("guiquestion3.fxml"));
//            AnchorPane questionPane3 = makeDefaultGUI(i, questionLoader);
//            guiquestion3 controller = questionLoader.getController();
//            controller.setImageViews(answers);
//            //cần hàm để đưa hình ảnh vào đáp án
//            questionContainer.getChildren().add(questionPane3);
//
//        }
//        else if (questions.get(i).getMedia() != null && answers.get(1).getMedia() != null ){
//            FXMLLoader questionLoader = new FXMLLoader(getClass().getResource("guiquestion4.fxml"));
//            AnchorPane questionPane4 = makeDefaultGUI(i, questionLoader);
//            // cần hàm  đap án
//            guiquestion4 controller = questionLoader.getController();
//            controller.setImageViews(answers);
//            insertMedia2(questions.get(i), questionPane4, questionLoader);
//            questionContainer.getChildren().add(questionPane4);
//        }

        if(answers.get(0).getMedia() == null){
            if(questions.get(i).getMedia() == null){
                FXMLLoader questionLoader = new FXMLLoader(getClass().getResource("guiquestion.fxml"));
             AnchorPane questionPane1 = makeDefaultGUI(i, questionLoader);
            questionContainer.getChildren().add(questionPane1);

            }
            else{
                FXMLLoader questionLoader = new FXMLLoader(getClass().getResource("guiquestion2.fxml"));
            AnchorPane questionPane2 = makeDefaultGUI(i, questionLoader);
            insertMedia(questions.get(i), questionPane2, questionLoader);
            questionContainer.getChildren().add(questionPane2);

            }
        }else{
            if(questions.get(i).getMedia() == null){
                FXMLLoader questionLoader = new FXMLLoader(getClass().getResource("guiquestion3.fxml"));
            AnchorPane questionPane3 = makeDefaultGUI(i, questionLoader);
            guiquestion3 controller = questionLoader.getController();
            controller.setImageViews(answers);
            //cần hàm để đưa hình ảnh vào đáp án
            questionContainer.getChildren().add(questionPane3);

            }
            else{
                FXMLLoader questionLoader = new FXMLLoader(getClass().getResource("guiquestion4.fxml"));
            AnchorPane questionPane4 = makeDefaultGUI(i, questionLoader);
            // cần hàm  đap án
            guiquestion4 controller = questionLoader.getController();
            controller.setImageViews(answers);
            insertMedia2(questions.get(i), questionPane4, questionLoader);
            questionContainer.getChildren().add(questionPane4);

            }
        }
    }
    private AnchorPane makeDefaultGUI(int i, FXMLLoader questionLoader){
        try {
            // Tạo một AnchorPane mới bằng cách tải tệp FXML
            AnchorPane questionPane = questionLoader.load();

            // Lấy các Label trong AnchorPane
            Label questionContent = (Label) questionPane.lookup("#questionContent");
            Label questionNumber = (Label) questionPane.lookup("#questionNumber");

            // Lay cac Check Box
            List<CheckBox> checkBoxList = new ArrayList<>(4);
            CheckBox answer1 = (CheckBox) questionPane.lookup("#answer1");
            CheckBox answer2 = (CheckBox) questionPane.lookup("#answer2");
            CheckBox answer3 = (CheckBox) questionPane.lookup("#answer3");
            CheckBox answer4 = (CheckBox) questionPane.lookup("#answer4");
            checkBoxList.add(answer1);
            checkBoxList.add(answer2);
            checkBoxList.add(answer3);
            checkBoxList.add(answer4);

            // Đặt nội dung và số thứ tự của câu hỏi trong các Label
            questionContent.setText(questions.get(i).getText());
            questionNumber.setText(String.valueOf(i + 1));

            // Dat noi dung va hien thi checkbox
            List<Answer> answers = new ArrayList<>(questions.get(i).getAnswers());
            int answerCount = answers.size();
            if (answerCount < 4){
                // 0, 1, 2
                // neu so cau hoi it hon 3 thi an checkbox thua
                for(int j = answerCount; j<4; j++){
                    checkBoxList.get(j).setVisible(false);
                }
            }
            for (int j = 0; j<answerCount; j++){
                checkBoxList.get(j).setText(answers.get(j).getChoice());
                map.put(checkBoxList.get(j), answers.get(j));
            }

            // Thêm AnchorPane mới vào giao diện người dùng
            // (ví dụ: vào một VBox có tên là "questionContainer")
            return questionPane;
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Loi : makeDefaultGUI");
        return null;
    }

    private  void insertMedia(Question question, AnchorPane questionPane2, FXMLLoader loader2){
        guiquestion2 controller = loader2.getController();
        MediaView mediaView = (MediaView) questionPane2.lookup("#mediaView");
        ImageView imageView = (ImageView) questionPane2.lookup("#imageView");
        ImageView gifView = (ImageView) questionPane2.lookup("#gifView");
        controller.setQuestion(question);
    }
    private  void insertMedia2(Question question, AnchorPane questionPane2, FXMLLoader loader4){
        guiquestion4 controller = loader4.getController();
        MediaView mediaView = (MediaView) questionPane2.lookup("#mediaView");
        ImageView imageView = (ImageView) questionPane2.lookup("#imageView");
        ImageView gifView = (ImageView) questionPane2.lookup("#gifView");
        controller.setQuestion(question);
    }

    @FXML
    private void backHome(Event event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("gui11.fxml"));
            Parent gui11Parent = loader.load();
            Scene gui11Scene = new Scene(gui11Parent);
            Stage stage = (Stage) finishQuiz.getScene().getWindow();
            stage.setScene(gui11Scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void finish2(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác nhận nạp bài");
        alert.setHeaderText(null);
        alert.setContentText("Bạn có muốn nạp bài không?");

        ButtonType okButton = new ButtonType("Ok");
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(okButton, cancelButton);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == okButton) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("finishpane.fxml"));
                AnchorPane finishpane = loader.load();
                // stop thoi gian
                if (currentQuiz.getLimit_Time() != null) {
                    timeline.stop();
                }
                quizPane.setVvalue(0.0);
                // Cham diem
                finishpane controller = loader.getController();
                // get finish time
                LocalTime finishLocalTime = LocalTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                String finishTime = finishLocalTime.format(formatter);
                // get startTime
                String startTime = this.startTime.format(formatter);
                // get time taken
                java.time.Duration duration = java.time.Duration.between(this.startTime, finishLocalTime);
                LocalTime localTime = LocalTime.ofNanoOfDay(duration.toNanos());
                String timeTaken = localTime.format(formatter);
                // calculate mark
                Double quizMark = 0.0;
                for (Question question : questions) {
                    if (question.getMark() != null) {
                        quizMark += question.getMark();
                    } else {
                        quizMark += 1.0;
                    }
                }
                double finalMark = 0.0;
                List<Double> checks =new ArrayList<>();
                for (int i = 0; i < questions.size(); i++) {
                    double grade;
                    int answerCount = 0;
                    // i as index of question in the questions list
                    // with each question, get the i-th anchor pane contain the answer
                    AnchorPane currentPane = (AnchorPane) questionContainer.getChildren().get(i);
                    Question currentQuestion = questions.get(i);
                    // now we check the box and get checked answer
                    CheckBox answer1 = (CheckBox) currentPane.lookup("#answer1");
                    CheckBox answer2 = (CheckBox) currentPane.lookup("#answer2");
                    CheckBox answer3 = (CheckBox) currentPane.lookup("#answer3");
                    CheckBox answer4 = (CheckBox) currentPane.lookup("#answer4");
                    Answer Answer1 = processCheckBox(answer1);
                    Answer Answer2 = processCheckBox(answer2);
                    Answer Answer3 = processCheckBox(answer3);
                    Answer Answer4 = processCheckBox(answer4);
                    grade = calculateMark(Answer1, Answer2, Answer3, Answer4, currentQuestion);
                    checks.add(grade);
                    // now calculate the mark for each question
                    finalMark += calculateMark(Answer1, Answer2, Answer3, Answer4, currentQuestion);
                }
                finalMark = ((int)(finalMark * 10)) / 10.0;
                String mark = finalMark + "/" + quizMark;

                // calculate grade
                double gradee = (double) Math.round(finalMark / quizMark * 100) / 10;
                String grade = Double.toString(gradee) + "/10.0";

                controller.setInfo(finishTime, startTime, timeTaken, grade, mark);

                questionContainer.getChildren().add(0, finishpane);
                finishQuiz.setVisible(false);
                backHome.setVisible(true);
                for (int i = 0; i < questions.size(); i++) {
                    int index = 2 * i + 2;
                    FXMLLoader newloader = new FXMLLoader(getClass().getResource("answer.fxml"));
                    AnchorPane answerPane = newloader.load();
                    if(checks.get(i) >0.0){
                        answerPane.setStyle("-fx-background-color: rgb(0, 128, 0);");

                    }else{
                        answerPane.setStyle("-fx-background-color: rgb(255, 204, 153);");

                    }
                    answer newcontroller = newloader.getController();
                    newcontroller.setCurrentQuestion(questions.get(i));
                    questionContainer.getChildren().add(index, answerPane);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void timeLimitSetup(Quiz quiz){
        if(quiz.getLimit_Time() != null){
            // set up time
            quiztime.setText(String.format("%02d:%02d:%02d", remainingSeconds / 3600, (remainingSeconds % 3600) / 60, remainingSeconds % 60));
            timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    remainingSeconds--;
                    quiztime.setText(String.format("%02d:%02d:%02d", remainingSeconds / 3600, (remainingSeconds % 3600) / 60, remainingSeconds % 60));
                    if (remainingSeconds <= 0) {
                        timeline.stop();
                        finish2();
                    }
                }
            }));
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();
        } else {
            // set thoi gian mac dinh
            System.out.println("Quiz time null");
            quiztime.setText("00:00:00");
        }
    }
}