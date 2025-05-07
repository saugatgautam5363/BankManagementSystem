import BankSystem.User;
import LoginandRegister.LoginRegister;
import Services.BankServicesImp;
import Services.BanksServices;

import java.util.Scanner;

public class BankMainClass {
       static String loginUser = null;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("""
                    \n===== BANK SYSTEM =====
                    1. Register
                    2. Login
                    3. Exit
                    """);

            System.out.print("Enter your choice: ");
            int choice;

            // Validate integer input
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline left by nextInt()
            } else {
                System.out.println("Please enter a valid number!");
                scanner.nextLine(); // Clear invalid input
                continue;
            }

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter your full name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter your username: ");
                    String userName = scanner.nextLine();

                    System.out.print("Enter your password: ");
                    String password = scanner.nextLine();

                    LoginRegister.register(name, userName, password);
                    System.out.println("✅ Registered successfully!");
                }

                case 2 -> {
                    System.out.print("Enter your username: ");
                    String userNameToLogin = scanner.nextLine();

                    System.out.print("Enter your password: ");
                    String passwordToLogin = scanner.nextLine();
                    loginUser = userNameToLogin;

                    boolean isLoggedIn = LoginRegister.login(userNameToLogin, passwordToLogin);
                    if (isLoggedIn) {
                        System.out.println("✅ Login successful!");
                        // You can call dashboard or bank services here
                    } else {
                        System.out.println("❌ Invalid username or password.");
                    }
                }

                case 3 -> {
                    System.out.println("Thank you for using the Bank System. Goodbye!");
                    System.exit(0);
                }

                default -> System.out.println("❌ Invalid choice. Please select 1, 2, or 3.");
            }
        }
    }
    static void dashboard () {
        BanksServices bank = new BankServicesImp();
        Scanner scanner = new Scanner(System.in);
        while (loginUser != null){
            System.out.println("""
                    1.display the user details
                    2.deposit Amount
                    3.withdraw Amount
                    4.exit the bank
                    """);
            System.out.print("Enter your choices: ");
            int choices = scanner.nextInt();
            switch (choices){
                case 1:

                    break;
                case 2:
                    System.out.print("Enter the Account Number: ");
                    String accountNumber = scanner.nextLine();
                    System.out.println("Enter the userName: ");
                    String userName = scanner.nextLine();
                    System.out.println("Enter the deposit Balance: ");
                    double amount = scanner.nextDouble();
                    bank.depositAmount(accountNumber,userName,amount);
                    break;
                case 3:
                    System.out.println("Enter the account number: ");
                    String accountNumberToWithdraw = scanner.nextLine();
                    System.out.println("Enter the userName: ");
                    String userNameToWithdraw = scanner.nextLine();
                    System.out.println("Enter the withdraw amount: ");
                    double amountToWitdraw = scanner.nextDouble();
                    bank.Withdraw(accountNumberToWithdraw,userNameToWithdraw,amountToWitdraw);
            }
        }
    }

}