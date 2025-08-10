package utils;

import java.sql.*;

public class DatabaseStats {

    public static int getTotalSubscribers() {
        String query = "SELECT COUNT(*) FROM subscribers";
        return executeCountQuery(query);
    }

    public static int getActiveSubscriptions() {
        String query = "SELECT COUNT(*) FROM subscriptions WHERE status = 'نشط'";
        return executeCountQuery(query);
    }

    public static double getTotalPayments() {
        String query = "SELECT SUM(amount_paid) FROM payments";
        try (Connection conn = DatabaseUtils.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            return rs.next() ? rs.getDouble(1) : 0.0;

        } catch (SQLException e) {
            e.printStackTrace();
            return 0.0;
        }
    }

    private static int executeCountQuery(String query) {
        try (Connection conn = DatabaseUtils.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            return rs.next() ? rs.getInt(1) : 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
    public static int getExpiredSubscriptions() {
        String query = "SELECT COUNT(*) FROM subscriptions WHERE status = 'منتهي'";
        return executeCountQuery(query);
    }

    public static int getRejectedPayments() {
        String query = "SELECT COUNT(*) FROM payments WHERE status = 'مرفوض'";
        return executeCountQuery(query);
    }
}