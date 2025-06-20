import BankSystem.User;
import Database.*;
import LoginandRegister.LoginRegister;
import Services.BankServicesImp;
import Services.BanksServices;
import BankSystem.*;
import Services.UserDetails;
import java.sql.SQLOutput;
import java.util.Scanner;
import Services.BankServicesImp;


public  class BankMainClass {
    static String loginUser = null;
    static BanksServices bank = new BankServicesImp();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserDetails user = new UserDetails();
        while (true) {
            System.out.println("""
                    \n===== BANK SYSTEM =====
                    1. Register
                    2. Login
                    3. Exit
                    """);

            System.out.print("Enter your choice: ");
            int choice;

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Please enter a valid number!");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter your full name: ");
                    String name = scanner.nextLine().trim();

                    System.out.print("Enter your username: ");
                    String userName = scanner.nextLine().trim();

                    System.out.print("Enter your password: ");
                    String password = scanner.nextLine().trim();

                    System.out.print("Enter the Account Number: ");
                    String accountNumber = scanner.nextLine().trim();

                    User newuser = new User(name,userName,password,accountNumber);
                    user.userDetails(newuser);
                    LoginRegister.register(name, userName, password,accountNumber);
                    bank.Adduser(newuser);
                    Register.register(name,userName,password,accountNumber);
                    System.out.println("✅ Registered successfully!");
                }

                case 2 -> {
                    System.out.print("Enter your username: ");
                    String userNameToLogin = scanner.nextLine().trim();

                    System.out.print("Enter your password: ");
                    String passwordToLogin = scanner.nextLine().trim();

                    if (userNameToLogin.isEmpty() || passwordToLogin.isEmpty()) {
                        System.out.println("❌ Username and password cannot be empty.");
                        break;
                    }

                    boolean isLoggedIn = Login.authenticate(userNameToLogin, passwordToLogin);

                    if (isLoggedIn) {
                        loginUser = userNameToLogin;
                        System.out.println("✅ Login successful!");
                        System.out.println("Logged in user: " + loginUser);
                        dashboard();
                    } else {
                        loginUser = null;
                        System.out.println("❌ Invalid username or password.");
                    }
                }


                case 3 -> {
                    System.out.println("Thank you for using the Bank System. tanks santosh!");
                    System.exit(0);
                }
                default -> System.out.println("❌ Invalid choice. Please select 1, 2, or 3.");
            }
        }
    }

    static void dashboard() {
        User user = new User();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("""
                    \n===== DASHBOARD =====
                    1. Display User Details
                    2. Deposit Amount
                    3. Withdraw Amount
                    4. Logout
                    """);

            System.out.print("Enter your choice: ");
            int choice;

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // consume newline
            } else {
                System.out.println("Please enter a valid number!");
                scanner.nextLine(); // clear input
                continue;
            }

            switch (choice) {
                case 1 -> {

                    System.out.println("Enter the user name: ");
                    String username = scanner.nextLine();
                    DisplayUser.displayuser(username);
//                    BankServicesImp.displayDetails(username);
                }

                case 2 -> {
                    System.out.print("Enter deposit amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();
                    Deposit.depositAmount(loginUser, amount);
                    bank.depositAmount(amount);
                }

                case 3 -> {
//                    System.out.print("Enter the account number: ");
//                    String accountNumber = scanner.nextLine();
//
//                    System.out.print("Enter the user Name: ");
//                    String userName = scanner.nextLine();

                    System.out.print("Enter withdraw amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();
                    Withdraw.withdraw(loginUser,amount);
                    bank.Withdraw(amount);
                }

                case 4 -> {
                    System.out.println("✅ Logged out successfully!");
                    loginUser = null;
                    return;
                }

                default -> System.out.println("❌ Invalid choice!");
            }
        }
    }
}