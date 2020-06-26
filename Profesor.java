import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ResourceBundle;

public class Profesor {

    @FXML
    private TextField hour;

    @FXML
    private TextField minutes;

    @FXML
    private ToggleGroup salla;

    @FXML
    private CheckBox eHene;

    @FXML
    private CheckBox eMarte;

    @FXML
    private CheckBox eMerkure;

    @FXML
    private CheckBox eEnjte;

    @FXML
    private CheckBox ePremte;

    @FXML
    private Button largo;

    @FXML
    private ChoiceBox<?> lendet;

    @FXML
    private TableView<?> tabelaPersonale;

    @FXML
    private TableColumn<?, ?> kolonaLenda;

    @FXML
    private TableColumn<?, ?> kolonaKoha;

    @FXML
    private TableColumn<?, ?> kolonaSalla;

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

    @FXML
    void largoKonsultim(ActionEvent event) throws IOException {
//        System.out.println("hehhe");
//       Parent root = FXMLLoader.load(getClass().getResource("user.fxml"));
//       Stage primaryStage = new Stage();
//        primaryStage.setScene(new Scene(root, 700, 550));
//        primaryStage.setResizable(false);
//        primaryStage.show();
//        ((Node)event.getSource()).getScene().getWindow().hide();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Wrong password or email...");
        alert.showAndWait();
    }

    @FXML
    void ruajKonsultim(ActionEvent event) {

    }




}
