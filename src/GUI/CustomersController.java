package GUI;

import Func.DB;
import Func.Functions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CustomersController implements Initializable {

    DB db=new DB();
    @FXML
    private TableView<CustomerModel> table;
    @FXML
    private TableColumn<CustomerModel,Double> bal;
    @FXML
    private TableColumn<CustomerModel,String> cid;
    @FXML
    private TableColumn<CustomerModel,String> name;
    @FXML
    private TableColumn<CustomerModel,String> tel;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtCid;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtTel;
    @FXML
    private TextField txtBal;
    @FXML
    private TextField txtSearch;
    @FXML
    private TextField txtNic;


    @FXML
    private Button RegisterButton;
    @FXML
    private Button EditButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button searchButton;
    @FXML
    private ComboBox com;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        com.getItems().addAll("Customer Id", "Customer Name");
        com.getSelectionModel().select("Customer Id");
        ArrayList<CustomerModel> arr = new ArrayList<CustomerModel>();
        String getQuery="SELECT * FROM customer";
        ResultSet rs = db.getData(getQuery);


        try {
            while (rs.next()) {
                CustomerModel cm = new CustomerModel();

                cm.setCid(rs.getString(1));
                cm.setName(rs.getString(2));
                cm.setAddress(rs.getString(3));
                cm.setTelNo(""+rs.getInt(4));
                cm.setBal(rs.getDouble(5));
                cm.setNic(rs.getString(6));
                arr.add(cm);
            }
        } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error in rs!");
        }


        ObservableList<CustomerModel> list= FXCollections.observableArrayList(arr);


        cid.setCellValueFactory(new PropertyValueFactory<CustomerModel,String>("cid"));
        name.setCellValueFactory(new PropertyValueFactory<CustomerModel,String>("name"));
        tel.setCellValueFactory(new PropertyValueFactory<CustomerModel, String>("telNo"));
        bal.setCellValueFactory(new PropertyValueFactory<CustomerModel,Double>("bal"));
        table.setItems(list);



        table.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                txtCid.setText(table.getSelectionModel().getSelectedItem().getCid());
                txtName.setText(table.getSelectionModel().getSelectedItem().getName());
                txtAddress.setText(table.getSelectionModel().getSelectedItem().getAddress());
                txtTel.setText(table.getSelectionModel().getSelectedItem().getTelNo());
                txtBal.setText(""+table.getSelectionModel().getSelectedItem().getBal());
               // txtEmail.setText(table.getSelectionModel().getSelectedItem().getEmail());
                txtNic.setText(table.getSelectionModel().getSelectedItem().getNic());
            }
        });




        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadTbl();
            }
        });

        txtSearch.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                loadTbl();
            }
        });

        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                removeCustomer();
            }
        });


        /*searchButton.setOnAction(new EventHandler<ActionEvent>() {


            @Override
            public void handle(ActionEvent event) {
                String search=txtSearch.getText();
                //System.out.println(search);
                String getQuery1;
                ArrayList<CustomerModel> arr1=new ArrayList<CustomerModel>();
                if(com.getSelectionModel().getSelectedItem()=="Customer Id") {
                    getQuery1 = "SELECT * FROM customer where cid='" + search + "'";
                }
                else{
                    getQuery1 = "SELECT * FROM customer where name='" + search + "'";
                }
                ResultSet rs1=db.getData(getQuery1);
                if(rs1 != null){
                    System.out.println("rs is not null!");
                }


                try {
                    while (rs1.next()) {
                        //System.out.println(rs1.getString(1) + "  " + rs1.getString(2) + "  " + rs1.getString(3)+ "  " + rs1.getString(4)+ "  " + rs1.getString(5)+ "  " + rs1.getString(6));
                        //supplierModal sm1=new supplierModal(rs1.getString(1),rs1.getString(2),rs1.getString(3),rs1.getString(4),Double.parseDouble(rs1.getString(5)),rs1.getString(6));
                        //arr1.add(sm1);

                        CustomerModel cm = new CustomerModel();

                        cm.setCid(rs.getString(1));
                        cm.setName(rs.getString(2));
                        cm.setAddress(rs.getString(3));
                        cm.setTelNo(""+rs.getString(4));
                        cm.setBal(rs.getDouble(5));
                        cm.setNic(rs.getString(6));
                        arr1.add(cm);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                ObservableList<CustomerModel> list1= FXCollections.observableArrayList(arr1);

                table.setItems(list1);
            }
        });
        */


        RegisterButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getClassLoader().getResource("GUI/newCustomer.fxml"), resources);
                    Stage stage = new Stage();
                    stage.setTitle("Add new Customer");
                    stage.setScene(new Scene(root, 634, 604));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        EditButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Parent root;
                FXMLLoader loader=new FXMLLoader();
                loader.setLocation(getClass().getClassLoader().getResource("GUI/EditCustomer.fxml"));
                try {
                    loader.load();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                EditCustomerController editControl=loader.getController();
                editControl.setDet(txtCid.getText(),txtName.getText(),txtAddress.getText(),txtTel.getText(),txtBal.getText(),txtNic.getText());
                root=loader.getRoot();
                Stage stage = new Stage();
                stage.setTitle("My New Stage Title");
                stage.setScene(new Scene(root, 700, 700));
                stage.show();
            }
        });


    }

    private void removeCustomer(){
        if(txtCid.getText().isEmpty()){
            Functions.showMessage("Alert","Select customer", Alert.AlertType.INFORMATION);
            return;
        }
        if(HomePageController.curUser.getUserType().equals("admin")){
            ButtonType bt=Functions.confirmMessage("Remove customer","Are you sure to remove Customer?");
            if(bt==ButtonType.YES){
                db.putData("DELETE FROM customer WHERE cid='"+txtCid.getText()+"'");
                Functions.showMessage("Alert","Customer has deleted.", Alert.AlertType.CONFIRMATION);
                db.close();
                loadTbl();
            }
        }else{
            Functions.showMessage("Alert","Login with administrator account.", Alert.AlertType.WARNING);
        }
    }

    private void loadTbl(){
        try{
            String comVal;
            if(com.getValue().toString()=="Customer Id"){
                comVal = "cid";
            } else {
                comVal = "name";
            }
            ResultSet rs3 = db.getData("SELECT * FROM customer WHERE "+comVal+" like '%"+txtSearch.getText()+"%'");
            ArrayList<CustomerModel> arr1=new ArrayList<>();

            try {
                while (rs3.next()) {
                    CustomerModel cm = new CustomerModel();

                    cm.setCid(rs3.getString(1));
                    cm.setName(rs3.getString(2));
                    cm.setAddress(rs3.getString(3));
                    cm.setTelNo(""+rs3.getString(4));
                    cm.setBal(rs3.getDouble(5));
                    cm.setNic(rs3.getString(6));
                    arr1.add(cm);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ObservableList<CustomerModel> list1= FXCollections.observableArrayList(arr1);

            cid.setCellValueFactory(new PropertyValueFactory<CustomerModel,String>("Cid"));
            name.setCellValueFactory(new PropertyValueFactory<CustomerModel,String>("name"));
            tel.setCellValueFactory(new PropertyValueFactory<CustomerModel,String>("telNo"));
            bal.setCellValueFactory(new PropertyValueFactory<CustomerModel,Double>("Bal"));
            table.setItems(list1);
            db.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}