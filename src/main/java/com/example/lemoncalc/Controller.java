package com.example.lemoncalc;

import java.time.LocalDateTime;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    //экран расчета
    @FXML
    private AnchorPane mainScene;
    @FXML
    private ChoiceBox<String> productSelection;
    private final String[] products = {"Конфитюр Абрикос", "Тест"};
    @FXML
    private TextField amountTextField;
    private double requestedAmount;
    @FXML
    private Label amountInputAlert;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //выбор продукции
        if (productSelection != null) {
            productSelection.getItems().addAll(products);
            productSelection.setOnAction(this::selectProduct);
        }
    }
    public void selectProduct (ActionEvent event) {
        String selectedProduct = productSelection.getValue();
        System.out.println(LocalDateTime.now() + " Выбрана продукция " + selectedProduct);
    }
    public void selectAmountAndCalculate () {
        boolean amountInputCorrect = true;
        try {
            requestedAmount = Double.parseDouble(amountTextField.getText());
            System.out.println(LocalDateTime.now() + " Выбрано количество продукции " + requestedAmount + " т");
        }
        catch (NumberFormatException e) {
            amountInputAlert.setText("Введите целую и дробную часть через точку");
            calculationResult.setText("");
            amountInputCorrect = false;
        }
        if (amountInputCorrect) {
            amountInputAlert.setText("");
            calculationResult.setText("Для приготовления " + requestedAmount + " т " + productSelection.getValue() + " понадобится " + requestedAmount*recipeWater + " т воды");
        }
    }
    //расчет
    public Label calculationResult;
    public void switchToChangeScene(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LemonCalculator.class.getResource("changingScene.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
        System.out.println(LocalDateTime.now() + " Открыт экран рецепутр");
    }
    public void logOut() {
        Stage stage = (Stage) mainScene.getScene().getWindow();
        stage.close();
        System.out.println(LocalDateTime.now() + " Приложение закрыто");
    }
    //экран изменения рецептуры
    @FXML
    private AnchorPane changingScene;
    @FXML
    private Label recipeWaterLabel;
    private double recipeWater = 0.5;
    @FXML
    private TextField changeRecipeWater;
    @FXML
    private Label changedRecipeWater;
    public void refreshRecipes () {
        recipeWaterLabel.setText(recipeWater + " т");
        System.out.println(LocalDateTime.now() + " Рецептуры обновлены");
    }
    public void changeRecipeWater () {
        boolean recipeInputCorrect = true;
        try {
            recipeWater = Double.parseDouble(changeRecipeWater.getText());
        }
        catch (NumberFormatException e) {
            changedRecipeWater.setText("Введите целую и дробную часть через точку");
            recipeInputCorrect = false;
        }
        if (recipeInputCorrect) {
            changedRecipeWater.setText("рецептура изменена");
            System.out.println(LocalDateTime.now() + " Изменена рецептура воды " + recipeWater + " т");
        }
    }

    public void switchToMainScene(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LemonCalculator.class.getResource("mainScene.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
        System.out.println(LocalDateTime.now() + " Открыт экран расчета");
    }
}