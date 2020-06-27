import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import org.sqlite.SQLiteException;
import properties.Konsultim;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Profesor implements Initializable {
    private static Connection dbCon = null;


    private static Statement statement = null;


    private static String email;
    private static int mesimdhenesi;


    @FXML
    private TextField hour;

    @FXML
    private TextField minutes;

    @FXML
    private ToggleGroup salla;

    @FXML
    private final CheckBox eHene = new CheckBox("E hene");

    @FXML
    private final CheckBox eMarte = new CheckBox("E marte");

    @FXML
    private final CheckBox eMerkure = new CheckBox("E merkure");

    @FXML
    private final CheckBox eEnjte = new CheckBox("E enjte");

    @FXML
    private final CheckBox ePremte = new CheckBox("E premte");

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
    private TableColumn<?, ?> kolonaDita;

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
    private String stringOra = "";
    private int salle;

    public Profesor() {
    }

    public static void saveEmail(String em) {
        email = em;
    }

    private static void caktoMesimdhenes(String email) throws SQLException {
        String sql = "SELECT m_ID, Emri, Mbiemri from Mesimdhenesit WHERE email = '" + email + "'";
        ResultSet resultSet = null;
        try {
            initializeDB();
            resultSet = statement.executeQuery(sql);
            if (resultSet.next())
                mesimdhenesi = resultSet.getInt("m_ID");

        } catch (SQLException throwables) {
            printError("Ju nuk jeni te regjistruar ne databaze");
        } finally {
            if (statement != null)
                statement.close();
            if (dbCon != null)
                dbCon.close();
        }

    }

    private static void printError(String mesazh) {
        Alert.AlertType alertAlertType;
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Gabim!");
        alert.setHeaderText(null);
        alert.setContentText(mesazh);
        alert.showAndWait();
    }

    private static void initializeDB() {
        try {
            if (dbCon == null || dbCon.isClosed())
                dbCon = DriverManager.getConnection("jdbc:sqlite:C:\\Sqlite\\db\\menaxhimi_konsultimeve.db");
            if (statement == null || statement.isClosed())
                statement = dbCon.createStatement();
        } catch (Exception e) {
            printError("Probleme me databazen.");
        }
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
            salle = Integer.parseInt(toogleGroupValue);
            stringOra = hour.getText().replaceAll("\\s", "") + ":" + minutes.getText();
            String dita = "";

            try {
                initializeDB();
                if (eHene.isSelected()) {

                    dita = "E hene";
                    String sql = queryForKonsultim(dita);
                    statement.executeUpdate(sql);
                }
                if (eMarte.isSelected()) {
                    dita = "E marte";
                    String sql = queryForKonsultim(dita);
                    statement.executeUpdate(sql);
                }
                if (eMerkure.isSelected()) {
                    dita = "E merkure";
                    String sql = queryForKonsultim(dita);
                    statement.executeUpdate(sql);
                }
                if (eEnjte.isSelected()) {
                    dita = "E enjte";
                    String sql = queryForKonsultim(dita);
                    statement.executeUpdate(sql);
                }
                if (ePremte.isSelected()) {
                    dita = "E premte";
                    String sql = queryForKonsultim(dita);
                    statement.executeUpdate(sql);
                }
                if (dita.equals("")) {
                    printError("Ju lutem zgjedhni se paku nje dite kur doni te mbani konsultime.");
                }
                clearInputs();
                printConfirmation("Orari i konsultimeve u perditesua.");

            } catch (SQLiteException e) {
                printError("Salla eshte e rezervuar gjate asaj kohe. Ju lutem ndryshoni orarin.");
            } finally {
                statement.close();
                dbCon.close();
            }

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            initializeDB();
            caktoMesimdhenes(email);
            populateChoicebox(lendet);
        } catch (SQLException e) {

        }
        initializeDB();
        Konsultim.startColumn(dbCon, tabelaPersonale, kolonaLenda, kolonaDita, kolonaKoha, kolonaSalla, mesimdhenesi);



    }

    private void populateChoicebox(ChoiceBox<String> choiceBox) throws SQLException {

        String sql = "SELECT Lenda FROM Lendet WHERE Profesori = '" + mesimdhenesi + "' OR asistenti ='" + mesimdhenesi + "'";

        initializeDB();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            try {
                if (!resultSet.next()) break;
            } catch (SQLException throwables) {
                printError("Ndodhi nje gabim me databaze.");
            }
            try {
                choiceBox.setValue(resultSet.getString("Lenda"));
                choiceBox.getItems().add(resultSet.getString("Lenda"));
            } catch (SQLException e) {
                printError(e + "");
            } finally {
                if (statement != null)
                    statement.close();
                if (dbCon != null)
                    dbCon.close();
            }

        }
    }

    private void printConfirmation(String mesazhi) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Sukses");
        alert.setContentText(mesazhi);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    private String queryForKonsultim(String dita) {
        String sql = "INSERT INTO Orari(Mesimdhenesi, Salla, Ora, Dita, Lenda) VALUES (" + mesimdhenesi + ", " + salle
                + ", '" + stringOra + "', '" + dita + "', '" + lendet.getValue() + "')";
        return sql;
    }

    private void clearInputs() {
        hour.clear();
        minutes.clear();
        eHene.setSelected(false);
        eMarte.setSelected(false);
        eMerkure.setSelected(false);
        eEnjte.setSelected(false);
        ePremte.setSelected(false);
        salla.getSelectedToggle().setSelected(false);

    }


}
