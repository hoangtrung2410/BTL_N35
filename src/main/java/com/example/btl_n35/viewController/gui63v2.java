package com.example.btl_n35.viewController;

import com.example.btl_n35.dao.QuizDao;
import com.example.btl_n35.entity.Category;
import com.example.btl_n35.entity.Question;
import com.example.btl_n35.entity.Quiz;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class gui63v2 {
    @FXML
    private Button cancel;
    private Quiz currentQuiz;
    public void setCurrentQuiz(Quiz quiz){
        this.currentQuiz = quiz;

    }
    private Set<Quiz> setQuiz = new HashSet<>();
    private Set<Question> questionsAdd = new HashSet<>();
    @FXML
    private Button addQuestion;

    @FXML
    private TextField selectcategory;

    @FXML
    private GridPane showQuestionGridPane;

    @FXML
    private CheckBox showall;

    @FXML
    private TableView<Question> showquestion;
    TableColumn<Question, String> idColumn = new TableColumn<Question, String>("Id");
    TableColumn<Question, CheckBox> checkbox = new TableColumn<Question, CheckBox>("Add");
    TableColumn<Question, String> nameColumn = new TableColumn<Question, String>();


    @FXML
    void showall(ActionEvent event) {

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

    public void setCategoryName(String categoryName) {
        selectcategory.setText(categoryName);
    }

    public void setCheck(boolean checked) {
        showall.setSelected(checked);
    }

    public void showQuestion(Category selectedCategory, List<Question> questionList) {
        showall.setDisable(true);
        // Set up the columns in the TableView (assumed column names: "Name", "Category", etc.)
        TableColumn<Question, String> questionColumn = new TableColumn<>("");
        // Định dạng hiển thị của cột nameColumn
        questionColumn.setCellFactory(column -> {
            TableCell<Question, String> cell = new TableCell<Question, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item != null) {
                        HBox container = new HBox();
                        container.setAlignment(Pos.CENTER_LEFT);
                        container.setSpacing(10);
                        // Tạo checkbox không có nội dung
                        CheckBox checkBox = new CheckBox();
                        checkBox.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                        checkBox.setMinWidth(1);
                        checkBox.setMaxWidth(1);
                        checkBox.setPrefWidth(1);


                        // Add event listener to the checkbox
                        checkBox.setOnAction(event -> {
                            if (checkBox.isSelected()) {
                                Question question = getTableRow().getItem();
                                question.setListQuizQuestion(setQuiz);
                                questionsAdd.add(question);
                            } else {
                                Question question = getTableRow().getItem();
                                if(questionsAdd.contains(question)){
                                    questionsAdd.remove(question);
                                }
                            }
                        });


                        // Tạo biểu tượng SVGPath
                        SVGPath icon = new SVGPath();
                        icon.setContent("M2 2.5a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5V3a.5.5 0 0 0-.5-.5H2zM3 3H2v1h1V3z M5 3.5a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9a.5.5 0 0 1-.5-.5zM5.5 7a.5.5 0 0 0 0 1h9a.5.5 0 0 0 0-1h-9zm0 4a.5.5 0 0 0 0 1h9a.5.5 0 0 0 0-1h-9z M1.5 7a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5H2a.5.5 0 0 1-.5-.5V7zM2 7h1v1H2V7zm0 3.5a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5H2zm1 .5H2v1h1v-1z");

                        // Thiết lập kích thước của biểu tượng
                        icon.setScaleX(1);
                        icon.setScaleY(1);

                        // Đặt biểu tượng và checkbox vào container
                        container.getChildren().addAll(checkBox, icon);

                        // Đặt container vào ô cell
                        setGraphic(container);
                        container.setStyle("-fx-fill: black;");

                        // Đặt text cho ô cell
                        setText(item);
                        setFont(Font.font("Arial", FontWeight.NORMAL, 16));
                        setStyle("-fx-padding: 10,0,0,10");
                    } else {
                        setText(null);
                    }
                }
            };
            return cell;
        });

        questionColumn.setPrefWidth(826);
        questionColumn.setStyle("-fx-background-color: white;-fx-font-size:14;-fx-font-weight:normal;");
        questionColumn.setCellValueFactory(cellData -> {
            Question question = cellData.getValue();
            Integer questionId = question.getId();
            String questionName = question.getText();
            String displayText = "  " + questionId + ": " + questionName;
            return new SimpleStringProperty(displayText);
        });

        // Set up column header
        // Add the column to the TableView
        showquestion.getColumns().clear();
        showquestion.getColumns().add(questionColumn);
        showquestion.setStyle("-fx-background-color: white");

        // Populate the TableView with the questionList
        showquestion.getItems().addAll(questionList);
    }

    @FXML
    void addSelectedQuestion(ActionEvent event) {
        System.out.println("Adding questions...");
        if(currentQuiz.getListQuestionQuiz().size() == 0) {
            currentQuiz.getListQuestionQuiz().addAll(questionsAdd);
            Alert();
        } else {
            System.out.println("Quiz da co cau hoi, vui long khong them cau hoi moi!");
            Alert2();
            return;
        }
        try{
            QuizDao.getInstance().update(currentQuiz);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    private void reloadGUI() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("gui61.fxml"));
            Parent gui61Parent = loader.load();
            gui61 controller = loader.getController();
            controller.setQuiz(currentQuiz);
            controller.setUpQuiz();
            Scene gui61Scene = new Scene(gui61Parent);
            Stage stage = (Stage) addQuestion.getScene().getWindow();
            stage.setScene(gui61Scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void Alert(){
        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setTitle("So cau hoi da them: " + currentQuiz.getListQuestionQuiz().size());
        successAlert.setHeaderText(null);
        successAlert.setContentText("Thêm câu hỏi thành công, click OK để tiếp tục");
        successAlert.showAndWait();
        reloadGUI();
    }

    private void Alert2(){
        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setTitle("Quiz đã có câu hỏi");
        successAlert.setHeaderText(null);
        successAlert.setContentText("Quiz đã có câu hỏi, click OK để tiếp tục");
        successAlert.showAndWait();
        reloadGUI();
    }
}