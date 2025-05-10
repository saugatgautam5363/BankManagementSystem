package Services;

import BankSystem.User;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class UserDetails {
    public void userDetails(User users){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("userdetails.txt",true))){
            bw.write(users.toString());
            bw.newLine();
        }catch (IOException e){
            System.out.println("Message: "+e.getMessage());
        }
    }


}
