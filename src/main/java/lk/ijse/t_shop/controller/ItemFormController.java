package lk.ijse.t_shop.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.t_shop.bo.BOFactory;
import lk.ijse.t_shop.bo.custom.ItemBO;
import lk.ijse.t_shop.bo.custom.impl.ItemBOImpl;
import lk.ijse.t_shop.dao.custom.ItemDAO;
import lk.ijse.t_shop.dao.custom.impl.ItemDAOImpl;
import lk.ijse.t_shop.dto.ItemDto;
import lk.ijse.t_shop.view.tdm.itemTm;

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
    private ComboBox<String> comType;

    @FXML
    private JFXTextField textDiscount;

    @FXML
    private TextField textSearch;

    @FXML
    private TextField textColor;
    @FXML
    private TableView<itemTm> tablItem;
    @FXML
    private JFXComboBox<String> comSize;
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

    ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.ITEM);

    public void initialize(){
        genarateNextItemCode();
        selectAllFromItem();
        setvaluesFactory();
        loadAllType();
        loadAllSize();
    }
    public void loadAllSize(){
        ObservableList<String> obList = FXCollections.observableArrayList();
        obList.add("S");
        obList.add("M");
        obList.add("L");
        obList.add("XL");
        comSize.setItems(obList);
    }
    public void loadAllType(){
        ObservableList<String> obList = FXCollections.observableArrayList();
        obList.add("Trouser");
        obList.add("Shorts");
        obList.add("Long sleeve shirt");
        obList.add("Short sleeve shirt");
        obList.add("long sleeve T shirt");
        obList.add("Short sleeve T shirt");
        comType.setItems(obList);
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
    public void clearFiled() throws SQLException, ClassNotFoundException {
        textItemCode.setText(itemBO.generateNextItemCode());
        textPrice.setText("");
        comType.setValue("");
        textColor.setText("");
        comSize.setValue("");
        textDiscount.setText("");
        textQty.setText("");
        textSearch.setText("");
    }
    public void genarateNextItemCode(){
        try {
            String itemCode= itemBO.generateNextItemCode();
            textItemCode.setText(itemCode);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void btnClearOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        clearFiled();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String code = textItemCode.getText();
        try {
            boolean isDelete = itemBO.deleteItem(code);
            if (isDelete) {
                new Alert(Alert.AlertType.INFORMATION, "Delete Successful").show();
                clearFiled();
                selectAllFromItem();
            }else {
                new Alert(Alert.AlertType.ERROR,"Item not Found").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        boolean isCorrect = validateCustomer();
        String itemCode =textItemCode.getText();
        String type = comType.getValue();
        double price = Double.parseDouble(textPrice.getText());
        int quntity = Integer.parseInt(textQty.getText());
        double discountPercentage = Double.parseDouble(textDiscount.getText());
        String size = comSize.getValue();
        String color = textColor.getText();
        var dto = new ItemDto(itemCode,type,price,quntity,discountPercentage,size,color);
        if (isCorrect) {
            try {
                boolean isSaved = itemBO.saveItem(dto);
                if (isSaved) {
                    new Alert(Alert.AlertType.INFORMATION, "Item Saved Successful").show();
                    clearFiled();
                    selectAllFromItem();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private boolean validateCustomer() {
        boolean matches = Pattern.compile("[I][0-9]{3,}").matcher(textItemCode.getText()).matches();
        if (!matches) {
            new Alert(Alert.AlertType.ERROR, "Invalid Item Code !!").show();
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
            List<ItemDto> dtoList = itemBO.getAllItem();

            for (ItemDto dto : dtoList) {
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
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        boolean isCorrect = validateCustomer();
        String code = textItemCode.getText();
        String type =comType.getValue();
        double price= Double.parseDouble(textPrice.getText());
        int qty = Integer.parseInt(textQty.getText());
        double disc = Double.parseDouble(textDiscount.getText());
        String size = comSize.getValue();
        String color = textColor.getText();

        var dto = new ItemDto(code,type,price,qty,disc,size,color);
        if (isCorrect) {
            try {
                boolean isUpdate = itemBO.updateItem(dto);
                if (isUpdate) {
                    new Alert(Alert.AlertType.INFORMATION, "Update Successfully").show();
                    clearFiled();
                    selectAllFromItem();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Item Not Found").show();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void textSearchOnAction(ActionEvent event) {
        String code=textSearch.getText();
        try {
            ItemDto dto = itemBO.searchItem(code);
            if (dto!= null){
                textItemCode.setText(dto.getItemCode());
                comType.setValue(dto.getType());
                textPrice.setText(String.valueOf(dto.getPrice()));
                textQty.setText(String.valueOf(dto.getQuntity()));
                textDiscount.setText(String.valueOf(dto.getDiscountPercentage()));
                comSize.setValue(dto.getSize());
                textColor.setText(dto.getColor());
            }else {
                new Alert(Alert.AlertType.ERROR,"Item not found").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}