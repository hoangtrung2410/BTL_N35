package com.example.btl_n35.viewController;

import com.example.btl_n35.dao.QuizDao;
import com.example.btl_n35.entity.Quiz;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Time;
import java.time.*;

public class gui51 {
    @FXML
    private Button createQuiz;
    @FXML
    private DatePicker dateClose;
    @FXML
    private DatePicker dateOpen;
    @FXML
    private ComboBox<Integer> hourClose;
    @FXML
    private ComboBox<Integer> hourLimit;
    @FXML
    private ComboBox<Integer> hourOpen;
    @FXML
    private ComboBox<Integer> minuteClose;
    @FXML
    private ComboBox<Integer> minuteLimit;
    @FXML
    private ComboBox<Integer> minuteOpen;
    @FXML
    private TextArea quizDescription;
    @FXML
    private TextField quizName;
    @FXML
    private Button cancelgui11;
    @FXML
    private CheckBox timelimitcheck;
    @FXML
    private SVGPath chamthan1;
    @FXML
    private SVGPath chamhoi1;
    @FXML
    private SVGPath chamhoi2;
    @FXML
    private SVGPath chamhoi3;
    @FXML
    void createQuiz(ActionEvent event) {
        String name = quizName.getText();
    }

    @FXML
    void timelimitcheck(ActionEvent event) {
        if (timelimitcheck.isSelected()) {
            hourLimit.setDisable(false);
            minuteLimit.setDisable(false);
        } else {
            hourLimit.setDisable(true);
            minuteLimit.setDisable(true);
        }
    }

    @FXML
    public void initialize() {
        ObservableList<Integer> numbers1 = FXCollections.observableArrayList();
        for (int i = 0; i <= 24; i++) {
            numbers1.add(i);
        }
        hourClose.setItems(numbers1);
        hourOpen.setItems(numbers1);
        hourLimit.setItems(numbers1);
        ObservableList<Integer> numbers2 = FXCollections.observableArrayList();
        for (int i = 0; i <= 59; i++) {
            numbers2.add(i);
        }
        minuteOpen.setItems(numbers2);
        minuteClose.setItems(numbers2);
        minuteLimit.setItems(numbers2);
        // Listen to changes in the quiz name field
        quizName.textProperty().addListener((observable, oldValue, newValue) -> {
            disableQuiz(newValue.isEmpty());
            disablechamthan1(newValue.isEmpty());
        });
        minuteOpen.valueProperty().addListener((observable, oldValue, newValue) -> {
            disablechamhoi1(newValue.describeConstable().isEmpty());
        });
        minuteLimit.valueProperty().addListener((observable, oldValue, newValue) -> {
            disablechamhoi2(newValue.describeConstable().isEmpty());
        });
        createQuiz.setOnMouseClicked(event -> {
            try {
                Quiz quiz = new Quiz();
                quiz.setName(quizName.getText());
                quiz.setDescription(quizDescription.getText());
                DateTimeUtils dateTimeUtils = new DateTimeUtils();
                LocalDateTime timeClose = dateTimeUtils.combineToDateTime(dateClose, hourClose, minuteClose);
                LocalDateTime timeOpen = dateTimeUtils.combineToDateTime(dateOpen, hourOpen, minuteOpen);
                if (minuteLimit.getValue() != null && hourLimit.getValue() != null) {
                    LocalTime timeLimit = LocalTime.of(hourLimit.getValue(), minuteLimit.getValue());
                    if (timeOpen == null && timeClose == null) {
                        quiz.setLimit_Time(timeLimit);
                        QuizDao.getInstance().insert(quiz);
                        System.out.println("Thêm quiz thành công");
                        gui51Success();
                    } else if (timeOpen != null && timeClose == null) {
                        quiz.setTimeOpen(timeOpen);
                        quiz.setLimit_Time(timeLimit);
                        QuizDao.getInstance().insert(quiz);
                        System.out.println("Thêm quiz thành công");
                        gui51Success();
                    } else if (timeClose != null && timeOpen != null) {
                        LocalDateTime endTime = LocalDateTime.of(dateOpen.getValue(), timeOpen.toLocalTime())
                                .plusHours(timeLimit.getHour())
                                .plusMinutes(timeLimit.getMinute());
                        if (!endTime.isAfter(timeClose)) { // Check if endTime is not after timeClose
                            quiz.setTimeClose(timeClose);
                            quiz.setTimeOpen(timeOpen);
                            quiz.setLimit_Time(timeLimit);
                            QuizDao.getInstance().insert(quiz);
                            System.out.println("Thêm quiz thành công");
                            gui51Success();
                        } else {
                            System.out.println("Thời gian mở cửa và thời gian giới hạn phải nhỏ hơn hoặc bằng thời gian đóng cửa");
                            gui51Error();
                        }
                    } else {
                        gui51Error();
                    }
                } else if (minuteLimit.getValue() == null && hourLimit.getValue() == null) {
                    if (timeOpen == null && timeClose == null) {
                        QuizDao.getInstance().insert(quiz);
                        System.out.println("Thêm quiz thành công");
                        gui51Success();
                    } else if (timeOpen != null && timeClose == null) {
                        quiz.setTimeOpen(timeOpen);
                        QuizDao.getInstance().insert(quiz);
                        System.out.println("Thêm quiz thành công");
                        gui51Success();
                    } else {
                        gui51Error();
                    }
                } else {
                    gui51Error();
                }
            } catch (Exception e) {
                e.printStackTrace();
                gui51Error();
            }
        });


    }


    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    void cancelgui11(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gui11.fxml"));
        Parent gui11Parent = loader.load();
        Scene gui11Scene = new Scene(gui11Parent);
        Stage stage = (Stage) cancelgui11.getScene().getWindow();
        stage.setScene(gui11Scene);
    }


