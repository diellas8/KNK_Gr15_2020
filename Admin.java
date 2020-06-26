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
            lbStatus.setText("Studentet");
            Roli.setText("Email");
            tabelaAdmin.getItems().clear();


        } else if (event.getSource() == btnProfesor) {
            lbStatus.setText("Profesoret");
            tabelaAdmin.getItems().clear();
            Mesimdhenes.startColumn(dbcon, tabelaAdmin, id, Emri, Mbiemri, Email, Roli);

        } else if (event.getSource() == btnLendet) {
            tabelaAdmin.getItems().clear();
            lbStatus.setText("Lendet");
            id.setText("Lenda");
            Emri.setText("Ligjeruesi");
            Mbiemri.setMinWidth(0);
            Mbiemri.setMaxWidth(0);
            Mbiemri.setPrefWidth(0);


        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    private Lenda getLendetFromUI(){
        String Lenda= id.getText();
        String Profesori=Emri.getText();
        int Viti = Integer.parseInt(Roli.getText());
        return new Lenda(Lenda, Profesori, Viti);
    }

    private void setLendetToUI(Lenda lenda){
//        id.setText(lenda.getLenda());
//        Emri.setText(lenda.getProfesoret());
//        Roli.setText(lenda.getViti()+"");

    }



    @FXML
    void shto(ActionEvent event) {

    }

    @FXML
  void fshij(ActionEvent event) {


    }

}



