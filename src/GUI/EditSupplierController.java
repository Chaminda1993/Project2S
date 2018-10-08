package GUI;

import Func.DB;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EditSupplierController implements Initializable {

    DB db=new DB();

    @FXML
    private TextField saveId;
    @FXML
    private TextField saveName;
    @FXML
    private TextField saveEmail;
    @FXML
    private TextField saveTel;
    @FXML
    private TextField saveBalance;
    @FXML
    private TextField saveAddress;
    @FXML
    private Button saveButton;
    @FXML
    private Button clearButton;

    public void setDet(String id,String name,String add,String tel,String balance,String email){
        saveId.setText(id);
        saveName.setText(name);
        saveAddress.setText(add);
        saveTel.setText(tel);
        saveBalance.setText(balance);
        saveEmail.setText(email);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        saveButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {


                String name,email,add,sid;
                String tel;
                double balance=0;
                name=saveName.getText();
                email=saveEmail.getText();
                tel=saveTel.getText();
                try{
                    balance= Double.parseDouble(saveBalance.getText());
                }
                catch (NumberFormatException e)
                {
                    System.out.println(e);
                }

                add=saveAddress.getText();
                sid=saveId.getText();
                String query="INSERT INTO supplier(sid,name,address,telNo,balance,email) VALUES ('"+sid+"','"+name+"','"+add+"','"+tel+"',"+balance+",'"+email+"')";
                db.putData(query);
            }
        });
        clearButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                saveName.setText("");
                saveEmail.setText("");
                saveBalance.setText("");
                saveAddress.setText("");
                saveId.setText("");
                saveTel.setText("");
            }
        });


    }
}
