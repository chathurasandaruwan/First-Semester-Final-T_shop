package lk.ijse.t_shop.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.t_shop.db.DbConnection;
import lk.ijse.t_shop.model.CustomerModel;
import lk.ijse.t_shop.model.OrderModel;
import lk.ijse.t_shop.model.RecordModel;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DashboardFromController {
    @FXML
    private Label lableTime;

    @FXML
    private PieChart pieChart = new PieChart();

    @FXML
    private Label lablNumOfCust;

    @FXML
    private Label lablNumOfOrder;

    @FXML
    private Label lableSewingOrderPres;

    @FXML
    private Label lableItemOrderPres;

    private OrderModel orderModel = new OrderModel();

    private CustomerModel customerModel = new CustomerModel();
    private RecordModel recordModel = new RecordModel();
    static DecimalFormat decimalFormat = new DecimalFormat("0.00");


    @FXML
    private Label lableDate;
    public void initialize() throws SQLException {
        setDate();
        setTime();
        addValuesPieChart();
        setOrderCount();
        setCustCount();

    }

    public void addValuesPieChart() throws SQLException {
        String presentId = orderModel.genarateNextId();
        int OrderCount = splitOrderId(presentId);

        String PresentRecordId = recordModel.genarateNextId();
        int RecCount = splitRecId(PresentRecordId);

        int itemCount = OrderCount-RecCount;

        double itemOrderPres = setItemPres(OrderCount,itemCount);
        String formattedValue1 = decimalFormat.format(itemOrderPres);
        lableItemOrderPres.setText(formattedValue1);

        double recordCount = setSewingPre(OrderCount,RecCount);;
        String formattedValue = decimalFormat.format(recordCount);
        lableSewingOrderPres.setText(formattedValue);

        pieChart.getData().add(new PieChart.Data("Sewing Orders", RecCount));
        pieChart.getData().add(new PieChart.Data("Item Orders", itemCount));

        // Customize the colors of individual slices
        pieChart.getData().get(0).getNode().setStyle("-fx-pie-color:  #33d1ff;"); // Red
        pieChart.getData().get(1).getNode().setStyle("-fx-pie-color:  #3985f9;"); // Blue



       /* pieChart.getData().add(new PieChart.Data("Category 1", 30));
        pieChart.getData().add(new PieChart.Data("Category 3", 70));*/
    }
    public double setItemPres(int OrderCount,int itemCount){
         return (((double) itemCount /OrderCount)*100);
    }
    public double setSewingPre(int OrderCount,int recCount){
        return (((double) recCount /OrderCount)*100);
    }
    private void setTime() {
        Timeline timeline=new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    LocalTime currentTime =LocalTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                    String formattedTime = currentTime.format(formatter);
                    lableTime.setText(formattedTime);
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void btnPrintOrderHisOnAction(ActionEvent event) throws SQLException, JRException {
        InputStream resourceAsStream = getClass().getResourceAsStream("/report/orderInfo2.jrxml");
        JasperDesign load = JRXmlLoader.load(resourceAsStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(load);

        JasperPrint jasperPrint =
                JasperFillManager.fillReport(
                        jasperReport,
                        null,
                        DbConnection.getInstance().getConnection()
                );

        JasperViewer.viewReport(jasperPrint, false);
    }
    public void setDate() {
        lableDate.setText(String.valueOf(LocalDate.now()));
    }
    public void setOrderCount() throws SQLException {
        String presentId = orderModel.genarateNextId();
        int count = splitOrderId(presentId);
        lablNumOfOrder.setText(String.valueOf(count));
    }
    public  void setCustCount() throws SQLException {
        String presentId = customerModel.genarateCustId();
        int count = splitCustId(presentId);
        lablNumOfCust.setText(String.valueOf(count));
    }
    private int splitOrderId(String currentOrderId) {
        if(currentOrderId != null) {
            String[] split = currentOrderId.split("O0");

            int id = Integer.parseInt(split[1]);
            return id-1;
        } else {
            return 0;
        }
    }
    private int splitCustId(String currentOrderId) {
        if(currentOrderId != null) {
            String[] split = currentOrderId.split("C0");

            int id = Integer.parseInt(split[1]);
            return id-1;
        } else {
            return 0;
        }
    }
    private int splitRecId(String currentOrderId) {
        if(currentOrderId != null) {
            String[] split = currentOrderId.split("R0");

            int id = Integer.parseInt(split[1]);
            return id-1;
        } else {
            return 0;
        }
    }
    @FXML
    void btnPrintRecOrderHisOnAction(ActionEvent event) throws JRException, SQLException {
        InputStream resourceAsStream = getClass().getResourceAsStream("/report/recordOrderHis.jrxml");
        JasperDesign load = JRXmlLoader.load(resourceAsStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(load);

        JasperPrint jasperPrint =
                JasperFillManager.fillReport(
                        jasperReport,
                        null,
                        DbConnection.getInstance().getConnection()
                );

        JasperViewer.viewReport(jasperPrint, false);
    }
}
