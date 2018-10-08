package GUI;

import Func.DB;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.util.Date;
import java.net.URL;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import static java.lang.Integer.*;

public class addGRNController implements Initializable {
    DB db =new DB();
    @FXML
    private TextField txtGRNNo;
    @FXML
    private TextField txtProId;
    @FXML
    private TextField txtSup;
    @FXML
    private TextField txtQty;
    @FXML
    private TextField txtAmount;
    @FXML
    private TextField txtUid;
    @FXML
    private Button saveButton;
    @FXML
    private Button clearButton;
    private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    public void buttonAction(ActionEvent actionEvent) throws SQLException{


    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {


        saveButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {




                String pId,grnNo,supplierNo,uid;
                double amount=0;int quantity;
                grnNo=txtGRNNo.getText();
                supplierNo=txtSup.getText();
                pId=txtProId.getText();
                uid=txtUid.getText();
               quantity=Integer.parseInt(txtQty.getText());
                try{
                    amount= Double.parseDouble(txtAmount.getText());
                }
                catch (NumberFormatException e)
                {
                    System.out.println(e);
                }

                Date date = new Date();

                String query="INSERT INTO grn VALUES (null,'"+grnNo+"','"+pId+"','"+quantity+"','"+amount+"')";
                db.putData(query);
                double sum=0;
                String totalquery="SELECT amount FROM grn WHERE grnNO='"+grnNo+"'";
               ResultSet rs=db.getData(totalquery);
                try {
                       while(rs.next()){

                               sum=sum+rs.getDouble(1);

                       }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                String inLog="INSERT INTO grnlog VALUES ('"+grnNo+"','"+ sdf.format(date)+"','"+uid+"','"+supplierNo+"','"+sum+"')";
                db.putData(inLog);
            }
        });
        clearButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                txtAmount.setText("");
                txtSup.setText("");
                txtGRNNo.setText("");
                txtUid.setText("");
                txtProId.setText("");
                txtQty.setText("");
            }
        });



    }
}
