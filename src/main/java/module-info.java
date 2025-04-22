module com.ferchoo.conversorjavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.ferchoo.conversorjavafx to javafx.fxml;
    exports com.ferchoo.conversorjavafx;
}