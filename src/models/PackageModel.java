package models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import utils.DatabaseUtils;

public class PackageModel {

    public static List<String> getAllPackageNames() {
        List<String> packages = new ArrayList<>();
        String query = "SELECT name FROM packages";

        try (Connection conn = DatabaseUtils.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                packages.add(rs.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return packages;
    }
}
