import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import properties.Lenda;
import properties.Mesimdhenes;
import properties.Perdoruesit;
import properties.baza;

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
    private Button deleteBtn ;



    @FXML
    public void handleClicks(javafx.event.ActionEvent event) {
        if (event.getSource() == btnStudentet) {
            tabelaAdmin.getItems().clear();
            lbStatus.setText("Perdoruesit");
            Perdoruesit.ViewColumn(dbcon, tabelaAdmin, id, Emri, Mbiemri, Email, Roli);



        } else if (event.getSource() == btnProfesor) {
            lbStatus.setText("Profesoret");
            tabelaAdmin.getItems().clear();
            Mesimdhenes.startColumn(dbcon, tabelaAdmin, id, Emri, Mbiemri, Email, Roli);


        } else if (event.getSource() == btnLendet) {
            tabelaAdmin.getItems().clear();
            lbStatus.setText("Lendet");
            Lenda.startColumn(dbcon, tabelaAdmin, id, Emri, Roli);
            Mbiemri.setVisible(false);
            Email.setVisible(false);

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lbStatus.setText("Lendet");
        Lenda.startColumn(dbcon, tabelaAdmin, id, Emri, Roli);
        Mbiemri.setVisible(false);
        Email.setVisible(false);


    }

    public void fshij(javafx.event.ActionEvent event){
        event.getSource();
        if(tabelaAdmin.getSelectionModel().getSelectedItem() instanceof Lenda) {
            Lenda.fshijLendet(tabelaAdmin, dbcon);
            tabelaAdmin.getItems().removeAll(tabelaAdmin.getSelectionModel().getSelectedItem());
        }else if(tabelaAdmin.getSelectionModel().getSelectedItem() instanceof Mesimdhenes) {
            Mesimdhenes.fshijMesimdhenesit(tabelaAdmin, dbcon);
            tabelaAdmin.getItems().removeAll(tabelaAdmin.getSelectionModel().getSelectedItem());
        }
        else {
            Perdoruesit.fshijPerdoruesit(dbcon);
            tabelaAdmin.getItems().removeAll(tabelaAdmin.getSelectionModel().getSelectedItem());


        }
    }

    public void shto(javafx.event.ActionEvent event){


    }
}