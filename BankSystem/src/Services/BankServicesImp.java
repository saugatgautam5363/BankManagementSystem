package Services;

import BankSystem.User;
import BankSystem.UserManager;
import LoginandRegister.LoginRegister;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class BankServicesImp implements BanksServices {
    Scanner scanner = new Scanner(System.in);
    List<User> users = new ArrayList<>();
    private static User currentLoggedInUser = null;

    @Override
    public void Adduser(User user) {
        for (User existingUser : users) {
            if (existingUser.getAccountNumber().equals(user.getAccountNumber())) {
                System.out.println("A user with this account number already exists!");
                return;
            }
        }

        users.add(user);
        System.out.println("User added successfully!");
    }

    @Override
    public void Login(LoginRegister loginRegister) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        currentLoggedInUser = loginRegister.login(username, password);

        if (currentLoggedInUser != null) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Login failed. Invalid username or password.");
        }
    }

    @Override
    public void register(LoginRegister loginRegister) {
        System.out.println("Enter full name: ");
        String name = scanner.nextLine();
        System.out.println("Enter username: ");
        String userName = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();

        loginRegister.register(name, userName, password, accountNumber);
    }

    @Override
    public void depositAmount(double amount) {
        if (currentLoggedInUser == null) {
            System.out.println("Please login first!");
            return;
        }

        if (amount <= 0) {
            System.out.println("Invalid amount! Deposit amount must be greater than zero.");
            return;
        }

        synchronized (currentLoggedInUser) {
            currentLoggedInUser.setBalance(currentLoggedInUser.getBalance() + amount);
        }

        System.out.println("Deposit successful!");
        System.out.printf("New Balance: %.2f%n", currentLoggedInUser.getBalance());
    }

    @Override
    public void Withdraw(double amount) {
        if (currentLoggedInUser == null) {
            System.out.println("Please login first!");
            return;
        }

//        // Ensure the logged-in user is withdrawing from their own account
//        if (!currentLoggedInUser.getAccountNumber().equals(accountNumber.trim()) ||
//                !currentLoggedInUser.getUsername().equalsIgnoreCase(userName.trim())) {
//            System.out.println("Unauthorized access! You can only withdraw from your own account.");
//            return;
//        }

        if (amount <= 0) {
            System.out.println("Invalid amount! Withdrawal amount must be greater than zero.");
            return;
        }

        synchronized (currentLoggedInUser) {
            if (amount > currentLoggedInUser.getBalance()) {
                System.out.println("Insufficient balance!");
            } else {
                currentLoggedInUser.setBalance(currentLoggedInUser.getBalance() - amount);
                System.out.println("Withdrawal successful!");
                System.out.printf("New Balance: %.2f%n", currentLoggedInUser.getBalance());
            }
        }
    }

    public void displayDetails(String username) {
        if (currentLoggedInUser == null) {
            System.out.println("Please login first to view account details.");
            return;
        }

//        if (!currentLoggedInUser.getUsername().equalsIgnoreCase(username.trim()) ||
//                !currentLoggedInUser.getPassword().equals(password) ||
//                !currentLoggedInUser.getAccountNumber().equals(accountNumber.trim())) {
//            System.out.println("The provided information does not match the logged-in user.");
//            return;
//        }

        System.out.println("User Details:");
        System.out.println("Username: " + currentLoggedInUser.getUsername());
        System.out.println("Account Number: " + currentLoggedInUser.getAccountNumber());
        System.out.printf("Balance: %.2f%n", currentLoggedInUser.getBalance());
    }
}
