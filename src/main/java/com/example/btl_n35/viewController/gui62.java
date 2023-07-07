package com.example.btl_n35.viewController;

import com.example.btl_n35.entity.Question;
import com.example.btl_n35.entity.Quiz;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class gui62 {
	@FXML
	private CheckBox tick;
	@FXML
	private SVGPath chamhoi;
	@FXML
	private Button returngui11;
	private Quiz currentQuiz;
	public void setQuiz(Quiz quiz){
		this.currentQuiz = quiz;
		setQuizName();
		setUpQuiz62();
		setupInfo();
	}

	@FXML
	private TableView<Question> showquestion; // hien thi string : id + text
	@FXML
	private  TableColumn<Question, String> info; //
	private void setUpQuiz62(){
		this.questions = new ArrayList<>(currentQuiz.getListQuestionQuiz());
		System.out.println(questions);
		// Định dạng hiển thị của cột nameColumn
		info.setCellFactory(column -> {
			TableCell<Question, String> cell = new TableCell<Question, String>() {
				@Override
				protected void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					if (item != null) {
						SVGPath icon = new SVGPath();
						icon.setContent("M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z");
						// Thiết lập kích thước của biểu tượng
						icon.setScaleX(1);
						icon.setScaleY(1);
						// Đặt biểu tượng vào ô cell
						setGraphic(icon);
						icon.setStyle("-fx-fill: #3399FF;");
						// Đặt text cho ô cell
						setText(item);
						setFont(Font.font("Arial", FontWeight.NORMAL,16));
						setStyle("-fx-padding: 10,0,0,10");

					} else {
						setText(null);
					}
				}
			};
			return cell;
		});
		info.setPrefWidth(1920);
		info.setStyle("-fx-background-color: white;-fx-font-size:14;-fx-font-weight:normal;");
		info.setCellValueFactory(cellData -> {
			Question question = cellData.getValue();
			Integer questionId = question.getId();
			String questionName = question.getText();
			String questionMark = "\0";
			if (question.getMark() == null){
				questionMark = "0";
			} else {
				questionMark = question.getMark().toString();
			}
			String displayText = questionId + ": " + questionName + "(" + questionMark + ")";
			return new SimpleStringProperty(displayText);
		});

		// Set up column header
		// Add the column to the TableView
		showquestion.getColumns().clear();
		showquestion.getColumns().add(info);
		showquestion.setStyle("-fx-background-color: white");
		// Populate the TableView with the questionList
		showquestion.getItems().addAll(questions);
	}
	private void setQuizName() {
		tenquiz.setText(currentQuiz.getName());
		tenquiz2.setText(currentQuiz.getName());
	}
	private List<Question> questions;
	@FXML
	private Label tenquiz;
	@FXML
	private Button tenquiz2;
	@FXML
	private Label totalMark;

	@FXML
	private Label questionCount;
	private void setupInfo(){
		int totalMark = 0;
		for(Question question : currentQuiz.getListQuestionQuiz()){
			if(question.getMark() != null){
				totalMark += question.getMark();
			}
		}
		this.totalMark.setText(Integer.toString(totalMark));
		int questionCount = currentQuiz.getListQuestionQuiz().size();
		this.questionCount.setText(Integer.toString(questionCount));
	}

	@FXML
	void REPAGINATE(ActionEvent event) {

	}

	@FXML
	void SAVE(ActionEvent event) {

	}

	@FXML
	void SELECT(ActionEvent event) {

	}


	@FXML
	void fromQuestionBank(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("gui63.fxml"));
			Parent gui62 = loader.load();
			gui63 controller = loader.getController();
			controller.setCurrentQuiz(currentQuiz);
			Scene gui62Scene = new Scene(gui62);
			Stage stage = (Stage) returngui11.getScene().getWindow();
			stage.setScene(gui62Scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
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
	void randomQuestion(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("gui65.fxml"));
			Parent gui62 = loader.load();
			gui65 controller = loader.getController();
			controller.setCurrentQuiz(currentQuiz);
			Scene gui62Scene = new Scene(gui62);
			Stage stage = (Stage) returngui11.getScene().getWindow();
			stage.setScene(gui62Scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
	void initialize(){

	}
	@FXML
	void clicktick(ActionEvent event) {
		if(tick.isSelected()){
			disablechamhoi(false);
			System.out.println("click");
		}
		else {
			disablechamhoi(true);
			System.out.println("no");
		}
	}

	@FXML
	void returngui61(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("gui61.fxml"));
			Parent gui61Parent = loader.load();
			gui61 controller = loader.getController();
			controller.setQuiz(currentQuiz);
			controller.setUpQuiz();
			Scene gui61Scene = new Scene(gui61Parent);
			Stage stage = (Stage) tenquiz2.getScene().getWindow();
			stage.setScene(gui61Scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void disablechamhoi(boolean b){
		chamhoi.setVisible(b);
	}

}
