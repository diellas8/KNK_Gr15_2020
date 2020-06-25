import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {


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


    @Override
    public void initialize(URL location, ResourceBundle resources){

    }

    @FXML


    public void handleClicks(javafx.event.ActionEvent event) {
        if(event.getSource()==btnStudentet)
        {
            lbStatus.setText("Studentet");

        }
        else if(event.getSource()==btnProfesor){
            lbStatus.setText("Profesoret");

        }

        else if(event.getSource()==btnLendet){
            lbStatus.setText("Lendet");


        }
    }

}


