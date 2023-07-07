package com.example.btl_35.viewController;

import com.example.btl_35.entity.Answer;
import com.example.btl_35.entity.Question;
import com.example.btl_35.entity.Quiz;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class gui72 {
    private Quiz currentQuiz;
    public void setQuiz(Quiz quiz){
        this.currentQuiz = quiz;
    }
    @FXML
    private Button gui73;
    @FXML
    private Button cancel;
    @FXML
    private Button exportButton;
    @FXML
    private void exportPDF(Event event) throws FileNotFoundException, DocumentException {
        List<Question> questions = new ArrayList<>(currentQuiz.getListQuestionQuiz());
        Double totalMark = 0.0;
        for(Question question : questions){
            if(question.getMark() != null){
                totalMark += question.getMark();
            } else {
                totalMark += 1;
            }
        }
        Document document = new com.itextpdf.text.Document();

        try {
            // Yêu cầu người dùng nhập mật khẩu
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Mật khẩu");
            dialog.setHeaderText("Thiết lập mật khẩu cho tệp PDF");
            dialog.setContentText("Mật khẩu:");
            dialog.setResizable(true);
            String password = dialog.showAndWait().orElse("");
            // tao 1 filechooser
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save PDF");
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
            File outputFile = fileChooser.showSaveDialog(null);

            if (outputFile != null) {
                FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(outputFile));
                writer.setEncryption(
                        password.getBytes(),
                        password.getBytes(),
                        PdfWriter.ALLOW_PRINTING,
                        PdfWriter.ENCRYPTION_AES_128
                );
                // load font tu folder
                InputStream fontStream = getClass().getResourceAsStream("/fonts/DroidSansMono.ttf");
                assert fontStream != null;
                BaseFont unicodeFont = BaseFont.createFont("DroidSansMono.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, true, fontStream.readAllBytes(), null);

                // tao 1 doi tuong font moi
                com.itextpdf.text.Font vietnameseFont = new com.itextpdf.text.Font(unicodeFont, 12);

                document.open();
                Paragraph header1 = new Paragraph("Tên Quiz: " + currentQuiz.getName(), vietnameseFont);
                Paragraph header2 = new Paragraph("Số lượng câu hỏi: " + currentQuiz.getListQuestionQuiz().size(), vietnameseFont);
                Paragraph header3 = new Paragraph("Tổng điểm: " + totalMark, vietnameseFont);
                document.add(header1);
                document.add(header2);
                document.add(header3);

                // add an empty paragraph with spacing before the line separator
                Paragraph emptyParagraph = new Paragraph();
                emptyParagraph.setSpacingAfter(5f);
                document.add(emptyParagraph);
                document.add(new LineSeparator());
                for (int i = 0; i < questions.size(); i++) {
                    Question question = questions.get(i);
                    Paragraph p1 = new Paragraph("Câu " + (i+1) + ": " + question.getText(), vietnameseFont);
                    document.add(p1);

//                    if (question.getMedia() != null && question.getMedia().getUrl().endsWith("png")) {
//                        try {
//                            System.out.println("Link: "+question.getMedia().getUrl());
//                            // load the image from the resources directory
//                            InputStream imageStream = getClass().getResourceAsStream(question.getMedia().getUrl());
//                            assert imageStream != null;
//                            com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance(IOUtils.toByteArray(imageStream));
//                            image.scaleToFit(320f, 180);
//                            // add the image to the PDF document
//                            document.add(image);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }

                    List<Answer> answers = new ArrayList<>(question.getAnswers());
                    for(int j = 0; j < answers.size(); j++){
                        Answer answer = answers.get(j);
                        Paragraph answerPara = new Paragraph((char)('A' + j) + ". " + answer.getChoice(), vietnameseFont);
                        document.add(answerPara);
                    }
                    // add an empty paragraph with spacing before the line separator
                    document.add(emptyParagraph);
                }
                document.close();
                // Hiển thị thông báo khi xuất tệp PDF thành công
                showSuccessAlert("Xuất tệp PDF thành công", "Tệp PDF đã được xuất thành công,click OK để tiếp tục");
        }
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void gui73(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("gui73.fxml"));
            Parent gui73Parent = loader.load();
            gui73 controller = loader.getController();
            controller.setQuiz(currentQuiz);
            controller.setUpQuiz(LocalTime.now()); // setup also the start time
            Scene gui73Scene = new Scene(gui73Parent);
            Stage stage = (Stage) gui73.getScene().getWindow();
            stage.setScene(gui73Scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void cancel(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gui61.fxml"));
        Parent gui61Parent = loader.load();
        gui61 controller = loader.getController();
        controller.setQuiz(currentQuiz);
        controller.setUpQuiz();
        Scene gui61Scene = new Scene(gui61Parent);
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.setScene(gui61Scene);
    }
    private void showSuccessAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}