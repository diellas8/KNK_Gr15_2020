import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import properties.Konsultim_P;
import properties.baza;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Student implements Initializable {

    @FXML
    private TableView<baza> tabela;

    @FXML
    private TableColumn<?, ?> kolonaMesimdhenes;



    @FXML
    private TableColumn<?, ?> kolonaLenda;

    @FXML
    private TableColumn<?, ?> kolonaDita;

    @FXML
    private TableColumn<?, ?> kolonaOra;

    @FXML
    private TableColumn<?, ?> kolonaSalla;
    private Connection dbCon;
    private Statement statement;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeDB();
        Konsultim_P.startTable(dbCon,statement,tabela, kolonaMesimdhenes, kolonaLenda, kolonaDita, kolonaOra, kolonaSalla);

    }
    private  void initializeDB() {
        try {
            if (dbCon == null || dbCon.isClosed())
                dbCon = DriverManager.getConnection("jdbc:sqlite:C:\\Sqlite\\db\\menaxhimi_konsultimeve.db");
            if (statement == null || statement.isClosed())
                statement = dbCon.createStatement();
        } catch (Exception e) {

        }
    }
}