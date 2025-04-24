package com.ferchoo.conversorjavafx.controller;

import com.ferchoo.conversorjavafx.model.Conversion;
import com.ferchoo.conversorjavafx.model.Currency;
import com.ferchoo.conversorjavafx.service.CurrencyService;
import com.ferchoo.conversorjavafx.util.AlertUtils;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

public class MainController {

    @FXML
    private ComboBox<Currency> fromCurrencyCombo;
    @FXML
    private ComboBox<Currency> toCurrencyCombo;
    @FXML
    private TextField amountField;
    @FXML
    private TextArea resultArea;
    @FXML
    private Button convertButton;
    @FXML
    private Label welcomeLabel;
    @FXML
    private TableView<Conversion> historyTable;
    @FXML
    private TableColumn<Conversion, String> dateColumn;
    @FXML
    private TableColumn<Conversion, String> fromColumn;
    @FXML
    private TableColumn<Conversion, String> toColumn;
    @FXML
    private TableColumn<Conversion, String> amountColumn;
    @FXML
    private TableColumn<Conversion, String> resultColumn;

    private final CurrencyService currencyService = new CurrencyService();
    private final ObservableList<Conversion> historyData = FXCollections.observableArrayList();


    @FXML
    private void handleExit() {
        Platform.exit();
    }

    @FXML
    private void initialize() {
        setupCurrencyCombos();
        setupHistoryTable();
        loadHistory();

        convertButton.setOnAction(e -> convertCurrency());
    }

    private void setupCurrencyCombos() {
        List<Currency> currencies = currencyService.getAllCurrencies();
        fromCurrencyCombo.setItems(FXCollections.observableArrayList(currencies));
        toCurrencyCombo.setItems(FXCollections.observableArrayList(currencies));

        //Seleccionar valores por defecto
        fromCurrencyCombo.getSelectionModel().selectFirst();
        toCurrencyCombo.getSelectionModel().select(1);
    }

    private void setupHistoryTable() {
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        fromColumn.setCellValueFactory(cellData -> cellData.getValue().fromCurrencyProperty());
        toColumn.setCellValueFactory(cellData -> cellData.getValue().toCurrencyProperty());
        amountColumn.setCellValueFactory(cellData -> cellData.getValue().amountProperty());
        resultColumn.setCellValueFactory(cellData -> cellData.getValue().resultProperty());
    }

    private void loadHistory() {
        historyData.setAll(currencyService.getConversionHistory());
    }

    private void convertCurrency() {
        try {
            Currency from = fromCurrencyCombo.getValue();
            Currency to = toCurrencyCombo.getValue();
            double amount = Double.parseDouble(amountField.getText());

            double result = currencyService.convert(from.getCode(), to.getCode(), amount);

            String resultText = String.format("%.2f %s = %.2f %s", amount, from.getCode(), result, to.getCode());

            resultArea.setText(resultText);

            //Actualizar historia
            loadHistory();

        } catch (NumberFormatException e) {
            AlertUtils.showError("Error", "Ingrese un monto válido");
        } catch (Exception e) {
            AlertUtils.showError("Error", "No se pudo realizar la conversion: " + e.getMessage());
        }
    }

    public void setUsername(String username) {
        welcomeLabel.setText("Bienvenido, " + username);
    }

}
