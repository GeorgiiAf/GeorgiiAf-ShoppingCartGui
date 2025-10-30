package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class ShoppingCart extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        ResourceBundle bundle = ResourceBundle.getBundle("messages", new Locale("en", "US"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/shoppingcart.fxml"));
        loader.setResources(bundle);
        Parent root = loader.load();
        primaryStage.setTitle("Shopping Cart");
        primaryStage.setScene(new Scene(root, 400, 400));
        primaryStage.show();
    }
}
