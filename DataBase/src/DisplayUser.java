import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DisplayUser {
    public static void displayuser(String username){
        try(Connection connection = DBConnection.getConnection()){
            String query = "SELECT * FROM user_details WHERE user_name";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean found = false;
            if(resultSet.next()){
                found = true;
                System.out.println("Name: "+resultSet.getString("name"));
                System.out.println("userName: "+resultSet.getString("uer_name"));
                System.out.println("Account Number: "+resultSet.getString("account_number"));
                System.out.println("Balance: "+resultSet.getString("balance"));
                if(!found){
                    System.out.println("No user name found with user name "+username);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
