package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class ManagementLogInFormController {
    public AnchorPane ManagementLogInContext;
    public JFXTextField managementName;
    public JFXPasswordField managementPassword;
    public Label managementLabel;
    public JFXButton btnCancel;

    public void goToTasks(ActionEvent actionEvent) throws IOException {
        if(managementPassword.getText().equals("1234") & managementName.getText().equalsIgnoreCase("Admin")){
            URL resource = getClass().getResource("../view/DetailsForm.fxml");
            Parent load = FXMLLoader.load(resource);
            Stage window = (Stage) ManagementLogInContext.getScene().getWindow();
            window.setScene(new Scene(load));
        }
        else{
            managementLabel.setText("Enter correct username or password");
        }
    }

    public void goToHomePage(ActionEvent actionEvent) throws IOException {
       Stage stage=(Stage)btnCancel.getScene().getWindow();
       stage.close();
    }
    public void goToPassword(ActionEvent actionEvent) {
        managementPassword.requestFocus();
    }

}
