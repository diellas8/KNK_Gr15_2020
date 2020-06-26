import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Login {

private Parent root;
private Stage primaryStage= new Stage();

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    private MenuButton language;

    @FXML
    private ToggleGroup lang;

    @FXML
    void changeLang(ActionEvent event) {

    }

    @FXML
    void checkData(ActionEvent event) {

        try {
            int role = database.Password.getData(email.getText(), password.getText());
        switch (role){
            case 1: {
                root = FXMLLoader.load(getClass().getResource("admin.fxml"));
                primaryStage.setTitle("admin");
                primaryStage.setScene(new Scene(root, 700, 550));
                primaryStage.setResizable(false);
                primaryStage.show();
                ((Node) (event.getSource())).getScene().getWindow().hide();
                break;
            }
            case 2:{
                root = FXMLLoader.load(getClass().getResource("profesor.fxml"));
                primaryStage.setTitle("profesor");
                primaryStage.setScene(new Scene(root, 700, 550));
                primaryStage.setResizable(false);
                primaryStage.show();
                ((Node) (event.getSource())).getScene().getWindow().hide();
                break;

            }
            case 3: {
                root = FXMLLoader.load(getClass().getResource("user.fxml"));
                primaryStage.setTitle("user");
                primaryStage.setScene(new Scene(root, 700, 550));
                primaryStage.setResizable(false);
                primaryStage.show();
                ((Node) (event.getSource())).getScene().getWindow().hide();
                break;
            }

        }
    }
        catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Email or password is wrong...");
            alert.showAndWait();


        }
    }

}



