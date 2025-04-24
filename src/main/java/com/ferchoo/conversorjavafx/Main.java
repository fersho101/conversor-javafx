//package com.ferchoo.conversorjavafx;
//
//import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//import java.util.Objects;
//
//public class Main extends Application {
//    @Override
//    public void start(Stage stage) throws Exception {
//        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/ferchoo/conversorjavafx/view/login.fxml"))); //TODO: Revisar dirección
//
//        Scene scene = new Scene(root, 600, 400);
//        scene.getStylesheets().add(
//                Objects.requireNonNull(
//                        getClass().getResource("/com/ferchoo/conversorjavafx/view/style.css")).toExternalForm()); //TODO: Revisar dirección
//        stage.setTitle("Conversor de monedas - Login");
//        stage.setScene(scene);
//        stage.setResizable(false);
//        stage.show();
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}

package com.ferchoo.conversorjavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Carga el FXML usando el classloader
        URL fxmlUrl = getClass().getResource("/com/ferchoo/conversorjavafx/view/login.fxml");
        if (fxmlUrl == null) {
            throw new IOException("No se pudo encontrar el archivo FXML. Ruta incorrecta: "
                    + "/com/ferchoo/conversorjavafx/view/login.fxml");
        }

        FXMLLoader loader = new FXMLLoader(fxmlUrl);
        Parent root = loader.load();

        Scene scene = new Scene(root, 600, 400);
        scene.getStylesheets().add(getClass().getResource("/com/ferchoo/conversorjavafx/view/styles.css").toExternalForm());

        stage.setTitle("Conversor de Monedas - Login");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}