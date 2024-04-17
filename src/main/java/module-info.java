module com.example.finalpractice2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.finalpractice2 to javafx.fxml;
    exports com.example.finalpractice2;
}