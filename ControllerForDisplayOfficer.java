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

public class ControllerForDisplayOfficer  implements Initializable {
Police police=new Police();
    @FXML
    private TableView<Officer> officerDisplayTable;

@FXML
TableColumn<Officer ,String> nameColumn;
    @FXML
    TableColumn<Officer ,String> idColumn;
    @FXML
    TableColumn<Officer ,String> residenceColumn;
    @FXML
    TableColumn<Officer ,String> cnicColumn;
    @FXML
    TableColumn<Officer ,String> ageColumn;
    @FXML
    private Button goBack;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
       idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        cnicColumn.setCellValueFactory(new PropertyValueFactory<>("cnic"));
        residenceColumn.setCellValueFactory(new PropertyValueFactory<>("residence"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));



        }
        public void populateTable(ArrayList<Officer> officers){
        ObservableList<Officer> officerObservableList=FXCollections.observableArrayList(officers);
        officerDisplayTable.setItems(officerObservableList);
        }



        public void goBackButtonClicked(ActionEvent event) throws IOException {

            Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();

            Parent reportFileParent= FXMLLoader.load(getClass().getResource("FxmlForExecutiveOption.fxml"));
            Scene scene=new Scene(reportFileParent);
            window.setScene(scene);
            window.show();
        }
    }

