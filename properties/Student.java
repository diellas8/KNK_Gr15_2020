package properties;

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


public class Student extends baza {
    private int s_ID;
    private String emri;
    private String mbiemri;
    private String email;


    public Student(int s_id, String emri, String mbiemri, String email) {
        this.s_ID = s_id;
        this.email = email;
        this.emri = emri;
        this.mbiemri = mbiemri;

    }

    public Student() {
    }

    public static void ViewColumn(Connection dbcon, TableView tabelaAdmin, TableColumn<?, ?> first, TableColumn<?, ?> second, TableColumn<?, ?> third, TableColumn<?, ?> fourth, TextField filterField) {
        changeWidth(tabelaAdmin, first, second, third, fourth);
        changeVisibility(first, second, third, fourth);
        first.setCellValueFactory(new PropertyValueFactory<>("s_ID"));
        second.setCellValueFactory(new PropertyValueFactory<>("emri"));
        third.setCellValueFactory(new PropertyValueFactory<>("mbiemri"));
        fourth.setCellValueFactory(new PropertyValueFactory<>("email"));


        tabelaAdmin.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        try {
            FilteredList<Student> filteredData = new FilteredList<>(getStudentet(dbcon), b -> true);

            filterField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(student -> {


                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }


                    String lowerCaseFilter = newValue.toLowerCase();

                    if (student.getEmri().toLowerCase().contains(lowerCaseFilter)) {
                        return true; // Filter matches first name.
                    } else // Does not match.
                        if (student.getMbiemri().toLowerCase().contains(lowerCaseFilter)) {
                            return true; // Filter matches last name.
                        } else return String.valueOf(student.getEmail()).contains(lowerCaseFilter);
                });
            });

            SortedList<Student> sortedData = new SortedList<>(filteredData);


            sortedData.comparatorProperty().bind(tabelaAdmin.comparatorProperty());


            tabelaAdmin.setItems(sortedData);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void changeVisibility(TableColumn<?, ?> first, TableColumn<?, ?> second, TableColumn<?, ?> third, TableColumn<?, ?> fourth) {
        first.setVisible(true);
        second.setVisible(true);
        third.setVisible(true);
        fourth.setVisible(true);
    }

    private static void changeWidth(TableView tableView, TableColumn<?, ?> first, TableColumn<?, ?> second, TableColumn<?, ?> third, TableColumn<?, ?> fourth) {
        first.prefWidthProperty().bind(tableView.widthProperty().multiply(0.1));
        second.prefWidthProperty().bind(tableView.widthProperty().multiply(0.3));
        third.prefWidthProperty().bind(tableView.widthProperty().multiply(0.3));
        fourth.prefWidthProperty().bind(tableView.widthProperty().multiply(0.3));

        first.minWidthProperty().bind(tableView.widthProperty().multiply(0.1));
        second.minWidthProperty().bind(tableView.widthProperty().multiply(0.3));
        third.minWidthProperty().bind(tableView.widthProperty().multiply(0.3));
        fourth.minWidthProperty().bind(tableView.widthProperty().multiply(0.3));


        first.maxWidthProperty().bind(tableView.widthProperty().multiply(0.1));
        second.maxWidthProperty().bind(tableView.widthProperty().multiply(0.3));
        third.maxWidthProperty().bind(tableView.widthProperty().multiply(0.3));
        fourth.maxWidthProperty().bind(tableView.widthProperty().multiply(0.3));
    }

    private static ObservableList<Student> getStudentet(Connection dbcon) throws SQLException {
        ObservableList<Student> list = FXCollections.observableArrayList();

        Statement stmt = dbcon.createStatement();
        String query = "SELECT * FROM Studentet";
        ResultSet res = stmt.executeQuery(query);

        while (res.next()) {
            int s_ID = res.getInt("s_ID");
            String emri = res.getString("Emri");
            String mbiemri = res.getString("Mbiemri");
            String email = res.getString("Email");

            list.add(new Student(s_ID, emri, mbiemri, email));

        }
        return list;
    }

    public static void fshijPerdoruesit(TableView tabelaAdmin, Connection dbcon) {
        Student student = (Student) tabelaAdmin.getSelectionModel().getSelectedItem();

        try {
            String query = "DELETE FROM Studentet WHERE s_ID=?";
            PreparedStatement preparedStatement = dbcon.prepareStatement(query);
            preparedStatement.setInt(1, student.getS_ID());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();

        }

    }

    public int getS_ID() {
        return s_ID;
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


}
