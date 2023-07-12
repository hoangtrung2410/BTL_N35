package com.example.btl_35.viewController;

import com.example.btl_35.entity.Category;
import com.example.btl_35.entity.Question;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.util.List;

public class gui31 {

	@FXML
	private CheckBox showall;
	@FXML
	private Button categoriestogui33;
	@FXML
	private Button create;
	@FXML
	private Button importtogui34;
	@FXML
	private Label show1;
	@FXML
	private Label show2;
	@FXML
	private TableView<Question> showquestion;
	@FXML
	private TextField selectcategory;
	@FXML
	private Button returngui11;
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
	void showall(ActionEvent event) {

	}

	@FXML
	void categoriestogui33(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("gui33.fxml"));
			Parent gui33Parent = loader.load();
			Scene gui33Scene = new Scene(gui33Parent);
			Stage stage = (Stage) create.getScene().getWindow();
			stage.setScene(gui33Scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void importtogui34(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("gui34.fxml"));
			Parent gui34Parent = loader.load();
			Scene gui34Scene = new Scene(gui34Parent);
			Stage stage = (Stage) create.getScene().getWindow();
			stage.setScene(gui34Scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void showgui32(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("gui32.fxml"));
			Parent gui32Parent = loader.load();
			Scene gui32Scene = new Scene(gui32Parent);
			Stage stage = (Stage) create.getScene().getWindow();
			stage.setScene(gui32Scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setCategoryName(String categoryName) {
		selectcategory.setText(categoryName);
	}
	public void setCheck(boolean checked) {
		showall.setSelected(checked);
	}
	public void initialize(Category selectedCategory, List<Question> questionList) {
		showall.setDisable(true);

		// Set up the columns in the TableView (assumed column names: "Name", "Category", etc.)
		TableColumn<Question, HBox> questionColumn = new TableColumn<>("");
		TableColumn<Question, Void> editColumn = new TableColumn<>("Action");

		// Set up cell value factories
		questionColumn.setCellValueFactory(cellData -> {
			Question question = cellData.getValue();
			Integer questionId = question.getId();
			String questionName = question.getText();
			String displayText = questionId + ": " + questionName;

			HBox container = new HBox();
			container.setAlignment(Pos.CENTER_LEFT);
			container.setSpacing(10);

			SVGPath icon = new SVGPath();
			icon.setContent("M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z");
			icon.setScaleX(1);
			icon.setScaleY(1);

			Label textLabel = new Label(displayText);
			textLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
			textLabel.setStyle("-fx-padding: 0 0 0 5");
			icon.setStyle("-fx-fill: #3399FF;");
			container.getChildren().addAll(icon, textLabel);

			return new SimpleObjectProperty<>(container);
		});

		// Set up cell factories for editColumn
		Callback<TableColumn<Question, Void>, TableCell<Question, Void>> cellFactory = new Callback<>() {
			@Override
			public TableCell<Question, Void> call(final TableColumn<Question, Void> param) {
				final TableCell<Question, Void> cell = new TableCell<>() {
					private final HBox container = new HBox();
					private final Button editButton = new Button("Edit");
					{
						container.setAlignment(Pos.CENTER);
						container.getChildren().add(editButton);
						editButton.setOnAction(event -> {
							Question selectedQuestion = getTableView().getItems().get(getIndex());
							test(selectedQuestion);
						});
						editButton.setStyle("-fx-background-color: White; -fx-text-fill: #3399FF;-fx-font-family: Arial;-fx-font-size: 16");
					}

					@Override
					protected void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setGraphic(editButton);
						}
					}
				};
				return cell;
			}
		};

		// Add cell factories to columns
		editColumn.setCellFactory(cellFactory);

		// Set preferred widths for columns
		questionColumn.setPrefWidth(723);
		editColumn.setPrefWidth(100);

		// Add columns to the TableView
		showquestion.getColumns().addAll(questionColumn, editColumn);

		// Populate the TableView with the questionList
		showquestion.getItems().addAll(questionList);
	}



	private void test(Question selectedQuestion){
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("gui32b.fxml"));
			Parent gui33Parent = loader.load();
			gui32b controller = loader.getController();
			controller.setCurrentQuestion(selectedQuestion);
			controller.setQuestionInfo();
			controller.setAnswersInfo();
			Scene gui33Scene = new Scene(gui33Parent);
			Stage stage = (Stage) showquestion.getScene().getWindow();
			stage.setScene(gui33Scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
