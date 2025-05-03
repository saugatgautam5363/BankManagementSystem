package LoginandRegister;

import java.util.ArrayList;
import java.util.List;

public class LoginRegister {
    List<Users> users = new ArrayList<>();

    public List<Users> register(String name, String userName, String password) {
        Users newUser = new Users(name, userName, password);
        users.add(newUser);
        System.out.println("Registered Successfully!");
        return users;
    }

    public void login(String userName, String password) {
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
    }
}
