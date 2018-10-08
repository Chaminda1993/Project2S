package GUI;

import Func.DB;

import Func.Functions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProductsCtl implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> al = new ArrayList<String>();
        al.add("pid");
        al.add("description");
        al.add("company");
        al.add("type");
        al.add("supplier");
        ObservableList<String> list= FXCollections.observableArrayList(al);
        cmbPro.setItems(list);
        cmbPro.setValue("description");

        loadTbl();


        tblPro.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                txtItemID.setText(tblPro.getSelectionModel().getSelectedItem().getProID());
                txtDescription.setText(tblPro.getSelectionModel().getSelectedItem().getDescription());
                txtType.setText(tblPro.getSelectionModel().getSelectedItem().getType());
                txtCompany.setText(tblPro.getSelectionModel().getSelectedItem().getCompany());
                txtSupplier.setText(tblPro.getSelectionModel().getSelectedItem().getSupplier());
                txtPrice.setText(Double.toString(tblPro.getSelectionModel().getSelectedItem().getPrice()));
                txtStock.setText(Integer.toString(tblPro.getSelectionModel().getSelectedItem().getStock()));
                txtStockMin.setText(Integer.toString(tblPro.getSelectionModel().getSelectedItem().getStockMin()));
                txtStockMx.setText(Integer.toString(tblPro.getSelectionModel().getSelectedItem().getStockMax()));
            }
        });

        btnSearchPro.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadTbl();
            }
        });

        txtSearchPro.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                loadTbl();
            }
        });

        btnEditPro.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                isUpdate=true;
                textEditable(true);
            }
        });

        btnAddPro.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                isUpdate=false;
                textEditable(true);
                textClear();
                genPID();
            }
        });

        btnRemovePro.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                removeItem();
            }
        });

        btnSavePro.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(isUpdate){
                    saveUpdate();
                }else {
                    saveNew();
                }
            }
        });

        btnCancelPro.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textEditable(false);
                textClear();
            }
        });

    }


    private void loadTbl(){
        try{
            ResultSet rs=db.getData("SELECT * FROM product WHERE "+cmbPro.getValue().toString()+" like '%"+txtSearchPro.getText()+"%'");
            ArrayList<ProductModel> arr=new ArrayList<>();

            try {
                while (rs.next()) {
                    ProductModel pm=new ProductModel(rs.getString("pid"),rs.getString("description"),rs.getString("company"),rs.getString("type"),rs.getString("supplier"),rs.getDouble("price"),rs.getInt("stock"),rs.getInt("stockMinLevel"),rs.getInt("stockMaxLevel"));
                    arr.add(pm);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ObservableList<ProductModel> list= FXCollections.observableArrayList(arr);

            colItem.setCellValueFactory(new PropertyValueFactory<ProductModel,String>("proID"));
            colPrice.setCellValueFactory(new PropertyValueFactory<ProductModel,String>("price"));
            colQty.setCellValueFactory(new PropertyValueFactory<ProductModel,String>("stock"));
            colDescription.setCellValueFactory(new PropertyValueFactory<ProductModel,String>("description"));
            tblPro.setItems(list);
            db.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void removeItem() {
        if(txtItemID.getText().isEmpty()){
            Functions.showMessage("Alert","Select Item", Alert.AlertType.INFORMATION);
            return;
        }
        if(HomePageController.curUser.getUserType().equals("admin")){
            ButtonType bt=Functions.confirmMessage("Remove Item","Are you sure to remove Item?");
            if(bt==ButtonType.YES){
                db.putData("DELETE FROM product WHERE pid='"+txtItemID.getText()+"'");
                Functions.showMessage("Alert","Item has deleted.", Alert.AlertType.CONFIRMATION);
                db.close();
            }
        }else{
            Functions.showMessage("Alert","Login with administrator account.", Alert.AlertType.WARNING);
        }
    }

    private void saveUpdate(){
            if(HomePageController.curUser.getUserType().equals("admin")){
                ButtonType bt=Functions.confirmMessage("Save Item","Are you sure to Save Item?");
                if(bt==ButtonType.YES) {
                    try {
                        db.putData( "UPDATE product SET `description`='" + txtDescription.getText() + "',`type`='" + txtType.getText() + "',`company`='" + txtCompany.getText() + "',`supplier`='" + txtSupplier.getText() + "',`price`='" + Double.valueOf( txtPrice.getText() ) + "',`stock`='" + Integer.valueOf( txtStock.getText() ) + "',`stockMinLevel`='" + Integer.valueOf( txtStockMin.getText() ) + "',`stockMaxLevel`='" + Integer.valueOf( txtStockMx.getText() ) + "' WHERE `pid`='" + txtItemID.getText() + "' " );
                        db.close();
                        loadTbl();
                        textEditable( false );
                    }
                    catch (Exception e){
                    e.printStackTrace();
                    }
                }
            }else{
                Functions.showMessage("Alert","Login with administrator account.", Alert.AlertType.WARNING);
            }

    }

    private void saveNew() {
        if(txtDescription.getText().isEmpty() || txtCompany.getText().isEmpty() || txtSupplier.getText().isEmpty() || txtPrice.getText().isEmpty() || txtType.getText().isEmpty()){
            Functions.showMessage("Alert","Some fields are empty", Alert.AlertType.WARNING);
            return;
        }
        if(HomePageController.curUser.getUserType().equals("admin")) {
            ButtonType bt = Functions.confirmMessage( "Save Item", "Are you sure to Save Item?" );
            if (bt == ButtonType.YES) {
                try {
                    db.putData( "INSERT INTO product(pid,description,company,type,supplier,price,stock,stockMinLevel,stockMaxLevel) VALUES('" + txtItemID.getText() + "','" + txtDescription.getText() + "','" + txtCompany.getText() + "','" + txtType.getText() + "','" + txtSupplier.getText() + "','" + Double.valueOf( txtPrice.getText() ) + "','" + Integer.valueOf( txtStock.getText() ) + "','" + Integer.valueOf( txtStockMin.getText() ) + "','" + Integer.valueOf( txtStockMx.getText() ) + "')" );
                    db.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }else{
            Functions.showMessage("Alert","Login with administrator account.", Alert.AlertType.WARNING);
        }

    }

    private void textEditable(boolean val){
        txtItemID.setEditable(false);
        txtDescription.setEditable(val);
        txtType.setEditable(val);
        txtCompany.setEditable(val);
        txtSupplier.setEditable(val);
        txtType.setEditable(val);
        txtPrice.setEditable(val);
        txtStock.setEditable(val);
        txtStockMin.setEditable(val);
        txtStockMx.setEditable(val);
        btnSavePro.setVisible(val);
        btnCancelPro.setVisible(val);
    }

    private void textClear() {
        txtItemID.clear();
        txtDescription.clear();
        txtType.clear();
        txtCompany.clear();
        txtSupplier.clear();
        txtPrice.clear();
        txtStock.clear();
        txtStockMin.clear();
        txtStockMx.clear();
    }

    private void genPID() {
        try {
            ResultSet rs=db.getData("SELECT MAX(pid) FROM product");
            rs.next();
            String pid=rs.getString("MAX(pid)");
            db.close();
            int pidV=Integer.parseInt(pid.substring(1,pid.length()));
            pidV++;
            txtItemID.setText("p"+pidV);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    DB db = new DB();
    private boolean isUpdate;
    @FXML
    private TableView<ProductModel> tblPro;
    @FXML
    private ComboBox cmbPro;
    @FXML
    private TextField txtSearchPro;
    @FXML
    private Button btnAddPro;
    @FXML
    private Button btnEditPro;
    @FXML
    private Button btnRemovePro;
    @FXML
    private Button btnSearchPro;
    @FXML
    private Button btnSavePro;
    @FXML
    private Button btnCancelPro;
    @FXML
    private TextField txtItemID;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextField txtType;
    @FXML
    private TextField txtCompany;
    @FXML
    private TextField txtSupplier;
    @FXML
    private TextField txtPrice;
    @FXML
    private TextField txtStock;
    @FXML
    private TextField txtStockMin;
    @FXML
    private TextField txtStockMx;
    @FXML
    private TableColumn<ProductModel,String> colItem;
    @FXML
    private TableColumn<ProductModel,String> colPrice;
    @FXML
    private TableColumn<ProductModel,String> colQty;
    @FXML
    private TableColumn<ProductModel,String> colDescription;

}
