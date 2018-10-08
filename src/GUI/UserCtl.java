package GUI;

import Func.DB;
import Func.Functions;
import Func.SendMail;
import Func.Validate;
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
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserCtl implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> al=new ArrayList<String >();
        al.add("uid");
        al.add("name");
        al.add("telno");
        ObservableList<String> list= FXCollections.observableArrayList(al);
        cmbSearch.setItems(list);
        cmbSearch.setValue("name");

        textEditable(false);

        pneChPass.setVisible(false);

        loadTbl();

        tblUsers.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                txtID.setText(tblUsers.getSelectionModel().getSelectedItem().getUid());
                txtName.setText(tblUsers.getSelectionModel().getSelectedItem().getName());
                txtEmail.setText(tblUsers.getSelectionModel().getSelectedItem().getEmail());
                txtTelNo.setText(tblUsers.getSelectionModel().getSelectedItem().getTelNo());
                txtNIC.setText(tblUsers.getSelectionModel().getSelectedItem().getNic());
                txtAdd.setText(tblUsers.getSelectionModel().getSelectedItem().getAddress());
                txtType.setText(tblUsers.getSelectionModel().getSelectedItem().getType());
            }
        });

        btnSearch.setOnAction(new EventHandler<ActionEvent>() {
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
        
        btnEdit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(HomePageController.curUser.getUserID().equals(txtID.getText())){
                    isUpdate=true;
                    textEditable(true);
                }else{
                    Functions.showMessage("Alert","Login with same account.", Alert.AlertType.WARNING);
                }
            }
        });
        
        btnRegister.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(HomePageController.curUser.getUserType().equals("admin")){
                    isUpdate=false;
                    textEditable(true);
                    textClear();
                    genUID();
                }else{
                    Functions.showMessage("Alert","Login with administor account.", Alert.AlertType.WARNING);
                }
            }
        });
        
        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Validate.isEmail(txtEmail);
                Validate.isTelNo(txtTelNo);
                Validate.isNIC(txtNIC);
                if(emptyFields())return;
                if(isUpdate){
                    saveUpdate();
                }else {
                    saveNew();
                }
            }
        });

        btnRemove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                removeUser();
            }
        });

        btnChPass.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pneChPass.setVisible(true);
            }
        });

        btnSNP.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                chPass();
            }
        });

        btnCancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textEditable(false);
                textClear();
            }
        });
    }

    private boolean emptyFields() {
        if(txtName.getText().toString().isEmpty() || txtEmail.getText().toString().isEmpty() || txtTelNo.getText().toString().isEmpty() || txtAdd.getText().toString().isEmpty() || txtType.getText().toString().isEmpty()){
            Functions.showMessage("Alert","Some fields are empty", Alert.AlertType.WARNING);
            return true;
        }
        return false;
    }

    private void chPass() {
        if(txtNP.getText().isEmpty() || txtRNP.getText().isEmpty() || txtCP.getText().isEmpty()){
            Functions.showMessage("Alert","Some fields are empty", Alert.AlertType.WARNING);
            return;
        }
        if(txtNP.getText().equals(txtRNP.getText())){
            try{
                ResultSet rs=db.getData("SELECT uid FROM users WHERE password=password('"+txtCP.getText()+"')");
                while(rs.next()){
                    if(rs.getString("uid").equals(HomePageController.curUser.getUserID())){
                        db.putData("UPDATE users SET password=password('"+txtNP.getText()+"') WHERE uid='"+HomePageController.curUser.getUserID()+"'");
                        db.close();
                        Functions.showMessage("Alert","Password change sucessful", Alert.AlertType.CONFIRMATION);
                        pneChPass.setVisible(false);
                        return;
                    }
                }
                Functions.showMessage("Alert","Invalid password", Alert.AlertType.ERROR);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            Functions.showMessage("Alert","New password is not match", Alert.AlertType.WARNING);
        }
    }

    private void removeUser() {
        if(txtID.getText().isEmpty()){
            Functions.showMessage("Alert","Select user", Alert.AlertType.INFORMATION);
            return;
        }
        if(HomePageController.curUser.getUserType().equals("admin")){
            ButtonType bt=Functions.confirmMessage("Remove user","Are you sure to remove user?");
            if(bt==ButtonType.YES){
                db.putData("UPDATE users SET status='deactive' WHERE uid='"+txtID.getText()+"'");
                Functions.showMessage("Alert","User has deleted.", Alert.AlertType.CONFIRMATION);
                db.close();
                loadTbl();
            }
        }else{
            Functions.showMessage("Alert","Login with administor account.", Alert.AlertType.WARNING);
        }
    }

    private void saveNew() {

        try {
            String newkey=Functions.genKey();
            boolean sent= SendMail.sendMail(txtEmail.getText(),"Registration of System", "Your registration successful. New password is "+newkey);
            if(sent){
                db.putData("INSERT INTO users(uid,name,address,telNo,type,nic,email,password,status) VALUES('"+txtID.getText()+"','"+txtName.getText()+"','"+txtAdd.getText()+"','"+txtTelNo.getText()+"','"+txtType.getText()+"','"+txtNIC.getText()+"','"+txtEmail.getText()+"',password('"+newkey+"'),'active')");
                db.close();
                Functions.showMessage("Alert","You have registered successful.", Alert.AlertType.CONFIRMATION);
            }else{
                Functions.showMessage("Alert","Check your internet connection", Alert.AlertType.WARNING);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        textEditable(false);
        loadTbl();
    }

    private void genUID() {
        try {
            ResultSet rs=db.getData("SELECT MAX(uid) FROM users");
            rs.next();
            String uid=rs.getString("MAX(uid)");
            db.close();
            int uidV=Integer.parseInt(uid.substring(1,uid.length()));
            uidV++;
            txtID.setText("u"+uidV);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void textClear() {
        txtID.clear();
        txtName.clear();
        txtEmail.clear();
        txtTelNo.clear();
        txtType.clear();
        txtAdd.clear();
        txtNIC.clear();
    }

    private void loadTbl(){
        try{
            ResultSet rs=db.getData("SELECT * FROM users WHERE status='active' AND "+cmbSearch.getValue().toString()+" like '%"+txtSearch.getText()+"%'");
            ArrayList<UserModal> arr=new ArrayList<>();

            try {
                while (rs.next()) {
                    UserModal um=new UserModal(rs.getString("uid"),rs.getString("name"),rs.getString("address"),rs.getString("telNo"),rs.getString("type"),rs.getString("nic"),rs.getString("email"));
                    arr.add(um);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ObservableList<UserModal> list= FXCollections.observableArrayList(arr);
            
            uid.setCellValueFactory(new PropertyValueFactory<UserModal,String>("uid"));
            uname.setCellValueFactory(new PropertyValueFactory<UserModal,String>("name"));
            utp.setCellValueFactory(new PropertyValueFactory<UserModal,String>("telNo"));
            uemail.setCellValueFactory(new PropertyValueFactory<UserModal,Double>("email"));
            tblUsers.setItems(list);
            db.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void saveUpdate(){
        try{
            db.putData("UPDATE users SET name='"+txtName.getText()+"',email='"+txtEmail.getText()+"',telNo='"+txtTelNo.getText()+"',nic='"+txtNIC.getText()+"',type='"+txtType.getText()+"',address='"+txtAdd.getText()+"' WHERE uid='"+txtID.getText()+"' ");
            db.close();
            loadTbl();
            textEditable(false);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private void textEditable(boolean val){
        txtID.setEditable(false);
        txtName.setEditable(val);
        txtEmail.setEditable(val);
        txtNIC.setEditable(val);
        txtAdd.setEditable(val);
        txtType.setEditable(val);
        txtTelNo.setEditable(val);
        btnSave.setVisible(val);
        btnCancel.setVisible(val);
    }


    DB db=new DB();
    private boolean isUpdate;
    @FXML
    private TableView<UserModal> tblUsers;
    @FXML
    private Pane pneChPass;
    @FXML
    private Button btnSearch;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnRegister;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnChPass;
    @FXML
    private Button btnSNP;
    @FXML
    private Button btnRemove;
    @FXML
    private TextField txtSearch;
    @FXML
    private ComboBox cmbSearch;
    @FXML
    private TableColumn<UserModal,String> uid;
    @FXML
    private TableColumn<UserModal,String> uname;
    @FXML
    private TableColumn<UserModal,String> utp;
    @FXML
    private TableColumn<UserModal, Double> uemail;
    @FXML
    private TextField txtID;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtTelNo;
    @FXML
    private TextField txtNIC;
    @FXML
    private TextField txtAdd;
    @FXML
    private TextField txtType;
    @FXML
    private PasswordField txtNP;
    @FXML
    private PasswordField txtRNP;
    @FXML
    private PasswordField txtCP;
}
