package com.example.btl_35.viewController;

import com.example.btl_35.dao.AnswerDao;
import com.example.btl_35.dao.QuestionDao;
import com.example.btl_35.entity.Answer;
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
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.SVGPath;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.scene.image.ImageView;
public class gui32b {

    @FXML
    private Button replay;
    @FXML
    private AnchorPane anchorInfoPane;
    @FXML
    private ScrollPane scrollPane;
    private Question currentQuestion; // for saving
    private List<Answer> answerList;

    // new saveImport solution
    private void setAnswerList(Question question) {
        this.answerList = new ArrayList<>(question.getAnswers());
        this.answerCount = answerList.size();
        for (int i = 0; i < 6; i++) {
            if (i < answerCount) {
                defaultAnswers.add(answerList.get(i));
            } else {
                defaultAnswers.add(null);
            }
        }
        System.out.println(answerCount);
    }

    private int answerCount;
    private List<Answer> defaultAnswers = new ArrayList<>(6);

    //
    public void setCurrentQuestion(Question question) {
        this.currentQuestion = question;
    }

    @FXML
    private Button cancel2;
    @FXML
    private Button savechanges2;
    @FXML
    private Button savechangesandcontinue;
    @FXML
    private TextField questionNameField;
    @FXML
    private TextField questionCategoryField;
    @FXML
    private TextArea questionTextField;
    @FXML
    private TextField questionMarkField;
    @FXML
    private Button returngui11;
    @FXML
    private SVGPath chamthan1;
    @FXML
    private SVGPath chamthan2;
    @FXML
    private SVGPath chamthan3;
    // answer field
    @FXML
    private TextField answerChoice1;
    @FXML
    private ComboBox<String> answerGrade1;
    @FXML
    private TextField answerChoice2;
    @FXML
    private ComboBox<String> answerGrade2;
    @FXML
    private TextField answerChoice3;
    @FXML
    private ComboBox<String> answerGrade3;
    @FXML
    private TextField answerChoice4;
    @FXML
    private TextField answerChoice5;
    @FXML
    private TextField answerChoice6;
    @FXML
    private ComboBox<String> answerGrade4;
    @FXML
    private ComboBox<String> answerGrade5;
    @FXML
    private ComboBox<String> answerGrade6;
    @FXML
    private Button deleteimage1;

    @FXML
    private Button deleteimage2;

    @FXML
    private Button deleteimage3;

    @FXML
    private Button deleteimage4;

    @FXML
    private Button deleteimage5;

    @FXML
    private Button deleteimage6;
    @FXML
    private Button image32;

    @FXML
    private Button image321;

    @FXML
    private Button image322;

    @FXML
    private Button image323;

    @FXML
    private Button image324;

    @FXML
    private Button image325;

    @FXML
    private Button image326;
    @FXML
    private TextField imagepath;

    @FXML
    private TextField imagepath1;

    @FXML
    private TextField imagepath2;

    @FXML
    private TextField imagepath3;

    @FXML
    private TextField imagepath4;

    @FXML
    private TextField imagepath5;

    @FXML
    private TextField imagepath6;

    @FXML
    private ImageView imageview;

    @FXML
    private ImageView imageview1;

    @FXML
    private ImageView imageview2;

    @FXML
    private ImageView imageview3;

    @FXML
    private ImageView imageview4;

    @FXML
    private ImageView imageview5;

    @FXML
    private ImageView imageview6;

    @FXML
    private MediaView preview;
    @FXML
    private Button saveimage;

    @FXML
    private Button saveimage1;

    @FXML
    private Button saveimage2;

    @FXML
    private Button saveimage3;

    @FXML
    private Button saveimage4;

    @FXML
    private Button saveimage5;

