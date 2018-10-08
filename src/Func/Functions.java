package Func;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class Functions {
    public static void showMessage(String header, String content, Alert.AlertType at){
        Alert alert = new Alert(at);
        alert.setTitle("BookLab ICS");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static String genKey(){
        String txt="";
        int i=0;
        while(i<7){
            int j=(int)(Math.random()*123);
            if((j>=48 && j<=57) || (j>=65 && j<=90) || (j>=97 && j<=122) ){
                txt+=(char)j;
                i++;
            }
        }
        return txt;
    }

    public static ButtonType confirmMessage(String header, String content){
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION,content,ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.setTitle("BookLab ICS");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
        return alert.getResult();
    }

    public static String inputDialog(String header, String content){
        TextInputDialog dialog=new TextInputDialog();
        dialog.setTitle("BookLab ICS");
        dialog.setHeaderText(header);
        dialog.setContentText(content);
        Optional<String> result = dialog.showAndWait();
        return result.toString();
    }

}
