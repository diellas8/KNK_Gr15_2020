import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import properties.Lenda;
import properties.Mesimdhenes;
import properties.baza;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Admin implements Initializable {

    Connection dbcon;

    {
        try {
            dbcon = DriverManager.getConnection("jdbc:sqlite:C:\\Sqlite\\db\\menaxhimi_konsultimeve.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


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
    private TableView<baza> tabelaAdmin;

    @FXML
    private TableColumn<?, ?> id;

    @FXML
    private TableColumn<?, ?> Emri;

    @FXML
    private TableColumn<?, ?> Mbiemri;

    @FXML
    private TableColumn<?, ?> Roli;

    @FXML
    private TableColumn<?, ?> Email;

    @FXML
    private TextField textInput;

    @FXML
    private Button addBtn;

    @FXML
    private Button deleteBtn;



    @FXML
    public void handleClicks(javafx.event.ActionEvent event) {
        if (event.getSource() == btnStudentet) {
            tabelaAdmin.getItems().clear();
            lbStatus.setText("Studentet");
            Roli.setText("Email");



        } else if (event.getSource() == btnProfesor) {
            lbStatus.setText("Profesoret");
            tabelaAdmin.getItems().clear();
            tabelaAdmin=Mesimdhenes.startColumn(dbcon, tabelaAdmin, id, Emri, Mbiemri, Email, Roli);
            Mbiemri.setMinWidth(100);
            Mbiemri.setMaxWidth(100);
            Mbiemri.setPrefWidth(100);
            Email.setMinWidth(100);
            Email.setMaxWidth(100);
            Email.setPrefWidth(100);




        } else if (event.getSource() == btnLendet) {
            tabelaAdmin.getItems().clear();
            lbStatus.setText("Lendet");
            id.setText("Lenda");
            Emri.setText("Ligjeruesi");
            Mbiemri.setMinWidth(0);
            Mbiemri.setMaxWidth(0);
            Mbiemri.setPrefWidth(0);
            Email.setMinWidth(0);
            Email.setMaxWidth(0);
            Email.setPrefWidth(0);
            Roli.setText("Viti");
            Lenda.startColumn(dbcon, tabelaAdmin, id, Emri, Roli);

        }
   }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

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