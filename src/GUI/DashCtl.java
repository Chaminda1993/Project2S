package GUI;

import Func.DB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DashCtl implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadUserDetails();
        loadProductTbl();
    }

    public void loadProductTbl() {
        DB db=new DB();
        try{
            ResultSet rs=db.getData("SELECT pid,description,stock,stockMinLevel FROM product p where p.stock<p.stockMinLevel");


            ArrayList<DashTblModal> arr=new ArrayList<>();

            try {
                while (rs.next()) {
                    DashTblModal dtm=new DashTblModal(rs.getString("pid"),rs.getString("description"),rs.getInt("stock"),rs.getInt("stockMinLevel"));
                    arr.add(dtm);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ObservableList<DashTblModal> list= FXCollections.observableArrayList(arr);

            colID.setCellValueFactory(new PropertyValueFactory<DashTblModal,String>("pid"));
            colItem.setCellValueFactory(new PropertyValueFactory<DashTblModal,String>("pName"));
            colCurLvl.setCellValueFactory(new PropertyValueFactory<DashTblModal,Integer>("stock"));
            colMinLvl.setCellValueFactory(new PropertyValueFactory<DashTblModal,Integer>("minLvl"));
            tblPro.setItems(list);
            db.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void loadUserDetails() {
        txtUID.setText(HomePageController.curUser.getUserID());
        txtName.setText(HomePageController.curUser.getUserName());
        txtType.setText(HomePageController.curUser.getUserType());
        txtEmail.setText(HomePageController.curUser.getUserEmail());
    }


    //Var
    @FXML
    private TextField txtUID;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtType;
    @FXML
    private TextField txtEmail;
    @FXML
    private TableView<DashTblModal> tblPro;
    @FXML
    private TableColumn<DashTblModal, String> colID;
    @FXML
    private TableColumn<DashTblModal, String> colItem;
    @FXML
    private TableColumn<DashTblModal, Integer> colCurLvl;
    @FXML
    private TableColumn<DashTblModal, Integer> colMinLvl;
}
