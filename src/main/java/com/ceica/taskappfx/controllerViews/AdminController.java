package com.ceica.taskappfx.controllerViews;

import com.ceica.taskappfx.controller.TaskController;
import com.ceica.taskappfx.models.Rol;
import com.ceica.taskappfx.models.User;
import javafx.beans.Observable;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.util.List;

public class AdminController extends ControllerView {
    @FXML
    protected TableView<User> tblUser;
    @FXML
    protected TableColumn<User, Integer> idColumn;
    @FXML
    protected TableColumn<User, String> nameColumn;
    @FXML
    protected TableColumn<User, String> rolColumn;
    @FXML
    protected TextField txtName;
    @FXML
    protected PasswordField txtPassword;
    @FXML
    protected PasswordField txtRepeatPassword;
    @FXML
    protected ComboBox<Rol> comboRol;
    @FXML
    protected Button btnAddUser;
    @FXML
    protected Label lblMsg;


    private ObservableList<User> userObservableList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getIduser()));
        nameColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getUsername()));
        rolColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getRol().getDescription()));
        comboRol.setConverter(new StringConverter<Rol>() {
            @Override
            public String toString(Rol rol) {
                return rol.getDescription();
            }
            @Override
            public Rol fromString(String s) {
                return null;
            }
        });
    }

    public AdminController() {
    }

    @Override
    public void cargaInicial() {
        List<User> userList = TaskController.getAllUser();
        userObservableList.addAll(userList);
        tblUser.setItems(userObservableList);
        List<Rol> rolList=taskController.getAllRol();
        comboRol.getItems().addAll(rolList);
    }

    public void btnAddUser(ActionEvent actionEvent) {
        if(txtPassword.getText().equals(txtRepeatPassword.getText())){
            taskController.createUser(txtName.getText(),txtPassword.getText(),comboRol.getSelectionModel().getSelectedItem().getIdrol());
            List<User> userList = TaskController.getAllUser();
            userObservableList.removeAll();
            userObservableList.addAll(userList);
            tblUser.refresh();
        }else{
            lblMsg.setText("Password must be equals");
        }
    }
}
