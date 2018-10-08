package GUI;

import Func.DB;
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
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;


public class InvoiceController implements Initializable {

    @FXML
    private TableView<InvoiceModel> tableProduct;
    @FXML
    private TableColumn<InvoiceModel,String> ItemId;
    @FXML
    private TableColumn<InvoiceModel,String> Description;
    @FXML
    private TableColumn<InvoiceModel,Double> price;
    @FXML
    private TableColumn<InvoiceModel,Integer> qty;
    @FXML
    private TableColumn<InvoiceModel,Double> amount;
    @FXML
    private TextField txtUid;
    @FXML
    private TextField txtDate;
    @FXML
    private TextField txtID;
    @FXML
    private TextField txtSearch;
    @FXML
    private TextField indexId;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextField txtCompany;
    @FXML
    private TextField txtType;
    @FXML
    private TextField txtCustomerNo;
    @FXML
    private TextField txtPrice;
    @FXML
    private TextField txtStock;
    @FXML
    private TextField txtItemId;
    @FXML
    private TextField txtQty;
    @FXML
    private TextField txtBal;
    @FXML
    private RadioButton rbdDesSubPer;
    @FXML
    private TextField txtDiscSub;
    @FXML
    private TextField txtAmount;
    @FXML
    private RadioButton rbdDesSubRs;
    @FXML
    private RadioButton radioRs;
    @FXML
    private RadioButton radioPer;
    @FXML
    private ToggleGroup tg;
    @FXML
    private ToggleGroup tg1;
    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button clearButton;
    @FXML
    private Button addPButton;
    @FXML
    private TextField txtCustomerName;
    @FXML
    private TextField txtDiscFull;
    @FXML
    private TextField txtGrAmount;


    boolean idSelected = false;
    int select = 0;

    DB db = new DB();

    int count=-1;
    ArrayList<InvoiceModel> arr=new ArrayList<InvoiceModel>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
         loadUserDetails();
         loadDate();
         loadInvoiceId();
        Validate validate=new Validate();
        txtItemId.setEditable(false);
        txtDescription.setEditable(false);
        txtCompany.setEditable(false);
        txtType.setEditable(false);
        txtPrice.setEditable(false);

        ArrayList<ProductModel> arr=new ArrayList<>();
        ArrayList<InvoiceModel> arr2=new ArrayList<>();
        ArrayList<InvoiceModel> arr3=new ArrayList<>();



        ResultSet rs = db.getData("SELECT * FROM product");

