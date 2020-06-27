package properties;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.*;
import java.sql.Statement;



public class Lenda extends baza{
    private static String Lenda;
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
        changeVisibility(nje, dy, tre);
        changeWidth(tabelaAdmin,nje, dy, tre);
        changeName(nje, dy, tre);

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

    private static void changeWidth(TableView tableView,TableColumn<?,?> nje, TableColumn<?,?> dy, TableColumn<?,?> tre) {
        nje.prefWidthProperty().bind(tableView.widthProperty().multiply(0.4));
        dy.prefWidthProperty().bind(tableView.widthProperty().multiply(0.4));
        tre.prefWidthProperty().bind(tableView.widthProperty().multiply(0.2));

        nje.minWidthProperty().bind(tableView.widthProperty().multiply(0.4));
        dy.minWidthProperty().bind(tableView.widthProperty().multiply(0.4));
        tre.minWidthProperty().bind(tableView.widthProperty().multiply(0.2));

        nje.maxWidthProperty().bind(tableView.widthProperty().multiply(0.4));
        dy.maxWidthProperty().bind(tableView.widthProperty().multiply(0.4));
        tre.maxWidthProperty().bind(tableView.widthProperty().multiply(0.2));

    }

    private static void changeName(TableColumn<?,?> nje, TableColumn<?,?> dy, TableColumn<?,?> tre) {
        nje.setText("Lenda");
        dy.setText("Ligjeruesi");
        tre.setText("Viti");
    }

    private static void changeVisibility(TableColumn<?,?> nje, TableColumn<?,?> dy, TableColumn<?,?> tre) {
        nje.setVisible(true);
        dy.setVisible(true);
        tre.setVisible(true);
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

    public static ObservableList<baza> fshijLendet(Connection dbcon) {
        ObservableList <baza> list = FXCollections.observableArrayList();
        try {
            String query="DELETE FROM Lendet WHERE Lenda=?";
            PreparedStatement preparedStatement = dbcon.prepareStatement(query);

            preparedStatement.setString(1, Lenda);
            preparedStatement.executeUpdate();
        } catch (SQLException ex){
            ex.printStackTrace();

        }
        return list;
    }



}