package com.ferchoo.conversorjavafx.controller;

import com.ferchoo.conversorjavafx.Main;
import com.ferchoo.conversorjavafx.model.User;
import com.ferchoo.conversorjavafx.service.UserService;
import com.ferchoo.conversorjavafx.util.AlertUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;
    @FXML
    private Label messageLabel;

    private final UserService userService = new UserService();

    @FXML
    private void initialize() {
        loginButton.setOnAction(e -> handleLogin());
        registerButton.setOnAction(e -> handleRegister());
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        boolean authenticatedUser = userService.authenticate(username, password);

        if (authenticatedUser) {
            loadMainView();
        } else {
            messageLabel.setText("Usuario o password incorrectos");
        }
    }

    private void handleRegister() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (userService.register(username, password)) {
            AlertUtils.showInfo("Registro exitoso", "Usuario registrado correctamente");
        } else {
            messageLabel.setText("El usuario ya existe");
        }
    }

    private void loadMainView() {
        try {
            Stage stage = (Stage) loginButton.getScene().getWindow();
            Parent root = FXMLLoader.load(Main.class.getResource("/com/ferchoo/conversorjavafx/view/main.fxml"));
            stage.setScene(new Scene(root, 800, 600));
            stage.setTitle("Conversor de Monedas");
            stage.centerOnScreen();
        } catch (IOException e) {
            AlertUtils.showError("Error", "No se pudo cargar la interfaz principal");
        }
    }

}
