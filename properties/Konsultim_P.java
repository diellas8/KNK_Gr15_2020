package properties;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.sqlite.SQLiteException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Konsultim_P extends baza{

    private int mesimdhenesi;
    private String mesimdhenesiString;
    private  int salla;
    private String ora;
    private String dita;
    private String lenda;

    public Konsultim_P(String ora, String dita, String lenda, int salla){
        this.ora = ora;
        this.dita = dita;
        this.lenda = lenda;
        this.salla = salla;
    }

    public int getMesimdhenesi() {
        return mesimdhenesi;
    }
    public String getMesimdhenesiString(){return mesimdhenesiString;}

    public int getSalla() {
        return salla;
    }

    public String getOra() {
        return ora;
    }

    public String getDita() {
        return dita;
    }

    public String getLenda() {
        return lenda;
    }

    public Konsultim_P(String mesimdhenesi, int salla, String ora, String dita, String lenda) {
        this.mesimdhenesiString = mesimdhenesi;
        this.salla = salla;
        this.ora = ora;
        this.dita = dita;
        this.lenda = lenda;
    }

    private static ObservableList<baza> getKonsultim(Connection dbcon, int mesimdhenesi) throws Exception{
        ObservableList <baza> list = FXCollections.observableArrayList();
        Statement stmt = dbcon.createStatement();
        String query= "SELECT * FROM Orari WHERE Mesimdhenesi=" + mesimdhenesi;
        ResultSet res = stmt.executeQuery(query);

        while(res.next()) {
            String Lenda = res.getString("Lenda");
            String Dita = res.getString("Dita");
            String Ora= res.getString("Ora");
            int Salla  = res.getInt("Salla");
            list.add(new Konsultim_P( Ora,Dita,Lenda, Salla));
        }
        return list;
    }
    public static void startColumn(Connection dbcon, TableView<baza> tabela, TableColumn<?, ?> nje, TableColumn<?, ?> dy,
                                   TableColumn<?, ?> tre,TableColumn<?,?> kater, int mesimdhenesi){

        nje.setCellValueFactory(new PropertyValueFactory<>("lenda"));
        dy.setCellValueFactory(new PropertyValueFactory<>("dita"));
        tre.setCellValueFactory(new PropertyValueFactory<>("ora"));
        kater.setCellValueFactory(new PropertyValueFactory<>("salla"));

        tabela.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        //tabela.getSelectionModel().selectedItemProperty().addListener((ov, old, _new)->{
           // if(_new!=null){
                //  setLendetToUI((Lenda)_new);
           // }});
        try{
            tabela.setMinHeight(0);
            tabela.setItems(getKonsultim(dbcon, mesimdhenesi));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void startTable(Connection dbcon,Statement stmt, TableView<baza> tabela, TableColumn<?, ?> nje, TableColumn<?, ?> dy,
                                   TableColumn<?, ?> tre,TableColumn<?,?> kater, TableColumn<?,?> pese){
        nje.setCellValueFactory(new PropertyValueFactory<>("mesimdhenesiString"));
        dy.setCellValueFactory(new PropertyValueFactory<>("lenda"));
        tre.setCellValueFactory(new PropertyValueFactory<>("dita"));
        kater.setCellValueFactory(new PropertyValueFactory<>("ora"));
        pese.setCellValueFactory(new PropertyValueFactory<>("salla"));

        tabela.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        //tabela.getSelectionModel().selectedItemProperty().addListener((ov, old, _new)->{
        // if(_new!=null){
        //  setLendetToUI((Lenda)_new);
        // }});
        try{
            tabela.setMinHeight(0);
            tabela.setItems(getOrar(dbcon,stmt));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static ObservableList<baza> getOrar(Connection dbcon, Statement stmt) throws Exception{
        ObservableList <baza> list = FXCollections.observableArrayList();
        String query= "SELECT m.Emri, m.Mbiemri, o.Lenda, o.Dita, o.Ora, o.Salla FROM Orari o INNER JOIN Mesimdhenesit m ON o.Mesimdhenesi=m.m_ID";
        try {
            ResultSet res = stmt.executeQuery(query);



        while(res.next()) {
            String Mesimdhenesi = res.getString("Emri") + " " + res.getString("Mbiemri");
            String Lenda = res.getString("Lenda");
            String Dita = res.getString("Dita");
            String Ora= res.getString("Ora");
            int Salla  = res.getInt("Salla");
            list.add(new Konsultim_P( Mesimdhenesi,Salla, Ora, Dita,Lenda));
        }} catch (SQLiteException e){ System.out.println(e+"");}
        return list;
    }


}
