package GUI;

import Func.DB;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static java.lang.Integer.*;

public class AddCustomerController implements Initializable {
    DB db =new DB();
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


    public void buttonAction(ActionEvent actionEvent) throws SQLException{


    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {


        saveButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {


                String name,Nic,add,cid;
                String tel;
                double balance=0;
                name=saveName.getText();
                Nic=saveNic.getText();
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
                String query="INSERT INTO customer(cid,name,address,telNo,balance,nic) VALUES ('"+cid+"','"+name+"','"+add+"','"+tel+"',"+balance+",'"+Nic+"')";
                System.out.println("x");
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

