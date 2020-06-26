package properties;

import java.sql.Connection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Perdoruesit extends baza{
    private int u_ID;
    private String Email;
    private String Salt;
    private String Hash;
    private int Statusi;

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

    public int getStatusi() {
        return Statusi;
    }

    public Perdoruesit(int u_id, String Email, String Salt, String Hash, int Statusi){
        this.u_ID = u_ID;
        this.Email = Email;
        this.Salt = Salt;
        this.Hash = Hash;
        this.Statusi = Statusi;
    }
    public void ViewColumn(Connection dbcon, TableView tabelaAdmin, TableColumn <?,?> first, TableColumn<?,?> second, TableColumn<?,?> third, TableColumn<?,?> fourth, TableColumn<?,?> fifth){

        first.setCellValueFactory(new PropertyValueFactory<>("u_id"));
        second.setCellValueFactory(new PropertyValueFactory<>("Email"));
        third.setCellValueFactory(new PropertyValueFactory<>("Salt"));
        fourth.setCellValueFactory(new PropertyValueFactory<>("Hash"));
        fifth.setCellValueFactory(new PropertyValueFactory<>("Statusi"));

        tabelaAdmin.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

            try{
                tabelaAdmin.setItems(getLendet(dbcon));
    } catch (Exception e) {
        e.printStackTrace();
    }

}

    private ObservableList <baza> getLendet(Connection dbcon) throws SQLException {
       ObservableList <baza> list = FXCollections.observableArrayList();

        Statement stmt = dbcon.createStatement();
        String query= "SELECT * FROM Perdoruesit";
        ResultSet res = stmt.executeQuery(query);

       while(res.next()) {
           int u_ID = res.getInt("u_ID");
           String Email = res.getString("Email");
           String Salt = res.getString("Salt");
           String Hash = res.getString("Hash");
           int Statusi = res.getInt("Statusi");
           list.add(new Lenda(u_ID, Email, Salt, Hash, Statusi));

        }
        return list;
    }
    }





    public Perdoruesit(){
  }


}
