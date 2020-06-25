import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

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
    void largoKonsultim(ActionEvent event) {

    }

    @FXML
    void ruajKonsultim(ActionEvent event) {

    }

}
