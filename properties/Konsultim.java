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

public class Konsultim extends baza{

    private int mesimdhenesi;
    private  int salla;
    private String ora;
    private String dita;
    private String lenda;

    public Konsultim(String ora, String dita, String lenda, int salla){
        this.ora = ora;
        this.dita = dita;
        this.lenda = lenda;
        this.salla = salla;
    }

    public int getMesimdhenesi() {
        return mesimdhenesi;
    }

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

    public Konsultim(int mesimdhenesi, int salla, String ora, String dita, String lenda) {
        this.mesimdhenesi = mesimdhenesi;
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
            list.add(new Konsultim( Ora,Dita,Lenda, Salla));
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
}
