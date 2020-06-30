package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class Login implements Initializable {

    private final Stage primaryStage = new Stage();
    private String aboutFile;
    @FXML
    private Button button = new Button();

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
    private TextField email;

    @FXML
    private PasswordField password;


    @FXML
    void logOut(ActionEvent event) {


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
        password.setPromptText(bundle.getString("password"));
        file.setText(bundle.getString("menu1"));
        logout.setText(bundle.getString("menu1item1"));
        exit.setText(bundle.getString("menu1item2"));
        help.setText(bundle.getString("menu2"));
        about.setText(bundle.getString("menu2item1"));
        language.setText(bundle.getString("menu3"));
        button.setText(bundle.getString("buton"));
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
        ((Stage) email.getScene().getWindow()).close();
    }

    @FXML
    void checkData(ActionEvent event) {

        try {

            int role = hash.Password.getData(email.getText(), password.getText());
            Parent root;
            switch (role) {
                case 1: {
                    root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/admin.fxml"));
                    primaryStage.setTitle("controllers.Admin");
                    primaryStage.setScene(new Scene(root, 800, 550));
                    primaryStage.setResizable(false);
                    primaryStage.show();
                    ((Node) (event.getSource())).getScene().getWindow().hide();
                    break;
                }
                case 2: {
                    Profesor.saveEmail(email.getText());
                    root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/profesor1.fxml"));
                    primaryStage.setTitle("Orari i konsultimeve");
                    primaryStage.setScene(new Scene(root, 700, 500));
                    primaryStage.setResizable(false);
                    primaryStage.show();
                    ((Node) (event.getSource())).getScene().getWindow().hide();
                    break;

                }
                case 3: {
                    root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/student.fxml"));
                    primaryStage.setTitle("Orari i konsultimeve");
                    primaryStage.setScene(new Scene(root, 700, 550));
                    primaryStage.setResizable(false);
                    primaryStage.show();
                    ((Node) (event.getSource())).getScene().getWindow().hide();
                    break;
                }
                default: {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Email or password is wrong...");
                    alert.showAndWait();
                    password.clear();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button.setText("Kycu");

        lang.selectedToggleProperty().addListener((ob, o, n) -> lang());
        aboutFile="fxml/about2.fxml";


    }
}



