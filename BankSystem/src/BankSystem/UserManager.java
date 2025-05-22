package BankSystem;

import java.util.ArrayList;
import java.util.List;
public class UserManager {
    private static UserManager instance = new UserManager();
    private final List<User> users = new ArrayList<>();

    public static UserManager getinstance(){
        if(instance == null){
            instance = new UserManager();
        }
        return instance;
    }
    public void addUser(User user){
        if(user != null) {
            users.add(user);
        }
    }
    public List<User> getAllUser(){
        return users;
    }
    public User findUser(String userName, String accountNumber){
        for(User user : users){
            if(user.getUsername().equalsIgnoreCase(userName) && user.getAccountNumber().equals(accountNumber)){
                return user;
            }
        }

        return null;
    }
    public User findUsersName(String username) {
        for (User user : users) {
            String existingUsername = user.getUsername();
            if (existingUsername != null && existingUsername.equalsIgnoreCase(username)) {
                return user;
            }
        }
        return null;
    }
    public void userDetails(User user) {
        if (user == null) {
            System.out.println("❌ User is null. Cannot display details.");
            return;
        }

        System.out.println("========== USER DETAILS ==========");
        System.out.println("Full Name     : " + user.getName());
        System.out.println("Username      : " + user.getUsername());
        System.out.println("Account Number: " + user.getAccountNumber());
        System.out.println("Password      : " + user.getPassword());
        System.out.printf("Balance       : %.2f%n", user.getBalance());
        System.out.println("==================================");
    }


}
