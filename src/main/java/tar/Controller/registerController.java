package tar.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class registerController {
    @FXML
    private TextArea addressTextArea;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField passTextField;

    @FXML
    private TextField phoneTextField;

    @FXML
    void backToLoginButton(ActionEvent event) throws IOException {
        phoneTextField.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/tar/fxml/Login.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
//        stage.setMaximized(true);
        stage.show();
        stage.setResizable(false);
    }

    @FXML
    void submitButton(ActionEvent event) throws SQLException, IOException {
        Connection c = tar.DB.DBConnection.connect();
        Statement mystatement = c.createStatement();
        String sql = "INSERT INTO login (Customer_Name,Customer_Password)VALUES(" +
                "'" + nameTextField.getText() + "',\n" +
                "'" + passTextField.getText() + "');";
        mystatement.execute(sql);

        Statement newstate = c.createStatement();
        String sq = "INSERT INTO customertable (CustomerName,CustomerContact,CustomerAddress)VALUES(" +
                "'" + nameTextField.getText() + "',\n" +
                "'" + phoneTextField.getText() + "',\n" +
                "'" + addressTextArea.getText() + "');";
        newstate.execute(sq);
        phoneTextField.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/tar/fxml/Login.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
//        stage.setMaximized(true);
        stage.show();
        stage.setResizable(false);

    }
}
