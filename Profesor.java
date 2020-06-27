import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import org.sqlite.SQLiteException;
import properties.Lenda;
import properties.Mesimdhenes;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.sql.*;
import java.util.ResourceBundle;

public class Profesor implements Initializable {
    private static Connection dbCon;

    static {
        try {
            dbCon = DriverManager.getConnection("jdbc:sqlite:C:\\Sqlite\\db\\menaxhimi_konsultimeve.db");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static Statement statement;
    static {
        try {
            statement = dbCon.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    private static String email;
    private static int mesimdhenesi;
    RadioButton chk = null;
    private String lenda;
    @FXML
    private TextField hour;

    @FXML
    private TextField minutes;

    @FXML
    private ToggleGroup salla;

    @FXML
    private CheckBox eHene = new CheckBox("E hene");

    @FXML
    private CheckBox eMarte = new CheckBox("E marte");

    @FXML
    private CheckBox eMerkure = new CheckBox("E merkure");

    @FXML
    private CheckBox eEnjte = new CheckBox("E enjte");

    @FXML
    private CheckBox ePremte = new CheckBox("E premte");

    @FXML
    private Button largo;

    @FXML
    private ChoiceBox<String> lendet;

    @FXML
    private TableView<?> tabelaPersonale;

    @FXML
    private TableColumn<?, ?> kolonaLenda;

    @FXML
    private TableColumn<?, ?> kolonaKoha;

    @FXML
    private TableColumn<?, ?> kolonaSalla;

    @FXML
    private TableView<?> tabelaPergjithshme;

    @FXML
    private TableColumn<?, ?> kolonaMesimdhenesi;

    @FXML
    private TableColumn<?, ?> KolonaLendaP;

    @FXML
    private TableColumn<?, ?> KolonaKohaP;

    @FXML
    private TableColumn<?, ?> KolonaSallaP;

    public Profesor() {
    }

    @FXML
    void largoKonsultim(ActionEvent event) throws IOException {

    }

    @FXML
    void ruajKonsultim(ActionEvent event) throws SQLException {
        int ora = 0;
        int minuta = 0;

        try {
            ora = Integer.parseInt(hour.getText());
            minuta = Integer.parseInt(minutes.getText());
        } catch (Exception e) {
            printError("Ju lutem shkruani nje ore valide!");
        }
        if (ora > 18 || ora < 8 || minuta < 0 || minuta > 60) {
            printError("Konsultimet nuk mund te mbahen me heret se ne oren 8:00 ose me vone se ne oren 19:00.");
        } else if (salla.getSelectedToggle() == null) {
            printError("Ju lutem caktoni sallen.");

        }
//
//        }
        else {
            RadioButton selectedRadioButton = (RadioButton) salla.getSelectedToggle();
            String toogleGroupValue = selectedRadioButton.getText();
            int salle = Integer.parseInt(toogleGroupValue);
            String stringOra = hour.getText().replaceAll("\\s", "") + ":" + minutes.getText();
            String lenda = lendet.getValue();
            String dita = null;
            try {
                if(eHene.isSelected()) {
                    String sql = "INSERT INTO Orari(Mesimdhenesi, Salla, Ora, Dita, Lenda) VALUES (" + mesimdhenesi + ", " + salle
                            + ", '" + stringOra + "', '" + eHene.getText() + "', '" + lenda + "')";
                    statement.executeUpdate(sql);
                }
            } catch (SQLiteException e){
                printError("Salla eshte e rezervuar gjate asaj kohe. Ju lutem ndryshoni orarin.");
            }

 //else if (!eHene.isSelected() && !eMarte.isSelected() && !eMerkure.isSelected() && !eEnjte.isSelected() && !ePremte.isSelected()){
//              printError("Ju lutem caktoni se paku nje dite per tu mbajtur konsultimi.");
        }
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        caktoMesimdhenes(email);
        populateChoicebox(lendet);


    }


    private static void printError(String mesazh){
        Alert.AlertType alertAlertType;
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Gabim!");
        alert.setHeaderText(null);
        alert.setContentText(mesazh);
        alert.showAndWait();
    }

    public static void saveEmail(String em){
        email = em;
        System.out.println(mesimdhenesi);
    }

    private void populateChoicebox(ChoiceBox<String> choiceBox){
        String sql = "SELECT Lenda FROM Lendet WHERE Profesori = '" + mesimdhenesi + "' OR asistenti ='"+mesimdhenesi +"'";
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        while (true){
            try {
                if (!resultSet.next()) break;
            } catch (SQLException throwables) {
                printError("Ndodhi nje gabim me databaze.");
            }
            try {
                choiceBox.setValue(resultSet.getString("Lenda"));
                choiceBox.getItems().add(resultSet.getString("Lenda"));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private static void caktoMesimdhenes(String email){
        String sql = "SELECT m_ID, Emri, Mbiemri from Mesimdhenesit WHERE email = '" + email + "'";
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(sql);
            if(resultSet.next())
            mesimdhenesi = resultSet.getInt("m_ID");
            System.out.println(mesimdhenesi);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }




}
