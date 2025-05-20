import java.sql.Connection;
import java.sql.SQLException;

public class Deposit {
    public static void depositAmount(String accountNumber, double amount) {
        try(Connection connection = DBConnection.getConnection()){
            String query = "SELECT FROM user_details WHERE account_number = ?";
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
