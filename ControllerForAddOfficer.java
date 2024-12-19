package SemesterProject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerForAddOfficer {
    Police police = new Police();
    @FXML
    private TextField officerName;
    @FXML
    private TextField officerAge;
    @FXML
    private TextField officerCnic;
    @FXML
    private TextField officerResidence;
    @FXML
    private Label officerAddedNotification;
    @FXML
    private Button goBackButton;
    @FXML
    private Label notification;



    @FXML
    void onAddOfficerButtonClick(ActionEvent event) throws IOException {
        String name = officerName.getText();
        int age = Integer.parseInt(officerAge.getText());
        String cnic = officerCnic.getText();
        String residence = officerResidence.getText();
        police.addOfficer(name, age, residence, cnic);
      //  officerAddedNotification.setText("Officer added");
        notification.setText("Officer Added");
    }

    @FXML
    void onGoBackButtonClicked(ActionEvent event) throws IOException {

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Parent addOfficerParent = FXMLLoader.load(getClass().getResource("FxmlForExecutiveOption.fxml"));
        Scene scene = new Scene(addOfficerParent);
        window.setScene(scene);
        window.show();
    }


}