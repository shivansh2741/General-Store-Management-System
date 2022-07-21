package tar.Customer;

import tar.DB.DBConnection;
import tar.DB.QueryDatabase;
import tar.Items;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

public class SaleCounterSceneController implements Initializable {

    @FXML
    private TextField pName;
    @FXML
    private TextField quantity;
    @FXML
    private TextField cName;
    @FXML
    private DatePicker sDate;
    @FXML
    private ComboBox<String> batchCombo;

    @FXML
    private TableView<Items> saleTable;
    @FXML
    private Label expiry;
    //DisplayDatabase invoiceData = new DisplayDatabase();
    ObservableList<String> bList = FXCollections.observableArrayList();
    ObservableList<String> pList = FXCollections.observableArrayList();
    ObservableList<String> cList = FXCollections.observableArrayList();

    @FXML
    private Label sellRate;
    @FXML
    private Label totalL;
    @FXML
    private Label warnMsg;
    @FXML
    private Label gTotal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createSaleTable();
//         invoiceData.buildData(invoiceTable, "Select * from InvoiceTable;");
        ResultSet rs = QueryDatabase.QueryDatabase("Select CustomerName from customertable;");
        if (rs != null) {
            try {
                while (rs.next()) {
                    cList.add(rs.getString(1));
                }
            } catch (SQLException ex) {
                Logger.getLogger(SaleCounterSceneController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

//        AutoCompletionBinding<String> autoCust = TextFields.bindAutoCompletion(cName, cList);

        rs = QueryDatabase.QueryDatabase("Select Distinct(ProductName) from inventorytable;");
        if (rs != null) {
            try {
                while (rs.next()) {
                    pList.add(rs.getString(1));
                }
            } catch (SQLException ex) {
                Logger.getLogger(SaleCounterSceneController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        AutoCompletionBinding<String> auto = TextFields.bindAutoCompletion(pName, pList);
        auto.setOnAutoCompleted(

                e -> {
                    setBatch(e.getCompletion());
                });

    }

    String nameP = "";
    String batch = "";
    double qty = 0;
    String nameC = "";
    double tAmount = 0;
    LocalDateTime iDate;
    double rate;
    double amount = 0;
    String expiryD = "";
    ObservableList<Items> saleList = FXCollections.observableArrayList();

    @FXML
    private void addItem(ActionEvent event) {
        getSaleFields();
        saleList.add(new Items(nameP, batch, qty, String.valueOf(amount)));
        saleTable.setItems(saleList);
        tAmount = tAmount + amount;
        System.out.println(tAmount);
        gTotal.setText(String.format("%.2f", tAmount));
        clearSaleFields();

        AutoCompletionBinding<String> auto = TextFields.bindAutoCompletion(pName, pList);
        auto.setOnAutoCompleted(

                e -> {
                    setBatch(e.getCompletion());
                });


    }

    private void createSaleTable() {

        TableColumn pro_name = new TableColumn("ProductName");
        pro_name.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Items, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Items, String> param) {
                return new SimpleStringProperty(param.getValue().getNameP());
            }
        });
        saleTable.getColumns().addAll(pro_name);

        TableColumn col_batch = new TableColumn("Batch");
        col_batch.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Items, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Items, String> param) {
                return new SimpleStringProperty(param.getValue().getBatch());
            }
        });
        saleTable.getColumns().addAll(col_batch);

