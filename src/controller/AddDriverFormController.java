package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.Drivers;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddDriverFormController {
    public JFXTextField txtName;
    public JFXTextField txtNIC;
    public JFXTextField txtLicene;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public TableView tblDrivers;
    public TableColumn colName;
    public TableColumn colNIC;
    public TableColumn colLicense;
    public TableColumn colAddress;
    public TableColumn colContact;
    public AnchorPane driverContext;

    public Label dLabel5;
    public Label dLabel4;
    public Label dLabel3;
    public Label dLabel2;
    public Label dLabel1;

    static ArrayList<Drivers> driverArrayList = new ArrayList();

    public void initialize(){
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("NIC"));
        colLicense.setCellValueFactory(new PropertyValueFactory<>("liceneNo"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
    }

    public void addDriverOnAction(ActionEvent actionEvent) {
        Drivers driver = new Drivers(txtName.getText(),txtNIC.getText(),txtLicene.getText(),txtAddress.getText(),Integer.parseInt(txtContact.getText()));

        txtName.clear();txtNIC.clear();txtLicene.clear();txtAddress.clear();txtContact.clear();

        if (driverArrayList.add(driver)){
            loadAllDriver();
            new Alert(Alert.AlertType.CONFIRMATION,"Saved successfully...", ButtonType.CLOSE).show();
        }else{
            new Alert(Alert.AlertType.WARNING,"Try Again...", ButtonType.CLOSE).show();
        }
    }

    private void loadAllDriver(){
        ObservableList<Drivers>observableList= FXCollections.observableArrayList();
        for (Drivers temp : driverArrayList){
            observableList.add(new Drivers(temp.getName(),temp.getNIC(),temp.getLiceneNo(),temp.getAddress(),temp.getContact()));
        }
        tblDrivers.setItems(observableList);
    }

    public void checkingDContactNo(javafx.scene.input.KeyEvent keyEvent) {
        String value ="^([0-9]{10})$";
        Pattern pattern=Pattern.compile(value);
        Matcher match=pattern.matcher(txtContact.getText());
        if(!match.matches()){
            dLabel5.setText("Invalid Contact Number!");
        }else{
            dLabel5.setText("");
        }
    }

    public void checkingDAddress(javafx.scene.input.KeyEvent keyEvent) {
        String value ="^([A-Za-z]{3,40})$";
        Pattern pattern=Pattern.compile(value);
        Matcher match=pattern.matcher(txtAddress.getText());
        if(!match.matches()){
            dLabel4.setText("Invalid Address!");
        }else{
            dLabel4.setText("");
        }
    }

    public void checkingDLiceneNo(javafx.scene.input.KeyEvent keyEvent) {
        String value ="^([A-Z]{1}.([0-9]{7}))$";
        Pattern pattern=Pattern.compile(value);
        Matcher match=pattern.matcher(txtLicene.getText());
        if(!match.matches()){
            dLabel3.setText("Invalid Licene Number!");
        }else{
            dLabel3.setText("");
        }
    }

    public void checkingDNIC(javafx.scene.input.KeyEvent keyEvent) {
        String value ="^([0-9]{11}.([A-Z]{1}))$";
        Pattern pattern=Pattern.compile(value);
        Matcher match=pattern.matcher(txtNIC.getText());
        if(!match.matches()){
            dLabel2.setText("Invalid NIC!");
        }else{
            dLabel2.setText("");
        }
    }

    public void checkingDName(javafx.scene.input.KeyEvent keyEvent) {
        String value ="^([A-Za-z]{1,30})$";
        Pattern pattern=Pattern.compile(value);
        Matcher match=pattern.matcher(txtName.getText());
        if(!match.matches()){
            dLabel1.setText("Invalid Name!");
        }else{
            dLabel1.setText("");
        }
    }


    public void moveToNIC(ActionEvent actionEvent) {
        txtNIC.requestFocus();
    }

    public void moveToLiceneNo(ActionEvent actionEvent) {
        txtLicene.requestFocus();
    }

    public void moveToAddress(ActionEvent actionEvent) {
        txtAddress.requestFocus();
    }

    public void moveToContact(ActionEvent actionEvent) {
        txtContact.requestFocus();
    }



}
