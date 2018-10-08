package Func;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class Validate {

    public static boolean isEmail(String val){
        if(!(val.contains("@") && val.contains("."))){
            Functions.showMessage("Invalid","Enter valied e-mail", Alert.AlertType.WARNING);
            return false;
        }
        return true;
    }

    public static boolean isTelNo(String val){
        if(val.length()<9 || val.length() >12){
            Functions.showMessage("Invalid","Enter valied number", Alert.AlertType.WARNING);
            return false;
        }
        for(int i=0;i<val.length();i++){
            if(!Character.isDigit(val.charAt(i))){
                Functions.showMessage("Invalid","Enter valied number", Alert.AlertType.WARNING);
                return false;
            }
        }
        return true;
    }

    public static boolean isNIC(String val){
        if(val.length()!=10) {
            Functions.showMessage("Invalid", "Enter valied NIC", Alert.AlertType.WARNING);
            return false;
        }
        if(!(val.charAt(val.length()-1)=='v' || val.charAt(val.length()-1)=='V')){
            Functions.showMessage("Invalid","Enter valied NIC", Alert.AlertType.WARNING);
            return false;
        }
        for(int i=0;i<val.length()-1;i++){
            if(!Character.isDigit(val.charAt(i))){
                Functions.showMessage("Invalid","Enter valied NIC", Alert.AlertType.WARNING);
                return false;
            }
        }
        return true;
    }

    public static void isEmail(TextField txt){
        if(!isEmail(txt.getText())){
            txt.clear();
            txt.requestFocus();
        }
    }

    public static void isTelNo(TextField txt){
        if(!isTelNo(txt.getText())){
            txt.clear();
            txt.requestFocus();
        }
    }

    public static void isNIC(TextField txt){
        if(!isNIC(txt.getText())){
            txt.clear();
            txt.requestFocus();
        }
    }
}
