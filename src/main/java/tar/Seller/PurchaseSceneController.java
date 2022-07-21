package tar.Seller;

import tar.DB.DBConnection;
import tar.DB.DeleteDatabase;
import tar.DB.DisplayDatabase;
import tar.DB.QueryDatabase;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
//import org.controlsfx.control.textfield.AutoCompletionBinding;
//import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author tanzeem
 */
public class PurchaseSceneController implements Initializable {
    @FXML
    private TextField pName;
    @FXML

    private TextField sName;
    @FXML
    private TextField bNum;
    @FXML
    private TextField quantity;
    @FXML
    private TextField pRate;
    @FXML
    private TextField sRate;
    @FXML
    private DatePicker eDate;
    @FXML
    private DatePicker pDate;
    @FXML
    private Label warnMsg;
    ObservableList<String> pList = FXCollections.observableArrayList();
    DisplayDatabase pData = new DisplayDatabase();
    @FXML
    private TableView<?> purchasetable;


    /**
     * Initializes the controller class.
     */
    ObservableList<String> sList = FXCollections.observableArrayList();
    @FXML
    private Button btnAdd;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ResultSet rs = QueryDatabase.QueryDatabase("Select SellerName from sellertable;");
        if (rs != null) {
            try {
                while (rs.next()) {
                    sList.add(rs.getString(1));
                }
            } catch (SQLException ex) {
                Logger.getLogger(PurchaseSceneController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

//      AutoCompletionBinding<String> auto = TextFields.bindAutoCompletion(sName,sList);

        pData.buildData(purchasetable, "Select * from purchasetable;");

        // TODO
    }

    String nameP = "";
    String nameS = "";
    String batchNum = "";
    String qty = "";
    LocalDate dateP;
    double rateP = 0;
    double rateS = 0;
    LocalDate dateE;

    @FXML
    private void addPurchase(ActionEvent event) {
        Connection c;
        boolean val = GetPurchaseFields();
        if (!val) {
            return;
        }
        String query;
        try {
            if (!update) {
                c = DBConnection.connect();
                query = "INSERT INTO purchasetable (ProductName,SellerName,BatchNumber,Quantity,PurchaseDate,PurchaseRate,SellingRate,ExpiryDate)VALUES(" +
                        "'" + nameP + "',\n" +
                        "'" + nameS + "',\n" +
                        "'" + batchNum + "',\n" +
                        "'" + qty + "',\n" +
                        "'" + dateP + "',\n" +
                        "'" + rateP + "',\n" +
                        "'" + rateS + "',\n" +
                        "'" + dateE + "');";

                c.createStatement().execute(query);
            } else {
                c = DBConnection.connect();
                query = "Update purchasetable set ProductName='" + nameP + "',SellerName='" + nameS + "',BatchNumber='" + batchNum + "',"
                        + "Quantity='" + qty + "',PurchaseDate='" + dateP + "',PurchaseRate='" + rateP + "',SellingRate='" + rateS + "',ExpiryDate='" + dateE + "'Where Id='" + id + "';";
                System.out.println(query);
                c.createStatement().executeUpdate(query);

            }

            if (update) {


                qty = String.valueOf(Double.parseDouble(qty) - Double.parseDouble(oldQty));
                query = "Update inventorytable set Quantity=Quantity+" + qty + " where ProductName='" + nameP + "' And BatchNumber='" + batchNum + "';";
                c.createStatement().executeUpdate(query);


            } else {
                ResultSet rs = QueryDatabase.QueryDatabase("Select * from inventorytable where ProductName='" + nameP + "' And BatchNumber='" + batchNum + "';");
                if (rs != null) {
                    if (rs.next()) {
                        query = "Update inventorytable set Quantity=Quantity+" + qty + " where ProductName='" + nameP + "' And BatchNumber='" + batchNum + "';";
                    } else {
                        query = "INSERT INTO inventorytable (ProductName,BatchNumber,Quantity,PurchaseDate,PurchaseRate,SellingRate,ExpiryDate)VALUES(" +
                                "'" + nameP + "',\n" +
                                "'" + batchNum + "',\n" +
                                "'" + qty + "',\n" +
                                "'" + dateP + "',\n" +
                                "'" + rateP + "',\n" +
                                "'" + rateS + "',\n" +
                                "'" + dateE + "');";
                    }
                } else {
                    query = "INSERT INTO inventorytable (ProductName,BatchNumber,Quantity,PurchaseDate,PurchaseRate,SellingRate,ExpiryDate)VALUES(" +
                            "'" + nameP + "',\n" +
                            "'" + batchNum + "',\n" +
                            "'" + qty + "',\n" +
                            "'" + dateP + "',\n" +
                            "'" + rateP + "',\n" +
                            "'" + rateS + "',\n" +
                            "'" + dateE + "');";
                }
            }

            c.createStatement().execute(query);


            c.close();

        } catch (SQLException ex) {
            Logger.getLogger(PurchaseSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }

        clearAllFields();
        pData.buildData(purchasetable, "Select * from purchasetable;");


    }

    private boolean GetPurchaseFields() {
        nameP = pName.getText();

        nameS = sName.getText();

        batchNum = bNum.getText();
        qty = quantity.getText();
        dateP = pDate.getValue();
        rateP = Double.parseDouble(pRate.getText());
        rateS = Double.parseDouble(sRate.getText());
        dateE = eDate.getValue();

        if (nameP == null || nameP.isEmpty()) {
            pName.requestFocus();
            warnMsg.setText("Pls enter Product Name.");
            return false;
        }
        if (nameS == null || nameS.isEmpty()) {
            sName.requestFocus();
            warnMsg.setText("Pls enterSellers Name.");
            return false;
        }
        if (batchNum == null || batchNum.isEmpty()) {
            bNum.requestFocus();
            warnMsg.setText("Pls enter Batch Number.");
            return false;
        }
        if (qty == null || qty.isEmpty()) {
            quantity.requestFocus();
            warnMsg.setText("Pls enter Quantity.");
            return false;
        }
        if (dateE == null) {
            eDate.requestFocus();
            warnMsg.setText("Pls enter Expiry Date.");
            return false;
        }


        return true;
    }

    private void clearAllFields() {
        pName.clear();
        sName.clear();
        bNum.clear();
        quantity.clear();
        pRate.clear();
        sRate.clear();
        pName.requestFocus();
        update = false;
        btnAdd.setText("Add");
    }

    @FXML
    private void DeletePurchcase(ActionEvent event) {
        int index = purchasetable.getSelectionModel().getSelectedIndex();
        ObservableList data = pData.getData();
        ObservableList<String> items = (ObservableList) data.get(index);
        DeleteDatabase.deleteRecord(Integer.parseInt(items.get(0)), "purchasetable");

        pData.buildData(purchasetable, "Select * from purchasetable;");
    }

    int id;
    boolean update = false;
    String oldQty = "0";
    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @FXML
    private void UpdatePurchcase(ActionEvent event) {
        int index = purchasetable.getSelectionModel().getFocusedIndex();
        ObservableList<ObservableList> data = pData.getData();
        ObservableList<String> itemData = data.get(index);

        oldQty = itemData.get(4);
        id = Integer.parseInt(itemData.get(0));
        pName.setText(itemData.get(1));
        sName.setText(itemData.get(2));
        bNum.setText(itemData.get(3));
        quantity.setText(oldQty);

        pDate.setValue(LocalDate.parse(itemData.get(5)));
        pRate.setText(itemData.get(6));
        sRate.setText(itemData.get(7));
        eDate.setValue(LocalDate.parse(itemData.get(8)));
        update = true;
        btnAdd.setText("Update");
    }


}