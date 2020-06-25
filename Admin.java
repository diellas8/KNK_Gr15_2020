import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class Admin implements Initializable {


    @FXML
    private Button btnProfesor;

    @FXML
    private Button btnLendet;

    @FXML
    private Button btnStudentet;

    @FXML
    private Pane pnStatus;

    @FXML
    private Label lbStatus;

    @FXML
    private TableView<?> tabelaAdmin;

    @FXML
    private TableColumn<?, ?> id;

    @FXML
    private TableColumn<?, ?> Emri;

    @FXML
    private TableColumn<?, ?> Mbiemri;

    @FXML
    private TableColumn<?, ?> Roli;

    @FXML
    private TextField textInput;

    @FXML
    private Button addBtn;

    @FXML
    private Button deleteBtn;


    @Override
    public void initialize(URL location, ResourceBundle resources){

    }

    @FXML


    public void handleClicks(javafx.event.ActionEvent event) {
        if(event.getSource()==btnStudentet)
        {
            lbStatus.setText("Studentet");

        }
        else if(event.getSource()==btnProfesor){
            lbStatus.setText("Profesoret");

        }

        else if(event.getSource()==btnLendet){
            lbStatus.setText("Lendet");


        }
    }
    @FXML
    void shto(ActionEvent event) {

    }
    @FXML
    void fshij(ActionEvent event) {

    }

    public void fshij(javafx.event.ActionEvent actionEvent) {
    }

    public void shto(javafx.event.ActionEvent actionEvent) {
    }
}


