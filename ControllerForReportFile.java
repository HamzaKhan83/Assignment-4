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
import java.util.ArrayList;

public class ControllerForReportFile {

    @FXML
    private TextField applicantName ;

Police police=new Police();
    @FXML
    private TextField applicaantAge ;
    @FXML
    private TextField applicantCnic ;
    @FXML
    private TextField applicantResidence ;
    @FXML
    private TextField crimeOccured ;
@FXML
private Label reportHasBeenAddedButton;
@FXML
private  Button goBackFromFileReport;
    @FXML
    private Button saveReportButton;

    @FXML
    void saveReportButtonClicked(ActionEvent e){
        String name=applicantName.getText();
        int age=(Integer.parseInt(applicaantAge.getText())) ;
        String cnic=applicantCnic.getText();
        String residence=applicantResidence.getText();
        String crime=crimeOccured.getText();
        police.addReport(crime,name,age,residence,cnic);
        reportHasBeenAddedButton.setText("Report has been added");

    }

    @FXML
    void setGoBackFromFileReportButtonClicked(ActionEvent event) throws IOException {
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();

        Parent reportFileParent= FXMLLoader.load(getClass().getResource("FxmlForScreen1.fxml"));
        Scene scene=new Scene(reportFileParent);
        window.setScene(scene);
        window.show();

    }
}