        try {
            while (rs.next()) {
                ProductModel pm = new ProductModel(rs.getString("pid"), rs.getString("description"), rs.getString("company"), rs.getString("type"), rs.getString("supplier"), rs.getDouble("price"), rs.getInt("stock"), rs.getInt("stockMinLevel"), rs.getInt("stockMaxLevel"));
                arr.add(pm);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<String> arrId = new ArrayList<>();
        for (ProductModel pm : arr) {
            arrId.add(pm.getProID());
        }

        List<String> arrName = new ArrayList<>();
        for (ProductModel pm : arr) {
            arrName.add(pm.getDescription());
        }



//        comSearch.setItems(FXCollections.observableArrayList(idSelected ? arrId : arrName));
//
//        comSearch.setEditable(true);
//        TextFields.bindAutoCompletion(comSearch.getEditor(), comSearch.getItems());
        txtQty.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                calcAmo();
            }
        });

        txtSearch.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String searchVal =txtSearch.getText();
                String getReq;
                getReq ="SELECT * FROM product WHERE pid='"+searchVal+"'";


                ResultSet rs=db.getData(getReq);
                try {
                    while (rs.next()){
                        txtDescription.setText(rs.getString(2));
                        txtCompany.setText(rs.getString(3));
                        txtType.setText(rs.getString(4));
                        //txtCustomerNo.setText(rs.getString(5));
                        txtPrice.setText(rs.getString(6));
                        txtStock.setText(rs.getString(7));
                        txtItemId.setText(rs.getString(1));

                        /*String cno=rs.getString(5);
                        ResultSet rsup=db.getData("SELECT name FROM customer WHERE cid='"+cno+"'");
                        while (rsup.next()) {
                            txtCustomerNo.setText(rsup.getString(1));
                        }*/

                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });


        addButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                if(validate.isNum(txtQty.getText())) {


                    InvoiceModel im = new InvoiceModel();
                    im.setDescription(txtDescription.getText());
                    im.setcName(txtCustomerName.getText());
                    try {
                        double x = Double.parseDouble(txtAmount.getText());
                        im.setAmount(x);
                    } catch (NumberFormatException e) {
                        System.out.println(e);
                    }
                    try {
                        double x = Double.parseDouble(txtDiscFull.getText());
                        im.setDiscountFull(x);
                    } catch (NumberFormatException e) {
                        System.out.println(e);
                    }
                    try {
                        double x = Double.parseDouble(txtDiscSub.getText());
                        im.setDiscountSub(x);
                    } catch (NumberFormatException e) {
                        System.out.println(e);
                    }
                    try {
                        double x = Double.parseDouble(txtGrAmount.getText());
                        im.setGrAmount(x);
                    } catch (NumberFormatException e) {
                        System.out.println(e);
                    }
                    try {
                        double x = Double.parseDouble(txtPrice.getText());
                        im.setPrice(x);
                    } catch (NumberFormatException e) {
                        System.out.println(e);
                    }
                    try {
                        int x = Integer.parseInt(txtStock.getText());
                        im.setStock(x);
                    } catch (NumberFormatException e) {
                        System.out.println(e);
                    }
                    try {
                        int x = Integer.parseInt(txtQty.getText());
                        im.setQuantity(x);
                    } catch (NumberFormatException e) {
                        System.out.println(e);
                    }
                    count++;
                    im.setId(txtItemId.getText());
                    im.setcNo(txtCustomerNo.getText());
                    im.setUser(txtUid.getText());
                    im.setId(txtItemId.getText());
                    im.setArrId(count);
                    im.setDate(txtDate.getText());
                    im.setCompany(txtCompany.getText());


                    arr2.add(im);
                    double sum = 0;
                    for (InvoiceModel obj : arr2) {
                        sum = sum + obj.getAmount();
                    }
                    txtGrAmount.setText("" + sum);
                    txtBal.setText(""+sum);
                    //addDiscFullNew();


                    ObservableList<InvoiceModel> list = FXCollections.observableArrayList(arr2);
                    ItemId.setCellValueFactory(new PropertyValueFactory<InvoiceModel, String>("id"));
                    Description.setCellValueFactory(new PropertyValueFactory<InvoiceModel, String>("description"));
                    price.setCellValueFactory(new PropertyValueFactory<InvoiceModel, Double>("price"));
                    qty.setCellValueFactory(new PropertyValueFactory<InvoiceModel, Integer>("quantity"));
                    amount.setCellValueFactory(new PropertyValueFactory<InvoiceModel, Double>("amount"));
                    tableProduct.setItems(list);


                    txtDescription.setText("");
                    txtItemId.setText("");
                    txtCompany.setText("");
                    txtType.setText("");
                    txtPrice.setText("");
                    txtQty.setText("");
                    txtStock.setText("");
                    txtAmount.setText("");


                }

            }
        });

        editButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int aid=tableProduct.getSelectionModel().getSelectedItem().getArrId();
                int c=0;
                for (InvoiceModel obj:arr2) {
                    if(aid!=obj.getArrId()) {
                        c++;
                    }
                    else{
                        break;
                    }
                }

                txtDate.setText(tableProduct.getSelectionModel().getSelectedItem().getDate());
                txtCustomerName.setText(tableProduct.getSelectionModel().getSelectedItem().getcName());
                txtDescription.setText(tableProduct.getSelectionModel().getSelectedItem().getDescription());
                txtItemId.setText(tableProduct.getSelectionModel().getSelectedItem().getId());
                txtCompany.setText(tableProduct.getSelectionModel().getSelectedItem().getCompany());
                txtType.setText(tableProduct.getSelectionModel().getSelectedItem().getType());
                txtPrice.setText(""+tableProduct.getSelectionModel().getSelectedItem().getPrice());
                txtQty.setText(""+tableProduct.getSelectionModel().getSelectedItem().getQuantity());
                txtStock.setText(""+tableProduct.getSelectionModel().getSelectedItem().getStock());
                double tot=tableProduct.getSelectionModel().getSelectedItem().getGrAmount()-tableProduct.getSelectionModel().getSelectedItem().getAmount();
                txtAmount.setText(""+tot);
                txtUid.setText(tableProduct.getSelectionModel().getSelectedItem().getUser());
                txtID.setText(tableProduct.getSelectionModel().getSelectedItem().getGrnNo());


                arr2.remove(c);
                ObservableList<InvoiceModel> list=FXCollections.observableArrayList(arr2);
                ItemId.setCellValueFactory(new PropertyValueFactory<InvoiceModel,String>("id"));
                Description.setCellValueFactory(new PropertyValueFactory<InvoiceModel,String>("description"));
                price.setCellValueFactory(new PropertyValueFactory<InvoiceModel,Double>("price"));
                qty.setCellValueFactory(new PropertyValueFactory<InvoiceModel,Integer>("quantity"));
                amount.setCellValueFactory(new PropertyValueFactory<InvoiceModel,Double>("amount"));
                tableProduct.setItems(list);
            }
        });

        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int aid=tableProduct.getSelectionModel().getSelectedItem().getArrId();
                int c=0;
                for (InvoiceModel obj:arr2) {
                    if(aid!=obj.getArrId()) {
                        c++;
                    }
                    else{
                        break;
                    }
                }
                arr2.remove(c);
                ObservableList<InvoiceModel> list=FXCollections.observableArrayList(arr2);
                ItemId.setCellValueFactory(new PropertyValueFactory<InvoiceModel,String>("id"));
                Description.setCellValueFactory(new PropertyValueFactory<InvoiceModel,String>("description"));
                price.setCellValueFactory(new PropertyValueFactory<InvoiceModel,Double>("price"));
                qty.setCellValueFactory(new PropertyValueFactory<InvoiceModel,Integer>("quantity"));
                amount.setCellValueFactory(new PropertyValueFactory<InvoiceModel,Double>("amount"));
                tableProduct.setItems(list);
                double sum = 0;
                for (InvoiceModel obj : arr2) {
                    sum = sum + obj.getAmount();
                }

                txtGrAmount.setText("" + sum);
                txtBal.setText(""+sum);
                //addDiscFullNew();
            }
        });
       clearButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                ObservableList<InvoiceModel> list=FXCollections.observableArrayList(arr3);
                ItemId.setCellValueFactory(new PropertyValueFactory<InvoiceModel,String>("id"));
                Description.setCellValueFactory(new PropertyValueFactory<InvoiceModel,String>("description"));
                price.setCellValueFactory(new PropertyValueFactory<InvoiceModel,Double>("price"));
                qty.setCellValueFactory(new PropertyValueFactory<InvoiceModel,Integer>("quantity"));
                amount.setCellValueFactory(new PropertyValueFactory<InvoiceModel,Double>("amount"));
                tableProduct.setItems(list);

            }

        });

        addPButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getClassLoader().getResource("GUI/addProduct.fxml"), resources);
                    Stage stage = new Stage();
                    stage.setTitle("Add new Product");
                    stage.setScene(new Scene(root, 634, 604));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


  /*      txtDiscFull.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                double de =0.0;
                double x= 0.0;
                try {
                    x = Double.parseDouble(txtGrAmount.getText());
                    //y=Integer.parseInt(txtQty.getText());
                }catch (NumberFormatException e){
                    System.out.println(e);
                }

                if(radioPer.isSelected()==true){
                    try {
                        de = Double.parseDouble(txtDiscFull.getText());
                    }catch (NumberFormatException e){
                        System.out.println(e);
                    }
                    txtBal.setText(""+((x-(x*de/100))));
                }
                else if(radioRs.isSelected()==true){
                    try {
                        de = Double.parseDouble(txtDiscFull.getText());
                    }catch (NumberFormatException e){
                        System.out.println(e);
                    }
                    txtBal.setText(""+((x-de)));
                } else {
                    txtBal.setText(""+(x));
                }
                txtBal.getText();
            }
        });
*/
    }


    private void loadUserDetails() {
        txtUid.setText(HomePageController.curUser.getUserID());
    }

    private void loadDate(){
        //DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        txtDate.setText(df.format(date));
        //System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43
    }

    private void loadInvoiceId(){
        DB db = new DB();
        try {
            ResultSet rs=db.getData("SELECT MAX(invoiceID) FROM invoicelog");
            rs.next();
            int Iid=rs.getInt("MAX(invoiceID)");
            db.close();
            Iid++;
            txtID.setText(""+Iid);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void calcAmo(){
        double x=0.0,de=0.0;
        int y=0;
        try {
            x = Double.parseDouble(txtPrice.getText());
            y=Integer.parseInt(txtQty.getText());
        }catch (NumberFormatException e){
            System.out.println(e);
        }


        if(rbdDesSubPer.isSelected()==true){
            try {
                de = Double.parseDouble(txtDiscSub.getText());
            }catch (NumberFormatException e){
                System.out.println(e);
            }
            txtAmount.setText(""+((x-(x*de/100))*y));
        }
        else if(rbdDesSubRs.isSelected()==true){
            try {
                de = Double.parseDouble(txtDiscSub.getText());
            }catch (NumberFormatException e){
                System.out.println(e);
            }
            txtAmount.setText(""+((x-de)*y));
        } else {
            txtAmount.setText(""+(x*y));
        }


    }

    public void addDiscFullNew(){

        double x=0.0,de=0.0;
        //int y=0;
        try {
            x = Double.parseDouble(txtGrAmount.getText());
            //y=Integer.parseInt(txtQty.getText());
        }catch (NumberFormatException e){
            System.out.println(e);
        }


        if(radioPer.isSelected()==true){
            try {
                de = Double.parseDouble(txtDiscFull.getText());
            }catch (NumberFormatException e){
                System.out.println(e);
            }
            txtBal.setText(""+((x-(x*de/100))));
        }
        else if(radioRs.isSelected()==true){
            try {
                de = Double.parseDouble(txtDiscFull.getText());
            }catch (NumberFormatException e){
                System.out.println(e);
            }
            txtBal.setText(""+((x-de)));
        } else {
            txtBal.setText(""+(x));
        }

    }


}
