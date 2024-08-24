package SrcCode;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import java.io.IOException;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class NewFXMain extends Application {

    private static Stage stg;
    public static final String CURRENCY = "LE";

    @Override
    public void start(Stage primaryStage) throws Exception {
        stg = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("Register.fxml"));
        primaryStage.setTitle("EL-JACKET EL-PUMP");
        primaryStage.setScene(new Scene(root));
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(400);
        primaryStage.setMaxWidth(Double.MAX_VALUE);
        primaryStage.setMaxHeight(Double.MAX_VALUE);
        primaryStage.show();
        primaryStage.setResizable(false);
        Image image =new Image("data\\cat cry.JPEG");
        primaryStage.getIcons().add(image);
    }

    public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
