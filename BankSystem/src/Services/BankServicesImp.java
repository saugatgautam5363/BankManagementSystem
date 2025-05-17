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

    @Override
    public void Adduser(User user) {
        for(User user1 : users){
            if(user1.getAccountNumber().equals(user.getAccountNumber())){
                System.out.println("A user with this account number already exists!");
                return;
            }
            users.add(user1);
            System.out.println("User add Successfully!!");
        }
    }

    @Override
    public void Login(LoginRegister loginRegister) {
    }

    @Override
    public void register(LoginRegister loginRegister) {
        System.out.println("Enter the User Name: ");
        String name = scanner.nextLine();
        System.out.println("Enter the userName: ");
        String userName = scanner.nextLine();
        System.out.println("Enter the Password: ");
        String password = scanner.nextLine();
        System.out.print("Enter the account Number: ");
        String accountNumber = scanner.nextLine();
        loginRegister.register(name, userName, password, accountNumber);
    }

    @Override
    public void depositAmount(String accountNumber, String userName, double amount) {
        if (accountNumber == null || accountNumber.trim().isEmpty()) {
            System.out.println("Invalid account number.");
            return;
        }
        if (userName == null || userName.trim().isEmpty()) {
            System.out.println("Invalid username.");
            return;
        }
        if (amount <= 0) {
            System.out.println("Invalid amount! Deposit amount must be greater than zero.");
            return;
        }

        User user = UserManager.getinstance().findUser(userName.trim(), accountNumber.trim());

        if (user != null) {
            synchronized (user) {
                user.setBalance(user.getBalance() + amount);
            }
            System.out.println("Deposit successful!");
            System.out.printf("New Balance: %.2f%n", user.getBalance());
        } else {
            System.out.println("User not found or the information provided doesn't match.");
        }
    }


    @Override
    public void Withdraw(String accountNumber, String userName, double amount) {
        if (accountNumber == null || accountNumber.trim().isEmpty()) {
            System.out.println("Invalid account number.");
            return;
        }

        if (userName == null || userName.trim().isEmpty()) {
            System.out.println("Invalid username.");
            return;
        }

        if (amount <= 0) {
            System.out.println("Invalid amount! Withdrawal amount must be greater than zero.");
            return;
        }

        User user = UserManager.getinstance().findUser(userName.trim(), accountNumber.trim());

        if (user != null) {
            synchronized (user) {
                if (amount > user.getBalance()) {
                    System.out.println("Insufficient balance!");
                    return;
                }
                user.setBalance(user.getBalance() - amount);
                System.out.println("Withdrawal successful!");
                System.out.printf("New Balance: %.2f%n", user.getBalance());
            }
        } else {
            System.out.println("User not found or the information provided doesn't match.");
        }
    }
    @Override
    public void displayDetails(String username, String password, String accountNumber) {
        for (User user : users) {

            if (user != null && user.getUsername().equalsIgnoreCase(username) && user.getPassword().equals(password) && user.getAccountNumber().equals(accountNumber)) {
                System.out.println("UserName: " + user.getUsername());
                System.out.println("Password: " + user.getPassword());
                System.out.println("Balance: " + user.getBalance());
                return;
            } else {
                System.out.println("User is not Found!!");
            }
        }

    }
}

