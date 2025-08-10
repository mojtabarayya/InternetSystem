package models;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DatabaseUtils;

import java.sql.*;

public class PaymentModel {
    private final StringProperty paymentDate, method, status;
    private final DoubleProperty amount;

    public PaymentModel(String date, double amount, String method, String status) {
        this.paymentDate = new SimpleStringProperty(date);
        this.amount = new SimpleDoubleProperty(amount);
        this.method = new SimpleStringProperty(method);
        this.status = new SimpleStringProperty(status);
    }

    public StringProperty paymentDateProperty() { return paymentDate; }
    public DoubleProperty amountProperty() { return amount; }
    public StringProperty methodProperty() { return method; }
    public StringProperty statusProperty() { return status; }

    public static ObservableList<PaymentModel> getPaymentsBySubscriber(int subscriberId) {
        ObservableList<PaymentModel> list = FXCollections.observableArrayList();
        String query = """
            SELECT p.payment_date, p.amount_paid, p.payment_method, p.status
            FROM payments p
            JOIN subscriptions s ON p.subscription_id = s.id
            WHERE s.subscriber_id = ?
            ORDER BY p.payment_date DESC
        """;

        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, subscriberId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new PaymentModel(
                        rs.getString("payment_date"),
                        rs.getDouble("amount_paid"),
                        rs.getString("payment_method"),
                        rs.getString("status")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}