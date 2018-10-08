package GUI;
import java.sql.*;

import Func.DB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SupplierController implements Initializable {

    DB db=new DB();
    @FXML
    private TableView<supplierModal> table;
    @FXML
    private TableColumn<supplierModal,Double> balance;
    @FXML
    private TableColumn<supplierModal,String> sid;
    @FXML
    private TableColumn<supplierModal,String> name;
    @FXML
    private TableColumn<supplierModal,String> tel;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtSid;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtTel;
    @FXML
    private TextField txtBalance;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtSearch;

    @FXML
    private Button RegisterButton;
    @FXML
    private Button EditButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button searchButton;
    @FXML
    private ComboBox cmb;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        cmb.getItems().addAll("Supplier Id", "Supplier Name");
        cmb.getSelectionModel().select("Supplier Id");
      //  System.out.print(cmb.getSelectionModel().getSelectedItem());
        ArrayList<supplierModal> arr=new ArrayList<>();
        String getQuery="SELECT * FROM supplier";
        ResultSet rs=db.getData(getQuery);


        try {
            while (rs.next()) {
                //System.out.println(rs.getString(1) + "  " + rs.getString(2) + "  " + rs.getString(3)+ "  " + rs.getString(4)+ "  " + rs.getString(5)+ "  " + rs.getString(6));
                supplierModal sm=new supplierModal(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),Double.parseDouble(rs.getString(5)),rs.getString(6));
                arr.add(sm);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ObservableList<supplierModal> list= FXCollections.observableArrayList(arr);

        sid.setCellValueFactory(new PropertyValueFactory<supplierModal,String>("sid"));
        name.setCellValueFactory(new PropertyValueFactory<supplierModal,String>("name"));
        tel.setCellValueFactory(new PropertyValueFactory<supplierModal,String>("telNo"));
        balance.setCellValueFactory(new PropertyValueFactory<supplierModal,Double>("balance"));
        table.setItems(list);

        table.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                txtSid.setText(table.getSelectionModel().getSelectedItem().getSid());
                txtName.setText(table.getSelectionModel().getSelectedItem().getName());
                txtAddress.setText(table.getSelectionModel().getSelectedItem().getAddress());
                txtTel.setText(table.getSelectionModel().getSelectedItem().getTelNo());
                txtBalance.setText(""+table.getSelectionModel().getSelectedItem().getBalance());
                txtEmail.setText(table.getSelectionModel().getSelectedItem().getEmail());
            }
        });

        /**/

        RegisterButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getClassLoader().getResource("GUI/newSupplier.fxml"), resources);
                    Stage stage = new Stage();
                    stage.setTitle("Add new supplier");
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
                    loader.setLocation(getClass().getClassLoader().getResource("GUI/EditSupplier.fxml"));
                try {
                    loader.load();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                EditSupplierController editControl=loader.getController();
                editControl.setDet(txtSid.getText(),txtName.getText(),txtAddress.getText(),txtTel.getText(),txtBalance.getText(),txtEmail.getText());
                root=loader.getRoot();
                Stage stage = new Stage();
                stage.setTitle("My New Stage Title");
                stage.setScene(new Scene(root, 700, 700));
                stage.show();




            }
        });
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {


            @Override
            public void handle(ActionEvent event) {
                String dId=txtSid.getText();
                String dltQuery="DELETE FROM supplier WHERE sid='"+dId+"'";
                db.putData(dltQuery);
            }
        });

        searchButton.setOnAction(new EventHandler<ActionEvent>() {


            @Override
            public void handle(ActionEvent event) {
                String search=txtSearch.getText();
                //System.out.println(search);
                String getQuery1;
                ArrayList<supplierModal> arr1=new ArrayList<>();
                if(cmb.getSelectionModel().getSelectedItem()=="Supplier Id") {

                    getQuery1 = "SELECT * FROM supplier where sid='" + search + "'";
                }
                else{
                    getQuery1 = "SELECT * FROM supplier where name='" + search + "'";
                }
                ResultSet rs1=db.getData(getQuery1);


                try {
                    while (rs1.next()) {
                        System.out.println(rs1.getString(1) + "  " + rs1.getString(2) + "  " + rs1.getString(3)+ "  " + rs1.getString(4)+ "  " + rs1.getString(5)+ "  " + rs1.getString(6));
                        supplierModal sm1=new supplierModal(rs1.getString(1),rs1.getString(2),rs1.getString(3),rs1.getString(4),Double.parseDouble(rs1.getString(5)),rs1.getString(6));
                        arr1.add(sm1);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                ObservableList<supplierModal> list1= FXCollections.observableArrayList(arr1);

                table.setItems(list1);
            }
        });



    }
    public void onkeySearch(){
        String search=txtSearch.getText();
        //System.out.println(search);
        String getQuery1;
        ArrayList<supplierModal> arr1=new ArrayList<>();
        if(cmb.getSelectionModel().getSelectedItem()=="Supplier Id") {

            getQuery1 = "SELECT * FROM supplier where sid='" + search + "'";
        }
        else{
            getQuery1 = "SELECT * FROM supplier where name LIKE '%" + search + "%'";
        }
        ResultSet rs1=db.getData(getQuery1);


        try {
            while (rs1.next()) {
                System.out.println(rs1.getString(1) + "  " + rs1.getString(2) + "  " + rs1.getString(3)+ "  " + rs1.getString(4)+ "  " + rs1.getString(5)+ "  " + rs1.getString(6));
                supplierModal sm1=new supplierModal(rs1.getString(1),rs1.getString(2),rs1.getString(3),rs1.getString(4),Double.parseDouble(rs1.getString(5)),rs1.getString(6));
                arr1.add(sm1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ObservableList<supplierModal> list1= FXCollections.observableArrayList(arr1);

        table.setItems(list1);

    }



}
