package SemesterProject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerForAddCriminal {
    Police police=new Police();
    @FXML
    private TextField nameField;
    @FXML
    private TextField ageField;
    @FXML
    private TextField cnicField;
    @FXML
    private TextField residenceField;
    @FXML
    private TextField crimeField;
    @FXML
    private Button addButton;
    @FXML
    private Button goBack;

    public void onAddButtonClicked(ActionEvent event){
        String name=nameField.getText();
        int age=Integer.parseInt(ageField.getText());
        String cnic=cnicField.getText();
        String residence=residenceField.getText();
        String crime=crimeField.getText();
        police.addCriminals(name,age,residence,cnic,crime);


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
