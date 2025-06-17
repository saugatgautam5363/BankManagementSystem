package Database;

import BankSystem.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    private static String loggedInUser = null;

    public static boolean authenticate(String username, String password) {
        // authentication logic from DB
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT * FROM user_details WHERE user_name = ? AND password = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                loggedInUser = password;
                System.out.println("✅ Login successful!!!!");
                return true;
            } else {
                System.out.println("❌ Invalid username or password....");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("🔴 Login error: " + e.getMessage());
            return false;
        }
    }

    public static String getLoggedInUser() {
        return loggedInUser;
    }

    public static void logout() {
        loggedInUser = null;
        System.out.println("👋 Logged out.");
    }


}