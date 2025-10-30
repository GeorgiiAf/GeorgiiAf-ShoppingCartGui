package app;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.*;

public class ShoppingCartController {

    @FXML private ChoiceBox<String> languageChoiceBox;
    @FXML private Label numberLabel, totalLabel;
    @FXML private TextField numberField;
    @FXML private VBox itemBox;
    @FXML private Button calculateButton;
    @FXML private Button generateItemsButton;

    private ResourceBundle bundle;

    @FXML
    public void initialize() {
        languageChoiceBox.getItems().addAll("English", "Finnish", "Swedish", "Japanese");
        languageChoiceBox.setValue("English");

        languageChoiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            switch (newVal) {
                case "Finnish" -> bundle = ResourceBundle.getBundle("messages", new Locale("fi", "FI"));
                case "Swedish" -> bundle = ResourceBundle.getBundle("messages", new Locale("sv", "SE"));
                case "Japanese" -> bundle = ResourceBundle.getBundle("messages", new Locale("ja", "JP"));
                default -> bundle = ResourceBundle.getBundle("messages", new Locale("en", "US"));
            }
            updateLabels();
        });

        bundle = ResourceBundle.getBundle("messages", new Locale("en", "US"));
        updateLabels();
    }

    private void updateLabels() {
        numberLabel.setText(bundle.getString("enter.number.of.items"));
        totalLabel.setText(bundle.getString("total.cost"));
        calculateButton.setText(bundle.getString("calculate.button"));
        generateItemsButton.setText(bundle.getString("generate.button"));

    }

    @FXML
    private void generateItemFields() {
        itemBox.getChildren().clear();
        int num;
        try {
            num = Integer.parseInt(numberField.getText());
        } catch (NumberFormatException e) {
            totalLabel.setText("Invalid number");
            return;
        }

        for (int i = 1; i <= num; i++) {
            Label priceL = new Label(bundle.getString("enter.price.for.item") + " " + i + ":");
            TextField priceF = new TextField();
            Label qtyL = new Label(bundle.getString("enter.quantity.for.item") + " " + i + ":");
            TextField qtyF = new TextField();

            HBox row = new HBox(10, priceL, priceF, qtyL, qtyF);
            itemBox.getChildren().add(row);
        }
    }

    @FXML
    private void calculateTotal() {
        List<Double> prices = new ArrayList<>();
        List<Integer> quantities = new ArrayList<>();

        for (Node node : itemBox.getChildren()) {
            if (node instanceof HBox hbox) {
                try {
                    TextField priceField = (TextField) hbox.getChildren().get(1);
                    TextField qtyField = (TextField) hbox.getChildren().get(3);
                    double price = Double.parseDouble(priceField.getText());
                    int qty = Integer.parseInt(qtyField.getText());
                    prices.add(price);
                    quantities.add(qty);
                } catch (Exception ignored) {}
            }
        }

        double total = ShoppingCartLogic.calculateTotal(prices, quantities);
        totalLabel.setText(bundle.getString("total.cost") + " " + total);
    }
}
