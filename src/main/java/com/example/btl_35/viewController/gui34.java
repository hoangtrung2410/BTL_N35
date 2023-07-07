package com.example.btl_35.viewController;

import com.example.btl_35.dao.CategoryDao;
import com.example.btl_35.dao.QuestionDao;
import com.example.btl_35.entity.Answer;
import com.example.btl_35.entity.Category;
import com.example.btl_35.entity.Question;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.SVGPath;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;

public class gui34 {
    @FXML
    private AnchorPane anchorInfoPane;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Button categoriesgui33;
    @FXML
    private Button questiongui31;
    @FXML
    private Button importfile;
    @FXML
    private AnchorPane importfile2;
    @FXML
    private Button importquestion;
    @FXML
    private Button returngui11;
    @FXML
    private TextField linkFile;
    @FXML
    private SVGPath chamthan;
    private String filePath2 = null;
    private String filePath;
    private int check1;
    private int check2;


    @FXML
    void importquestion(ActionEvent event) {

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
    void importfile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        Stage stage = (Stage) importfile.getScene().getWindow();
        // Thiết lập tiêu đề cho hộp thoại chọn tệp tin
        fileChooser.setTitle("Chọn tệp tin");
        FileChooser.ExtensionFilter fileFilter = new FileChooser.ExtensionFilter("Image Files", "*.txt");
        fileChooser.getExtensionFilters().add(fileFilter);
        // Hiển thị hộp thoại chọn tệp tin và lấy đường dẫn được chọn
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            filePath = selectedFile.getAbsolutePath();
            // Đường dẫn tệp tin đã chọn được lưu trong biến filePath
            System.out.println("Đường dẫn tệp tin: " + filePath);
            linkFile.setText(filePath);
            // Xu ly va luu File
            checkFormat(filePath);
            check1 = checkFormat(filePath);

        }
    }

    @FXML
    void initialize() {
        linkFile.textProperty().addListener((observable, oldValue, newValue) -> {
            disablechamthan(newValue.isEmpty());
        });
        importfile2.setOnDragOver(this::handleDragOver);
        importfile2.setOnDragDropped(this::handleDragDropped);
        importquestion.setOnMouseClicked(event -> {
            if (1 == check1) {
                saveImport(filePath);
            } else if (1 == check2) {
                saveImport(filePath2);
            } else {
                // Xử lý khi không có trường hợp nào khớp
                System.out.println("Không thêm được file");
            }
        });
        scrollPane.widthProperty().addListener((observable, oldVal, newVal) ->{
            anchorInfoPane.setPrefWidth(newVal.doubleValue());
        });
    }
    private void disablechamthan(boolean disable) {
        chamthan.setVisible(disable);
    }
    private void handleDragOver(DragEvent event) {
        if (event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.COPY);
        }
        event.consume();
    }

    private void handleDragDropped(DragEvent event) {
        Dragboard dragboard = event.getDragboard();
        boolean success = false;
        if (dragboard.hasFiles()) {
            success = true;
            for (File file : dragboard.getFiles()) {
                filePath2 = file.getAbsolutePath();
                System.out.println("Đường dẫn tệp tin filePath2: " + filePath2);
                // Check file format
                String fileExtension = getFileExtension(file);
                if (!fileExtension.equalsIgnoreCase("txt")) {
                    showAlert("Wrong Format", "Only .txt files are allowed.", Alert.AlertType.ERROR);
                    filePath2 = null;
                    // Display an error message or handle the unsupported format case as needed
                    return;
                }
                linkFile.setText(filePath2);
                // Continue with the rest of your logic for processing the file
                check2 = checkFormat(filePath2);
                System.out.println("check2: " + check2);
            }
        }
        event.setDropCompleted(success);
        event.consume();
    }

    @FXML
    void Category(ActionEvent event) {

    }

    @FXML
    void categoriesgui33(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("gui33.fxml"));
            Parent gui33Parent = loader.load();
            Scene gui33Scene = new Scene(gui33Parent);
            Stage stage = (Stage) categoriesgui33.getScene().getWindow();
            stage.setScene(gui33Scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void questiongui31(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("gui21.fxml"));
            Parent gui21Parent = loader.load();
            Scene gui21Scene = new Scene(gui21Parent);
            Stage currentStage = (Stage) questiongui31.getScene().getWindow();
            currentStage.setScene(gui21Scene);
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /* 	Check if line is formatted
     * 	tra ve -1 neu dong trong
     * 	tra ve 0 neu la lua chon
     * 	tra ve 2 neu la dap an
     * 	tra ve 1 neu la cau hoi
     * 	tra ve 3 neu khong la gi trong 4 loai tren
     */
    public int checkLine(String choiceLine) {
        if (choiceLine.isEmpty()) {
            return -1;
        } else if (Character.isUpperCase(choiceLine.charAt(0)) && choiceLine.charAt(1) == '.' && choiceLine.charAt(2) == ' ' && choiceLine.charAt(3) != ' ') {
            return 0;
        } else if (choiceLine.length() == 9 && choiceLine.substring(0, 8).equals("ANSWER: ")) {
            return 2;
        } else if (choiceLine.charAt(0) == ' ') {
            return 3;
        } else {
            return 1;
        }
    }

    /* Check if File is formatted */
    public int checkFormat(String path) {
        File file = new File(path);
        int result = 1;

        try {
            int numberQuestions = 0;
            int countChoice = -2;
//			int result = 1;

            List<String> allLine = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
            ArrayList<Character> keyChoice = new ArrayList<Character>();

            int numberLine = allLine.size();
            for (int i = 0; i < numberLine; i++) {
                String checkLine = allLine.get(i);
                if (checkLine(checkLine) == 1 && countChoice == -2) {
                    // Truong hop gap cau hoi moi
                    countChoice = 0;
                    keyChoice.clear();
                    continue;
                } else if (checkLine(checkLine) == 0 && countChoice >= 0) {
                    // Gap lua chon moi cua cau hoi
                    countChoice++;
                    keyChoice.add(checkLine.charAt(0));
                    continue;
                } else if (checkLine(checkLine) == 2 && countChoice >= 2) {
                    // Gap dap an cua cau hoi da day du
                    if (keyChoice.contains(checkLine.charAt(8))) {
                        // Kiem tra neu dap an thuoc trong cac lua chon
                        countChoice = -1;
                        numberQuestions++;
                        continue;
                    } else {
                        System.out.println("Error at " + (i + 1));
                        showAlert("Error", "Lỗi ở dòng: " + (i + 1) + "\n(Answer not in choices)", Alert.AlertType.ERROR);
                        System.out.println("(Answer not in choices)");
                        result = 0;
                        countChoice = -2;
                        break;
                    }
                } else if (checkLine(checkLine) == -1 && countChoice == -1) {
                    // Gap
                    countChoice = -2;
                    continue;
                } else {
                    System.out.println("Error at " + (i + 1));
                    showAlert("Error", "Lỗi ở dòng: " + (i + 1), Alert.AlertType.ERROR);
                    result = 0;
                    countChoice = -2;
                    break;
                }
            }
            if (result == 1) {
                System.out.println("Success: " + numberQuestions);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /* 	Check if line is formatted
     * 	tra ve -1 neu dong trong
     * 	tra ve 0 neu la lua chon
     * 	tra ve 2 neu la dap an
     * 	tra ve 1 neu la cau hoi
     * 	tra ve 3 neu khong la gi trong 4 loai tren
     */
    public void saveImport(String path) {
        // Test unit
        System.out.println("Bắt đầu import");
        // Process
        // Lấy file
        File file = new File(path);
        try {
            int questionsCount = 0; // Đếm số lượng câu hỏi đã import
            Question question = new Question(); // Chứa dữ liệu
            char key = '\0'; // Kí tự trống
            List<String> choices = new ArrayList<>();
            List<Answer> answers = new ArrayList<>();
            List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8); // Các dòng trong file
            // Xử lý từng dòng
            for (String line : lines) {
                if (checkLine(line) == 1) {
                    // Gặp câu hỏi mới -> reset dữ liệu

                    choices = new ArrayList<>();
                    answers = new ArrayList<>();
                    key = '\0'; // Reset về kí tự trống

                    int colonIndex = line.indexOf(":"); // Vị trí dấu ":" đầu tiên
                    // Nếu không có ":" thì dùng cả câu làm nội dung
                    String questionText = colonIndex >= 0 ? line.substring(colonIndex + 2) : line;
                    // Set nội dung cho câu hỏi
                    question.setText(questionText);
                    // Set category cho question

                    String finalCategory = "";
                    // Lấy danh sách category
                    List<Category> categoryList = CategoryDao.getInstance().selectALl();
                    List<Integer> categoryIDs = new ArrayList<>();
                    // Lấy category của question đang xét
                    String questionCategory = line.substring(0, colonIndex - 1);
                    // Duyệt từng category -> Nếu gặp parent thì break (không add)
                    for (Category category : categoryList) {
                        String categoryName = category.getName();
                        int category_ID = category.getId(); // ID có sẵn
                        categoryIDs.add(category_ID);
                        if (questionCategory.equals(categoryName)) {
                            // Nếu bằng nhau thì set luôn
                            question.setCategory(category);
                            finalCategory = categoryName;
                            break;
                        }
                    }
                    // Không tìm thấy parent -> tạo ra 1 category mới
                    if (finalCategory.equals("")) {
                        Category newCategory = new Category();
                        newCategory.setName(questionCategory);
                        newCategory.setQuestionCount(0);

                        // Set ID cho newCategory
                        int newCategoryId;
                        Random rand = new Random();
                        do {
                            newCategoryId = rand.nextInt(Integer.MAX_VALUE); // Generate a random integer ID
                        } while (categoryIDs.contains(newCategoryId));
                        newCategory.setId(newCategoryId);
                        System.out.println("ID mới là: " + newCategoryId);
                        CategoryDao.getInstance().insert(newCategory);
                        // Thêm vào category
                        question.setCategory(newCategory);
                    }
                } else if (checkLine(line) == 0) {
                    // Gặp lựa chọn mới -> Thêm lựa chọn
                    Answer answer = new Answer();
                    answer.setChoice(line.substring(3)); // Nội dung lựa chọn
                    answer.setQuestion(question);
                    choices.add(line.substring(0, 1)); // Thêm A, B,... vào danh sách choices
                    answers.add(answer); // Thêm object câu hỏi vào danh sách answer
                } else if (checkLine(line) == 2) {
                    // Gặp đáp án -> Set đáp án của answer, lưu câu hỏi
                    key = line.charAt(8); // Đáp án
                    int keyIndex = choices.indexOf(String.valueOf(key));
                    for (String choice : choices) {
                        System.out.println(choice);
                    }
                    if (keyIndex >= 0) {
                        // Set đáp án của answer neu tim thay Answer trong choices
                        for (int i = 0; i < answers.size(); i++) {
                            Answer answer = answers.get(i);
                            answer.setIs_choice(i == keyIndex);
                            answer.setGrade(answer.isIs_choice() ? 1.0 : 0.0);
                        }
                        // Lưu câu hỏi
                        Set<Answer> setAnswers = new HashSet<>(answers);
                        for (Answer X : setAnswers) {
                            System.out.println("Lựa chọn: " + X.getChoice() + "-" + X.isIs_choice());

                        }
                        question.setAnswers(setAnswers);
                        QuestionDao.getInstance().insert(question);
                        questionsCount++;
                    } else {
                        System.out.println("Có lỗi gì đó, keyIndex = " + keyIndex);
                        System.out.println("Key: " + key);
                    }
                } else {
                    // Gặp dòng trống -> reset dữ liệu
                    question = new Question();
                    choices = new ArrayList<>();
                    answers = new ArrayList<>();
                    key = '\0'; // Reset về kí tự trống
                }
            }
            // thanh cong
            Alert();
        } catch (Exception e) {
            Alert2(); // that bai (do chuong trinh)
            e.printStackTrace();
        }
    }

    private String getFileExtension(File file) {
        String extension = "";
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            extension = fileName.substring(dotIndex + 1).toLowerCase();
        }
        return extension;
    }

    // them that bai (loi cu the dong nao)
    private void showAlert(String title, String message, Alert.AlertType alertType) {
        filePath2 = null;
        filePath = null;
        linkFile.setText(filePath);
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void reloadGUI() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("gui34.fxml"));
            Parent gui11Parent = loader.load();
            Scene gui11Scene = new Scene(gui11Parent);
            Stage stage = (Stage) importquestion.getScene().getWindow();
            stage.setScene(gui11Scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // them thanh cong
    private void Alert(){
        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setTitle("Thêm câu hỏi thành công");
        successAlert.setHeaderText(null);
        successAlert.setContentText("Thêm câu hỏi thành công, click OK để tiếp tục");
        successAlert.showAndWait();
        reloadGUI();
    }

    private void Alert2(){
        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setTitle("Thêm câu hỏi thất bại");
        successAlert.setHeaderText(null);
        successAlert.setContentText("Thêm câu hỏi thất bại, click OK để tiếp tục");
        successAlert.showAndWait();
        reloadGUI();
    }
}
