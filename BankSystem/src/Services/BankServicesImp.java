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
        loginRegister.register(name,userName,password,accountNumber);
    }

    @Override
    public void UserAccount(User user, String userName, String password) {
        if(user.getPassword().equals(password) && user.getUsername().equals(userName)){
            user.displayUserAccountDetails();
        }else {
            System.out.println("Not show the user details. try Again!!!");
        }
    }

    @Override
    public void depositAmount(String accountNumber, String userName, double amount) {
        // Validate input parameters
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

        // Find the user using account number and username
        User user = UserManager.getinstance().findUser(userName, accountNumber);

        // Check if the user is found
        if (user != null) {
            // Perform the deposit operation
            user.setBalance(user.getBalance() + amount);
            System.out.println("Deposit successful!");
            System.out.println("New Balance: " + user.getBalance());
        } else {
            // User not found or mismatched information
            System.out.println("User not found or the information provided doesn't match.");
        }
    }


    @Override
    public void Withdraw(String accountNumber, String userName, double amount) {
        for(User user: users){
            if(user.getUsername().equals(userName) && user.getAccountNumber().equals(accountNumber) && user.getBalance() == amount){
                if(amount >0 && amount<=user.getBalance()){
                    user.setBalance(user.getBalance()-amount);
                    System.out.println("Amount Withdraw Successfully!!");
                    System.out.println("New Balance: "+user.getBalance());
                }else{
                    System.out.println("Invalid Amount!!");
                }
                return;
            }
        }
        System.out.println("Not Match the user Information!!");
    }

    @Override
   public void displayDetails(String loginUser) {
//      System.out.println("Balance: "+ );
//      System.out.println("Account Number: "+);
//       System.out.println("userName: "+getUsername());
    }

}
