import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    public static boolean login(String userName, String password) {
        try (Connection connection = DBConnection.getConnection()) {
            if (connection == null) {
                System.out.println("❌ DB connection is null");
                return false;
            }
            System.out.println("✅ DB Connected");

            String query = "SELECT * FROM user_details WHERE LOWER(TRIM(user_name)) = LOWER(TRIM(?)) AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userName.trim());
            preparedStatement.setString(2, password.trim());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                System.out.println("✅ Login successful from database");
                return true;
            } else {
                System.out.println("❌ Invalid username or password (from DB)");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Login Error: " + e.getMessage());
            return false;
        }
    }
}
