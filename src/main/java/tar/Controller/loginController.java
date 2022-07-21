package tar.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;
import java.awt.*;
import java.io.IOException;
import java.sql.*;

public class loginController {
    @FXML
    private Button loginButton;
    @FXML
    private TextField passTextField;
    @FXML
    private TextField userTextField;

    @FXML
    private Label title;

    @FXML
    private Label subTitle;

    private String username;
    private String password;

    public void nextPage(ActionEvent event) throws IOException, SQLException {

        Connection c = tar.DB.DBConnection.connect();
        username = userTextField.getText();
        password = passTextField.getText();

        Statement mystatement = c.createStatement();
        PreparedStatement preparedStatement;
        ResultSet resultSet = null;

        preparedStatement = c.prepareStatement("SELECT Customer_Name,Customer_Password FROM login WHERE Customer_Name = ?");
        preparedStatement.setString(1, username);
        resultSet = preparedStatement.executeQuery();
        if (!resultSet.isBeforeFirst()) {
            title.setText("Username not found !");
            title.setStyle("-fx-text-fill: red; -fx-font-size: 24px;");
            title.setAlignment(Pos.CENTER_RIGHT);
            subTitle.setText("Try Again ");
            subTitle.setStyle("-fx-text-fill: red; -fx-font-size: 18px;");
            subTitle.setAlignment(Pos.CENTER_RIGHT);


        } else {
            String retrieved_username = "";
            String retrieved_password = "";

            while (resultSet.next()) {
                retrieved_username = resultSet.getString("Customer_Name");
                retrieved_password = resultSet.getString("Customer_Password");
                if (username.equals("admin")) {
                    if (password.equals("admin")) {
                        loginButton.getScene().getWindow().hide();
                        Parent root = FXMLLoader.load(getClass().getResource("/tar/fxml/AdminStoreMain.fxml"));
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.setMaximized(true);
                        stage.show();
//                        stage.setResizable(false);
                    } else {
                        title.setText("Admin password is incorrect !");
                        title.setStyle("-fx-text-fill: red; -fx-font-size: 24px;");
                        title.setAlignment(Pos.CENTER_RIGHT);
                        subTitle.setText("Try Again ");
                        subTitle.setStyle("-fx-text-fill: red; -fx-font-size: 18px;");
                        subTitle.setAlignment(Pos.CENTER_RIGHT);

                    }
                } else if (!retrieved_password.equals(password)) {
                   title.setText("User password is incorrect !");
                   title.setStyle("-fx-text-fill: red; -fx-font-size: 24px;");
                    title.setAlignment(Pos.CENTER_RIGHT);
                    subTitle.setText("Try Again ");
                    subTitle.setStyle("-fx-text-fill: red; -fx-font-size: 18px;");
                    subTitle.setAlignment(Pos.CENTER_RIGHT);

                } else {
                    loginButton.getScene().getWindow().hide();
                    Parent root = FXMLLoader.load(getClass().getResource("/tar/fxml/CustomerStoreMain.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setMaximized(true);
                    stage.show();
//                    stage.setResizable(false);
                }
            }
        }

    }
    public void signUp(ActionEvent event) throws IOException, SQLException {
        loginButton.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/tar/fxml/Register.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
//        stage.setMaximized(true);
        stage.show();
        stage.setResizable(false);
    }
}

