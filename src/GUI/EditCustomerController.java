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

public class EditCustomerController implements Initializable {

    DB db=new DB();

    @FXML
    private TextField saveId;
    @FXML
    private TextField saveName;
    @FXML
    private TextField saveNic;
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

    public void setDet(String id,String name,String add,String tel,String balance,String nic){
        saveId.setText(id);
        saveName.setText(name);
        saveAddress.setText(add);
        saveTel.setText(tel);
        saveBalance.setText(balance);
        saveNic.setText(nic);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        saveButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {


                String name,nic,add,cid,tel;
                double balance=0;
                name=saveName.getText();
                nic=saveNic.getText();
                tel=saveTel.getText();
                try{
                    balance= Double.parseDouble(saveBalance.getText());
                }
                catch (NumberFormatException e)
                {
                    System.out.println(e);
                }

                add=saveAddress.getText();
                cid=saveId.getText();
                String query="INSERT INTO customer(cid,name,address,telNo,balance,nic) VALUES ('"+cid+"','"+name+"','"+add+"','"+tel+"',"+balance+",'"+nic+"')";
                db.putData(query);
            }
        });
        clearButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                saveName.setText("");
                saveNic.setText("");
                saveBalance.setText("");
                saveAddress.setText("");
                saveId.setText("");
                saveTel.setText("");
            }
        });


    }
}

