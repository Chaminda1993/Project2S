package GUI;

import Func.CurUser;
import Func.DB;
import Func.Functions;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {
    @FXML
    private BorderPane borderPane;

    @FXML
    private StackPane pneStack;

    @FXML
    private Button btnClose;
    int count;
    @FXML
    protected void btnClose(){

        count=pneStack.getChildren().size();
        if(count>1){
            pneStack.getChildren().remove(count-1);
            btnClose.setText("Close:"+(count-1));
        }else if(count==1){
            btnClose.setText("Logout");
            pneStack.getChildren().remove(count-1);
        }else{
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateTime=sdf.format(new Date());
            db.putData("UPDATE loginlog SET logoutTime='"+dateTime+"' WHERE uid='"+curUser.getUserID()+"'");
            //db.putData("INSERT INTO loginlog(uid,logoutTime) VALUES ('"+curUser.getUserID()+"','"+dateTime+"')");
            db.close();
            isloged=false;
            curUser.clear();
            loadUI("Login");
        }
    }

    DB db=new DB();

    @FXML
    public void minimize(){
        Stage stage=(Stage)borderPane.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    public void btnDashboard(){
        if(isloged){loadUI("Dashboard");}else{Functions.showMessage("Error","Login first", Alert.AlertType.WARNING);}
    }

    @FXML
    public void btnInvoice(){
        if(isloged){loadUI("Invoice");}else{Functions.showMessage("Error","Login first", Alert.AlertType.WARNING);}
    }

    @FXML
    public void btnGRN(){
        if(isloged){loadUI("GRN");}else{Functions.showMessage("Error","Login first", Alert.AlertType.WARNING);}
    }

    @FXML
    public void btnProduct(){
        if(isloged){loadUI("Products");}else{Functions.showMessage("Error","Login first", Alert.AlertType.WARNING);}
    }

    @FXML
    public void btnReports(){
        if(isloged){loadUI("Reports");}else{Functions.showMessage("Error","Login first", Alert.AlertType.WARNING);}
    }

    @FXML
    public void btnCustomers(){
        if(isloged){loadUI("Customers");}else{Functions.showMessage("Error","Login first", Alert.AlertType.WARNING);}
    }

    @FXML
    public void btnSuppliers(){
        if(isloged){loadUI("Suppliers");}else{Functions.showMessage("Error","Login first", Alert.AlertType.WARNING);}
    }

    @FXML
    public void btnUsers(){
        if(isloged){loadUI("Users");}else{Functions.showMessage("Error","Login first", Alert.AlertType.WARNING);}
    }

    @FXML
    public void btnSettings(){
        if(isloged){loadUI("Settings");}else{Functions.showMessage("Error","Login first", Alert.AlertType.WARNING);}
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        isloged=false;
        loadUI("Login");

    }



    @FXML
    protected static Pane vBox;
    protected static boolean isloged;



    protected void loadUI(String ui){
        borderPane.setCenter(pneStack);
        Parent root=null;
        try {
            root= FXMLLoader.load(getClass().getResource(ui+".fxml"));
        } catch (IOException e) {
           // e.printStackTrace();
            System.out.println("loadUI");
        }
        //borderPane.setCenter(root);
        pneStack.getChildren().addAll(root);
        count=pneStack.getChildren().size();
        if(count>5){
            pneStack.getChildren().remove(0);
        }else if(count>0){
            btnClose.setText("Close:"+count);
        }
    }

    public void startUp(){
        if(isloged){
            btnDashboard();
        }
    }


    protected static CurUser curUser=new CurUser();
}
