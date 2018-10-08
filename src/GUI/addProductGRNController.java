package GUI;

import Func.DB;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class addProductGRNController implements Initializable{
    DB db=new DB();
    @FXML
    private TextField txtItemId;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextField txtPrice;
    @FXML
    private TextField txtCompany;
    @FXML
    private TextField txtStock;
    @FXML
    private TextField txtType;
    @FXML
    private TextField txtSupplierNo;
    @FXML
    private TextField txtStockMin;
    @FXML
    private TextField txtStockMax;
    @FXML
    private Button addButton;
    @FXML
    private Button clearButton;
    @FXML
    private Button cancelButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {




        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String pid,desc,comp,type,sup;
                int stock=0,min=0,max=0;
                double price=0;

                pid=txtItemId.getText();
                desc=txtDescription.getText();
                comp=txtCompany.getText();
                type=txtType.getText();
                sup=txtSupplierNo.getText();
                try{
                    stock=Integer.parseInt(txtStock.getText());
                    min=Integer.parseInt(txtStockMin.getText());
                    max=Integer.parseInt(txtStockMax.getText());
                    price=Double.parseDouble(txtPrice.getText());
                }
                catch (NumberFormatException e)
                {
                    System.out.println(e);
                }

                String addQuery="INSERT INTO product VALUES('"+pid+"','"+desc+"','"+comp+"','"+type+"','"+sup+"',"+price+","+stock+","+min+","+max+")";
                db.putData(addQuery);
            }
        });
        clearButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                txtItemId.setText("");
                txtDescription.setText("");
                txtCompany.setText("");
                txtPrice.setText("");
                txtStock.setText("");
                txtStockMax.setText("");
                txtStockMin.setText("");
                txtSupplierNo.setText("");
                txtType.setText("");
            }
        });

        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage)cancelButton.getScene().getWindow();
                stage.close();
            }
        });


    }
}
