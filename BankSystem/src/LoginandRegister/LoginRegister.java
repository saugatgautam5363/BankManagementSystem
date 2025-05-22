package LoginandRegister;

import BankSystem.User;
import BankSystem.UserManager;

public class LoginRegister {

    public static boolean register(String name, String userName, String password, String accountNumber) {
        User user = new User(name, userName, password, accountNumber); // ✅ Using correct User type
        UserManager.getinstance().addUser(user);                        // ✅ No type mismatch
        System.out.println("✅ Registered Successfully!");
        return true;
    }

        public static User login(String userName, String password) {
            User user = UserManager.getinstance().findUsersName(userName);

            if (user != null && user.getPassword() != null) {
                if (user.getPassword().equals(password)) {
                    System.out.println("✅ Login successful. Welcome, " + user.getUsername());
                    return true;
                }
            }

            System.out.println("❌ Login failed. Invalid credentials.");
            return false;
        }


}
