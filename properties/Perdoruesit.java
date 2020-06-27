package properties;

import java.sql.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.*;

import static properties.Mesimdhenes.changeVisibility;
import static properties.Mesimdhenes.changeWidth;


public class Perdoruesit extends baza{
    private  int u_ID;
    private String Email;
    private String Salt;
    private String Hash;
    private String Roli;

    public int getU_ID() {
        return u_ID;
    }

    public String getEmail() {
        return Email;
    }

    public String getSalt() {
        return Salt;
    }

    public String getHash() {
        return Hash;
    }

    public String getRoli() {
        return Roli;
    }

    public Perdoruesit(int u_id, String Email, String Salt, String Hash, String Roli){
        this.u_ID = u_id;
        this.Email = Email;
        this.Salt = Salt;
        this.Hash = Hash;
        this.Roli = Roli;
    }
    public static void ViewColumn(Connection dbcon, TableView tabelaAdmin, TableColumn <?,?> first, TableColumn<?,?> second, TableColumn<?,?> third, TableColumn<?,?> fourth, TableColumn<?,?> fifth){
        changeWidth(tabelaAdmin, first, second, third, fourth, fifth);
        changeVisibility(first, second, third, fourth, fifth);
        changeName(first, second, third, fourth, fifth);
        first.setCellValueFactory(new PropertyValueFactory<>("u_ID"));
        second.setCellValueFactory(new PropertyValueFactory<>("Email"));
        third.setCellValueFactory(new PropertyValueFactory<>("Salt"));
        fourth.setCellValueFactory(new PropertyValueFactory<>("Hash"));
        fifth.setCellValueFactory(new PropertyValueFactory<>("roli"));

        tabelaAdmin.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

            try{
                tabelaAdmin.setItems(getLendet(dbcon));
    } catch (Exception e) {
        e.printStackTrace();
    }

}
    private static void  changeWidth(TableView tableView,TableColumn<?,?> first, TableColumn<?,?> second, TableColumn<?,?> third, TableColumn<?,?> fourth, TableColumn<?,?> fifth) {
        first.prefWidthProperty().bind(tableView.widthProperty().multiply(0.1));
        second.prefWidthProperty().bind(tableView.widthProperty().multiply(0.3));
        third.prefWidthProperty().bind(tableView.widthProperty().multiply(0.20));
        fourth.prefWidthProperty().bind(tableView.widthProperty().multiply(0.20));
        fifth.prefWidthProperty().bind(tableView.widthProperty().multiply(0.2));

        first.minWidthProperty().bind(tableView.widthProperty().multiply(0.1));
        second.minWidthProperty().bind(tableView.widthProperty().multiply(0.3));
        third.minWidthProperty().bind(tableView.widthProperty().multiply(0.20));
        fourth.minWidthProperty().bind(tableView.widthProperty().multiply(0.20));
        fifth.minWidthProperty().bind(tableView.widthProperty().multiply(0.2));

        first.maxWidthProperty().bind(tableView.widthProperty().multiply(0.1));
        second.maxWidthProperty().bind(tableView.widthProperty().multiply(0.3));
        third.maxWidthProperty().bind(tableView.widthProperty().multiply(0.20));
        fourth.maxWidthProperty().bind(tableView.widthProperty().multiply(0.20));
        fifth.maxWidthProperty().bind(tableView.widthProperty().multiply(0.2));
    }

    private static void  changeName(TableColumn<?,?> first, TableColumn<?,?> second, TableColumn<?,?> third, TableColumn<?,?> fourth, TableColumn<?,?> fifth) {
        first.setText("ID");
        second.setText("Email");
        third.setText("Salt");
        fourth.setText("Hash");
        fifth.setText("Roli");
    }


    private static ObservableList <baza> getLendet(Connection dbcon) throws SQLException {
       ObservableList <baza> list = FXCollections.observableArrayList();

        Statement stmt = dbcon.createStatement();
        String query= "SELECT * FROM Users";
        ResultSet res = stmt.executeQuery(query);

       while(res.next()) {
           int u_ID = res.getInt("u_ID");
           String Email = res.getString("Email");
           String Salt = res.getString("Salt");
           String Hash = res.getString("Hash");
           int Statusi = res.getInt("Statusi");
           String roli;
           if(Statusi == 1){
               roli = "Admin";
           }
           else if(Statusi ==2){
               roli = "Profesor";
           }
           else{
               roli = "Student";
           }
           list.add(new Perdoruesit(u_ID, Email, Salt, Hash, roli));

        }
        return list;
    }


    public static void fshijPerdoruesit(TableView tabelaAdmin, Connection dbcon) {
        Perdoruesit perdoruesit = (Perdoruesit) tabelaAdmin.getSelectionModel().getSelectedItem();

        try {
            String query="DELETE FROM Users WHERE u_ID=?";
            PreparedStatement preparedStatement = dbcon.prepareStatement(query);

            preparedStatement.setInt(1, perdoruesit.getU_ID());
            preparedStatement.executeUpdate();
        } catch (SQLException ex){
            ex.printStackTrace();

        }

    }



    public Perdoruesit(){
  }


}
