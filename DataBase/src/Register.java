import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Register {
    public static void register(String name, String userName, String password, String accountNumber) {
        try (Connection connection = DBConnection.getConnection()) {

            if (connection == null) {
                System.out.println("❌ Cannot register: Database connection failed.");
                return;
            }

            String query = "INSERT INTO user_details (name, user_name, password, account_number) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, userName);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, accountNumber);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("✅ User registered successfully!");
            }

        } catch (SQLException e) {
            System.out.println("❌ SQL Error: " + e.getMessage());
        }
    }
}
