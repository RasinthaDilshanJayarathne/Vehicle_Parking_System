package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DetailsFormController {
    public AnchorPane adminTasksContext;
    public AnchorPane windowContext;
    public JFXButton btnLogOut;
    public JFXComboBox cmbOptions;

    public void initialize() throws IOException {
        cmbOptions.getItems().addAll("In Parking", "On Delivery");
        //cmbOptions.getSelectionModel().selectFirst();

        cmbOptions.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            if(newValue.equals("On Delivery")){
                URL resource = getClass().getResource("../view/OnDelivaryForm.fxml");
                Parent load = null;
                try {
                    load = FXMLLoader.load(resource);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                windowContext.getChildren().clear();
                windowContext.getChildren().add(load);
            }
            else if(newValue.equals("In Parking")){
                URL resource = getClass().getResource("../view/InParkingForm.fxml");
                Parent load = null;
                try {
                    load = FXMLLoader.load(resource);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                windowContext.getChildren().clear();
                windowContext.getChildren().add(load);
            }
        });
    }

    public void goToHomePage(ActionEvent actionEvent) throws IOException {
        /*URL resource = getClass().getResource("../view/HomePageForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) adminTasksContext.getScene().getWindow();
        window.setScene(new Scene(load));*/
        try {
            Stage stage = (Stage) adminTasksContext.getScene().getWindow();
            stage.close();
        }catch (Exception e) {}


    }

    public void goToAddVehicles(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/AddVehicleForm.fxml");
        Parent load = FXMLLoader.load(resource);
        windowContext.getChildren().clear();
        windowContext.getChildren().add(load);
    }

    public void goToAddDriver(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/AddDriverForm.fxml");
        Parent load = FXMLLoader.load(resource);
        windowContext.getChildren().clear();
        windowContext.getChildren().add(load);
    }
}
