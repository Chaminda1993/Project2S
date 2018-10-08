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

public class AddSupplierController implements Initializable {
    DB db =new DB();
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



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Validate validate=new Validate();


        saveButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                if (validate.isEmail(saveEmail.getText()) && validate.isNum(saveBalance.getText()) && validate.isTelNo(saveTel.getText())) {
                    String name, email, add, sid;
                    String tel;
                    double balance = 0;
                    name = saveName.getText();
                    email = saveEmail.getText();
                    tel = saveTel.getText();
                    try {
                        balance = Double.parseDouble(saveBalance.getText());
                    } catch (NumberFormatException e) {
                        System.out.println(e);
                    }

                    add = saveAddress.getText();
                    sid = saveId.getText();
                    String query = "INSERT INTO supplier(sid,name,address,telNo,balance,email) VALUES ('" + sid + "','" + name + "','" + add + "','" + tel + "'," + balance + ",'" + email + "')";
                    db.putData(query);
                }
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
