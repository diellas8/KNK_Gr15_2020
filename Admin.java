import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import properties.Lenda;
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


        } else if (event.getSource() == btnLendet) {
            tabelaAdmin.getItems().clear();
            lbStatus.setText("Lendet");
            id.setText("Lenda");
            Emri.setText("Ligjeruesi");
            Mbiemri.setMinWidth(0);
            Mbiemri.setMaxWidth(0);
            Mbiemri.setPrefWidth(0);
            Roli.setText("Viti");
            Lenda.startColumn(dbcon, tabelaAdmin, id, Emri, Roli);


//            id.setCellValueFactory(new PropertyValueFactory<>("Lenda"));
//            Emri.setCellValueFactory(new PropertyValueFactory<>("Profesoret"));
//            Roli.setCellValueFactory(new PropertyValueFactory<>("Viti"));
//            tabelaAdmin.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
//            tabelaAdmin.getSelectionModel().selectedItemProperty().addListener((ov, old, _new)->{
//                if(_new!=null)
//                    setLendetToUI((Lenda)_new);
//            });
//            try{
//                tabelaAdmin.setMinHeight(0);
//                 tabelaAdmin.setItems(getLendet());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
        }
   }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
//    private Lenda getLendetFromUI(){
//        String Lenda= id.getText();
//        String Profesori=Emri.getText();
//        int Viti = Integer.parseInt(Roli.getText());
//        return new Lenda(Lenda, Profesori, Viti);
//    }




//
//
//
  @FXML
  void shto(ActionEvent event) {
//
   }
//
   @FXML
  void fshij(ActionEvent event) {
//
//        try {
//            Lenda lenda = (Lenda) tabelaAdmin.getSelectionModel().getSelectedItem();
//            try {
//                deletelenda(lenda.getLenda());
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//            tabelaAdmin.getSelectionModel().clearSelection();
//            tabelaAdmin.getItems().remove(lenda);
//            setLendetToUI(new Lenda());
//
//        } catch (Exception exe) {
//            //    printError(e);
//        }
   }
//
//
//    private void deletelenda(String Lenda) throws Exception{
//        String sql = "DELETE FROM Lendet WHERE Lenda = ?";
//        PreparedStatement stmt = dbcon.prepareStatement(sql);
//        stmt.setString(1, Lenda);
//        if (stmt.executeUpdate()<=0){
//            throw new Exception("Nuk u fshi");
//        }
//
//    }
//
//    private  void printError(){
//        Alert Alert = new Alert(javafx.scene.control.Alert.AlertType.ERROR);
//
//    }
//
//
    public void fshij(javafx.event.ActionEvent actionEvent) {
    }
//
    public void shto(javafx.event.ActionEvent actionEvent) {
    }
//
//

}