package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import view.tm.OnDeliveryVehicleTM;


public class OnDelivaryFormController {
    public TableView tblOnDelivery;
    public TableColumn colNo;
    public TableColumn colType;
    public TableColumn colName;
    public TableColumn colLeftTime;

    public static ObservableList<OnDeliveryVehicleTM> onDeliveryVehiclesTMObservableList = FXCollections.observableArrayList();

    public void initialize(){
        tblOnDelivery.setItems(onDeliveryVehiclesTMObservableList);
        colNo.setCellValueFactory(new PropertyValueFactory<>("vehicleNo"));
        colType.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));
        colName.setCellValueFactory(new PropertyValueFactory<>("driverName"));
        colLeftTime.setCellValueFactory(new PropertyValueFactory<>("leftTime"));

    }

}
