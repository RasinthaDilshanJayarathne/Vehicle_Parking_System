package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Bus;
import model.CargoLorry;
import model.Van;
import model.Vehicle;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddVehicleFormController {
    public JFXComboBox vehicleCmb;
    public JFXTextField vehicleNo;
    public JFXTextField noOfPassengers;
    public JFXTextField maxWeight;

    public TableView tblVehicle;
    public TableColumn colVNo;
    public TableColumn colVType;
    public TableColumn colMWeight;
    public TableColumn colVPassengers;

    public Label vLabel3;
    public Label vLabel2;
    public Label vLabel1;

    static ArrayList<Vehicle> vehicleList = new ArrayList();

    public void initialize(){

        colVType.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));
        colMWeight.setCellValueFactory(new PropertyValueFactory<>("maxWeight"));
        colVNo.setCellValueFactory(new PropertyValueFactory<>("vehicleNo"));
        colVPassengers.setCellValueFactory(new PropertyValueFactory<>("noOfPassengers"));

        vehicleCmb.getItems().addAll("Van","Bus","Cargo Lorry");

    }

    public void addVehicle(ActionEvent actionEvent) {
        if(vehicleList.size()==14){
            new Alert(Alert.AlertType.WARNING,"You Cannot Add Vehicles!...", ButtonType.CLOSE).show();
        }else{
            try {

                if(vehicleCmb.getSelectionModel().getSelectedItem().toString().equals("Van")){
                    Van van = new Van(vehicleNo.getText(),vehicleCmb.getValue().toString(),Integer.parseInt(noOfPassengers.getText()),Integer.parseInt(maxWeight.getText()));
                    vehicleNo.clear();

                    noOfPassengers.clear();
                    maxWeight.clear();
                    if (vehicleList.add(van)){
                        loadAllVehicle();
                        vehicleCmb.getSelectionModel().clearSelection();
                        new Alert(Alert.AlertType.CONFIRMATION,"Saved successfully...", ButtonType.CLOSE).show();
                    }else{
                        new Alert(Alert.AlertType.WARNING,"Try Again...", ButtonType.CLOSE).show();
                    }
                }

                if(vehicleCmb.getSelectionModel().getSelectedItem().toString().equals("Bus")){
                    Bus bus = new Bus(vehicleNo.getText(),vehicleCmb.getValue().toString(),Integer.parseInt(noOfPassengers.getText()),Integer.parseInt(maxWeight.getText()));
                    vehicleNo.clear();

                    noOfPassengers.clear();
                    maxWeight.clear();

                    if (vehicleList.add(bus)){
                        loadAllVehicle();
                        vehicleCmb.getSelectionModel().clearSelection();
                        new Alert(Alert.AlertType.CONFIRMATION,"Saved successfully...", ButtonType.CLOSE).show();
                    }else{
                        new Alert(Alert.AlertType.WARNING,"Try Again...", ButtonType.CLOSE).show();
                    }
                }

                if(vehicleCmb.getSelectionModel().getSelectedItem().toString().equals("Cargo Lorry")){
                    CargoLorry cargoLorry = new CargoLorry(vehicleNo.getText(),vehicleCmb.getValue().toString(),Integer.parseInt(noOfPassengers.getText()),Integer.parseInt(maxWeight.getText()));
                    vehicleNo.clear();

                    noOfPassengers.clear();
                    maxWeight.clear();

                    if (vehicleList.add(cargoLorry)){
                        loadAllVehicle();
                        vehicleCmb.getSelectionModel().clearSelection();
                        new Alert(Alert.AlertType.CONFIRMATION,"Saved successfully...", ButtonType.CLOSE).show();
                    }else{
                        new Alert(Alert.AlertType.WARNING,"Try Again...", ButtonType.CLOSE).show();
                    }
                }

            }catch (NullPointerException n){

            }
        }

    }

    private void loadAllVehicle(){
        ObservableList<Vehicle>observableList=FXCollections.observableArrayList();
        if (vehicleCmb.getSelectionModel().getSelectedItem().toString().equals("Cargo Lorry")){
            for (Vehicle t : vehicleList){
                observableList.add(new CargoLorry(t.getVehicleNo(),t.getVehicleType(),t.getNoOfPassengers(),t.getMaxWeight()));
            }
            tblVehicle.setItems(observableList);
        }
        if (vehicleCmb.getSelectionModel().getSelectedItem().toString().equals("Van")){
            for (Vehicle t1 : vehicleList){
                observableList.add(new Van(t1.getVehicleNo(),t1.getVehicleType(),t1.getNoOfPassengers(),t1.getMaxWeight()));
            }
            tblVehicle.setItems(observableList);
        }
        if (vehicleCmb.getSelectionModel().getSelectedItem().toString().equals("Bus")){
            for (Vehicle t2 : vehicleList){
                observableList.add(new Bus(t2.getVehicleNo(),t2.getVehicleType(),t2.getNoOfPassengers(),t2.getMaxWeight()));
            }
            tblVehicle.setItems(observableList);
        }
    }
    public void checkingVehicleNo(javafx.scene.input.KeyEvent keyEvent) {
        String value ="^([A-Z0-9 ]{1,3}[-]([0-9]{4}))$";
        Pattern pattern= Pattern.compile(value);
        Matcher match=pattern.matcher(vehicleNo.getText());

        if(!match.matches()){
            vLabel1.setText("Invalid Vehicle Number!");
        }else{
            vLabel1.setText("");
        }
    }

    public void checkingVehicleMaxWeight(javafx.scene.input.KeyEvent keyEvent) {
        String value ="^([0-9]{1,4})$";
        Pattern p=Pattern.compile(value);
        Matcher match=p.matcher(maxWeight.getText());
        if(!match.matches()){
            vLabel2.setText("Invalid Maximum Weight!");
        }else{
            vLabel2.setText("");
        }
    }

    public void checkingNoOfPassengers(javafx.scene.input.KeyEvent keyEvent) {
        String value ="^(([0-9]{1,2}))$";
        Pattern pattern= Pattern.compile(value);
        Matcher match=pattern.matcher(noOfPassengers.getText());

        if(!match.matches()){
            vLabel3.setText("Invalid Number Of Passengers!");
        }else{
            vLabel3.setText("");
        }
    }

    public void moveToVehicleType(ActionEvent actionEvent) {
        vehicleCmb.requestFocus();
    }

    public void moveToMaxWeight(ActionEvent actionEvent) {
        maxWeight.requestFocus();
    }

    public void moveToNoOfPassengers(ActionEvent actionEvent) {
        noOfPassengers.requestFocus();
    }


}
