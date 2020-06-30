package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import properties.Konsultim_P;
import properties.baza;

import java.io.IOException;
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

    @FXML
    void logOut(ActionEvent event) throws IOException {
        Parent root;
        root = FXMLLoader.load(getClass().getResource("fxml/login.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Orari i konsultimeve");
        primaryStage.setScene(new Scene(root, 700, 500));
        primaryStage.setResizable(false);
        primaryStage.show();
        Stage stage = (Stage) tabela.getScene().getWindow();
        stage.close();

    }

    @FXML
    void rrethNesh(ActionEvent event) throws IOException {
        Parent root;
        root = FXMLLoader.load(getClass().getResource("fxml/about.fxml"));
        Stage primaryStage = new Stage();
        Scene scene = new Scene(root,250,150);
        primaryStage.setScene(scene);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Rreth Nesh");
        primaryStage.show();



    }


    @FXML
    void exit(ActionEvent event) {
        ((Stage) tabela.getScene().getWindow()).close();
    }

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