package lk.ijse.t_shop.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.t_shop.dto.customerDto;
import lk.ijse.t_shop.dto.itemDto;
import lk.ijse.t_shop.dto.tm.itemTm;
import lk.ijse.t_shop.model.ItemModel;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class ItemFormController {

    @FXML
    private TextField textItemCode;

    @FXML
    private TextField textPrice;

    @FXML
    private TextField textQty;

    @FXML
    private TextField textType;

    @FXML
    private JFXTextField textDiscount;

    @FXML
    private TextField textSearch;

    @FXML
    private TextField textColor;

    @FXML
    private JFXTextField textSize;
    @FXML
    private TableView<itemTm> tablItem;
    @FXML
    private TableColumn<?, ?> columnCode;

    @FXML
    private TableColumn<?, ?> columnPrice;

    @FXML
    private TableColumn<?, ?> columnQty;

    @FXML
    private TableColumn<?, ?> columnType;

    @FXML
    private TableColumn<?, ?> columnColor;

    @FXML
    private TableColumn<?, ?> columnDiscount;

    @FXML
    private TableColumn<?, ?> columnSize;


    private ItemModel model=new ItemModel();

    public void initialize(){
        genarateNextItemCode();
        selectAllFromItem();
        setvaluesFactory();
    }
    private void setvaluesFactory() {
        columnCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        columnQty.setCellValueFactory(new PropertyValueFactory<>("quntity"));
        columnType.setCellValueFactory(new PropertyValueFactory<>("type"));
        columnColor.setCellValueFactory(new PropertyValueFactory<>("color"));
        columnDiscount.setCellValueFactory(new PropertyValueFactory<>("discountPercentage"));
        columnSize.setCellValueFactory(new PropertyValueFactory<>("size"));
    }
    public void clearFiled() throws SQLException {
        textItemCode.setText(model.genarateItemCode());
        textPrice.setText("");
        textType.setText("");
        textColor.setText("");
        textSize.setText("");
        textDiscount.setText("");
        textQty.setText("");
        textSearch.setText("");
    }
    public void genarateNextItemCode(){
        try {
            String itemCode= model.genarateItemCode();
            textItemCode.setText(itemCode);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void btnClearOnAction(ActionEvent event) throws SQLException {
        clearFiled();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String code = textItemCode.getText();
        try {
            boolean isDelete = model.deleteItem(code);
            if (isDelete) {
                new Alert(Alert.AlertType.INFORMATION, "Delete Successful").show();
                clearFiled();
                selectAllFromItem();
            }else {
                new Alert(Alert.AlertType.ERROR,"Item not Found").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String itemCode =textItemCode.getText();
        String type = textType.getText();
        double price = Double.parseDouble(textPrice.getText());
        int quntity = Integer.parseInt(textQty.getText());
        double discountPercentage = Double.parseDouble(textDiscount.getText());
        String size = textSize.getText();
        String color = textColor.getText();
        var dto = new itemDto(itemCode,type,price,quntity,discountPercentage,size,color);
        boolean isCorrect = validateCustomer(dto);
        if (isCorrect) {
            try {
                boolean isSaved = model.saveItem(dto);
                if (isSaved) {
                    new Alert(Alert.AlertType.INFORMATION, "Item Saved Successful").show();
                    clearFiled();
                    selectAllFromItem();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                throw new RuntimeException(e);
            }
        }
    }
    private boolean validateCustomer(itemDto dto) {
        boolean matches = Pattern.compile("[I][0-9]{3,}").matcher(dto.getItemCode()).matches();
        if (!matches) {
            new Alert(Alert.AlertType.ERROR, "Invalid Item Code !!").show();
            return false;
        }
        boolean type = Pattern.matches("[A-Za-z]{4,}",dto.getType());
        if (!type){
            new Alert(Alert.AlertType.ERROR,"Invalid Type !!").show();
            return false;
        }
        boolean price = Pattern.matches("[0-9.]{1,}",textPrice.getText());
        if (!price){
            new Alert(Alert.AlertType.ERROR,"Invalid Price !!").show();
            return false;
        }
        boolean qty = Pattern.matches("[0-9]{1,}",textQty.getText());
        if (!qty){
            new Alert(Alert.AlertType.ERROR,"Invalid Quntity !!").show();
            return false;
        }
        boolean precentage = Pattern.matches("[0-9.]{1,}",textDiscount.getText());
        if (!precentage){
            new Alert(Alert.AlertType.ERROR,"Invalid Discount !!").show();
            return false;
        }
        boolean size = Pattern.matches("[A-Z]{1,}",textSize.getText());
        if (!size){
            new Alert(Alert.AlertType.ERROR,"Invalid Size !!").show();
            return false;
        }
        boolean color = Pattern.matches("[A-Za-z]{2,}",textColor.getText());
        if (!color){
            new Alert(Alert.AlertType.ERROR,"Invalid Color !!").show();
            return false;
        }
        return true;
    }
    private void selectAllFromItem() {
        ObservableList<itemTm> obList = FXCollections.observableArrayList();

        try {
            List<itemDto> dtoList = model.getAllItem();

            for (itemDto dto : dtoList) {
                obList.add(
                        new itemTm(
                                dto.getItemCode(),
                                dto.getType(),
                                dto.getPrice(),
                                dto.getQuntity(),
                                dto.getDiscountPercentage(),
                                dto.getSize(),
                                dto.getColor()
                        )
                );
            }

            tablItem.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String code = textItemCode.getText();
        String type =textType.getText();
        double price= Double.parseDouble(textPrice.getText());
        int qty = Integer.parseInt(textQty.getText());
        double disc = Double.parseDouble(textDiscount.getText());
        String size = textSize.getText();
        String color = textColor.getText();

        var dto = new itemDto(code,type,price,qty,disc,size,color);
        try {
            boolean isUpdate = model.updateItem(dto);
            if (isUpdate){
                new Alert(Alert.AlertType.INFORMATION,"Update Successfully").show();
                clearFiled();
                selectAllFromItem();
            }else {
                new Alert(Alert.AlertType.ERROR,"Item Not Found").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void textSearchOnAction(ActionEvent event) {
        String code=textSearch.getText();
        try {
            itemDto dto = model.searchItem(code);
            if (dto!= null){
                textItemCode.setText(dto.getItemCode());
                textType.setText(dto.getType());
                textPrice.setText(String.valueOf(dto.getPrice()));
                textQty.setText(String.valueOf(dto.getQuntity()));
                textDiscount.setText(String.valueOf(dto.getDiscountPercentage()));
                textSize.setText(dto.getSize());
                textColor.setText(dto.getColor());
            }else {
                new Alert(Alert.AlertType.ERROR,"Item not found").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}