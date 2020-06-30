
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.sqlite.SQLiteException;
import properties.Lenda;
import javax.swing.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Add {

    private static Connection dbCon = null;
    private static Statement statement = null;
    private static int viti;
    private static int profesori;
    private static final String regex = "^(.+)@(.+)$";


    @FXML
    private TextField Lenda;

    @FXML
    private TextField idMesimdhenesit;

    @FXML
    private ToggleGroup Viti;

    @FXML
    private ToggleGroup Statusi1;

    @FXML
    private TextField mEmri;

    @FXML
    private TextField mMbiemri;

    @FXML
    private TextField mEmail;

    @FXML
    private ToggleGroup Statusi;

    @FXML
    private TextField sEmri;

    @FXML
    private TextField sMbiemri;

    @FXML
    private TextField sEmail;


    @FXML
    void shtoLende(ActionEvent event) {

        String lenda = Lenda.getText();
        String prof = idMesimdhenesit.getText();

        try {
            profesori = Integer.parseInt(prof);
            RadioButton selectedRadioButton = (RadioButton) Viti.getSelectedToggle();
            String year = selectedRadioButton.getText();
            viti = Integer.parseInt(year);

        } catch (Exception ex) {
            printError("mbushini fushat!");
        }

        if ((lenda.equals(""))) {
            printError("Jepeni lenden");
        } else {
            initializeDB();
            try {
                String sql = "insert into Lendet"
                        + " (Lenda, Profesori, Viti)" + " values (?, ?, ?)";
                statement = dbCon.prepareStatement(sql);
                ((PreparedStatement) statement).setString(1, lenda);
                ((PreparedStatement) statement).setInt(2, profesori);
                ((PreparedStatement) statement).setInt(3, viti);
                ((PreparedStatement) statement).executeUpdate();
                Stage stage = (Stage) Lenda.getScene().getWindow();
                stage.close();
                printInformation("Lenda u shtua");

            } catch (SQLException ex) {
                printError("Lenda eshte e regjistruar");
            }
        }
    }


    @FXML
    void shtoMesimdhenes(ActionEvent event) {

        String emri = mEmri.getText();
        String mbiemri = mMbiemri.getText();
        String email = mEmail.getText();
        RadioButton selectedRadioButton = (RadioButton) Statusi.getSelectedToggle();

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        initializeDB();
        if ((emri.equals("")) && (mbiemri.equals("")) && (email.equals(""))) {
            printError("Plotesojini hapsirat");

        } else if (!matcher.matches()) {
            printError("Ju lutem jepeni nje email valid");
        } else {
            try {
                String stat = selectedRadioButton.getText();
                int statusi = Integer.parseInt(stat);

                String sql = "insert into Mesimdhenesit"
                        + " (Emri, Mbiemri, Email, Statusi)" + " values (?, ?, ?, ?)";
                statement = dbCon.prepareStatement(sql);
                ((PreparedStatement) statement).setString(1, emri);
                ((PreparedStatement) statement).setString(2, mbiemri);
                ((PreparedStatement) statement).setString(3, email);
                ((PreparedStatement) statement).setInt(4, statusi);

                ((PreparedStatement) statement).executeUpdate();
                Stage stage = (Stage) Lenda.getScene().getWindow();
                stage.close();
                printInformation("Mesimdhenesi u shtua me sukses!");


            } catch (SQLException ex) {
                printError("Mesimdhenesi eshte i regjistruar");

            }
        }

    }

    @FXML
    void shtuoStudent(ActionEvent event) {
        String emri = sEmri.getText();
        String mbiemri = sMbiemri.getText();
        String email = sEmail.getText();
        initializeDB();
        if ((emri.equals("")) && (mbiemri.equals("")) && (email.equals(""))) {
            printError("Ju lutem mbushini te gjitha fushat");

        } else {
            try {
                String sql = "insert into Studentet"
                        + " (Emri, Mbiemri, Email)" + " values (?, ?, ?)";
                statement = dbCon.prepareStatement(sql);
                ((PreparedStatement) statement).setString(1, emri);
                ((PreparedStatement) statement).setString(2, mbiemri);
                ((PreparedStatement) statement).setString(3, email);
                ((PreparedStatement) statement).executeUpdate();
                Stage stage = (Stage) Lenda.getScene().getWindow();
                stage.close();
                printInformation("Studenti u shtua.");
            } catch (SQLException ex) {
                printError("Studenti eshte i regjistruar.");

            }

        }


    }

    private static void printError(String mesazh) {
        Alert.AlertType alertAlertType;
        Alert alert = new Alert(Alert.AlertType.ERROR);
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

    private void printInformation(String mesazhi) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sukses");
        alert.setContentText(mesazhi);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

}


