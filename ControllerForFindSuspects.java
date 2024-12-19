package SemesterProject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerForFindSuspects implements Initializable {

    @FXML
    private TableView<Criminal> suspectTable;

    @FXML
    private Button findButton;
    @FXML
    private TextField enterCrimeField;

    @FXML
    private TableColumn<Criminal,String> nameColumn;
    @FXML
    private TableColumn<Criminal,Integer> ageColumn;
    @FXML
    private TableColumn<Criminal,String> cnicColumn;
    @FXML
    private TableColumn<Criminal,String> residenceColumn;
    @FXML
    private TableColumn<Criminal,String> crimeColumn;
    @FXML
    private TableColumn<Criminal,String> idColumn;
Police police=new Police();

    public void onFindButtonClicked(ActionEvent event){

        String crimeToFindCriminal=enterCrimeField.getText();
        ArrayList<Criminal> criminalForACrime=police.searchCriminalForCrime(crimeToFindCriminal);
populateTable(criminalForACrime);
       // return  criminalForACrime;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("criminalId"));
        cnicColumn.setCellValueFactory(new PropertyValueFactory<>("cnic"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        residenceColumn.setCellValueFactory(new PropertyValueFactory<>("residence"));
        crimeColumn.setCellValueFactory(new PropertyValueFactory<>("crimeDone"));
    }

    public void populateTable(ArrayList<Criminal> criminals){
        ObservableList<Criminal> criminalObservableList= FXCollections.observableArrayList(criminals);
        suspectTable.setItems(criminalObservableList);
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
