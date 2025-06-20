
package LoginandRegister;
import BankSystem.User;
import BankSystem.UserManager;
import Database.Login;

public class LoginRegister {
    private String username;
    private String password;
    private String name;
    private String accountNumber;
    private boolean authenticated = false;

    public static boolean register(String name, String userName, String password, String accountNumber) {
        User user = new User(name, userName, password, accountNumber); // ✅ Using correct User type
        UserManager.getinstance().addUser(user);                        // ✅ No type mismatch
        System.out.println("✅ Registered Successfully!");
        return true;
    }


    public static User login(String userName, String password) {
        User user = UserManager.getinstance().findUsersName(userName);

        if (user != null && user.getPassword() != null || Login.authenticate(userName,password)) {
            if (user != null ? user.getPassword().equals(password) : false) {
                System.out.println("✅ Login successful. Welcome, " + user.getUsername());
            }
        }else {
            System.out.println("❌ Login failed. Invalid credentials.");
        }
        return user;
    }
    public void setInput(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public boolean isAuthenticated() {
        return authenticated;
    }
    public void setRegisterInput(String name, String username, String password, String accountNumber) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.accountNumber = accountNumber;
    }
}