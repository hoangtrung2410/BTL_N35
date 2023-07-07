module com.example.btl_n35 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.btl_n35 to javafx.fxml;
    exports com.example.btl_n35;
}