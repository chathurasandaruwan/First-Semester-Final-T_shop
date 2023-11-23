package lk.ijse.t_shop.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.t_shop.dto.PlaceOrderDto;
import lk.ijse.t_shop.dto.customerDto;
import lk.ijse.t_shop.dto.itemDto;
import lk.ijse.t_shop.dto.tm.CartTm;
import lk.ijse.t_shop.model.CustomerModel;
import lk.ijse.t_shop.model.ItemModel;
import lk.ijse.t_shop.model.OrderModel;
import lk.ijse.t_shop.model.PlaceOrderModel;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderFromController {

    @FXML
    private Label lableOrderId;

    @FXML
    private Label lableDate;

    @FXML
    private JFXComboBox<String> combCustId;

    @FXML
    private Label lableCustName;

    @FXML
    private Label lableTime;

    @FXML
    private TableView<CartTm> tableOrder;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colDiscount;

    @FXML
    private TableColumn<?, ?> colTot;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private Label lablePrice;

    private OrderModel model=new OrderModel();
    private ItemModel itemModel=new ItemModel();
    private CustomerModel customerModel=new CustomerModel();
    private PlaceOrderModel placeOrderModel = new PlaceOrderModel();
    @FXML
    private AnchorPane root;
    @FXML
    private Label lableSize;

    @FXML
    private Label lableColor;

    @FXML
    private Label lableDesc;

    @FXML
    private JFXComboBox<String> combItemCode;

    @FXML
    private Label lableQuntity;

    @FXML
    private Label lableDiscountPre;

    @FXML
    private Label lableNetTotal;

    @FXML
    private TextField textQty;
    private ObservableList<CartTm> obList = FXCollections.observableArrayList();

    public void initialize(){
        setDate();
        setTime();
        genarateNextOrderId();
        loadItemCodes();
        loadCustomerIds();
        setCellValueFactory();
    }
    public void clearFailed(){
        combItemCode.setValue("");
        lableDesc.setText("");
        lableColor.setText("");
        lableSize.setText("");
        lableQuntity.setText("");
        lablePrice.setText("");
        lableDiscountPre.setText("");
    }
    public void afterPlaseBtn() throws SQLException {
        combCustId.setValue("");
        lableOrderId.setText(model.genarateNextId());
        lableNetTotal.setText("");
        lableCustName.setText("");
        obList.clear();
        tableOrder.refresh();
    }
    private void setCellValueFactory() {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTot.setCellValueFactory(new PropertyValueFactory<>("tot"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    private void setTime(){
        Timeline timeline=new Timeline(
                new KeyFrame(Duration.seconds(1),event -> {
                    LocalTime currentTime =LocalTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                    String formattedTime = currentTime.format(formatter);
                    lableTime.setText(formattedTime);
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    private void setDate() {
        lableDate.setText(String.valueOf(LocalDate.now()));
    }
    private void loadItemCodes() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<itemDto> idList = itemModel.getAllItem();

            for (itemDto dto : idList) {
                obList.add(dto.getItemCode());
            }
            combItemCode.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void loadCustomerIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<customerDto> idList = customerModel.getAllCustomer();

            for (customerDto dto : idList) {
                obList.add(dto.getCustId());
            }

            combCustId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void genarateNextOrderId(){
        try {
            String id = model.genarateNextId();
            lableOrderId.setText(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void btnAddCustomerOnAction(ActionEvent event) throws IOException {
        Parent rootNode =FXMLLoader.load(getClass().getResource("/view/customer_form.fxml"));
        this.root.getChildren().clear();
        this.root.getChildren().add(rootNode);
    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        String code = combItemCode.getValue();
        String description = lableDesc.getText();
        int qty = Integer.parseInt(textQty.getText());
        int quntity= Integer.parseInt(lableQuntity.getText());
        double unitPrice = Double.parseDouble(lablePrice.getText());
       double discount = Double.parseDouble(lableDiscountPre.getText());
        double tot = (unitPrice-(unitPrice*(discount/100))) * qty;
        Button btn = new Button("Remove");

        setRemoveBtnAction(btn);
        btn.setCursor(Cursor.HAND);

        if (quntity>0) {
            if (!obList.isEmpty()) {
                for (int i = 0; i < tableOrder.getItems().size(); i++) {
                    if (colItemCode.getCellData(i).equals(code)) {
                        int col_qty = (int) colQty.getCellData(i);
                        qty += col_qty;
                        tot += (unitPrice - (unitPrice * (discount / 100))) * qty;

                        obList.get(i).setQty(qty);
                        obList.get(i).setTot(tot);

                        calculateTotal();
                        tableOrder.refresh();
                        return;
                    }
                }
            }
            var cartTm = new CartTm(code, description, unitPrice, qty, tot, discount, btn);

            obList.add(cartTm);

            tableOrder.setItems(obList);
            calculateTotal();
            clearFailed();
            textQty.clear();
        }else {
            new Alert(Alert.AlertType.ERROR,"Item Out of stoke").show();
        }
    }
    private void setRemoveBtnAction(Button btn) {
        btn.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
                int focusedIndex = tableOrder.getSelectionModel().getSelectedIndex();
                obList.remove(focusedIndex);
                tableOrder.refresh();
                calculateTotal();
            }
        });
    }
    private void calculateTotal() {
        double total = 0;
        for (int i = 0; i < tableOrder.getItems().size(); i++) {
            total += (double) colTot.getCellData(i);
        }
        lableNetTotal.setText(String.valueOf(total));
    }
    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) throws SQLException {
        String orderId = lableOrderId.getText();
        LocalDate date = LocalDate.parse(lableDate.getText());
        String customerId = combCustId.getValue();

        List<CartTm> cartTmList = new ArrayList<>();
        for (int i = 0; i < tableOrder.getItems().size(); i++) {
            CartTm cartTm = obList.get(i);
            cartTmList.add(cartTm);
        }
        var placeOrderDto = new PlaceOrderDto(orderId, date, customerId, cartTmList);
        try {
            boolean isSuccess = placeOrderModel.placeOrder(placeOrderDto);
            if (isSuccess) {
                new Alert(Alert.AlertType.CONFIRMATION, "Order Success!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        afterPlaseBtn();
    }

    @FXML
    void combCustIdOnAction(ActionEvent event) {
        String id = combCustId.getValue();
        try {
            customerDto customerDto = customerModel.searchCustomer(id);
            lableCustName.setText(customerDto.getName());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void comnItemOnAction(ActionEvent event) {
        String code = combItemCode.getValue();

        textQty.requestFocus();
        try {
            itemDto dto = itemModel.searchItem(code);
            lableDesc.setText(dto.getType());
            lablePrice.setText(String.valueOf(dto.getPrice()));
            lableQuntity.setText(String.valueOf(dto.getQuntity()));
            lableDiscountPre.setText(String.valueOf(dto.getDiscountPercentage()));
            lableSize.setText(dto.getSize());
            lableColor.setText(dto.getColor());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void textQtyOnAction(ActionEvent event) {
        btnAddToCartOnAction(event);
    }

}
