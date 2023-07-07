package com.example.btl_35.viewController;

import com.example.btl_35.entity.Quiz;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class gui61 {
	private Quiz currentQuiz;
	public void setQuiz(Quiz quiz){
		this.currentQuiz = quiz;
	}
	public void setUpQuiz(){
		setQuizName(currentQuiz.getName());
		setTimelimit(currentQuiz.getLimit_Time());
	}

	@FXML
	private SVGPath question_bank;
	@FXML
	private Button returngui11;

	@FXML
	private Label quizNameLabel;
	@FXML
	private Button quizNameLabel2;
	private String quizName; // Variable to store the quiz name
	@FXML
	private Label timelimit;
	@FXML
	private MenuButton gui62b;
	@FXML
	private Button previewquiznow;
	@FXML
	private SVGPath gui62b2;

	@FXML
	void gui62b(MouseEvent event) throws IOException{
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("gui62.fxml"));
			Parent gui62Parent = loader.load();
			Scene gui62Scene = new Scene(gui62Parent);
			gui62 controller = loader.getController();
			controller.setQuiz(currentQuiz);
			Stage stage = (Stage) gui62b.getScene().getWindow();
			stage.setScene(gui62Scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void setQuizName(String quizName) {
		this.quizName = quizName;
		quizNameLabel.setText(quizName);
		quizNameLabel2.setText(quizName);
	}
	private void setTimelimit(LocalTime time) {
// Định dạng thời gian thành chuỗi theo định dạng "HH:mm:ss"
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		String timeString = "\0";
		if(time != null){timeString = time.format(formatter);}
		else {
			timeString = "\0";
		}
// Đặt chuỗi thời gian làm nội dung cho label
		timelimit.setText(timeString);
	}

	@FXML
	void previewquiznow1(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("gui72.fxml"));
			Parent gui72Parent = loader.load();
			gui72 controller = loader.getController();
			controller.setQuiz(currentQuiz);
			Scene gui72Scene = new Scene(gui72Parent);
			Stage stage = (Stage) previewquiznow.getScene().getWindow();
			stage.setScene(gui72Scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void questionClick(MouseEvent event) {

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
	int timeLimitInSeconds;
	private int getTimeLimitInSeconds() {
// Đọc thời gian giới hạn từ timelimit Label và chuyển đổi thành giây
		LocalTime timeLimit = LocalTime.parse(timelimit.getText());
		timeLimitInSeconds = timeLimit.toSecondOfDay();
		return timeLimitInSeconds;
	}
}