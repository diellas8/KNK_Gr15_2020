package properties;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Mesimdhenes extends baza {
    private int m_id;
    private String emri;
    private String mbiemri;
    private String email;
    private int statusi;

    public Mesimdhenes(int m_id, String emri, String mbiemri, String email, int statusi) {
        this.m_id = m_id;
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.email = email;
        this.statusi = statusi;
    }
    public Mesimdhenes (){

    }

    public int getM_id() {
        return m_id;
    }

    public String getEmri() {
        return emri;
    }

    public String getMbiemri() {
        return mbiemri;
    }

    public String getEmail() {
        return email;
    }

    public int getStatusi() {
        return statusi;
    }

    public static void startColumn(Connection dbcon,TableView tabelaAdmin, TableColumn<?,?> first, TableColumn<?,?>second,
                              TableColumn<?,?> third, TableColumn<?,?>fourth, TableColumn<?,?> fifth){
        first.setCellValueFactory(new PropertyValueFactory<>("m_id"));
        second.setCellValueFactory(new PropertyValueFactory<>("Emri"));
        third.setCellValueFactory(new PropertyValueFactory<>("Mbiemri"));
        fourth.setCellValueFactory(new PropertyValueFactory<>("Email"));
        fifth.setCellValueFactory(new PropertyValueFactory<>("Statusi"));
        tabelaAdmin.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tabelaAdmin.getSelectionModel().selectedItemProperty().addListener((ov, old, _new)->{
            if(_new!=null) {
                // setLendetToUI((Lenda)_new);
            }
        });
        try{
             tabelaAdmin.setItems(getMesimdhenesit(dbcon));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static ObservableList<baza> getMesimdhenesit(Connection dbcon) throws Exception{
       ObservableList <baza> list = FXCollections.observableArrayList();
        Statement stmt = dbcon.createStatement();
       String query= "SELECT * from Mesimdhenesit";
        ResultSet res = stmt.executeQuery(query);

       while(res.next()) {
           int m_id = res.getInt("m_ID");
           String emri= res.getString("Emri");
           String mbiemri = res.getString("Mbiemri");
           String email = res.getString("Email");
           int statusi = res.getInt("Statusi");
           list.add(new Mesimdhenes(m_id, emri, mbiemri, email, statusi));

        }
        return list;
   }






}
