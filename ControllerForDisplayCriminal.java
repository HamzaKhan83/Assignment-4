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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class ControllerForDisplayCriminal implements Initializable
{
    Police police =new Police();

    @FXML
    private TableView<Criminal> criminalTable;

    @FXML
    private TableColumn<Criminal,String > nameColumn;
    @FXML
    private TableColumn<Criminal,String > idColumn;
    @FXML
    private TableColumn<Criminal,String > cnicColumn;
    @FXML
    private TableColumn<Criminal,Integer > ageColumn;
    @FXML
    private TableColumn<Criminal,String > residenceColumn;
    @FXML
    private TableColumn<Criminal,String > crimeColumn;
    @FXML
    private Button goBack;

@FXML
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
        criminalTable.setItems(criminalObservableList);
    }
@FXML
    public void goBackButtonClicked(ActionEvent event) throws IOException {

        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();

        Parent reportFileParent= FXMLLoader.load(getClass().getResource("FxmlForExecutiveOption.fxml"));
        Scene scene=new Scene(reportFileParent);
        window.setScene(scene);
        window.show();
    }
}
