package BankSystem;

import java.io.File;
import java.io.IOException;

public class FileCreate {
    public static void main(String[] args) {
        try {

            File file = new File("userdetails.txt");
            file.createNewFile();
        }catch (IOException e){
            System.out.println("Message: "+e.getMessage());
        }
    }
}
