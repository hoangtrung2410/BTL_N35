package com.example.btl_35.viewController;

import com.example.btl_35.dao.QuizDao;
import com.example.btl_35.entity.Category;
import com.example.btl_35.entity.Question;
import com.example.btl_35.entity.Quiz;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class gui65v2 {
    @FXML
    private Button save;
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
    private ComboBox<Integer> randomNumber;
    @FXML
    private TextField selectcategory;
    @FXML
    private CheckBox showall;
    @FXML
    private Pagination pagination;
    public void setCategoryName(String categoryName) {
        selectcategory.setText(categoryName);
    }
    public void setCheck(boolean checked) {
        showall.setSelected(checked);
    }
    private ObservableList<Question> questionData = FXCollections.observableArrayList();
    private int itemsPerPage = 10;
    public void initialize(Category selectedCategory, List<Question> questionList) {
        ObservableList<Integer> numbers1 = FXCollections.observableArrayList();
        for (int i = 1; i <= selectedCategory.getQuestionCount(); i++) {
            numbers1.add(i);
        }
        randomNumber.setItems(numbers1);
        showall.setDisable(true);
        // Set up the question data
        questionData.addAll(questionList);
        // Set up the pagination control
        int pageCount = (questionData.size() / itemsPerPage) + 1;
        pagination.setPageCount(pageCount);
        pagination.setCurrentPageIndex(0);
        pagination.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer pageIndex) {
                VBox pageBox = new VBox();
                pageBox.setSpacing(10);
                int startIndex = pageIndex * itemsPerPage;
                int endIndex = Math.min(startIndex + itemsPerPage, questionData.size());

                for (int i = startIndex; i < endIndex; i++) {
                    Question question = questionData.get(i);
                    TextFlow questionFlow = new TextFlow();
                    questionFlow.setStyle("-fx-padding: 10,0,0,10; -fx-border-color: #CCCCCC; -fx-border-width: 1 0 0 0;");
                    // Create an SVGPath
                    SVGPath svgPath = new SVGPath();
                    svgPath.setContent("M2 2.5a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5V3a.5.5 0 0 0-.5-.5H2zM3 3H2v1h1V3z M5 3.5a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9a.5.5 0 0 1-.5-.5zM5.5 7a.5.5 0 0 0 0 1h9a.5.5 0 0 0 0-1h-9zm0 4a.5.5 0 0 0 0 1h9a.5.5 0 0 0 0-1h-9z M1.5 7a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5H2a.5.5 0 0 1-.5-.5V7zM2 7h1v1H2V7zm0 3.5a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5H2zm1 .5H2v1h1v-1z");
                    svgPath.setFill(Color.BLACK); // Set the fill color of the SVG path
                    // Add the SVGPath to the TextFlow as the first element
                    questionFlow.getChildren().add(svgPath);
                    Text questionText = new Text("  "+ question.getId() + ": " + question.getText());
                    questionText.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
                    // Add the question text to the TextFlow
                    questionFlow.getChildren().add(questionText);
                    pageBox.getChildren().add(questionFlow);
                }
                return pageBox;
            }
        });
        scrollPane.widthProperty().addListener((observable, oldVal, newVal) -> {
            anchorInfoPane.setPrefWidth(newVal.doubleValue());
        });
    }

    @FXML
    void randomNumber(ActionEvent event) {
        int random = randomNumber.getValue();
        if (currentQuiz.getListQuestionQuiz().size() == 0){
            // add
            List<Question> randomQuestions = new ArrayList<>(questionData.subList(0, random));
            Set<Question> add = new HashSet<>(randomQuestions);
            currentQuiz.setListQuestionQuiz(add);
            QuizDao.getInstance().update(currentQuiz);
            System.out.println("Them thanh cong !!");
            System.out.println("So cau hoi da them: " + currentQuiz.getListQuestionQuiz().size());
            Alert();
        } else {
            Alert2();
            System.out.println("Quiz da co cau hoi !!");
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
            Stage stage = (Stage) cancel.getScene().getWindow();
            stage.setScene(gui61Scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void showall(ActionEvent event) {

    }
    private void reloadGUI() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("gui61.fxml"));
            Parent gui61Parent = loader.load();
            gui61 controller = loader.getController();
            controller.setQuiz(currentQuiz);
            controller.setUpQuiz();
            Scene gui61Scene = new Scene(gui61Parent);
            Stage stage = (Stage) save.getScene().getWindow();
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
