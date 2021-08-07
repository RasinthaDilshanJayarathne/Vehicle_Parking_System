package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.*;
import view.tm.OnDeliveryVehicleTM;
import view.tm.ParkVehicleTM;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import static controller.AddDriverFormController.driverArrayList;
import static controller.AddVehicleFormController.vehicleList;
import static controller.InParkingFormController.parkedVehiclesTMObservableList;
import static controller.OnDelivaryFormController.onDeliveryVehiclesTMObservableList;



public class HomePageFormController {
    public AnchorPane HomePageContext;
    public JFXComboBox<String> vehicleTypeCmb;
    public JFXComboBox<String> driverCmb;
    public JFXTextField txtVehicleType;
    public TextField Date;
    public TextField Time;
    public Button btnRefresh;
    public TextField slotID;
    public JFXButton btnPark;
    public JFXButton btnDelivery;
    public FontAwesomeIconView carOne;
    public FontAwesomeIconView carTwo;
    public Label txtAnswer;

    public void initialize() {
        for (Vehicle temp : vehicleList) {
            vehicleTypeCmb.getItems().add(temp.getVehicleNo());
        }

        vehicleTypeCmb.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            slotID.clear();
            vehicleTypeCmb.setAccessibleText(newValue);
            for (Vehicle temp : vehicleList) {
                if (newValue.equals(temp.getVehicleNo())) {
                    txtVehicleType.setText(temp.getVehicleType());
                    disableAndEnable(vehicleTypeCmb.getValue().toString());
                    if(!btnPark.isDisable()){
                        try {
                            slotID.setText(temp.park(temp.getVehicleNo(), temp.getVehicleType()) + "");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        for (Drivers d : driverArrayList) {
            driverCmb.getItems().add(d.getName());
        }

        //-------------------------------------------RealTimeClock & Date----------------------------------------------------------
        Thread clock = new Thread() {
            public void run() {
                for (; ; ) {
                    DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
                    Calendar cal = Calendar.getInstance();

                    int second = cal.get(Calendar.SECOND);
                    int minute = cal.get(Calendar.MINUTE);
                    int hour = cal.get(Calendar.HOUR_OF_DAY);
                    //System.out.println(hour + ":" + (minute) + ":" + second);
                    try {
                        String state = null;
                        if (hour >= 12) {
                            state = "PM";
                        } else {
                            state = "AM";
                        }
                        Time.setText("" + String.format("%02d", hour) + ":" + String.format("%02d", minute) + ":" + String.format("%02d", second) + " " + state);
                        try {
                            sleep(1000);
                        } catch (InterruptedException ex) {
                            System.out.println(ex);
                        }
                    }catch (NullPointerException e){
                        System.out.println(e);
                    }
                }
            }
        };
        clock.start();

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        // System.out.println(dateFormat.format(date));
        Date.setText(dateFormat.format(date));

        try {
            animation(carOne);
            animation(carTwo);
        }catch (NullPointerException e){
            System.out.println(e);
        }
    }


    public void animation(FontAwesomeIconView imageView){
        ScaleTransition scaleTransition=new ScaleTransition();
        scaleTransition.setNode(imageView);
        scaleTransition.setDuration(Duration.millis(500));
        scaleTransition.setCycleCount(TranslateTransition.INDEFINITE);
        scaleTransition.setInterpolator(Interpolator.LINEAR);
        scaleTransition.setByX(2.0);
        scaleTransition.setByY(2.0);
        scaleTransition.setAutoReverse(true);
        scaleTransition.play();
    }

    //--------------------------------------------------------------------------------------------------------------------

    public void disableAndEnable(String vehicleNo){
        btnPark.setDisable(false);
        btnDelivery.setDisable(false);
        for (ParkVehicleTM temp:parkedVehiclesTMObservableList) {
            if(temp.getVehicleNo().equals(vehicleNo)){
                btnPark.setDisable(true);
            }
        }
        if(!btnPark.isDisable()){
            btnDelivery.setDisable(true);
        }
    }


    public void goToManagementLogIn(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/ManagementLogInForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene=new Scene(load);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();
    }


    public void parkingVehicle(ActionEvent actionEvent) throws IOException {
        try{
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm a");
            ParkVehicleTM parkedVehicleTM = new ParkVehicleTM(vehicleTypeCmb.getValue().toString(),txtVehicleType.getText(), Integer.parseInt(slotID.getText()),java.time.LocalDateTime.now().format(dateTimeFormatter));
            parkedVehiclesTMObservableList.add(parkedVehicleTM);

            slotID.clear();
        }catch (NullPointerException e){

        }
    }

    public void onDelivaryShift(ActionEvent actionEvent) {
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm a");
            parkedVehiclesTMObservableList.removeIf(temp -> temp.getVehicleNo().equals(vehicleTypeCmb.getValue().toString()));
            OnDeliveryVehicleTM onDeliveryVehicleTM = new OnDeliveryVehicleTM(vehicleTypeCmb.getValue().toString(),txtVehicleType.getText(),driverCmb.getValue().toString(),java.time.LocalDateTime.now().format(dateTimeFormatter));
            onDeliveryVehiclesTMObservableList.add(onDeliveryVehicleTM);
        }catch (NullPointerException e){

        }
    }

    public void refreshOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/HomePageForm.fxml");
        Parent load = FXMLLoader.load(resource);
        HomePageContext.getChildren().clear();
        HomePageContext.getChildren().add(load);
    }
}
