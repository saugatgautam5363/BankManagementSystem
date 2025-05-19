package FileHandling;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class UserDetails {
    public static void userBankDetails(){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("userdetails.txt"))) {
            // Write user details
            bufferedWriter.write("Username: johndoe");
            bufferedWriter.newLine();
            bufferedWriter.write("Email: johndoe@example.com");
            bufferedWriter.newLine();
            bufferedWriter.write("Phone: 123-456-7890");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
