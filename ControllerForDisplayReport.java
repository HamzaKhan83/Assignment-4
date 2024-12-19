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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerForDisplayReport implements Initializable {


    @FXML
    private TableView<Report> reportsTable;

    @FXML
    private TableColumn<Report, String> nameColumn;
    @FXML
    private TableColumn<Report, Integer> ageColumn;
    @FXML
    private TableColumn<Report, String> cnicColumn;
    @FXML
    private TableColumn<Report, String> crimeColumn;
    @FXML
    private TableColumn<Report, String> residenceColumn;
    @FXML
    private TableColumn<Report, String> reportTimeColumn;
    @FXML
    private TableColumn<Report ,String> caseId;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("applicantName"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("applicantAge"));
        cnicColumn.setCellValueFactory(new PropertyValueFactory<>("applicantCnic"));
        residenceColumn.setCellValueFactory(new PropertyValueFactory<>("applicantResidence"));
        crimeColumn.setCellValueFactory(new PropertyValueFactory<>("crime"));
        reportTimeColumn.setCellValueFactory(new PropertyValueFactory<>("reportTime"));
        caseId.setCellValueFactory(new PropertyValueFactory<>("caseId"));
    }


    @FXML
    public void populateTable(ArrayList<Report> reports){
        ObservableList<Report> reportObservableList= FXCollections.observableArrayList(reports);
        reportsTable.setItems(reportObservableList);
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
