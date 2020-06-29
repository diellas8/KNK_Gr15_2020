package properties;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.*;
import java.sql.Statement;



public class Lenda extends baza {
    private String Lenda;
    private String Profesoret;
    private int Viti;

    public Lenda(String lenda, String profesori, int viti) {
        this.Lenda = lenda;
        this.Profesoret = profesori;
        this.Viti = viti;
    }

    public Lenda() {
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

    public static void startColumn(Connection dbcon, TableView tabelaAdmin, TableColumn<?, ?> nje, TableColumn<?, ?> dy, TableColumn<?, ?> tre, TextField filterField) throws Exception {
        changeVisibility(nje, dy, tre);
        changeWidth(tabelaAdmin, nje, dy, tre);
        changeName(nje, dy, tre);

        nje.setCellValueFactory(new PropertyValueFactory<>("Lenda"));
        dy.setCellValueFactory(new PropertyValueFactory<>("Profesoret"));
        tre.setCellValueFactory(new PropertyValueFactory<>("Viti"));
        tabelaAdmin.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tabelaAdmin.getSelectionModel().selectedItemProperty().addListener((ov, old, _new) -> {
            if (_new != null) {
                //  setLendetToUI((Lenda)_new);
            }
        });
        try {
            FilteredList<Lenda> filteredData = new FilteredList<Lenda>(getLendet(dbcon), b -> true);

            filterField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(lenda -> {

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    if (lenda.getLenda().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (lenda.getProfesoret().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (String.valueOf(lenda.getViti()).contains(lowerCaseFilter))
                        return true;
                    else
                        return false; // Does not match.
                });
            });

            SortedList<baza> sortedData = new SortedList<>(filteredData);


            sortedData.comparatorProperty().bind(tabelaAdmin.comparatorProperty());

            tabelaAdmin.setItems(sortedData);

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

    private static ObservableList<Lenda> getLendet(Connection dbcon) throws Exception{
        ObservableList <Lenda> list = FXCollections.observableArrayList();
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

    public static void fshijLendet(TableView tabelaAdmin,Connection dbcon) {
        try {
            Lenda lenda = (Lenda) tabelaAdmin.getSelectionModel().getSelectedItem();
            String query="DELETE FROM Lendet WHERE Lenda=?";
            PreparedStatement preparedStatement = dbcon.prepareStatement(query);

            preparedStatement.setString(1, lenda.getLenda());
            preparedStatement.executeUpdate();
            //printSuccess("Lenda " + lenda.getLenda()+ "e ligjeruar nga " + lenda.getProfesoret() + " u fshi me sukses");
        } catch (SQLException ex){
            ex.printStackTrace();

        }


    }

    public static void printSuccess(String mesazh){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(mesazh);
        alert.setHeaderText(null);
        alert.setTitle("Sukses");
        alert.showAndWait();
    }




}