package BankSystem;

import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private static final UserManager instance = new UserManager();
    private final List<User> users = new ArrayList<>();

    public static UserManager getinstance(){
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
    public User findUser(String userName, String password){
        for(User user : users){
            if(user.getUsername().equalsIgnoreCase(userName) && user.getPassword().equals(password)){
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

}
