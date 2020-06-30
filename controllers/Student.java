package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
import java.util.Locale;
import java.util.ResourceBundle;

public class Student implements Initializable {
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
        root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/login.fxml"));
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
        root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/about.fxml"));
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
        lang.selectedToggleProperty().addListener((ob, o, n) ->lang());
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
        kolonaMesimdhenes.setText(bundle.getString("student_k1"));
        kolonaLenda.setText(bundle.getString("student_k2"));
        kolonaDita.setText(bundle.getString("student_k3"));
        kolonaOra.setText(bundle.getString("student_k4"));
        kolonaSalla.setText(bundle.getString("student_k5"));
    }

}