package GUI;

import Func.CurUser;
import Func.DB;
import Func.Functions;
import Func.SendMail;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class LoginCtl implements Initializable {
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnExit;
    @FXML
    private Button btnForget;
    @FXML
    private TextField txtUName;
    @FXML
    private PasswordField txtPass;
    @FXML
    private AnchorPane loginPane;


    @FXML
    public void btnExit(){
        Stage stage=(Stage)loginPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void btnForget(){
        if(txtUName.getText().isEmpty()){
            Functions.showMessage("Alert","Enter user ID", Alert.AlertType.WARNING);
            return;
        }
        try {
            String newKey=Functions.genKey();
            ResultSet rs=db.getData("SELECT email FROM users WHERE uid='"+txtUName.getText()+"'");
            rs.next();
            Boolean sent= SendMail.sendMail(rs.getString("email"),"BookLab ICS","Your new password is "+newKey+". Please login with this.");
            if(sent){
                sent=db.putData("UPDATE users SET password=password('"+newKey+"') WHERE uid='"+txtUName.getText()+"'");
                if(sent){
                    Functions.showMessage("Reset password","Your new password has sent to "+rs.getString("email")+"Please check it.", Alert.AlertType.CONFIRMATION);
                }else{
                    Functions.showMessage("Error","Some error from saving password", Alert.AlertType.ERROR);
                }
            }else{
                Functions.showMessage("Error","Email not send. Check your internet connection", Alert.AlertType.ERROR);
            }
            db.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    DB db=new DB();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(isNotNull()){
                    try {
                        ResultSet rs=db.getData("SELECT * FROM users WHERE password=password('"+new String(txtPass.getText())+"')");
                        while(rs.next()){
                            if(txtUName.getText().equals(rs.getString("uid")) && rs.getString("status").equals("active")){
                                HomePageController.curUser.setUserID(rs.getString("uid"));
                                HomePageController.curUser.setUserName(rs.getString("name"));
                                HomePageController.curUser.setUserType(rs.getString("type"));
                                HomePageController.curUser.setUserEmail(rs.getString("email"));
                                loginPane.getChildren().remove(0);
                                HomePageController.isloged=true;
                                Functions.showMessage("WELCOME","Login successful", Alert.AlertType.CONFIRMATION);
                                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                String dateTime=sdf.format(new Date());
                                db.putData("INSERT INTO loginlog(uid,loginTime,logoutTime) VALUES ('"+rs.getString("uid")+"','"+dateTime+"','online')");
                                return;
                            }
                        }
                        Functions.showMessage("Invalid login","User ID or password is invalid", Alert.AlertType.WARNING);
                        txtUName.clear();
                        txtPass.clear();
                        HomePageController.isloged=false;
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    db.close();
                }
            }
        });
    }


    private boolean isNotNull() {
        if(txtUName.getText().isEmpty()){
            Functions.showMessage("Alert","Enter user ID", Alert.AlertType.WARNING);
            return false;
        }else if(txtPass.getText().isEmpty()){
            Functions.showMessage("Alert","Enter password", Alert.AlertType.WARNING);
            return false;
        }
        return true;
    }
}