    @FXML
    private Button saveimage6;
    private File selectedImageFile;
    private String newImagepath;
    private String newImagepath1;
    private String newImagepath2;
    private String newImagepath3;
    private String newImagepath4;
    private String newImagepath5;
    private String newImagepath6;
    private MediaPlayer mediaPlayer;
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
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Video không hợp lệ");
                            alert.setHeaderText("Video có độ dài lớn hơn 10 giây");
                            alert.setContentText("Vui lòng chọn một video có độ dài dưới 10 giây.");
                            alert.showAndWait();
                            System.out.println(durationSeconds);
                            System.out.println("Độ dài video lớn hơn 10 giây.");
                        }
                    } else {
//                        Alert alert = new Alert(Alert.AlertType.WARNING);
//                        alert.setTitle("Video không hợp lệ");
//                        alert.setHeaderText("Không thể lấy được độ dài video.");
//                        alert.setContentText("Vui lòng chọn 1 video hợp lệ");
//                        alert.showAndWait();
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
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg","*.gif");
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
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg","*.gif");
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
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg","*.gif");
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
    void image32click4(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        Stage stage = (Stage) image324.getScene().getWindow();
        // Thiết lập tiêu đề cho hộp thoại chọn tệp tin
        fileChooser.setTitle("Chọn tệp tin");
        // Thiết lập bộ lọc cho hộp thoại chọn tệp tin
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg","*.gif");
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
                imagepath4.setText(filePath);
            }
        }
    }

    @FXML
    void image32click5(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        Stage stage = (Stage) image325.getScene().getWindow();
        // Thiết lập tiêu đề cho hộp thoại chọn tệp tin
        fileChooser.setTitle("Chọn tệp tin");
        // Thiết lập bộ lọc cho hộp thoại chọn tệp tin
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg","*.gif");
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
                imagepath5.setText(filePath);
            }
        }
    }

    @FXML
    void image32click6(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        Stage stage = (Stage) image326.getScene().getWindow();
        // Thiết lập tiêu đề cho hộp thoại chọn tệp tin
        fileChooser.setTitle("Chọn tệp tin");
        // Thiết lập bộ lọc cho hộp thoại chọn tệp tin
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg","*.gif");
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
                imagepath6.setText(filePath);
            }
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
    void deleteimage4(ActionEvent event) {
        imagepath4.clear();
        selectedImageFile = null;
        if (newImagepath4 != null) {
            File videoFile = new File(newImagepath4);
            System.out.println("Image đã được xóa: " + newImagepath4);
            imageview4.setImage(null); // Clear the image in ImageView
        } else {
            System.out.println("Không thể xóa Image: " + newImagepath4);
        }
        newImagepath4 = null;
    }

    @FXML
    void deleteimage5(ActionEvent event) {
        imagepath5.clear();
        selectedImageFile = null;
        if (newImagepath5 != null) {
            File videoFile = new File(newImagepath5);
            System.out.println("Image đã được xóa: " + newImagepath5);
            imageview5.setImage(null); // Clear the image in ImageView
        } else {
            System.out.println("Không thể xóa Image: " + newImagepath5);
        }
        newImagepath5 = null;
    }

    @FXML
    void deleteimage6(ActionEvent event) {
        imagepath6.clear();
        selectedImageFile = null;
        if (newImagepath6 != null) {
            File videoFile = new File(newImagepath6);
            System.out.println("Image đã được xóa: " + newImagepath6);
            imageview6.setImage(null); // Clear the image in ImageView
        } else {
            System.out.println("Không thể xóa Image: " + newImagepath6);
        }
        newImagepath6 = null;
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
    void saveimage4(ActionEvent event) {
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
                this.newImagepath4 = newimagepath.toString();
                // Hiển thị hình ảnh trong ImageView
                ImageView image = new ImageView(selectedImageFile.toURI().toString());
                imageview4.setImage(image.getImage());
            } catch (IOException e) {
                System.out.println("Lỗi khi sao chép file.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Không có file để sao chép.");
        }
    }

    @FXML
    void saveimage5(ActionEvent event) {
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
                this.newImagepath5 = newimagepath.toString();
                // Hiển thị hình ảnh trong ImageView
                ImageView image = new ImageView(selectedImageFile.toURI().toString());
                imageview5.setImage(image.getImage());
            } catch (IOException e) {
                System.out.println("Lỗi khi sao chép file.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Không có file để sao chép.");
        }
    }

    @FXML
    void saveimage6(ActionEvent event) {
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
                this.newImagepath6 = newimagepath.toString();
                // Hiển thị hình ảnh trong ImageView
                ImageView image = new ImageView(selectedImageFile.toURI().toString());
                imageview6.setImage(image.getImage());
            } catch (IOException e) {
                System.out.println("Lỗi khi sao chép file.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Không có file để sao chép.");
        }
    }

    @FXML
    void replayAction(ActionEvent event) {
        if (mediaPlayer != null) {
            mediaPlayer.seek(javafx.util.Duration.ZERO);
            mediaPlayer.play();
        }
    }
    // call in previous GUI
    private void setQuestionName(String questionName) {
//        questionNameField.setText(questionName == null ? "" : questionName);
        questionNameField.setText(questionName);
    }

    private void setQuestionCategory(String questionCategory) {
        questionCategoryField.setText(questionCategory);
    }

    private void setQuestionText(String questionText) {
        questionTextField.setText(questionText);
    }

    private void setQuestionMark(Integer questionMark) {
        if (questionMark != null) {
            questionMarkField.setText(questionMark.toString());
        } else {
            questionMarkField.setText("");
        }
    }
    private void setQuestionMedia(Question question, TextField fileImage){
        if (question.getMedia().getUrl() != null) {
            fileImage.setText(question.getMedia().getUrl());
        } else {
            imagepath.setText("");
        }

    }
    public void setQuestionInfo() {
        setQuestionName(currentQuestion.getName());
        setQuestionMark(currentQuestion.getMark());
        setQuestionCategory(currentQuestion.getCategory().getName());
        setQuestionText(currentQuestion.getText());
        if(currentQuestion.getMedia()!=null){
            setContent(this.currentQuestion.getMedia().getUrl());
            setQuestionMedia(currentQuestion,imagepath) ;
//        setQuestionImageView(currentQuestion,imageview);
        }

    }
    private void setAnswerMedia(Answer answer, TextField filePath){
        if (answer.getMedia().getUrl() != null) {
            filePath.setText(answer.getMedia().getUrl());
        } else {
            filePath.setText("");
        }

    }

    // set textfield for the answer
    private void setAnswerChoice(Answer answer, TextField answerChoice) {

        answerChoice.setText(answer.getChoice());
    }

    // set combobox label for the answer
    private void setAnswerGrade(Answer answer, ComboBox<String> gradeChoice) {
        if (answer.getGrade() != null) {
            String answerGrade = String.format("%.0f%%", answer.getGrade() * 100);
            gradeChoice.setValue(answerGrade);
        } else {
            gradeChoice.setValue("");
        }
    }
    private void setAnsweImageView(Answer answer, ImageView imageView) {
        String imageUrl = answer.getMedia().getUrl();
        File contentFile = new File(imageUrl);
        if (imageUrl != null && !imageUrl.isEmpty()) {
            try {
                ImageView image = new ImageView(contentFile.toURI().toString());
                imageView.setImage(image.getImage());
            } catch (IllegalArgumentException e) {
                // Handle the case where the URL is invalid or the resource could not be found
                System.err.println("Failed to load image at URL: " + imageUrl);
                e.printStackTrace();
            }
        } else {
            imageView.setImage(null);
        }
    }

    // set the combo box for the answer
    private void setAnswerBox() {
        ObservableList<String> gradeList = FXCollections.observableArrayList(
                "100%", "90%", "83.33333%", "80%", "75%", "70%", "66,.66667%", "60%", "50%", "40%", "33.33333%", "30%", "25%", "20%", "16.66667%", "14.28571%", "12.5%", "11.11111%", "10%",
                "5%", "-5%", "-10%", "-11.11111%", "-12.5%", "-14.28571%", "-15%", "-16.66667%", "-20%", "-25%", "-30%", "-33.33333%", "-40%",
                "-50%", "-60%", "-70%", "-75%", "-80%", "-83.33333%"
        );
        answerGrade1.setItems(gradeList);
        answerGrade2.setItems(gradeList);
        answerGrade3.setItems(gradeList);
        answerGrade4.setItems(gradeList);
        answerGrade5.setItems(gradeList);
        answerGrade6.setItems(gradeList);
    }

    // IF ELSE TO DIE
    public void setAnswersInfo() {
        setAnswerList(currentQuestion);
        int questionCount = answerList.size();
        if (questionCount == 1) {
            setAnswerGrade(answerList.get(0), answerGrade1);
            setAnswerChoice(answerList.get(0), answerChoice1);
            if(answerList.get(0).getMedia()!= null){
            setAnswerMedia(answerList.get(0),imagepath1);
            setAnsweImageView(answerList.get(0),imageview1);
            }
        } else if (questionCount == 2) {
            setAnswerGrade(answerList.get(0), answerGrade1);
            setAnswerChoice(answerList.get(0), answerChoice1);
            setAnswerGrade(answerList.get(1), answerGrade2);
            setAnswerChoice(answerList.get(1), answerChoice2);
            if(answerList.get(0).getMedia() !=null){
                setAnswerMedia(answerList.get(0),imagepath1);
                setAnsweImageView(answerList.get(0),imageview1);
                setAnswerMedia(answerList.get(1),imagepath2);
                setAnsweImageView(answerList.get(1),imageview2);
            }
        }
        else if (questionCount == 3) {
            setAnswerGrade(answerList.get(0), answerGrade1);
            setAnswerChoice(answerList.get(0), answerChoice1);
            setAnswerGrade(answerList.get(1), answerGrade2);
            setAnswerChoice(answerList.get(1), answerChoice2);
            setAnswerGrade(answerList.get(2), answerGrade3);
            setAnswerChoice(answerList.get(2), answerChoice3);
            if(answerList.get(0).getMedia() !=null){
                setAnswerMedia(answerList.get(0),imagepath1);
                setAnsweImageView(answerList.get(0),imageview1);
                setAnswerMedia(answerList.get(1),imagepath2);
                setAnsweImageView(answerList.get(1),imageview2);
                setAnswerMedia(answerList.get(2),imagepath3);
                setAnsweImageView(answerList.get(2),imageview3);
            }
        }
        else if (questionCount == 4) {
            setAnswerGrade(answerList.get(0), answerGrade1);
            setAnswerChoice(answerList.get(0), answerChoice1);
            setAnswerGrade(answerList.get(1), answerGrade2);
            setAnswerChoice(answerList.get(1), answerChoice2);
            setAnswerGrade(answerList.get(2), answerGrade3);
            setAnswerChoice(answerList.get(2), answerChoice3);
            setAnswerGrade(answerList.get(3), answerGrade4);
            setAnswerChoice(answerList.get(3), answerChoice4);
            if(answerList.get(0).getMedia() !=null){
                setAnswerMedia(answerList.get(0),imagepath1);
                setAnsweImageView(answerList.get(0),imageview1);
                setAnswerMedia(answerList.get(1),imagepath2);
                setAnsweImageView(answerList.get(1),imageview2);
                setAnswerMedia(answerList.get(2),imagepath3);
                setAnsweImageView(answerList.get(2),imageview3);
                setAnswerMedia(answerList.get(3),imagepath4);
                setAnsweImageView(answerList.get(3),imageview4);
            }
        }else if (questionCount == 5) {
            setAnswerGrade(answerList.get(0), answerGrade1);
            setAnswerChoice(answerList.get(0), answerChoice1);
            setAnswerGrade(answerList.get(1), answerGrade2);
            setAnswerChoice(answerList.get(1), answerChoice2);
            setAnswerGrade(answerList.get(2), answerGrade3);
            setAnswerChoice(answerList.get(2), answerChoice3);
            setAnswerGrade(answerList.get(3), answerGrade4);
            setAnswerChoice(answerList.get(3), answerChoice4);
            setAnswerGrade(answerList.get(4), answerGrade5);
            setAnswerChoice(answerList.get(4), answerChoice5);
            if(answerList.get(0).getMedia() !=null){
                setAnswerMedia(answerList.get(0),imagepath1);
                setAnsweImageView(answerList.get(0),imageview1);
                setAnswerMedia(answerList.get(1),imagepath2);
                setAnsweImageView(answerList.get(1),imageview2);
                setAnswerMedia(answerList.get(2),imagepath3);
                setAnsweImageView(answerList.get(2),imageview3);
                setAnswerMedia(answerList.get(3),imagepath4);
                setAnsweImageView(answerList.get(3),imageview4);
                setAnswerMedia(answerList.get(4),imagepath5);
                setAnsweImageView(answerList.get(4),imageview5);
            }
        }
        else if (questionCount == 6) {
            setAnswerGrade(answerList.get(0), answerGrade1);
            setAnswerChoice(answerList.get(0), answerChoice1);
            setAnswerGrade(answerList.get(1), answerGrade2);
            setAnswerChoice(answerList.get(1), answerChoice2);
            setAnswerGrade(answerList.get(2), answerGrade3);
            setAnswerChoice(answerList.get(2), answerChoice3);
            setAnswerGrade(answerList.get(3), answerGrade4);
            setAnswerChoice(answerList.get(3), answerChoice4);
            setAnswerGrade(answerList.get(4), answerGrade5);
            setAnswerChoice(answerList.get(4), answerChoice5);
            setAnswerGrade(answerList.get(5), answerGrade6);
            setAnswerChoice(answerList.get(5), answerChoice6);
            if(answerList.get(0).getMedia() !=null){
                setAnswerMedia(answerList.get(0),imagepath1);
                setAnsweImageView(answerList.get(0),imageview1);
                setAnswerMedia(answerList.get(1),imagepath2);
                setAnsweImageView(answerList.get(1),imageview2);
                setAnswerMedia(answerList.get(2),imagepath3);
                setAnsweImageView(answerList.get(2),imageview3);
                setAnswerMedia(answerList.get(3),imagepath4);
                setAnsweImageView(answerList.get(3),imageview4);
                setAnswerMedia(answerList.get(4),imagepath5);
                setAnsweImageView(answerList.get(4),imageview5);
                setAnswerMedia(answerList.get(5),imagepath6);
                setAnsweImageView(answerList.get(5),imageview6);
            }

        }else {
            System.out.println("Loi hien thi");
            System.out.println("So cau hoi: " + questionCount);
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
    void cancel2(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("gui21.fxml"));
            Parent gui21Parent = loader.load();
            Scene gui21Scene = new Scene(gui21Parent);
            Stage stage = (Stage) cancel2.getScene().getWindow();
            stage.setScene(gui21Scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void savechanges2(ActionEvent event) {
        try {
            // save first
            savechangesandcontinue(event);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("gui21.fxml"));
            Parent gui21Parent = loader.load();
            Scene gui21Scene = new Scene(gui21Parent);
            Stage stage = (Stage) savechanges2.getScene().getWindow();
            stage.setScene(gui21Scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void savechangesandcontinue(ActionEvent event) {
        saveInfo();
        System.out.println("question updated success");
    }

    // for 2 buttons save / save and continue
    public void saveInfo() {
        if (defaultAnswers.get(0) != null) {
            AnswerDao.getInstance().delete(defaultAnswers.get(0).getId());
        }
        if (defaultAnswers.get(1) != null) {
            AnswerDao.getInstance().delete(defaultAnswers.get(1).getId());

        }
        if (defaultAnswers.get(2) != null) {
            AnswerDao.getInstance().delete(defaultAnswers.get(2).getId());
        }
        if (defaultAnswers.get(3) != null) {
            AnswerDao.getInstance().delete(defaultAnswers.get(3).getId());
        }
        if (defaultAnswers.get(4) != null) {
            AnswerDao.getInstance().delete(defaultAnswers.get(4).getId());

        }
        if (defaultAnswers.get(5) != null) {
            AnswerDao.getInstance().delete(defaultAnswers.get(5).getId());
        }
        Set<Answer> answers = new HashSet<>();
        if (!answerChoice1.getText().isEmpty()) {
            Answer da1 = createAnswer(answerChoice1.getText(), answerGrade1.getValue(),newImagepath1);
            da1.setQuestion(currentQuestion);
            answers.add(da1);
        }
        if (!answerChoice2.getText().isEmpty()) {
            Answer da2 = createAnswer(answerChoice2.getText(), answerGrade2.getValue(),newImagepath2);
            da2.setQuestion(currentQuestion);
            answers.add(da2);
        }
        if (!answerChoice3.getText().isEmpty()) {
            Answer da3 = createAnswer(answerChoice3.getText(), answerGrade3.getValue(),newImagepath3);
            da3.setQuestion(currentQuestion);
            answers.add(da3);
        }
        if (!answerChoice4.getText().isEmpty()) {
            Answer da4 = createAnswer(answerChoice4.getText(), answerGrade4.getValue(),newImagepath4);
            da4.setQuestion(currentQuestion);
            answers.add(da4);
        }
        if (!answerChoice5.getText().isEmpty()) {
            Answer da5 = createAnswer(answerChoice5.getText(), answerGrade5.getValue(),newImagepath5);
            da5.setQuestion(currentQuestion);
            answers.add(da5);
        }
        if (!answerChoice6.getText().isEmpty()) {
            Answer da6 = createAnswer(answerChoice6.getText(), answerGrade6.getValue(),newImagepath6);
            da6.setQuestion(currentQuestion);
            answers.add(da6);
        }
        Media media = new Media();
        media.setUrl(newImagepath);
        if(media.getUrl()!=null){
            currentQuestion.setMedia(media);
        }
        int int_value = Integer.parseInt(questionMarkField.getText());

        currentQuestion.setText(questionNameField.getText());
        currentQuestion.setText(questionTextField.getText());
        currentQuestion.setMark(int_value);
        currentQuestion.setAnswers(answers);
        QuestionDao.getInstance().update(currentQuestion);

    }

    public static Answer createAnswer(String choiceText, String gradeInput,String file) {
        Media media = new Media();
        media.setUrl(file);
        Answer answer = new Answer();
        if(media.getUrl()!=null){
            answer.setMedia(media);
        }
        answer.setChoice(choiceText);
        String numberString = gradeInput.replaceAll("%", "");
        double number = Double.parseDouble(numberString) / 100.0;
        answer.setGrade(number);
        boolean boolValue = (number > 0);
        answer.setIs_choice(boolValue);
        return answer;
    }

    @FXML
    void initialize() {
        // Listen to changes in the quiz name field
        questionNameField.textProperty().addListener((observable, oldValue, newValue) -> {
            disablechamthan1(newValue.isEmpty());
        });
        // Listen to changes in the quiz name field
        questionTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            disablechamthan2(newValue.isEmpty());
        });
        // Listen to changes in the quiz name field
        questionMarkField.textProperty().addListener((observable, oldValue, newValue) -> {
            disablechamthan3(newValue.isEmpty());
        });
        setAnswerBox();
        scrollPane.widthProperty().addListener((observable, oldVal, newVal) -> {
            anchorInfoPane.setPrefWidth(newVal.doubleValue());
        });
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
    private String getFileExtension(File file) {
        String extension = "";
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            extension = fileName.substring(dotIndex + 1).toLowerCase();
        }
        return extension;
    }
    private void setContent(String filePath){
        File contentFile = new File(filePath);
        String fileExtension = getFileExtension(contentFile);
        if(fileExtension.equalsIgnoreCase("mp4")){
            setVideo(contentFile);
        } else {
            setImage(contentFile);
        }
    }
    private void setImage(File contentFile){
        preview.setVisible(false);
        ImageView image = new ImageView(contentFile.toURI().toString());
        imageview.setImage(image.getImage());
    }
    private void setVideo(File contentFile){
        imageview.setVisible(false);
        replay.setVisible(true);
        javafx.scene.media.Media media = new javafx.scene.media.Media(contentFile.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        // Gán MediaPlayer vào MediaView để hiển thị video
        preview.setMediaPlayer(mediaPlayer);
        this.mediaPlayer = mediaPlayer;
    }

}
