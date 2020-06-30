import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

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
    void logOut(ActionEvent event)  {


    }

    @FXML
    void rrethNesh(ActionEvent event) throws IOException {
        Parent root;
        root = FXMLLoader.load(getClass().getResource("about.fxml"));
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
        ((Stage) email.getScene().getWindow()).close();
    }

    @FXML
    void checkData(ActionEvent event) {

        try {

            int role = database.Password.getData(email.getText(), password.getText());
        switch (role){
            case 1: {
                root = FXMLLoader.load(getClass().getResource("admin.fxml"));
                primaryStage.setTitle("Admin");
                primaryStage.setScene(new Scene(root, 800, 550));
                primaryStage.setResizable(false);
                primaryStage.show();
                ((Node) (event.getSource())).getScene().getWindow().hide();
                break;
            }
            case 2:{
                Profesor.saveEmail(email.getText());
                root = FXMLLoader.load(getClass().getResource("profesor1.fxml"));
                primaryStage.setTitle("Orari i konsultimeve");
                primaryStage.setScene(new Scene(root, 700, 500));
                primaryStage.setResizable(false);
                primaryStage.show();
                ((Node) (event.getSource())).getScene().getWindow().hide();
                break;

            }
            case 3: {
                root = FXMLLoader.load(getClass().getResource("student.fxml"));
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
    }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}



