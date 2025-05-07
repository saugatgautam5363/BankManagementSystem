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
        loginRegister.register(name,userName,password);
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
        for (User user : users){
            if(user.getUsername().equals(userName)&&user.getBalance() == amount){
                if(amount>0){
                    user.setBalance(user.getBalance()+amount);
                    System.out.println("Balance deposit Successfully!!");
                    System.out.println("New Balance: "+user.getBalance());
                }else {
                    System.out.println("Invalid Amount!!");
                }
            return;
            }
        }
        System.out.println("Not Match the user Information!!");
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
}
