package com.example.btl_35.viewController;

import com.example.btl_35.dao.CategoryDao;
import com.example.btl_35.dao.QuestionDao;
import com.example.btl_35.entity.Answer;
import com.example.btl_35.entity.Category;
import com.example.btl_35.entity.Media;
import com.example.btl_35.entity.Question;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.SVGPath;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

public class gui32v2 {

    @FXML
    private Button moreChoice;
    private Category currentCategory;
    @FXML
    private Button deleteimage1;

    @FXML
    private Button deleteimage2;

    @FXML
    private Button deleteimage3;
    @FXML
    private Button image321;

    @FXML
    private Button image322;

    @FXML
    private Button image323;
    @FXML
    private TextField imagepath1;

    @FXML
    private TextField imagepath2;

    @FXML
    private TextField imagepath3;

    @FXML
    private ImageView imageview1;

    @FXML
    private ImageView imageview2;

    @FXML
    private ImageView imageview3;
    @FXML
    private Button saveimage1;

    @FXML
    private Button saveimage2;

    @FXML
    private Button saveimage3;
    @FXML
    private AnchorPane anchorInfoPane;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Button cancel;
    @FXML
    private TextField choice1;
    @FXML
    private TextField choice2;
    @FXML
    private TextField choice3;
    @FXML
    private TextField defaultmark32;
    @FXML
    private ComboBox<String> grade1;
    @FXML
    private ComboBox<String> grade2;
    @FXML
    private ComboBox<String> grade3;
    @FXML
    private Button image32;
    @FXML
    private TextField questionName32;
    @FXML
    private TextArea questionText32;
    @FXML
    private Button savechanges;
    @FXML
    private Button savechangesandcontinue;
    @FXML
    private Button saveimage;
    @FXML
    private Button deleteimage;
    @FXML
    private TextField imagepath;
    @FXML
    private TextField selectedcategory2;
    @FXML
    private MediaView preview;
    @FXML
    private ImageView imageview;
    @FXML
    private SVGPath chamthan1;
    @FXML
    private SVGPath chamthan2;
    @FXML
    private Button replay;
    @FXML
    private SVGPath chamthan3;
    private File selectedImageFile;
//    private File selectedImageFile1;
//    private File selectedImageFile2;
//    private File selectedImageFile3;
    private String newImagepath;
    private String newImagepath1;
    private String newImagepath2;
    private String newImagepath3;
    private MediaPlayer mediaPlayer;
    public void setCategoryName2(String categoryName2) {
        selectedcategory2.setText(categoryName2);
    }
    int id;
    public int setCategory(int selectedId) {
        id = selectedId;
        return selectedId;
    }
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
    void cancelclick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("gui21.fxml"));
            Parent gui21Parent = loader.load();
            Scene gui21Scene = new Scene(gui21Parent);

            Stage stage = (Stage) cancel.getScene().getWindow();
            stage.setScene(gui21Scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void image32click(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        Stage stage = (Stage) image32.getScene().getWindow();
        // Thiết lập tiêu đề cho hộp thoại chọn tệp tin
        fileChooser.setTitle("Chọn tệp tin");
        // Thiết lập bộ lọc cho hộp thoại chọn tệp tin
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg","*.mp4","*.gif");
        fileChooser.getExtensionFilters().add(imageFilter);
        // Hiển thị hộp thoại chọn tệp tin và lấy đường dẫn được chọn
        selectedImageFile = fileChooser.showOpenDialog(stage);
        if (selectedImageFile != null) {
            String filePath = selectedImageFile.getAbsolutePath();
            // Kiểm tra định dạng file
            String fileExtension = getFileExtension(selectedImageFile);
            if (fileExtension.equalsIgnoreCase("mp4")) {
                System.out.println("Đường dẫn tệp tin: " + filePath);
                try {
                    // Use FFprobe to get the video duration
                    ProcessBuilder processBuilder = new ProcessBuilder("src/main/java/com/example/btl_35/media/ffprobe.exe", "-v", "error", "-show_entries", "format=duration", "-of", "default=noprint_wrappers=1:nokey=1", filePath);
                    Process process = processBuilder.start();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    String durationString = reader.readLine();
                    reader.close();
                    process.waitFor();
                    if (durationString != null) {
                        double durationSeconds = Double.parseDouble(durationString);
                        if (durationSeconds <= 10) {
                            System.out.println("Độ dài video hợp lệ: " + durationSeconds + " giây");
                            imagepath.setText(filePath);
                        } else {
                            System.out.println("Độ dài video lớn hơn 10 giây.");
                        }
                    } else {
                        System.out.println("Không thể lấy được độ dài video.");
                    }
                } catch (IOException | InterruptedException e) {
                    System.out.println("Lỗi khi kiểm tra độ dài video.");
                    e.printStackTrace();
                }
            }
            if (fileExtension.equalsIgnoreCase("png") || fileExtension.equalsIgnoreCase("jpg")|| fileExtension.equalsIgnoreCase("gif")) {
                // Đường dẫn tệp tin đã chọn có định dạng đúng
                System.out.println("Đường dẫn tệp tin: " + filePath);
                imagepath.setText(filePath);
            }
        }
    }
    @FXML
    void image32click1(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        Stage stage = (Stage) image321.getScene().getWindow();
        // Thiết lập tiêu đề cho hộp thoại chọn tệp tin
        fileChooser.setTitle("Chọn tệp tin");
        // Thiết lập bộ lọc cho hộp thoại chọn tệp tin
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg","*.mp4","*.gif");
        fileChooser.getExtensionFilters().add(imageFilter);
        // Hiển thị hộp thoại chọn tệp tin và lấy đường dẫn được chọn
        selectedImageFile = fileChooser.showOpenDialog(stage);
        if (selectedImageFile != null) {
            String filePath = selectedImageFile.getAbsolutePath();
            // Kiểm tra định dạng file
            String fileExtension = getFileExtension(selectedImageFile);
            if (fileExtension.equalsIgnoreCase("png") || fileExtension.equalsIgnoreCase("jpg")|| fileExtension.equalsIgnoreCase("gif")) {
                // Đường dẫn tệp tin đã chọn có định dạng đúng
                System.out.println("Đường dẫn tệp tin: " + filePath);
                imagepath1.setText(filePath);
            }
        }
    }
    @FXML
    void image32click2(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        Stage stage = (Stage) image322.getScene().getWindow();
        // Thiết lập tiêu đề cho hộp thoại chọn tệp tin
        fileChooser.setTitle("Chọn tệp tin");
        // Thiết lập bộ lọc cho hộp thoại chọn tệp tin
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg","*.mp4","*.gif");
        fileChooser.getExtensionFilters().add(imageFilter);
        // Hiển thị hộp thoại chọn tệp tin và lấy đường dẫn được chọn
        selectedImageFile = fileChooser.showOpenDialog(stage);
        if (selectedImageFile != null) {
            String filePath = selectedImageFile.getAbsolutePath();
            // Kiểm tra định dạng file
            String fileExtension = getFileExtension(selectedImageFile);
            if (fileExtension.equalsIgnoreCase("png") || fileExtension.equalsIgnoreCase("jpg")|| fileExtension.equalsIgnoreCase("gif")) {
                // Đường dẫn tệp tin đã chọn có định dạng đúng
                System.out.println("Đường dẫn tệp tin: " + filePath);
                imagepath2.setText(filePath);
            }
        }
    }
    @FXML
    void image32click3(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        Stage stage = (Stage) image323.getScene().getWindow();
        // Thiết lập tiêu đề cho hộp thoại chọn tệp tin
        fileChooser.setTitle("Chọn tệp tin");
        // Thiết lập bộ lọc cho hộp thoại chọn tệp tin
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg","*.mp4","*.gif");
        fileChooser.getExtensionFilters().add(imageFilter);
        // Hiển thị hộp thoại chọn tệp tin và lấy đường dẫn được chọn
        selectedImageFile = fileChooser.showOpenDialog(stage);
        if (selectedImageFile != null) {
            String filePath = selectedImageFile.getAbsolutePath();
            // Kiểm tra định dạng file
            String fileExtension = getFileExtension(selectedImageFile);
            if (fileExtension.equalsIgnoreCase("png") || fileExtension.equalsIgnoreCase("jpg")|| fileExtension.equalsIgnoreCase("gif")) {
                // Đường dẫn tệp tin đã chọn có định dạng đúng
                System.out.println("Đường dẫn tệp tin: " + filePath);
                imagepath3.setText(filePath);
            }
        }
    }
    @FXML
    void savechangesandcontinueclick(ActionEvent event) {

    }

    @FXML
    void savechangesclick(ActionEvent event) {

    }
    @FXML
    void replayAction(ActionEvent event) {
        if (mediaPlayer != null) {
            mediaPlayer.seek(javafx.util.Duration.ZERO);
            mediaPlayer.play();
        }
    }
    @FXML
    void saveimage(ActionEvent event) {
        if (selectedImageFile != null) {
            String fileName = selectedImageFile.getName();
            File destinationDir = new File("src/main/java/com/example/btl_35/media");
            if (!destinationDir.exists()) {
                destinationDir.mkdirs();
            }
            Path destinationPath = new File(destinationDir, fileName).toPath();
            try {
                // Kiểm tra định dạng file
                String fileExtension = getFileExtension(selectedImageFile);
                String imageFileName = selectedImageFile.getName();
                Path newimagepath = new File(destinationDir, imageFileName).toPath();
                Files.copy(selectedImageFile.toPath(), newimagepath, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("image đã được sao chép vào: " + newimagepath);
                this.newImagepath = newimagepath.toString();
                if(fileExtension.equalsIgnoreCase("mp4")) {
                    replay.setVisible(true);
                    // Tạo đối tượng MediaPlayer
                    javafx.scene.media.Media media = new javafx.scene.media.Media(selectedImageFile.toURI().toString());
                    MediaPlayer mediaPlayer = new MediaPlayer(media);
                    // Gán MediaPlayer vào MediaView để hiển thị video
                    preview.setMediaPlayer(mediaPlayer);
                    this.mediaPlayer = mediaPlayer;
                }
                else{
                    // Hiển thị hình ảnh trong ImageView
                    ImageView image = new ImageView(selectedImageFile.toURI().toString());
                    imageview.setImage(image.getImage());
                }
            } catch (IOException e) {
                System.out.println("Lỗi khi sao chép file.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Không có file để sao chép.");
        }
    }
    @FXML
    void saveimage1(ActionEvent event) {
        if (selectedImageFile != null) {
            String fileName = selectedImageFile.getName();
            File destinationDir = new File("src/main/java/com/example/btl_35/media");
            if (!destinationDir.exists()) {
                destinationDir.mkdirs();
            }
            Path destinationPath = new File(destinationDir, fileName).toPath();
            try {
                // Kiểm tra định dạng file
                String fileExtension = getFileExtension(selectedImageFile);
                String imageFileName = selectedImageFile.getName();
                Path newimagepath = new File(destinationDir, imageFileName).toPath();
                Files.copy(selectedImageFile.toPath(), newimagepath, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("image đã được sao chép vào: " + newimagepath);
                this.newImagepath1 = newimagepath.toString();
                // Hiển thị hình ảnh trong ImageView
                ImageView image = new ImageView(selectedImageFile.toURI().toString());
                imageview1.setImage(image.getImage());
            } catch (IOException e) {
                System.out.println("Lỗi khi sao chép file.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Không có file để sao chép.");
        }
    }

    @FXML
    void saveimage2(ActionEvent event) {
        if (selectedImageFile != null) {
            String fileName = selectedImageFile.getName();
            File destinationDir = new File("src/main/java/com/example/btl_35/media");
            if (!destinationDir.exists()) {
                destinationDir.mkdirs();
            }
            Path destinationPath = new File(destinationDir, fileName).toPath();
            try {
                // Kiểm tra định dạng file
                String fileExtension = getFileExtension(selectedImageFile);
                String imageFileName = selectedImageFile.getName();
                Path newimagepath = new File(destinationDir, imageFileName).toPath();
                Files.copy(selectedImageFile.toPath(), newimagepath, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("image đã được sao chép vào: " + newimagepath);
                this.newImagepath2 = newimagepath.toString();
                // Hiển thị hình ảnh trong ImageView
                ImageView image = new ImageView(selectedImageFile.toURI().toString());
                imageview2.setImage(image.getImage());
            } catch (IOException e) {
                System.out.println("Lỗi khi sao chép file.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Không có file để sao chép.");
        }
    }

    @FXML
    void saveimage3(ActionEvent event) {
        if (selectedImageFile != null) {
            String fileName = selectedImageFile.getName();
            File destinationDir = new File("src/main/java/com/example/btl_35/media");
            if (!destinationDir.exists()) {
                destinationDir.mkdirs();
            }
            Path destinationPath = new File(destinationDir, fileName).toPath();
            try {
                // Kiểm tra định dạng file
                String fileExtension = getFileExtension(selectedImageFile);
                String imageFileName = selectedImageFile.getName();
                Path newimagepath = new File(destinationDir, imageFileName).toPath();
                Files.copy(selectedImageFile.toPath(), newimagepath, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("image đã được sao chép vào: " + newimagepath);
                this.newImagepath3 = newimagepath.toString();
                // Hiển thị hình ảnh trong ImageView
                ImageView image = new ImageView(selectedImageFile.toURI().toString());
                imageview3.setImage(image.getImage());
            } catch (IOException e) {
                System.out.println("Lỗi khi sao chép file.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Không có file để sao chép.");
        }
    }
    @FXML
    void deleteimage(ActionEvent event) {
        imagepath.clear();
        selectedImageFile = null;
        replay.setVisible(false);
        // Xóa video ở địa chỉ mới (nếu có)
        if (newImagepath != null) {
            File videoFile = new File(newImagepath);
            if (videoFile.exists()) {
                boolean deleted = videoFile.delete();
                if (deleted) {
                    System.out.println("Image đã được xóa: " + newImagepath);
                    preview.setMediaPlayer(null); // Remove media player from MediaView
                    imageview.setImage(null); // Clear the image in ImageView
                    newImagepath = null;
                } else {
                    System.out.println("Không thể xóa Image: " + newImagepath);
                }
            }
            newImagepath = null;
        }
    }

    @FXML
    void deleteimage1(ActionEvent event) {
        imagepath1.clear();
        selectedImageFile = null;
        // Xóa video ở địa chỉ mới (nếu có)
        if (newImagepath1 != null) {
            File videoFile = new File(newImagepath1);
            System.out.println("Image đã được xóa: " + newImagepath1);
            imageview1.setImage(null); // Clear the image in ImageView
        } else {
            System.out.println("Không thể xóa Image: " + newImagepath1);
        }
        newImagepath1 = null;
    }


    @FXML
    void deleteimage2(ActionEvent event) {
        imagepath2.clear();
        selectedImageFile = null;
        // Xóa video ở địa chỉ mới (nếu có)
        if (newImagepath2 != null) {
            File videoFile = new File(newImagepath2);
            System.out.println("Image đã được xóa: " + newImagepath2);
            imageview2.setImage(null); // Clear the image in ImageView
        } else {
            System.out.println("Không thể xóa Image: " + newImagepath2);
        }
        newImagepath2 = null;
    }

    @FXML
    void deleteimage3(ActionEvent event) {
        imagepath3.clear();
        selectedImageFile = null;
        // Xóa video ở địa chỉ mới (nếu có)
        if (newImagepath3 != null) {
            File videoFile = new File(newImagepath3);
            System.out.println("Image đã được xóa: " + newImagepath3);
            imageview3.setImage(null); // Clear the image in ImageView
        } else {
            System.out.println("Không thể xóa Image: " + newImagepath3);
        }
        newImagepath3 = null;
    }
    @FXML
    void moreChoice(ActionEvent event) {
        try {
            Category category = CategoryDao.getInstance().selectById(id);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("gui32v3.fxml"));
            Parent gui32v2Parent = loader.load();
            Scene gui32v2Scene = new Scene(gui32v2Parent);
            gui32v3 controller = loader.getController();
            controller.setCurrentCategory(category);
            Stage stage = (Stage) moreChoice.getScene().getWindow();
            stage.setScene(gui32v2Scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void initialize() {
        // Listen to changes in the quiz name field
        questionName32.textProperty().addListener((observable, oldValue, newValue) -> {
            disablechamthan1(newValue.isEmpty());
        });
        // Listen to changes in the quiz name field
        questionText32.textProperty().addListener((observable, oldValue, newValue) -> {
            disablechamthan2(newValue.isEmpty());
        });
        // Listen to changes in the quiz name field
        defaultmark32.textProperty().addListener((observable, oldValue, newValue) -> {
            disablechamthan3(newValue.isEmpty());
        });
        // Tạo danh sách các mức điểm từ 100% đến -83.33%
        ObservableList<String> gradeList = FXCollections.observableArrayList(
                "100%", "90%","83.33333%" ,"80%","75%" ,"70%","66.66667%" ,"60%", "50%", "40%", "33.33333%","30%","25%" ,"20%","16.66667%","14.28571%","12.5%","11.11111%" ,"10%",
                "5%", "-5%", "-10%","-11.11111%","-12.5%","-14.28571%","-15%","-16.66667%", "-20%", "-25%", "-30%","-33.33333%", "-40%",
                "-50%", "-60%","-70%", "-75%", "-80%", "-83.33333%"
        );
        // Gán danh sách mức điểm vào ComboBox
        grade1.setItems(gradeList);
        grade2.setItems(gradeList);
        grade3.setItems(gradeList);
        // Mặc định ComboBox là "None"
        grade1.setValue("None");
        grade2.setValue("None");
        grade3.setValue("None");
        savechangesandcontinue.setOnMouseClicked(event -> {
            try {
                Question question = new Question();
                Category category = CategoryDao.getInstance().selectById(id);
                question.setCategory(category);
                question.setName(questionName32.getText());
                question.setText(questionText32.getText());
                int int_value = Integer.parseInt(defaultmark32.getText());
                question.setMark(int_value);
                Media media = new Media();
                media.setUrl(newImagepath);
                System.out.println("ok : "+newImagepath);
                Set<Answer> answers = new HashSet<>();
                if (!choice1.getText().isEmpty()) {
                    Answer da1 = createAnswer(choice1.getText(), grade1.getValue(),newImagepath1);
                    da1.setQuestion(question);
                    answers.add(da1);
                }
                if (!choice2.getText().isEmpty()) {
                    Answer da2 = createAnswer(choice2.getText(), grade2.getValue(),newImagepath2);
                    da2.setQuestion(question);
                    answers.add(da2);
                }
                if (!choice3.getText().isEmpty()) {
                    Answer da3 = createAnswer(choice3.getText(), grade3.getValue(),newImagepath3);
                    da3.setQuestion(question);
                    answers.add(da3);
                }
                if(media.getUrl()!=null){
                question.setMedia(media);
                }
                question.setAnswers(answers);
                QuestionDao.getInstance().insert(question);
                gui32Ok2();
            } catch (Exception e) {
                e.printStackTrace();
                gui32Error();
            }
        });
        savechanges.setOnMouseClicked(event -> {
            try {
                Question question = new Question();
                Category category = CategoryDao.getInstance().selectById(id);
                question.setCategory(category);
                question.setName(questionName32.getText());
                question.setText(questionText32.getText());
                int int_value = Integer.parseInt(defaultmark32.getText());
                question.setMark(int_value);
                Media media = new Media();
                media.setUrl(newImagepath);
                System.out.println("ok : "+newImagepath);
                Set<Answer> answers = new HashSet<>();
                if (!choice1.getText().isEmpty()) {
                    Answer da1 = createAnswer(choice1.getText(), grade1.getValue(),newImagepath1);
                    da1.setQuestion(question);
                    answers.add(da1);
                }
                if (!choice2.getText().isEmpty()) {
                    Answer da2 = createAnswer(choice2.getText(), grade2.getValue(),newImagepath2);
                    da2.setQuestion(question);
                    answers.add(da2);
                }
                if (!choice3.getText().isEmpty()) {
                    Answer da3 = createAnswer(choice3.getText(), grade3.getValue(),newImagepath3);
                    da3.setQuestion(question);
                    answers.add(da3);
                }
                question.setMedia(media);
                question.setAnswers(answers);
                QuestionDao.getInstance().insert(question);
                gui32Ok();
            } catch (Exception e) {
                e.printStackTrace();
                gui32Error();
            }
        });
        scrollPane.widthProperty().addListener((observable, oldVal, newVal) ->{
            anchorInfoPane.setPrefWidth(newVal.doubleValue());
        });
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
    public static Answer createAnswer(String choiceText, String gradeInput,String imagepath) {
        Media media = new Media();
        media.setUrl(imagepath);
        Answer answer = new Answer();
        answer.setMedia(media);
        answer.setChoice(choiceText);
        String numberString = gradeInput.replaceAll("%", "");
        double number = Double.parseDouble(numberString) / 100.0;
        answer.setGrade(number);
        boolean boolValue = (number > 0);
        answer.setIs_choice(boolValue);
        return answer;
    }
    private void gui32Ok(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("gui32Success.fxml"));
            Parent gui11Parent = loader.load();
            Scene gui11Scene = new Scene(gui11Parent);
            Stage stage = (Stage) savechanges.getScene().getWindow();
            stage.setScene(gui11Scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void gui32Error(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("gui32Error.fxml"));
            Parent gui11Parent = loader.load();
            Scene gui11Scene = new Scene(gui11Parent);
            Stage stage = (Stage) savechanges.getScene().getWindow();
            stage.setScene(gui11Scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void gui32Ok2(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("gui32Success2.fxml"));
            Parent gui11Parent = loader.load();
            Scene gui11Scene = new Scene(gui11Parent);
            Stage stage = (Stage) savechanges.getScene().getWindow();
            stage.setScene(gui11Scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    private void disablereplay(boolean disable) {
        replay.setVisible(disable);
    }
}


