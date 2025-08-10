package models;

import javafx.beans.property.*;
import javafx.collections.*;
import utils.DatabaseUtils;
import java.sql.*;

public class SubscriberModel {
    private final StringProperty name, phone, packageName, status;

    public SubscriberModel(String name, String phone, String packageName, String status) {
        this.name = new SimpleStringProperty(name);
        this.phone = new SimpleStringProperty(phone);
        this.packageName = new SimpleStringProperty(packageName);
        this.status = new SimpleStringProperty(status);
    }

    public StringProperty nameProperty() { return name; }
    public StringProperty phoneProperty() { return phone; }
    public StringProperty packageProperty() { return packageName; }
    public StringProperty statusProperty() { return status; }

    public static ObservableList<SubscriberModel> getAllSubscribers() {
        ObservableList<SubscriberModel> list = FXCollections.observableArrayList();
        String query = "SELECT * FROM subscribers";

        try (Connection conn = DatabaseUtils.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                list.add(new SubscriberModel(
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("package_name"),
                        rs.getString("status")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ObservableList<SubscriberModel> searchSubscribers(String keyword) {
        ObservableList<SubscriberModel> list = FXCollections.observableArrayList();
        String query = "SELECT * FROM subscribers WHERE name LIKE ?";

        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, "%" + keyword + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                list.add(new SubscriberModel(
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("package_name"),
                        rs.getString("status")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}