package tar.Customer;

import tar.DB.DBConnection;
import tar.DB.DisplayDatabase;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import tar.DB.QueryDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import tar.Seller.SellerSceneController;

public class CancelSceneController implements Initializable {

    @FXML
    private TextField sName;
    @FXML
    private Label warnMsg;
    @FXML
    private TableView<?> saleTable;
    DisplayDatabase sData = new DisplayDatabase();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sData.buildData(saleTable, "Select * from saletable;");
        // TODO
    }
    String nameS = "";

    private boolean GetCustomerFields() {
        nameS = sName.getText();
        if(nameS==null || nameS.isEmpty()){
            sName.requestFocus();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Please Enter Customer Name");
            alert.setContentText("Awaiting Response...");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    @FXML
    private void cancelOrders(ActionEvent event) {
        Connection c;
        boolean val = GetCustomerFields();
        if (!val) {
            return;
        }
        ResultSet rs = QueryDatabase.QueryDatabase("Select ID from saletable where Customer='" + nameS + "';");
        if (rs != null) {
            try {
                c = DBConnection.connect();
                while(rs.next()) {
                    Integer id = Integer.parseInt(rs.getString(1));
                    String q1 = "DELETE FROM saletable where Customer='"+nameS+"';";
                    c.createStatement().execute(q1);
                    String q2 = "DELETE FROM saleitemtable where InvoId='"+id+"';";
                    c.createStatement().execute(q2);
                }
                c.close();
            } catch(SQLException ex){
                Logger.getLogger(SellerSceneController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        sName.clear();
        sData.buildData(saleTable, "Select * from saletable;");
    }

}
