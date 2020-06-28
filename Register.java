import Main.*;
import Models.*;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Register extends Pane{
				
		VBox vbox = new VBox();
		
		
		private TextField txtName = new TextField();
		private TextField txtLastName= new TextField();
		private TextField txtUsername= new TextField();
		private TextField txtEmail= new TextField();
		private PasswordField txtPassword= new PasswordField();
		
		public Register(Stage curentStage){
		Text txt = new Text("Insert your information!");
		txt.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
		txt.setFill(Color.DARKBLUE);
		vbox.setMargin(txt, new Insets(0,0,0, 50));
		// GridPane per textfield-at edhe labels
		
		GridPane grid = new GridPane();
		grid.setHgap(5);
		grid.setVgap(5);
		grid.setPadding(new Insets(20,20,20,50));
		// Row1
		grid.add(new Label("Name: "), 0, 0);
		
		grid.add(txtName, 1, 0);
		//Row2
		grid.add(new Label("Lastname: "), 0, 1);
		
		grid.add(txtLastName, 1, 1);
		//Row3
		grid.add(new Label("Usermame: "), 0, 2);

		grid.add(txtUsername, 1, 2);
		//Row4
		grid.add(new Label("Email: "), 0, 3);
		
		grid.add(txtEmail, 1, 3);
		
		grid.add(new Label("Password: "), 0, 4);
		
		txtPassword.setPromptText("Enter your pass");
		grid.add(txtPassword, 1, 4);

		setPadding(new Insets(20));
		
		// Buttoni Register
		Button btnRegister = new Button("Register");
		btnRegister.setTranslateX(88);
		btnRegister.setTranslateY(6);
		grid.add(btnRegister, 1,5);
		
		btnRegister.setOnAction(e->{
			Insert();
			Test test=new Test();
			test.start(new Stage());
			curentStage.hide();
			
		});
		
		
		
		vbox.getChildren().addAll(txt, grid);
		
		getChildren().add(vbox);
		}
		
		private void Insert() {
			if(Regisster.insert(txtName.getText(), txtLastName.getText(), txtUsername.getText(), String.valueOf(txtPassword.getText()), txtEmail.getText())) {
				clearForm();
			}
			else {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
			
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("Plase fill all Fields");
            alert.showAndWait();
			}	
		}
		
		private void clearForm(){
			txtName.setText("");
			txtLastName.setText("");
			txtUsername.setText("");
			txtEmail.setText("");
			txtPassword.setText("");
		}
		
		
		
}
