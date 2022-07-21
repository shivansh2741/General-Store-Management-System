package tar.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class StoreMainController implements Initializable {

    @FXML
    private ToggleGroup g1;
    @FXML
    private BorderPane rootLayout;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        changeScene("/tar/fxml/OpeningPage.fxml");
        // TODO
    }

    @FXML
    private void SetSaleScene(ActionEvent event) {
        changeScene("/tar/fxml/SaleCounterScene.fxml");
    }

    @FXML
    private void SetCancelScene(ActionEvent event) {
        changeScene("/tar/fxml/CancelScene.fxml");
    }

    @FXML
    private void SetPurchaseScene(ActionEvent event) {
        changeScene("/tar/fxml/PurchaseScene.fxml");
    }

    @FXML
    private void SetCoustomerScene(ActionEvent event) {
        changeScene("/tar/fxml/CustomerScene.fxml");

    }

    @FXML
    private void SetCustomerDetails(ActionEvent event) {
        changeScene("/tar/fxml/CustomerDetails.fxml");

    }

    @FXML
    private void SetSellerScene(ActionEvent event) {
        changeScene("/tar/fxml/SellerScene.fxml");
    }

    public void changeScene(String scenePath) {

        FXMLLoader loader;
        loader = new FXMLLoader(getClass().getResource(scenePath));
        AnchorPane pane = new AnchorPane();
        try {
            pane = (AnchorPane) loader.load();
            rootLayout.setCenter(pane);
        } catch (Exception e) {
        }



    }

    @FXML
    private void SetInventoryScene(ActionEvent event) {
        changeScene("/tar/fxml/InventoryScene.fxml");
    }
}
    