        TableColumn col_qty = new TableColumn("Quantity");
        col_qty.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Items, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Items, String> param) {
                return new SimpleStringProperty(String.valueOf(param.getValue().getQty()));
            }
        });

        saleTable.getColumns().addAll(col_qty);

        TableColumn col_cName = new TableColumn("Amount");
        col_cName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Items, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Items, String> param) {
                return new SimpleStringProperty(param.getValue().getAmount());
            }
        });
        saleTable.getColumns().addAll(col_cName);


    }

    @FXML
    private void getRate() {
//        sellRate.setText("");

        String baCode = batchCombo.getValue();
        String proName = pName.getText();
        String sQty = quantity.getText();

        if (sQty == null || sQty.isEmpty()) {
            qty = 1;

        } else {
            qty = Double.parseDouble(sQty);
        }

        if (baCode == null || baCode.isEmpty()) {
            return;
        }

        if (proName == null || proName.isEmpty()) {
            return;
        }
        ResultSet rs = QueryDatabase.QueryDatabase("Select SellingRate,ExpiryDate from inventorytable where ProductName='" + proName + "' AND BatchNumber = '" + baCode + "';");
        if (rs != null) {
            try {
                if (rs.next()) {
                    rate = Double.parseDouble(rs.getString(1));
                    sellRate.setText(rs.getString(1));

                    expiry.setText(rs.getString(2));
                    amount = rate * qty;
                    totalL.setText(String.valueOf(amount));

                }
            } catch (SQLException ex) {
                Logger.getLogger(SaleCounterSceneController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }


    }


    @FXML
    private void RecordSale(ActionEvent event) {
        boolean val = getInvoiceFields();
        if (!val) {
            return;
        }

        Connection c;
        try {
            c = DBConnection.connect();
            System.out.println(tAmount);
            String query = "INSERT INTO saletable (Date,Customer,TotalAmount)VALUES(" +
                    "'" + iDate + "',\n" +
                    "'" + nameC + "',\n" +
                    "'" + tAmount + "');";

            PreparedStatement ps = c.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            String invoId = rs.getString(1);
            for (Items i : saleList) {

                query = "INSERT INTO saleitemtable (InvoId,ProductName,Batch,Quantity,Amount)VALUES(" +
                        "'" + invoId + "',\n" +
                        "'" + i.getNameP() + "',\n" +
                        "'" + i.getBatch() + "',\n" +
                        "'" + i.getQty() + "',\n" +
                        "'" + i.getAmount() + "');";

                c.createStatement().execute(query);

                String iQuery = "Update inventorytable set Quantity=Quantity-" + i.getQty() + " where ProductName='" + i.getNameP() + "' And BatchNumber='" + i.getBatch() + "';";
                c.createStatement().execute(iQuery);

            }


            c.close();

        } catch (SQLException ex) {
            Logger.getLogger(SaleCounterSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }

        clearAllFields();
//         invoiceData.buildData(invoiceTable, "Select * from InvoiceTable;");
    }

    @FXML
    private void DeleteSale(ActionEvent event) {
        int index = saleTable.getSelectionModel().getSelectedIndex();
        Items i = saleList.get(index);
        tAmount -= Double.parseDouble(i.getAmount());
        gTotal.setText(String.format("%.2f", tAmount));
        saleList.remove(index);
        saleTable.refresh();
    }


    private void getSaleFields() {
        nameP = pName.getText();


        if (nameP == null || nameP.isEmpty()) {
            pName.requestFocus();
            warnMsg.setText("Pls enter product name.");
            return;
        }
        batch = batchCombo.getValue();
        qty = Double.parseDouble(quantity.getText());
    }

    private void clearSaleFields() {
        pName.clear();
        sellRate.setText("");
        expiry.setText("");
        quantity.clear();
        pList.remove(nameP);

        totalL.setText("0.0");

    }

    private boolean getInvoiceFields() {
        nameC = cName.getText();
        iDate = LocalDateTime.of(sDate.getValue(), LocalTime.now());

//        tAmount = rate*qty;


        return true;

    }

    private void clearAllFields() {
        cName.clear();
        amount = 0;
        tAmount = 0;
        gTotal.setText("0.0");
        saleList.clear();
    }

    private void setBatch(String completion) {

        String proName = pName.getText();
        ResultSet rs = QueryDatabase.QueryDatabase("Select Distinct(BatchNumber) from inventorytable where productName = '" + proName + "';");
        if (rs != null) {
            try {
                while (rs.next()) {

                    bList.add(rs.getString(1));
                }
            } catch (SQLException ex) {
                Logger.getLogger(SaleCounterSceneController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        batchCombo.setItems(bList);
        batchCombo.setValue(bList.get(0));
        qty = 1;
        quantity.setText("1");
        getRate();
    }

    @FXML
    private void getBatch(ActionEvent event) {
        String proName = pName.getText();
        bList.clear();
        ResultSet rs = QueryDatabase.QueryDatabase("Select Distinct(BatchNumber) from inventorytable where productName = '" + proName + "';");
        if (rs != null) {
            try {
                while (rs.next()) {

                    bList.add(rs.getString(1));
                }
            } catch (SQLException ex) {
                Logger.getLogger(SaleCounterSceneController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        batchCombo.setItems(bList);
    }

    @FXML
    private void calcTotal(ActionEvent event) {

        String proName = pName.getText();
        String sQty = quantity.getText();

        if (sQty == null || sQty.isEmpty()) {
            qty = 1;

        } else {
            qty = Double.parseDouble(sQty);
        }


        if (proName == null || proName.isEmpty()) {
            warnMsg.setText("Select Product Name.");
            return;
        }

        amount = rate * qty;
        totalL.setText(String.valueOf(amount));


    }


}