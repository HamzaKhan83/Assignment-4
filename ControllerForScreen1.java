package SemesterProject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ControllerForScreen1 {
Police police =new Police();
    @FXML
    private Button loginButton;


    @FXML
    private TextField enterCnicField;
    @FXML
    private TextField passwordTextField;

    @FXML
    private Label welcomeLabel;
    @FXML
    private Button ReportButton;


    @FXML
    void fileAReportButtonClicked(ActionEvent event)throws IOException{
        Stage reportWindow=(Stage)((Node)event.getSource()).getScene().getWindow();

        Parent reportFileParent= FXMLLoader.load(getClass().getResource("FxmlForReportFile.fxml"));
        Scene scene=new Scene(reportFileParent);
        reportWindow.setScene(scene);
        reportWindow.show();
    }


    @FXML
    void onLoginButtonClicked(ActionEvent event) throws IOException {
        String cnic=enterCnicField.getText();
        String password=passwordTextField.getText();
        ArrayList<Executive> executives=police.loadExecutiveFromFile();
for(Executive e:executives){
    if(police.loginAsExecutive(cnic,password) ){
       // System.out.println("Welcome "+e.getName());
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();

        Parent loggedin= FXMLLoader.load(getClass().getResource("FxmlForExecutiveOption.fxml"));
        loggedin.setStyle("-fx-background-color: lightBlue;");

        Scene scene=new Scene(loggedin);

        window.setScene(scene);
        window.show();

        break;
    }else{
        welcomeLabel.setText("Information Invalid");

    }

}
    }



}