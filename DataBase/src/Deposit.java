import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Deposit {
    public static void depositAmount(String accountNumber, double amount) {
        try (Connection connection = DBConnection.getConnection()) {
            // 1. Select current balance
            String query = "SELECT balance FROM user_details WHERE account_number = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, accountNumber);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                double balance = resultSet.getDouble("balance");

                String query2 = "UPDATE user_details SET balance = balance + ? WHERE account_number = ?";
                PreparedStatement preparedStatement1 = connection.prepareStatement(query2);
                preparedStatement1.setDouble(1, amount);
                preparedStatement1.setString(2, accountNumber);

                int row = preparedStatement1.executeUpdate();
                if (row > 0) {
                    System.out.println("✅ Rs. " + amount + " deposited successfully!");
                    System.out.println("💰 New Balance: Rs. " + (balance + amount));
                } else {
                    System.out.println("❌ Failed to update balance.");
                }

            } else {
                System.out.println("❌ Account not found!");
            }

        } catch (SQLException e) {
            System.out.println("🔴 Error: " + e.getMessage());
        }
    }
}
