package lk.ijse.t_shop.controller;

import com.jfoenix.controls.JFXComboBox;
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
import lk.ijse.t_shop.dto.raw_materialDto;
import lk.ijse.t_shop.dto.tailorDto;
import lk.ijse.t_shop.model.ItemModel;
import lk.ijse.t_shop.model.TailorModel;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class TailorFromController {

    @FXML
    private TableView<tailorDto> tableTailer;

    @FXML
    private TableColumn<?, ?> columnId;

    @FXML
    private TableColumn<?, ?> columnName;

    @FXML
    private TableColumn<?, ?> columnItemCode;

    @FXML
    private TableColumn<?, ?> columnTel;

    @FXML
    private TextField textId;

    @FXML
    private TextField textName;

    @FXML
    private TextField textTel;
    @FXML
    private TextField textSearch;

    @FXML
    private JFXComboBox<String> combItem;
    private ItemModel itemModel=new ItemModel();
    private TailorModel model = new TailorModel();

   public void initialize(){
       loadItemCodes();
       genarateNextTailorId();
       loadAllTailor();
       valuesFactory();
   }
    public void valuesFactory(){
        columnId.setCellValueFactory(new PropertyValueFactory<>("tailerId"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnTel.setCellValueFactory(new PropertyValueFactory<>("contacNo"));
        columnItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));

    }
    private void loadItemCodes() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<itemDto> idList = itemModel.getAllItem();
            for (itemDto dto : idList) {
                obList.add(dto.getItemCode());
            }
            combItem.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void genarateNextTailorId(){
       try {
           String id = model.genarateNextId();
           textId.setText(id);
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
    }
    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = textId.getText();
        try {
            boolean isDelete = model.deleteTailor(id);
            if (isDelete) {
                new Alert(Alert.AlertType.INFORMATION, "Delete Successful").show();
                clearFiled();
                loadAllTailor();
            }else {
                new Alert(Alert.AlertType.ERROR,"Tailor not Found").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void btnSaveOnAction(ActionEvent event) {
        boolean isCorrect = validateInputs();
       String id= textId.getText();
       String name = textName.getText();
       int tel = Integer.parseInt(textTel.getText());
       String itemCode = combItem.getValue();
       var dto =new tailorDto(id,name,tel,itemCode);
       if (isCorrect) {
           try {
               boolean isSaved = model.saveTailor(dto);
               if (isSaved) {
                   new Alert(Alert.AlertType.INFORMATION, "Tailor Saved Successful").show();
                   clearFiled();
                   loadAllTailor();
               }
           } catch (SQLException e) {
               new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
               throw new RuntimeException(e);
           }
       }
    }
    private boolean validateInputs() {
        boolean matches = Pattern.compile("[T][0-9]{3,}").matcher(textId.getText()).matches();
        if (!matches){
            new Alert(Alert.AlertType.ERROR,"Invalid Tailor Id !!").show();
            return false;
        }
        boolean name = Pattern.matches("[A-Za-z]{4,}",textName.getText());
        if (!name){
            new Alert(Alert.AlertType.ERROR,"Invalid Name !!").show();
            return false;
        }
        boolean contactNo =Pattern.matches("^(?:0|94|\\+94|0094)?(?:(11|21|23|24|25|26|27|31|32|33|34|35|36|37|38|41|45|47|51|52|54|55|57|63|65|66|67|81|91)(0|2|3|4|5|7|9)|7(0|1|2|4|5|6|7|8)\\d)\\d{6}$",textTel.getText());
        if (!contactNo){
            new Alert(Alert.AlertType.ERROR,"Invalid Contact No !!").show();
            return false;
        }
        return true;
    }

    private void clearFiled() throws SQLException {
       textId.setText(model.genarateNextId());
       textName.setText("");
       textTel.setText("");
       combItem.setValue("");
       textSearch.setText("");
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = textId.getText();
        String name = textName.getText();
        int tel = Integer.parseInt(textTel.getText());
        String itemCode = combItem.getValue();

        var dto = new tailorDto(id,name,tel,itemCode);
        try {
            boolean isUpdate = model.updateTailor(dto);
            if (isUpdate){
                new Alert(Alert.AlertType.INFORMATION,"Update Successfully").show();
                clearFiled();
                loadAllTailor();
            }else {
                new Alert(Alert.AlertType.ERROR,"Tailor Not Found").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) throws SQLException {
        clearFiled();
    }

    @FXML
    void textSearchOnAction(ActionEvent event) {
        String id=textSearch.getText();
        try {
            tailorDto dto = model.searchTailer(id);
            if (dto!= null){
                textId.setText(dto.getTailerId());
                textName.setText(dto.getName());
                textTel.setText(String.valueOf(dto.getContacNo()));
               combItem.setValue(dto.getItemCode());

            }else {
                new Alert(Alert.AlertType.ERROR,"Tailor not found").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void loadAllTailor(){
        ObservableList<tailorDto> obList = FXCollections.observableArrayList();

        try {
            List<tailorDto> dtoList = model.getAllTailor();

            for (tailorDto dto : dtoList) {
                obList.add(
                        new tailorDto(
                                dto.getTailerId(),
                                dto.getName(),
                                dto.getContacNo(),
                                dto.getItemCode()
                        )
                );
            }

            tableTailer.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
