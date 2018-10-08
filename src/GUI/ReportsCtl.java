package GUI;

import Func.DB;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

    public class ReportsCtl implements Initializable {

        @FXML private Button dailyInvoice;
        @FXML private Button dailySales;
        @FXML private Button monthlySales;
        @FXML private Button userLogin;
        @FXML private TextField invoDate;
        @FXML private TextField saleDate;
        @FXML private TextField saleYear;
        @FXML private TextField saleMonth;
        @FXML private TextField saleType;
        @FXML private TextField logDate;
        @FXML private TextField logName;
        @Override
        public void initialize(URL location, ResourceBundle resources) {

            dailyInvoice.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    try {
                        Connection connection = DB.con();
                        String xml = "DailyInvoiceReports.jrxml";
                        JasperDesign jd= JRXmlLoader.load((getClass().getResourceAsStream(xml)));
                        Map<String, Object> params = new HashMap<String, Object>();
                        params.put("date", invoDate.getText());
                        JasperReport jr = JasperCompileManager.compileReport(jd);
                        JasperPrint jp = JasperFillManager.fillReport(jr, params,connection);
                        JasperViewer.viewReport(jp, false);
                        JasperPrintManager.printReport(jp, false);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            dailySales.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    try {
                        Connection connection = DB.con();
                        String xml = "DailySalesReport.jrxml";
                        JasperDesign jd= JRXmlLoader.load((getClass().getResourceAsStream(xml)));
                        Map<String, Object> params = new HashMap<String, Object>();
                        params.put("date", saleDate.getText());
                        JasperReport jr = JasperCompileManager.compileReport(jd);
                        JasperPrint jp = JasperFillManager.fillReport(jr, params,connection);
                        JasperViewer.viewReport(jp, false);
                        JasperPrintManager.printReport(jp, false);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            monthlySales.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    try {
                        Connection connection = DB.con();
                        String xml = "MonthlySalesReport.jrxml";
                        JasperDesign jd= JRXmlLoader.load((getClass().getResourceAsStream(xml)));
                        Map<String, Object> params = new HashMap<String, Object>();
                        params.put("year", saleYear.getText());
                        params.put("month", saleMonth.getText());
                        params.put("type", "%"+saleType.getText()+"%");
                        JasperReport jr = JasperCompileManager.compileReport(jd);
                        JasperPrint jp = JasperFillManager.fillReport(jr, params,connection);
                        JasperViewer.viewReport(jp, false);
                        JasperPrintManager.printReport(jp, false);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            userLogin.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    try {
                        Connection connection = DB.con();
                        String xml = "UserLogin.jrxml";
                        JasperDesign jd= JRXmlLoader.load((getClass().getResourceAsStream(xml)));
                        Map<String, Object> params = new HashMap<String, Object>();
                        params.put("date1", logDate.getText());
                        params.put("name1", logName.getText());
                        JasperReport jr = JasperCompileManager.compileReport(jd);
                        JasperPrint jp = JasperFillManager.fillReport(jr, params,connection);
                        JasperViewer.viewReport(jp, false);
                        JasperPrintManager.printReport(jp, false);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }