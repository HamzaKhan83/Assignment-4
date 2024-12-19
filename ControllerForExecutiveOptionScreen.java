package SemesterProject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.shape.FillRule;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ControllerForExecutiveOptionScreen {
Police police=new Police();
    @FXML
    private Button addOfficerButton;


    @FXML
    private Button addCriminalButton;
    @FXML
    private Button displayCriminal;
    @FXML
    private Button displayOfficerButton;
    @FXML
    private Button reportDisplayButton;

    @FXML
    private Button logoutButton;


@FXML
    void setAddOfficerButtonClicked(ActionEvent event) throws IOException{
    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();

    Parent addOfficerParent= FXMLLoader.load(getClass().getResource("FxmlForAddOfficer.fxml"));
    Scene scene=new Scene(addOfficerParent,700,500);
    window.setScene(scene);
    window.show();
}

@FXML
    void displayOfficerButtonClicked(ActionEvent event) throws  IOException{
    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();

  //  Parent addOfficerParent= FXMLLoader.load(getClass().getResource("FxmlForDisplayOfficerScreen.fxml"));
    FXMLLoader loader=new FXMLLoader(getClass().getResource("FxmlForDisplayOfficerScreen.fxml"));
    Parent root=loader.load();

    ControllerForDisplayOfficer controllerForDisplayOfficer=loader.getController();
    ArrayList<Officer> officers=police.loadOfficerFromFile();
    controllerForDisplayOfficer.populateTable(officers);
    Scene scene=new Scene(root);
    window.setScene(scene);
    window.show();


}

    @FXML
    void displayCriminalButtonClicked(ActionEvent event) throws  IOException {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        //  Parent addOfficerParent= FXMLLoader.load(getClass().getResource("FxmlForDisplayOfficerScreen.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FxmlForDisplayCriminal.fxml"));
        Parent root = loader.load();

        ControllerForDisplayCriminal controllerForDisplayCriminal = loader.getController();
        ArrayList<Criminal> criminals = police.loadCriminalArrayFromFile();
        controllerForDisplayCriminal.populateTable(criminals);
        Scene scene = new Scene(root);
        window.setScene(scene);
        window.show();

    }
@FXML
    void addCriminalButtonClicked(ActionEvent event)throws  IOException{
    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();

    Parent addOfficerParent= FXMLLoader.load(getClass().getResource("FxmlForAddCriminal.fxml"));
    Scene scene=new Scene(addOfficerParent,700,500);
    window.setScene(scene);
    window.show();
}

@FXML
    void displayReportButtonClicked(ActionEvent event)throws IOException{
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

    //  Parent addOfficerParent= FXMLLoader.load(getClass().getResource("FxmlForDisplayOfficerScreen.fxml"));
    FXMLLoader loader = new FXMLLoader(getClass().getResource("FxmlForDisplayReport.fxml"));
    Parent root = loader.load();

    ControllerForDisplayReport controllerForDisplayReport = loader.getController();
    ArrayList<Report> report = police.loadReportsFromFile();
    police.displayReports();
    controllerForDisplayReport .populateTable(report);
    Scene scene = new Scene(root);
    window.setScene(scene);
    window.show();
}

@FXML
    public void onFindSuspectForCrimeButtonClicked(ActionEvent event) throws IOException{
    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();

    Parent suspectList= FXMLLoader.load(getClass().getResource("FxmlForFindSuspects.fxml"));
    Scene scene=new Scene(suspectList);
    window.setScene(scene);
    window.show();
}


@FXML
void logoutButtonClicked(ActionEvent event) throws IOException {

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

    Parent screen1 = FXMLLoader.load(getClass().getResource("FxmlForScreen1.fxml"));
    Scene scene = new Scene(screen1);
    window.setScene(scene);
    window.show();
}
}
