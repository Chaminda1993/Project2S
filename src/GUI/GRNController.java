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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class GRNController implements Initializable {
    DB db=new DB();
    @FXML
    private TableView<GRNModel> tableProduct;
    @FXML
    private TableColumn<GRNModel,String> ItemId;
    @FXML
    private TableColumn<GRNModel,String> Description;
    @FXML
    private TableColumn<GRNModel,Double> price;
    @FXML
    private TableColumn<GRNModel,Integer> qty;
    @FXML
    private TableColumn<GRNModel,Double> amount;
    @FXML
    private TextField txtItemId;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextField txtPrice;
    @FXML
    private TextField txtcash;
    @FXML
    private TextField txtQty;
    @FXML
    private TextField txtAmount;
    @FXML
    private TextField txtCompany;
    @FXML
    private TextField txtStock;
    @FXML
    private TextField txtType;
    @FXML
    private TextField txtSupplierNo;
    @FXML
    private TextField txtSupplierName;
    @FXML
    private TextField txtBalance;
    @FXML
    private TextField txtDiscSub;
    @FXML
    private TextField txtDiscFull;
    @FXML
    private TextField txtGRNno;
    @FXML
    private TextField txtDateAndTime;
    @FXML
    private TextField txtUser;
    @FXML
    private TextField txtGrAmount;
    @FXML
    private Button printButton;
    @FXML
    private Button addButton;
    @FXML
    private Button EditButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button clearButton;
    @FXML
    private Button addProductButton;
    @FXML
    private RadioButton rbdDesSubPer;
    @FXML
    private RadioButton rbdDesSubRs;
    @FXML
    private RadioButton rbdDesGrPer;
    @FXML
    private RadioButton rbdDesGrRs;
    @FXML
    private RadioButton rbdDescName;
    @FXML
    private RadioButton rbdDescID;
    @FXML
    private ToggleGroup tg1;
    @FXML
    private ToggleGroup tg2;
    @FXML
    private Button saveButton;
    private static final DateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    int count=-1;
    ArrayList<GRNModel> arr=new ArrayList<GRNModel>();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rbdDesSubPer.setSelected(true);
        rbdDesGrPer.setSelected(true);
        rbdDescName.setSelected(true);
        Date date = new Date();
        /*rbd1.setToggleGroup(tg1);
        rbd2.setToggleGroup(tg1);*/

       /* ArrayList<productModal> arr=new ArrayList<>();
        String getQuery="SELECT * FROM grn";
        ResultSet rsg=db.getData(getQuery);

        try {
            while (rsg.next()) {
                String productQuery="SELECT * FROM product where pid='"+rsg.getString(3)+"'";
                ResultSet rs=db.getData(productQuery);
                String grnNumber = rsg.getString(2);
                while (rs.next()) {
                    System.out.println(rs.getString(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + "  " + rs.getString(4) + "  " + rs.getString(5) + "  " + rs.getString(6));
                    productModal pm = new productModal(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), Double.parseDouble(rs.getString(6)), Integer.parseInt(rs.getString(7)), Integer.parseInt(rs.getString(8)), Integer.parseInt(rs.getString(9)));
                    pm.setAmount(pm.getStock() * pm.getPrice());
                    arr.add(pm);

                }

        ObservableList<productModal> list= FXCollections.observableArrayList(arr);

        ItemId.setCellValueFactory(new PropertyValueFactory<productModal,String>("pid"));
        Description.setCellValueFactory(new PropertyValueFactory<productModal,String>("Description"));
        price.setCellValueFactory(new PropertyValueFactory<productModal,Double>("price"));
        qty.setCellValueFactory(new PropertyValueFactory<productModal,Integer>("stock"));
        amount.setCellValueFactory(new PropertyValueFactory<productModal,Double>("amount"));
        tableProduct.setItems(list);

        tableProduct.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                txtItemId.setText(tableProduct.getSelectionModel().getSelectedItem().getPid());
                txtDescription.setText(tableProduct.getSelectionModel().getSelectedItem().getDescription());
                txtCompany.setText(tableProduct.getSelectionModel().getSelectedItem().getCompany());
                txtPrice.setText(""+tableProduct.getSelectionModel().getSelectedItem().getPrice());
                txtQty.setText(""+tableProduct.getSelectionModel().getSelectedItem().getStock());
                txtAmount.setText(""+tableProduct.getSelectionModel().getSelectedItem().getAmount());
                txtType.setText(tableProduct.getSelectionModel().getSelectedItem().getType());
                txtStock.setText(""+tableProduct.getSelectionModel().getSelectedItem().getStock());
                String querySupplier="SELECT * FROM supplier WHERE sid='"+tableProduct.getSelectionModel().getSelectedItem().getSupplier()+"'";
                ResultSet rs1=db.getData(querySupplier);

                try {
                   //
                    while(rs1.next()) {
                        txtSupplierNo.setText(rs1.getString(1));
                        txtSupplierName.setText(rs1.getString(2));
                        txtBalance.setText(rs1.getString(5));
                       System.out.println(rs1.getString(1));
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }


              //  String grnQuery="SELECT * FROM grn ";


                        txtGRNno.setText(grnNumber);

                        String queryGRNLog="SELECT * FROM grnlog WHERE grnNo='"+grnNumber+"'";
                        ResultSet rs3=db.getData(queryGRNLog);
                try {

                        while (rs3.next()){
                            txtDateAndTime.setText(rs3.getString(2));
                            txtUser.setText(rs3.getString(3));
                            txtGrAmount.setText(rs3.getString(5));
                        }



                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        });


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getClassLoader().getResource("GUI/addGRN.fxml"), resources);
                    Stage stage = new Stage();
                    stage.setTitle("Add GRN");
                    stage.setScene(new Scene(root, 340, 308));
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
                loader.setLocation(getClass().getClassLoader().getResource("GUI/editGRN.fxml"));
                try {
                    loader.load();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                editGRNController editControl=loader.getController();
                editControl.setDet(txtItemId.getText(),txtGRNno.getText(),txtSupplierNo.getText(),txtQty.getText(),txtAmount.getText(),txtUser.getText());
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
                String dId=txtGRNno.getText();
                String dltQuery="DELETE FROM grn WHERE sid='"+dId+"'";
                db.putData(dltQuery);
            }
        });
        clearButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                txtAmount.setText("");
                txtItemId.setText("");
                txtDescription.setText("");
                txtPrice.setText("");
                txtCompany.setText("");
                txtQty.setText("");
                txtDateAndTime.setText("");
                txtStock.setText("");
                txtType.setText("");
                txtSupplierNo.setText("");
                txtSupplierName.setText("");
                txtBalance.setText("");
                txtGRNno.setText("");
                txtUser.setText("");
                 /* String dltFull="DELETE * FROM grn";
                    db.putData(dltFull);*/
           /* }
        });
        addProductButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getClassLoader().getResource("GUI/addProduct.fxml"), resources);
                    Stage stage = new Stage();
                    stage.setTitle("Add new product");
                    stage.setScene(new Scene(root, 300, 480));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        printButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    Connection connection = DB.con();
                    String xml = "GRNReceipt.jrxml";
                    JasperDesign jd= JRXmlLoader.load((getClass().getResourceAsStream(xml)));
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put("grnNo", txtGRNno.getText());
                    params.put("netAmo", txtGrAmount.getText());
                    params.put("date", txtDateAndTime.getText());
                    params.put("sName", txtSupplierName.getText());
                    JasperReport jr = JasperCompileManager.compileReport(jd);
                    JasperPrint jp = JasperFillManager.fillReport(jr, params,connection);
                    JasperViewer.viewReport(jp, false);
                    JasperPrintManager.printReport(jp, false);

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

        });
*/

        txtPrice.setEditable(false);
        txtAmount.setEditable(false);
        txtBalance.setEditable(false);
        txtSupplierName.setEditable(false);
        txtGrAmount.setEditable(false);
        Validate validate=new Validate();
        ResultSet rAuto=db.getData("SELECT grnNo FROM grnlog;");
        int cnt=0;
        try {
            while(rAuto.next()){
                cnt++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        txtGRNno.setText("g"+(cnt+1));

        txtDateAndTime.setText(sdft.format(date));

        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if(validate.isNum(txtQty.getText())) {


                    GRNModel grm = new GRNModel();
                    grm.setDescription(txtDescription.getText());
                    grm.setsName(txtSupplierName.getText());
                    try {
                        double x = Double.parseDouble(txtAmount.getText());
                        grm.setAmount(x);
                    } catch (NumberFormatException e) {
                        System.out.println(e);
                    }
                    try {
                        double x = Double.parseDouble(txtDiscFull.getText());
                        grm.setDiscountFull(x);
                    } catch (NumberFormatException e) {
                        System.out.println(e);
                    }
                    try {
                        double x = Double.parseDouble(txtDiscSub.getText());
                        grm.setDiscountSub(x);
                    } catch (NumberFormatException e) {
                        System.out.println(e);
                    }
                    try {
                        double x = Double.parseDouble(txtGrAmount.getText());
                        grm.setGrAmount(x);
                    } catch (NumberFormatException e) {
                        System.out.println(e);
                    }
                    try {
                        double x = Double.parseDouble(txtPrice.getText());
                        grm.setPrice(x);
                    } catch (NumberFormatException e) {
                        System.out.println(e);
                    }
                    try {
                        int x = Integer.parseInt(txtStock.getText());
                        grm.setStock(x);
                    } catch (NumberFormatException e) {
                        System.out.println(e);
                    }
                    try {
                        int x = Integer.parseInt(txtQty.getText());
                        grm.setQuantity(x);
                    } catch (NumberFormatException e) {
                        System.out.println(e);
                    }
                    count++;
                    grm.setGrnNo(txtGRNno.getText());
                    grm.setsNo(txtSupplierNo.getText());
                    grm.setUser(txtUser.getText());
                    grm.setId(txtItemId.getText());
                    grm.setArrId(count);
                    grm.setDate(txtDateAndTime.getText());
                    grm.setCompany(txtCompany.getText());


                    arr.add(grm);
                    double sum = 0;
                    for (GRNModel obj : arr) {
                        sum = sum + obj.getAmount();
                    }
                    txtGrAmount.setText("" + sum);


                    ObservableList<GRNModel> list = FXCollections.observableArrayList(arr);
                    ItemId.setCellValueFactory(new PropertyValueFactory<GRNModel, String>("id"));
                    Description.setCellValueFactory(new PropertyValueFactory<GRNModel, String>("description"));
                    price.setCellValueFactory(new PropertyValueFactory<GRNModel, Double>("price"));
                    qty.setCellValueFactory(new PropertyValueFactory<GRNModel, Integer>("quantity"));
                    amount.setCellValueFactory(new PropertyValueFactory<GRNModel, Double>("amount"));
                    tableProduct.setItems(list);


                    txtDescription.setText("");
                    txtItemId.setText("");
                    txtCompany.setText("");
                    txtType.setText("");
                    txtPrice.setText("");
                    txtQty.setText("");
                    txtStock.setText("");
                    txtAmount.setText("");
                    txtUser.setText("");


                }

            }
        });
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(validate.isNum(txtcash.getText())) {
                    double sum = 0;
                    double bal = 0.0;
                    double csh = 0.0;
                    try {
                        csh = Double.parseDouble(txtcash.getText());
                        sum = Double.parseDouble(txtGrAmount.getText());
                        bal = Double.parseDouble(txtBalance.getText());
                    } catch (NumberFormatException e) {
                        System.out.println(e);
                    }

                    String saveQueryGRNLog = "INSERT INTO grnlog VALUES('" + txtGRNno.getText() + "','" + txtDateAndTime.getText() + "','" + txtUser.getText() + "','" + txtSupplierNo.getText() + "','" + sum + "','" + csh + "','" + bal + "')";
                    db.putData(saveQueryGRNLog);
                }

            }
        });
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int aid=tableProduct.getSelectionModel().getSelectedItem().getArrId();
                int c=0;
                for (GRNModel obj:arr) {
                    if(aid!=obj.getArrId()) {
                        c++;
                    }
                    else{
                        break;
                    }
                }
                arr.remove(c);
                ObservableList<GRNModel> list=FXCollections.observableArrayList(arr);
                ItemId.setCellValueFactory(new PropertyValueFactory<GRNModel,String>("id"));
                Description.setCellValueFactory(new PropertyValueFactory<GRNModel,String>("description"));
                price.setCellValueFactory(new PropertyValueFactory<GRNModel,Double>("price"));
                qty.setCellValueFactory(new PropertyValueFactory<GRNModel,Integer>("quantity"));
                amount.setCellValueFactory(new PropertyValueFactory<GRNModel,Double>("amount"));
                tableProduct.setItems(list);
            }
        });
        clearButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               arr=new ArrayList<GRNModel>();

                ObservableList<GRNModel> list=FXCollections.observableArrayList(arr);
                ItemId.setCellValueFactory(new PropertyValueFactory<GRNModel,String>("id"));
                Description.setCellValueFactory(new PropertyValueFactory<GRNModel,String>("description"));
                price.setCellValueFactory(new PropertyValueFactory<GRNModel,Double>("price"));
                qty.setCellValueFactory(new PropertyValueFactory<GRNModel,Integer>("quantity"));
                amount.setCellValueFactory(new PropertyValueFactory<GRNModel,Double>("amount"));
                tableProduct.setItems(list);
            }

        });

        EditButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int aid=tableProduct.getSelectionModel().getSelectedItem().getArrId();
                int c=0;
                for (GRNModel obj:arr) {
                    if(aid!=obj.getArrId()) {
                        c++;
                    }
                    else{
                        break;
                    }
                }

                txtDateAndTime.setText(tableProduct.getSelectionModel().getSelectedItem().getDate());
                txtSupplierName.setText(tableProduct.getSelectionModel().getSelectedItem().getsName());
                txtDescription.setText(tableProduct.getSelectionModel().getSelectedItem().getDescription());
                txtItemId.setText(tableProduct.getSelectionModel().getSelectedItem().getId());
                txtCompany.setText(tableProduct.getSelectionModel().getSelectedItem().getCompany());
                txtType.setText(tableProduct.getSelectionModel().getSelectedItem().getType());
                txtPrice.setText(""+tableProduct.getSelectionModel().getSelectedItem().getPrice());
                txtQty.setText(""+tableProduct.getSelectionModel().getSelectedItem().getQuantity());
                txtStock.setText(""+tableProduct.getSelectionModel().getSelectedItem().getStock());
                double tot=tableProduct.getSelectionModel().getSelectedItem().getGrAmount()-tableProduct.getSelectionModel().getSelectedItem().getAmount();
                txtAmount.setText(""+tot);
                txtUser.setText(tableProduct.getSelectionModel().getSelectedItem().getUser());
                txtGRNno.setText(tableProduct.getSelectionModel().getSelectedItem().getGrnNo());


                arr.remove(c);
                ObservableList<GRNModel> list=FXCollections.observableArrayList(arr);
                ItemId.setCellValueFactory(new PropertyValueFactory<GRNModel,String>("id"));
                Description.setCellValueFactory(new PropertyValueFactory<GRNModel,String>("description"));
                price.setCellValueFactory(new PropertyValueFactory<GRNModel,Double>("price"));
                qty.setCellValueFactory(new PropertyValueFactory<GRNModel,Integer>("quantity"));
                amount.setCellValueFactory(new PropertyValueFactory<GRNModel,Double>("amount"));
                tableProduct.setItems(list);
            }
        });
        printButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    Connection connection = DB.con();
                    String xml = "GRNReceipt.jrxml";
                    JasperDesign jd= JRXmlLoader.load((getClass().getResourceAsStream(xml)));
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put("grnNo", txtGRNno.getText());
                    params.put("netAmo", txtGrAmount.getText());
                    params.put("date", txtDateAndTime.getText());
                    params.put("sName", txtSupplierName.getText());
                    JasperReport jr = JasperCompileManager.compileReport(jd);
                    JasperPrint jp = JasperFillManager.fillReport(jr, params,connection);
                    JasperViewer.viewReport(jp, false);
                    JasperPrintManager.printReport(jp, false);

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

        });

    }
    public void itemIdFillUp(){
        String pid=txtItemId.getText();
        String getReq="SELECT * FROM product WHERE pid='"+pid+"'";
        ResultSet rs=db.getData(getReq);
        try {
            while (rs.next()){
                    txtDescription.setText(rs.getString(2));
                    txtCompany.setText(rs.getString(3));
                    txtType.setText(rs.getString(4));
                    txtSupplierNo.setText(rs.getString(5));
                    txtPrice.setText(rs.getString(6));
                    txtStock.setText(rs.getString(7));

                    String sno=rs.getString(5);
                    ResultSet rsup=db.getData("SELECT name FROM supplier WHERE sid='"+sno+"'");
                    while (rsup.next()) {
                        txtSupplierName.setText(rsup.getString(1));
                    }

            }
        } catch (SQLException e) {
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
            }


    }
    public void balFill(){
        double csh=0.0,sum=0.0;
        double bal=0.0;
        double discount=0.0;
        try {
            csh = Double.parseDouble(txtcash.getText());
            sum=Double.parseDouble(txtGrAmount.getText());
            discount=Double.parseDouble(txtDiscFull.getText());
        }catch (NumberFormatException e){
            System.out.println(e);
        }

        if(rbdDesGrPer.isSelected()==true){
            sum=sum-(sum*discount/100);
        }
        else{
            sum=sum-discount;
        }
        bal=csh-sum;
        txtBalance.setText(""+bal);
    }
    public void addDiscNew(){


            if (!txtPrice.getText().equals("") && !txtQty.getText().equals("")) {

                try {
                    double x = Double.parseDouble(txtPrice.getText());
                    int y = Integer.parseInt(txtQty.getText());
                    if (rbdDesSubPer.isSelected() == true) {
                        double de = Double.parseDouble(txtDiscSub.getText());
                        txtAmount.setText("" + ((x - (x * de / 100)) * y));
                    } else {
                        double de = Double.parseDouble(txtDiscSub.getText());
                        txtAmount.setText("" + ((x - de)*y));
                    }
                } catch (NumberFormatException e) {
                    System.out.println(e);
                }

        }
    }
    public void addDiscFullNew(){


            if (!txtGrAmount.getText().equals("") && !txtcash.getText().equals("")) {

                double csh = 0.0, sum = 0.0;
                double bal = 0.0;
                double discount = 0.0;
                try {
                    csh = Double.parseDouble(txtcash.getText());
                    sum = Double.parseDouble(txtGrAmount.getText());
                    discount = Double.parseDouble(txtDiscFull.getText());
                } catch (NumberFormatException e) {
                    System.out.println(e);
                }

                if (rbdDesGrPer.isSelected() == true) {
                    sum = sum - (sum * discount / 100);
                } else {
                    sum = sum - discount;
                }
                bal = csh - sum;
                txtBalance.setText("" + bal);

            }


    }


}
