
    import javafx.event.ActionEvent;
    import javafx.fxml.FXML;
    import javafx.scene.control.*;
    import org.sqlite.SQLiteException;
    import properties.Lenda;

    import javax.swing.*;
    import java.sql.*;

    public class Add {
        private static Connection dbCon = null;


        private static Statement statement = null;
      private static int viti;


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
            int prff = Integer.parseInt(prof);
            RadioButton selectedRadioButton = (RadioButton) Viti.getSelectedToggle();
            String witi = selectedRadioButton.getText();
             viti = Integer.parseInt(witi);
            initializeDB();
            try {
                String sql = "insert into Lendet"
                        + " (Lenda, Profesori, Viti)" + " values (?, ?, ?)";
                statement = dbCon.prepareStatement(sql);
                ((PreparedStatement) statement).setString(1, lenda);
                ((PreparedStatement) statement).setInt(2, prff);
                ((PreparedStatement) statement).setInt(3, viti);
                ((PreparedStatement) statement).executeUpdate();


            }
          catch(SQLException ex){
                ex.printStackTrace();

            }


            }




        @FXML
        void shtoMesimdhenes(ActionEvent event) {


            String emri = mEmri.getText();
            String mbiemri = mMbiemri.getText();
            String email = mEmail.getText();
            RadioButton selectedRadioButton = (RadioButton) Statusi.getSelectedToggle();
            String stat = selectedRadioButton.getText();
            int statusi = Integer.parseInt(stat);
            initializeDB();
            try {
                String sql = "insert into Mesimdhenesit"
                        + " (Emri, Mbiemri, Email, Statusi)" + " values (?, ?, ?, ?)";
                statement = dbCon.prepareStatement(sql);
                ((PreparedStatement) statement).setString(1, emri);
                ((PreparedStatement) statement).setString(2, mbiemri);
                ((PreparedStatement) statement).setString(3, email);
                ((PreparedStatement) statement).setInt(4, statusi);

                ((PreparedStatement) statement).executeUpdate();


            } catch (SQLException ex) {
                ex.printStackTrace();

            }

        }

        @FXML
        void shtuoStudent(ActionEvent event) {


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
//        public static String shtimi(){
//
//        }

    }


