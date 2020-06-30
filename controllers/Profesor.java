package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.sqlite.SQLiteException;
import properties.Konsultim_P;
import properties.baza;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class Profesor implements Initializable {
    private static Connection dbCon = null;


    private static Statement statement = null;


    private static String email;
    private static int mesimdhenesi;

    @FXML
    private Menu file;

    @FXML
    private MenuItem logout;

    @FXML
    private MenuItem exit;

    @FXML
    private Menu help;

    @FXML
    private MenuItem about;

    @FXML
    private Menu language;

    @FXML
    private ToggleGroup lang;


    @FXML
    private TabPane tabPane;

    @FXML
    private Tab oraripTab;
    @FXML
    private Tab shtoTab;

    @FXML
    private Tab orariTab;

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
    private TableView<baza> tabelaPersonale;

    @FXML
    private TableColumn<?, ?> kolonaLenda;

    @FXML
    private TableColumn<?, ?> kolonaKoha;

    @FXML
    private TableColumn<?, ?> kolonaSalla;

    @FXML
    private TableColumn<?, ?> kolonaDita;

    @FXML
    private TableView<baza> tabelaPergjithshme;

    @FXML
    private TableColumn<?, ?> kolonaMesimdhenesi;

    @FXML
    private TableColumn<?, ?> KolonaLendaP;

    @FXML
    private TableColumn<?, ?> KolonaKohaP;

    @FXML
    private TableColumn<?, ?> KolonaDitaP;

    @FXML
    private TableColumn<?, ?> KolonaSallaP;

    @FXML
    private Text textWarning;

    @FXML
    private Label labelKryesore;

    @FXML
    private Label labelDita;

    @FXML
    private Label labelKoha;

    @FXML
    private Label labelSalla;
    @FXML
    private Button butoniRuaj;

    @FXML
    private Label labelLenda;
    @FXML
    private Label labelaKryesore2;


    private String stringOra = "";
    private int salle;

    public Profesor() {
    }

    @FXML
    void logOut(ActionEvent event) throws IOException {
        Parent root;
        root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/login.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Orari i konsultimeve");
        primaryStage.setScene(new Scene(root, 700, 500));
        primaryStage.setResizable(false);
        primaryStage.show();
        Stage stage = (Stage) tabelaPersonale.getScene().getWindow();
        stage.close();

    }

    @FXML
    void rrethNesh(ActionEvent event) throws IOException {
        Parent root;
        root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/about.fxml"));
        Stage primaryStage = new Stage();
        Scene scene = new Scene(root, 250, 150);
        primaryStage.setScene(scene);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Rreth Nesh");
        primaryStage.show();


    }


    @FXML
    void exit(ActionEvent event) {
        ((Stage) tabelaPersonale.getScene().getWindow()).close();
    }


    public static void saveEmail(String em) {
        email = em;
    }

    private static void caktoMesimdhenes(String email) throws SQLException {
        String sql = "SELECT m_ID, Emri, Mbiemri from Mesimdhenesit WHERE email = '" + email + "'";
        ResultSet resultSet;
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
        fshijKonsultim();
    }

    @FXML
    void ruajKonsultim(ActionEvent event) throws SQLException {
        int ora = 0;
        int minuta = 0;

        try {
            ora = Integer.parseInt(hour.getText());
            minuta = Integer.parseInt(minutes.getText());
        } catch (Exception e) {

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
                if (!dita.equals("")) {
                    printInformation("Orari i konsultimeve u perditesua.");
                    clearInputs();
                }


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
            printError("Gabim me databaze");

        }
        setOraripTab();
        setOrariTab();
        lang.selectedToggleProperty().addListener((ob, o, n) -> lang());


    }

    private void populateChoicebox(ChoiceBox<String> choiceBox) throws SQLException {

        String sql = "SELECT Lenda FROM Lendet WHERE Profesori = '" + mesimdhenesi + "' OR asistenti ='" + mesimdhenesi + "'";

        initializeDB();
        ResultSet resultSet = statement.executeQuery(sql);
        while (true) {
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
            }
        }
        if (!statement.isClosed() || statement == null)
            statement.close();
        if (!dbCon.isClosed() || dbCon == null)
            dbCon.close();

    }


    private void printInformation(String mesazhi) {
        Alert alert = new Alert(AlertType.INFORMATION);
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

    private void setOraripTab() {
        tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldTab, newTab) -> {
            if (newTab == oraripTab) {
                initializeDB();
                Konsultim_P.startColumn(dbCon, tabelaPersonale, kolonaLenda, kolonaDita, kolonaKoha, kolonaSalla, mesimdhenesi);
            }

        });
    }

    private void setOrariTab() {
        tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldTab, newTab) -> {
            if (newTab == orariTab) {
                initializeDB();
                Konsultim_P.startTable(dbCon, statement, tabelaPergjithshme, kolonaMesimdhenesi, KolonaLendaP, KolonaDitaP, KolonaKohaP, KolonaSallaP);
            }

        });
    }

    public void fshijKonsultim() {
        try {
            initializeDB();
            Konsultim_P konsultim_p = (Konsultim_P) tabelaPersonale.getSelectionModel().getSelectedItem();
            String query = "DELETE FROM Orari WHERE Lenda=? AND Ora = ? AND Salla = ? AND Dita = ?";
            PreparedStatement preparedStatement = dbCon.prepareStatement(query);
            preparedStatement.setString(1, konsultim_p.getLenda());
            preparedStatement.setString(2, konsultim_p.getOra());
            preparedStatement.setInt(3, konsultim_p.getSalla());
            preparedStatement.setString(4, konsultim_p.getDita());
            preparedStatement.executeUpdate();
            tabelaPersonale.getItems().removeAll(tabelaPersonale.getSelectionModel().getSelectedItem());
        } catch (SQLException ex) {
            ex.printStackTrace();

        }

    }


    void lang() {
        String lang = "";
        RadioMenuItem selectedRadioButton = (RadioMenuItem) this.lang.getSelectedToggle();
        if (selectedRadioButton.getText().equals("ALB")) lang = "al";
        else if (selectedRadioButton.getText().equals("EN")) lang = "en";
        Locale locale = new Locale(lang);
        ResourceBundle bundle = ResourceBundle.getBundle("bundles.lang", locale);
        file.setText(bundle.getString("menu1"));
        logout.setText(bundle.getString("menu1item1"));
        exit.setText(bundle.getString("menu1item2"));
        help.setText(bundle.getString("menu2"));
        about.setText(bundle.getString("menu2item1"));
        language.setText(bundle.getString("menu3"));
        labelKryesore.setText(bundle.getString("profesor_tab1_label"));
        labelDita.setText(bundle.getString("profesor_tab1_dita"));
        eHene.setText(bundle.getString("profesor_tab1_d1"));
        eMarte.setText(bundle.getString("profesor_tab1_d2"));
        eMerkure.setText(bundle.getString("profesor_tab1_d3"));
        eEnjte.setText(bundle.getString("profesor_tab1_d4"));
        ePremte.setText(bundle.getString("profesor_tab1_d5"));
        labelKoha.setText(bundle.getString("profesor_tab1_koha"));
        labelSalla.setText(bundle.getString("profesor_tab1_salla"));
        labelLenda.setText(bundle.getString("profesor_tab1_lenda"));
        butoniRuaj.setText(bundle.getString("profesor_tab1_ruaj"));
        shtoTab.setText(bundle.getString("profesor_tab1"));
        oraripTab.setText(bundle.getString("profesor_tab2"));
        orariTab.setText(bundle.getString("profesor_tab3"));
        textWarning.setText(bundle.getString("profesor_tab1_text"));
        labelaKryesore2.setText(bundle.getString("profesor_tab2_label"));
        kolonaLenda.setText(bundle.getString("profesor_tab2_lenda"));
        kolonaDita.setText(bundle.getString("profesor_tab2_dita"));
        kolonaKoha.setText(bundle.getString("profesor_tab2_koha"));
        kolonaSalla.setText(bundle.getString("profesor_tab2_salla"));
        largo.setText(bundle.getString("profesor_tab2_largo"));
        KolonaDitaP.setText(kolonaLenda.getText());
        KolonaKohaP.setText(kolonaKoha.getText());
        KolonaLendaP.setText(kolonaLenda.getText());
        KolonaSallaP.setText(kolonaSalla.getText());
        kolonaMesimdhenesi.setText(bundle.getString("profesor_tab3_mesimdhenesi"));


    }
}
