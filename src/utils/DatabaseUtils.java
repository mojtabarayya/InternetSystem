package utils;

import java.sql.*;
import java.time.LocalDate;

public class DatabaseUtils {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/Internet_System";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "abc###123";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }

    public static boolean insertSubscriber(String name, String phone, String address, String packageName) {
        String query = "INSERT INTO subscribers (name, phone, address, package_name) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, name);
            pstmt.setString(2, phone);
            pstmt.setString(3, address);
            pstmt.setString(4, packageName);

            int rows = pstmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean insertSubscription(String subscriberId, String month, String year) {
        String query = "INSERT INTO subscriptions (subscriber_id, month, year) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, Integer.parseInt(subscriberId));
            pstmt.setString(2, month);
            pstmt.setString(3, year);

            int rows = pstmt.executeUpdate();
            return rows > 0;

        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean insertPayment(int subscriptionId, double amount, LocalDate date) {
        String query = "INSERT INTO payments (subscription_id, amount, payment_date) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, subscriptionId);
            pstmt.setDouble(2, amount);
            pstmt.setDate(3, java.sql.Date.valueOf(date));

            int rows = pstmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}