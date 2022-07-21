package tar;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;

public class Main extends Application {
    @Override
    public void start (Stage stage) throws Exception {
            Parent root = FXMLLoader.load(getClass().getResource("/tar/fxml/Login.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
//            stage.setMaximized(true);
            stage.show();
            stage.setResizable(false);
    }
    public static void main(String[] args) throws SQLException {
        launch(args);
    }
}