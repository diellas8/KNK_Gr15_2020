package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import properties.Lenda;
import properties.Mesimdhenes;
import properties.Student;
import properties.baza;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Admin implements Initializable {

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
    private Button deleteBtn ;
    @FXML
    void logOut(ActionEvent event) throws IOException {
        Parent root;
        root = FXMLLoader.load(getClass().getResource("fxml/login.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Orari i konsultimeve");
        primaryStage.setScene(new Scene(root, 700, 500));
        primaryStage.setResizable(false);
        primaryStage.show();
        Stage stage = (Stage) tabelaAdmin.getScene().getWindow();
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
        ((Stage) tabelaAdmin.getScene().getWindow()).close();
    }



    @FXML
    public void handleClicks(javafx.event.ActionEvent event) throws Exception {
        if (event.getSource() == btnStudentet) {

            lbStatus.setText("Studentet");
            Student.ViewColumn(dbcon, tabelaAdmin, id, Emri, Mbiemri, Email, textInput);
            Roli.setVisible(false);



        } else if (event.getSource() == btnProfesor) {
            lbStatus.setText("Profesoret");
            Mesimdhenes.startColumn(dbcon, tabelaAdmin, id, Emri, Mbiemri, Email, Roli, textInput);


        } else if (event.getSource() == btnLendet) {
            lbStatus.setText("Lendet");
            Lenda.startColumn(dbcon, tabelaAdmin, id, Emri, Roli,textInput);
            Mbiemri.setVisible(false);
            Email.setVisible(false);

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lbStatus.setText("Lendet");
        try {
            Lenda.startColumn(dbcon, tabelaAdmin, id, Emri, Roli, textInput);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Mbiemri.setVisible(false);
        Email.setVisible(false);


    }

    public void fshij(javafx.event.ActionEvent event) throws Exception {
        tabelaAdmin.setEditable(true);
        event.getSource();
        if(tabelaAdmin.getSelectionModel().getSelectedItem() instanceof Lenda) {
            Lenda.fshijLendet(tabelaAdmin, dbcon);
            Lenda.startColumn(dbcon, tabelaAdmin, id, Emri, Roli, textInput);
        }else if(tabelaAdmin.getSelectionModel().getSelectedItem() instanceof Mesimdhenes) {
            Mesimdhenes.fshijMesimdhenesit(tabelaAdmin, dbcon);
            Mesimdhenes.startColumn(dbcon, tabelaAdmin, id, Emri, Mbiemri, Email, Roli, textInput);


        }
        else if (tabelaAdmin.getSelectionModel().getSelectedItem() instanceof Student) {
            Student.fshijPerdoruesit(tabelaAdmin, dbcon);
            Student.ViewColumn(dbcon, tabelaAdmin, id, Emri, Mbiemri, Email, textInput);
        }
    }


    public void shto(javafx.event.ActionEvent event) throws IOException {
        Parent root;
        root= FXMLLoader.load(getClass().getResource("fxml/add.fxml"));
         Stage primaryStage=new Stage();
        primaryStage.setTitle("add");
        primaryStage.setScene(new Scene(root, 285, 502));
        primaryStage.setResizable(false);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.show();
        primaryStage.setOnHidden(windowEvent ->{
            Student.ViewColumn(dbcon, tabelaAdmin, id, Emri, Mbiemri, Email, textInput);
            try {
                Lenda.startColumn(dbcon, tabelaAdmin, id, Emri, Roli, textInput);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Mesimdhenes.startColumn(dbcon, tabelaAdmin, id, Emri, Mbiemri, Email, Roli, textInput);




        });


    }
}