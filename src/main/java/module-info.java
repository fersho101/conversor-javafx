module com.ferchoo.conversorjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.httpcomponents.httpclient;
    requires com.google.gson;
    requires org.apache.httpcomponents.httpcore;

    opens com.ferchoo.conversorjavafx to javafx.fxml;
    opens com.ferchoo.conversorjavafx.controller to javafx.fxml;
    opens com.ferchoo.conversorjavafx.model to javafx.fxml;
    opens com.ferchoo.conversorjavafx.service to org.apache.httpcomponents.httpclient;
    opens com.ferchoo.conversorjavafx.view to javafx.fxml;

    exports com.ferchoo.conversorjavafx;
    exports com.ferchoo.conversorjavafx.controller;
}