    public class DateTimeUtils {
        public LocalDateTime combineToDateTime(DatePicker datePicker, ComboBox<Integer> hourObj, ComboBox<Integer> minuteObj) {
            LocalDate dateObj = datePicker.getValue();
            Integer hour = hourObj.getValue();
            Integer minute = minuteObj.getValue();

            if (dateObj == null || hour == null || minute == null) {
                return null; // Or handle the case when there is insufficient information selected
            }

            LocalDateTime localDateTime = LocalDateTime.of(dateObj, LocalTime.of(hour, minute));
            LocalDateTime combinedDateTime = convertUtilToSqlDate(localDateTime);
            return combinedDateTime;
        }

        private LocalDateTime convertUtilToSqlDate(LocalDateTime localDateTime) {
            long milliseconds = localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            return LocalDateTime.ofInstant(Instant.ofEpochMilli(milliseconds), ZoneId.systemDefault());
        }
    }


    public class TimeUtils {
        public Time combineToTime(int hour, int minute) {
            LocalTime localTime = LocalTime.of(hour, minute);
            Time combinedTime = Time.valueOf(localTime);

            return combinedTime;
        }

    }

    private void disablechamthan1(boolean disable) {
        chamthan1.setVisible(disable);
    }
    private void disableQuiz(boolean disable) {
        createQuiz.setDisable(disable);
    }
    private void disablechamhoi1(boolean b){
        chamhoi1.setVisible(b);
    }
    private void disablechamhoi2(boolean c){
        chamhoi2.setVisible(c);
    }
    private void gui51Success() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("gui51Success.fxml"));
            Parent gui51Parent = loader.load();
            Scene gui51Scene = new Scene(gui51Parent);
            Stage stage = (Stage) createQuiz.getScene().getWindow();
            stage.setScene(gui51Scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void gui51Error() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("gui51Error.fxml"));
            Parent gui51Parent = loader.load();
            Scene gui51Scene = new Scene(gui51Parent);
            Stage stage = (Stage) createQuiz.getScene().getWindow();
            stage.setScene(gui51Scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}