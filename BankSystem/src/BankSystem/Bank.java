package BankSystem;

import java.util.HashMap;
import java.util.Map;

public class Bank {
    private Map<String,User> users = new HashMap<>();
    private User logloggedInUser  = null;

    public boolean register(String username, String password) {
        if (users.containsKey(username)) {
            return false;
        }
        users.put(username, new User(username, password));
        return true;
    }
    public boolean Login(String userName, String password){
        User user = users.get(userName);
        if(users != null && users.equals(password)){
            logloggedInUser = user;
            return true;
        }
        return false;
    }
    public void logout(){
        logloggedInUser = null;
    }
    public boolean transfer(String userName,String password){
        if(Login(userName,password)){

        }
        return false;
    }
}
