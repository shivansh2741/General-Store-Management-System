/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tar.Customer;

import tar.DB.DBConnection;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import tar.Seller.CustomerSceneController;

import java.sql.ResultSet;

public class CustomerDetails implements Initializable {

    @FXML
    private TextField cName;

    @FXML
    private Label warnMsg;


//    DisplayDatabase cData = new DisplayDatabase();
    @FXML
    private TableView<?> customertable;
    @FXML
    private TextField cContact;
    @FXML
    private TextArea cAdd;

    @FXML
    private TextField newName;
    @FXML
    private TextField newAddress;
    @FXML
    private TextField newContact;

    String nameC = "";
    String contactC;
    String addC = "";

    String nameNew = "";
    long contactNew = -2;
    String addressNew = "";
    int validId = -1;
    @FXML
    private void CheckValidity(ActionEvent event) {
        Connection c;
        boolean val =  GetCustomerFields();
        if(!val){
            return;
        }
        try{
            c = DBConnection.connect();
            String query = "SELECT id from customertable WHERE CustomerName = '" + nameC +"' AND CustomerContact = '" + contactC +"'";
            ResultSet rs = c.createStatement().executeQuery(query);
            if(rs.next()) {
                validId = rs.getInt("Id");
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Successful");
            alert.setHeaderText("Records have been verified, Mr. "+ nameC);
            alert.setContentText("You can now proceed to update your details");
            alert.showAndWait();
            c.close();

        } catch (SQLException ex) {
            Logger.getLogger(CustomerSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    @FXML
    private void UpdateDetails(ActionEvent event) {
        Connection c;
        boolean val =  GetCustomerFields1();
        if(validId == -1){
            return;
        }
        try{
            c = DBConnection.connect();
            String query = "UPDATE customertable SET CustomerName = '" + nameNew +"',CustomerContact = '" + contactNew +"',CustomerAddress = '" + addressNew +"' where Id ='" + validId +"'";
            c.createStatement().execute(query);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Successful");
            alert.setHeaderText("Records have been modified, Mr. "+ nameNew);
            alert.setContentText("Continue shopping with us !");
            alert.showAndWait();
            c.close();

        } catch (SQLException ex) {
            Logger.getLogger(CustomerSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    private boolean GetCustomerFields1() {
        nameNew = newName.getText();
        contactNew = Long.parseLong(newContact.getText());
        addressNew = newAddress.getText();
        if(nameNew==null || nameNew.isEmpty()){
            cName.requestFocus();
            warnMsg.setText("Pls enter Customer's Name.");
            return false;
        }
        if(contactNew==-2){
            cContact.requestFocus();
            warnMsg.setText("Pls Customer's Contact.");
            return false;
        }
        if(addressNew==null || addressNew.isEmpty()){
            cName.requestFocus();
            warnMsg.setText("Pls enter address");
            return false;
        }
        return true;

    }

//    private void clearAllFields() {
//        cName.clear();
//        cContact.clear();
//        cAdd.clear();
//    }






//    @FXML
//    private void DeleteCustomer(ActionEvent event) {
//        int index = customertable.getSelectionModel().getSelectedIndex();
//        ObservableList data = cData.getData();
//        ObservableList<String> items = (ObservableList) data.get(index);
//        DeleteDatabase.deleteRecord(Integer.parseInt(items.get(0)), "customertable");
//
//        cData.buildData(customertable, "Select * from customertable;");
//    }

    private boolean GetCustomerFields() {
        nameC = cName.getText();
        contactC = cContact.getText();
        if(nameC==null || nameC.isEmpty()){
            cName.requestFocus();
            warnMsg.setText("Pls enter Customer's Name.");
            return false;
        }
        if(contactC==null || contactC.isEmpty()){
            cContact.requestFocus();
            warnMsg.setText("Pls Customer's Contact.");
            return false;
        }
        return true;

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
