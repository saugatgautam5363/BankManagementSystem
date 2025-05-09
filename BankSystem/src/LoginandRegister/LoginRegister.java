package LoginandRegister;

import java.util.ArrayList;
import java.util.List;
import java.util.SequencedCollection;

public  class LoginRegister {
    static List<Users> users = new ArrayList<>();
    public static boolean register(String name, String userName, String password,String accountNumber) {
        Users newUser = new Users(name, userName, password,accountNumber);
        users.add(newUser);
        System.out.println("Registered Successfully!");
        return true;
    }


    public static boolean login(String userName, String password) {
        boolean found = false;
        for (Users user : users) {
            if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
                System.out.println("Login successful. Welcome, " + user.getName() + "!");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Login failed. Invalid username or password.");
        }
        return found;
    }
}
