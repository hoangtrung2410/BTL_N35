package com.example.btl_35.viewController;

import com.example.btl_35.dao.CategoryDao;
import com.example.btl_35.entity.Category;
import com.example.btl_35.entity.Question;
import com.example.btl_35.entity.Quiz;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class gui63 {
	@FXML
	private Button cancel;
	@FXML
	private AnchorPane anchorInfoPane;
	@FXML
	private ScrollPane scrollPane;
	private Quiz currentQuiz;
	public void setCurrentQuiz(Quiz quiz){
		this.currentQuiz = quiz;
	}
	@FXML
	private Button addQuestion;

	@FXML
	private CheckBox alsoold;

	@FXML
	private CheckBox alsoquestion1;

	@FXML
	private TreeView<String> showcategory;
	private boolean alsoquestion1Checked;
	private List<Question> questionList; // Updated variable
	@FXML
	void addSelectedQuestion(ActionEvent event) {

	}
	@FXML
	void alsoquestion(ActionEvent event) {

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
			Stage stage = (Stage) cancel.getScene().getWindow();
			stage.setScene(gui61Scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
	void initialize(){
		List<Category> categories = CategoryDao.getInstance().selectALl();
		Map<Integer, TreeItem<String>> idToTree = new HashMap<>();
		TreeItem<String> root = new TreeItem<>("Top for IT");
		for (Category category : categories) {
			int id = category.getId();
			String name = category.getName();
			String displayName = name;
			int questionCount = category.getQuestionCount();
			int parentID;
			if (questionCount > 0) {
				displayName += "(" + questionCount + ")";
			} else {
				continue;
			}
			if (category.getParent() != null) {
				parentID = category.getParent().getId();
			} else {
				parentID = 0;
			}
			TreeItem<String> item = new TreeItem<>(displayName);
			idToTree.put(id, item);
			if (parentID == 0) {
				root.getChildren().add(item);
			} else {
				TreeItem<String> parentItem = idToTree.get(parentID);
				parentItem.getChildren().add(item);
			}
		}
		showcategory.setRoot(root);
		showAllNodes(root);
		showcategory.setOnMouseClicked(event -> {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("gui63v2.fxml"));
				Parent gui63v2Parent = loader.load();
				Scene gui63v2Scene = new Scene(gui63v2Parent);
				gui63v2 controller = loader.getController();
				controller.setCurrentQuiz(currentQuiz);
				// Pass the selected category and its questions to the gui31 controller
				TreeItem<String> selected = showcategory.getSelectionModel().getSelectedItem();
				if (selected != null) {
					String categoryName = selected.getValue();
					System.out.println("Selected category: " + categoryName);
					controller.setCategoryName(categoryName);
					int selectedId = 0;
					for (Map.Entry<Integer, TreeItem<String>> entry : idToTree.entrySet()) {
						if (entry.getValue() == selected) {
							selectedId = entry.getKey();
							break;
						}
					}
					Category selectedCategory = CategoryDao.getInstance().selectById(selectedId);
					alsoquestion1Checked = alsoquestion1.isSelected();
					controller.setCheck(alsoquestion1Checked);
					// Move the contents of the alsoquestion method here
					if (alsoquestion1.isSelected()) {
						// Use getAllQuestionsByCategoryId
						questionList = CategoryDao.getInstance().getAllQuestionsByCategoryId(selectedId);
					} else {
						// Use getQuestionByCategoryID
						questionList = CategoryDao.getInstance().getQuestionByCategoryID(selectedId);
					}

					controller.showQuestion(selectedCategory, questionList);
				}

				Stage stage = (Stage) showcategory.getScene().getWindow();
				stage.setScene(gui63v2Scene);
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		// Thêm CSS để item có font chữ Arial
		showcategory.setCellFactory(treeView -> {
			TreeCell<String> cell = new TreeCell<String>() {
				@Override
				protected void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					if (item != null) {
						setText(item);
						setStyle("-fx-font-family: Arial;-fx-font-size: 16");
					} else {
						setText(null);
						setStyle(null);
					}
				}
			};
			return cell;
		});
		scrollPane.widthProperty().addListener((observable, oldVal, newVal) ->{
			anchorInfoPane.setPrefWidth(newVal.doubleValue());
		});
	}
	private void showAllNodes(TreeItem<String> item) {
		item.setExpanded(true);
		for (TreeItem<String> child : item.getChildren()) {
			showAllNodes(child);
		}
	}
}
