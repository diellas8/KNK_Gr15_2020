package Controllers;

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
import Properties.Lenda;
import Properties.Mesimdhenes;
import Properties.Student;
import Properties.baza;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class Admin implements Initializable {
    Connection dbcon;
    private String aboutFile;
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
    private Button deleteBtn;

    {
        try {
            dbcon = DriverManager.getConnection("jdbc:sqlite:C:\\Sqlite\\db\\menaxhimi_konsultimeve.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void lang() {
        String lang = "";
        RadioMenuItem selectedRadioButton = (RadioMenuItem) this.lang.getSelectedToggle();
        if (selectedRadioButton.getText().equals("ALB")) {
            lang = "al";
            aboutFile="FXML/about2.fxml";
        }
        else if (selectedRadioButton.getText().equals("EN"))
        {
            lang = "en";
            aboutFile="FXML/about.fxml";
        }
        Locale locale = new Locale(lang);
        ResourceBundle bundle = ResourceBundle.getBundle("bundles.lang", locale);

        file.setText(bundle.getString("menu1"));
        logout.setText(bundle.getString("menu1item1"));
        exit.setText(bundle.getString("menu1item2"));
        help.setText(bundle.getString("menu2"));
        about.setText(bundle.getString("menu2item1"));
        language.setText(bundle.getString("menu3"));
        addBtn.setText(bundle.getString("admin_add"));
        deleteBtn.setText(bundle.getString("admin_delete"));
        textInput.setPromptText(bundle.getString("admin_searchbar"));
        btnLendet.setText(bundle.getString("admin_lendet"));
        btnProfesor.setText(bundle.getString("admin_prof"));
        btnStudentet.setText(bundle.getString("admin_studentet"));
        lbStatus.setText(bundle.getString("admin_label_l"));
        try {
            Lenda.startColumn(dbcon, tabelaAdmin, id, Emri, Roli, textInput);
        } catch (Exception e) {
            e.printStackTrace();
        }
        id.setText(bundle.getString("admin_lenda_k1"));
        Emri.setText(bundle.getString("admin_lenda_k2"));
        Roli.setText(bundle.getString("admin_lenda_k3"));
        Mbiemri.setVisible(false);
        Email.setVisible(false);
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
        Stage stage = (Stage) tabelaAdmin.getScene().getWindow();
        stage.close();

    }

    @FXML
    void rrethNesh(ActionEvent event) throws IOException {
        Parent root;
        root = FXMLLoader.load(getClass().getClassLoader().getResource(aboutFile));
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
        ((Stage) tabelaAdmin.getScene().getWindow()).close();
    }


    @FXML
    public void handleClicks(javafx.event.ActionEvent event) throws Exception {

        String lang = "";
        RadioMenuItem selectedRadioButton = (RadioMenuItem) this.lang.getSelectedToggle();
        if (selectedRadioButton.getText().equals("ALB")) lang = "al";
        else if (selectedRadioButton.getText().equals("EN")) lang = "en";
        Locale locale = new Locale(lang);
        ResourceBundle bundle = ResourceBundle.getBundle("bundles.lang", locale);
        if (event.getSource() == btnStudentet) {

            lbStatus.setText(bundle.getString("admin_label_s"));
            Student.ViewColumn(dbcon, tabelaAdmin, id, Emri, Mbiemri, Email, textInput);
            id.setText(bundle.getString("admin_profesor_k1"));
            Emri.setText(bundle.getString("admin_profesor_k2"));
            Mbiemri.setText(bundle.getString("admin_profesor_k3"));
            Email.setText(bundle.getString("admin_profesor_k4"));
            Roli.setVisible(false);


        } else if (event.getSource() == btnProfesor) {
            lbStatus.setText(bundle.getString("admin_label_p"));
            Mesimdhenes.startColumn(dbcon, tabelaAdmin, id, Emri, Mbiemri, Email, Roli, textInput);
            id.setText(bundle.getString("admin_profesor_k1"));
            Emri.setText(bundle.getString("admin_profesor_k2"));
            Mbiemri.setText(bundle.getString("admin_profesor_k3"));
            Email.setText(bundle.getString("admin_profesor_k4"));
            Roli.setText(bundle.getString("admin_profesor_k5"));

        } else if (event.getSource() == btnLendet) {
            lbStatus.setText(bundle.getString("admin_label_l"));
            Lenda.startColumn(dbcon, tabelaAdmin, id, Emri, Roli, textInput);
            id.setText(bundle.getString("admin_lenda_k1"));
            Emri.setText(bundle.getString("admin_lenda_k2"));
            Roli.setText(bundle.getString("admin_lenda_k3"));
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
        lang.selectedToggleProperty().addListener((ob, o, n) -> lang());
        aboutFile="FXML/about2.fxml";


    }

    public void fshij(javafx.event.ActionEvent event) throws Exception {
        tabelaAdmin.setEditable(true);
        event.getSource();
        if (tabelaAdmin.getSelectionModel().getSelectedItem() instanceof Lenda) {
            Lenda.fshijLendet(tabelaAdmin, dbcon);
            Lenda.startColumn(dbcon, tabelaAdmin, id, Emri, Roli, textInput);
        } else if (tabelaAdmin.getSelectionModel().getSelectedItem() instanceof Mesimdhenes) {
            Mesimdhenes.fshijMesimdhenesit(tabelaAdmin, dbcon);
            Mesimdhenes.startColumn(dbcon, tabelaAdmin, id, Emri, Mbiemri, Email, Roli, textInput);


        } else if (tabelaAdmin.getSelectionModel().getSelectedItem() instanceof Student) {
            Student.fshijPerdoruesit(tabelaAdmin, dbcon);
            Student.ViewColumn(dbcon, tabelaAdmin, id, Emri, Mbiemri, Email, textInput);
        }
    }


    public void shto(javafx.event.ActionEvent event) throws IOException {
        Parent root;
        root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/add.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("add");
        primaryStage.setScene(new Scene(root, 285, 502));
        primaryStage.setResizable(false);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.show();
        primaryStage.setOnHidden(windowEvent -> {
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