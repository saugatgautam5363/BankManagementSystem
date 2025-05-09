package Services;
import BankSystem.User;
import LoginandRegister.LoginRegister;
import LoginandRegister.Users;

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
    public void depositAmount(String accountNumber, String userName,double amount) {
        boolean found = false;
        for (User user : users){
            if(user.getAccountNumber().equals(accountNumber)){
                if(amount>0){
                    user.setBalance(user.getBalance()+amount);
                    System.out.println("Balance deposit Successfully!!");
                    System.out.println("New Balance: "+user.getBalance());
                    found = true;
                }else {
                    System.out.println("Invalid Amount!!");
                }
            }
        }
        if(!found){
        System.out.println("Not Match the user Information!!");
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
//        System.out.println("Balance: "+);
//        System.out.println("Account Number: "+);
//        System.out.println("userName: "+getUsername());
    }

}
