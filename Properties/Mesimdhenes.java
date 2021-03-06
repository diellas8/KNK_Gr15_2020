package Properties;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;


public class Mesimdhenes extends baza {
    private int m_id;
    private String emri;
    private String mbiemri;
    private String email;
    private String statusi;

    public Mesimdhenes(int m_id, String emri, String mbiemri, String email, String statusi) {
        this.m_id = m_id;
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.email = email;
        this.statusi = statusi;
    }

    public Mesimdhenes() {

    }

    public static void startColumn(Connection dbcon, TableView tabelaAdmin, TableColumn<?, ?> first, TableColumn<?, ?> second,
                                   TableColumn<?, ?> third, TableColumn<?, ?> fourth, TableColumn<?, ?> fifth, TextField filterField) {
        changeVisibility(first, second, third, fourth, fifth);
        changeWidth(tabelaAdmin, first, second, third, fourth, fifth);
        first.setCellValueFactory(new PropertyValueFactory<>("m_id"));
        second.setCellValueFactory(new PropertyValueFactory<>("Emri"));
        third.setCellValueFactory(new PropertyValueFactory<>("Mbiemri"));
        fourth.setCellValueFactory(new PropertyValueFactory<>("Email"));
        fifth.setCellValueFactory(new PropertyValueFactory<>("Statusi"));
        tabelaAdmin.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tabelaAdmin.getSelectionModel().selectedItemProperty().addListener((ov, old, _new) -> {
            if (_new != null) {
                // setLendetToUI((Lenda)_new);
            }
        });
        try {
            FilteredList<Mesimdhenes> filteredData = new FilteredList<>(getMesimdhenesit(dbcon), b -> true);
            filterField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(mesimdhenes -> {

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    if (mesimdhenes.getEmri().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (mesimdhenes.getMbiemri().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (mesimdhenes.getStatusi().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else return String.valueOf(mesimdhenes.getEmail()).contains(lowerCaseFilter);
                });
            });

            SortedList<Mesimdhenes> sortedData = new SortedList<>(filteredData);


            sortedData.comparatorProperty().bind(tabelaAdmin.comparatorProperty());

            tabelaAdmin.setItems(sortedData);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void changeVisibility(TableColumn<?, ?> first, TableColumn<?, ?> second, TableColumn<?, ?> third, TableColumn<?, ?> fourth, TableColumn<?, ?> fifth) {
        first.setVisible(true);
        second.setVisible(true);
        third.setVisible(true);
        fourth.setVisible(true);
        fifth.setVisible(true);
    }

    public static void changeWidth(TableView tableView, TableColumn<?, ?> first, TableColumn<?, ?> second, TableColumn<?, ?> third, TableColumn<?, ?> fourth, TableColumn<?, ?> fifth) {
        first.prefWidthProperty().bind(tableView.widthProperty().multiply(0.1));
        second.prefWidthProperty().bind(tableView.widthProperty().multiply(0.15));
        third.prefWidthProperty().bind(tableView.widthProperty().multiply(0.15));
        fourth.prefWidthProperty().bind(tableView.widthProperty().multiply(0.40));
        fifth.prefWidthProperty().bind(tableView.widthProperty().multiply(0.15));

        first.minWidthProperty().bind(tableView.widthProperty().multiply(0.1));
        second.minWidthProperty().bind(tableView.widthProperty().multiply(0.15));
        third.minWidthProperty().bind(tableView.widthProperty().multiply(0.15));
        fourth.minWidthProperty().bind(tableView.widthProperty().multiply(0.40));
        fifth.minWidthProperty().bind(tableView.widthProperty().multiply(0.15));

        first.maxWidthProperty().bind(tableView.widthProperty().multiply(0.1));
        second.maxWidthProperty().bind(tableView.widthProperty().multiply(0.15));
        third.maxWidthProperty().bind(tableView.widthProperty().multiply(0.15));
        fourth.maxWidthProperty().bind(tableView.widthProperty().multiply(0.40));
        fifth.maxWidthProperty().bind(tableView.widthProperty().multiply(0.15));
    }

    private static ObservableList<Mesimdhenes> getMesimdhenesit(Connection dbcon) throws Exception {
        ObservableList<Mesimdhenes> list = FXCollections.observableArrayList();
        Statement stmt = dbcon.createStatement();
        String query = "SELECT * from Mesimdhenesit";
        ResultSet res = stmt.executeQuery(query);

        while (res.next()) {
            int m_id = res.getInt("m_id");
            String emri = res.getString("Emri");
            String mbiemri = res.getString("Mbiemri");
            String email = res.getString("Email");
            int statusi = res.getInt("Statusi");
            String Statusi = statusi == 1 ? "Profesor" : "Asistent";
            list.add(new Mesimdhenes(m_id, emri, mbiemri, email, Statusi));

        }
        return list;
    }

    public static TableView createTable() {
        TableView tableView = new TableView<>();
        TableColumn<?, ?> m_id = new TableColumn<>("ID");
        TableColumn<?, ?> emri = new TableColumn<>("Emri");
        TableColumn<?, ?> mbiemri = new TableColumn<>("Mbiemri");
        TableColumn<?, ?> email = new TableColumn<>("Email");
        TableColumn<?, ?> Status = new TableColumn<>("Status");
        tableView.getColumns().addAll(m_id, emri, mbiemri, email, Status);
        return tableView;
    }

    public static void fshijMesimdhenesit(TableView tabelaAdmin, Connection dbcon) {
        Mesimdhenes mesimdhenes = (Mesimdhenes) tabelaAdmin.getSelectionModel().getSelectedItem();
        try {
            String query = "DELETE FROM Mesimdhenesit WHERE m_ID=?";
            PreparedStatement preparedStatement = dbcon.prepareStatement(query);

            preparedStatement.setInt(1, mesimdhenes.getM_id());
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

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

    public String getStatusi() {
        return statusi;
    }


}
