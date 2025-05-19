import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConnection {
    private static final String url = "jdbc:mysql://localhost:3306/bankmanagement_system";
    private static final String username = "root";
    private static final String password = "admin";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // optional in JDBC 4+, but good to include
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Connection error: " + e.getMessage());
        }
        return null;
    }
}
