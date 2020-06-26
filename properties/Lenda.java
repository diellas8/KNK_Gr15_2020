package properties;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.*;
import java.sql.Statement;



public class Lenda extends baza{
    private String Lenda;
    private String Profesoret;
    private int Viti;
    public Lenda(String lenda, String profesori, int viti){
        this.Lenda=lenda;
        this.Profesoret=profesori;
        this.Viti=viti;
    }
    public Lenda(){
    }
    public String getLenda() {
        return Lenda;
    }

    public String getProfesoret() {
        return Profesoret;
    }

    public int getViti() {
        return Viti;
    }

    public static void startColumn(Connection dbcon, TableView tabelaAdmin, TableColumn<?, ?> nje, TableColumn<?, ?> dy, TableColumn<?, ?> tre){
        nje.setCellValueFactory(new PropertyValueFactory<>("Lenda"));
        dy.setCellValueFactory(new PropertyValueFactory<>("Profesoret"));
        tre.setCellValueFactory(new PropertyValueFactory<>("Viti"));
        tabelaAdmin.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tabelaAdmin.getSelectionModel().selectedItemProperty().addListener((ov, old, _new)->{
            if(_new!=null){
                //  setLendetToUI((Lenda)_new);
            }});
        try{
            tabelaAdmin.setMinHeight(0);
            tabelaAdmin.setItems(getLendet(dbcon));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static ObservableList<baza> getLendet(Connection dbcon) throws Exception{
        ObservableList <baza> list = FXCollections.observableArrayList();
        Statement stmt = dbcon.createStatement();
        String query= "SELECT l.Lenda, m.Emri, m.Mbiemri, l.Viti FROM Lendet l INNER JOIN Mesimdhenesit m ON l.Profesori = m.m_ID";
        ResultSet res = stmt.executeQuery(query);

        while(res.next()) {
            String Emri = res.getString("Lenda");
            String Profesori= res.getString("Emri") + " " + res.getString("Mbiemri");
            int Viti = res.getInt("Viti");
            list.add(new Lenda(Emri, Profesori, Viti));

        }
        return list;
    }




}