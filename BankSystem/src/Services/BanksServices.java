package Services;

import BankSystem.User;
import LoginandRegister.LoginRegister;

import java.util.List;

public interface BanksServices {
    void Adduser(User user);
    void Login(LoginRegister loginRegister);

    void register(LoginRegister loginRegister);

    void depositAmount(String accountNumber, String userName, double amount);
    void Withdraw(String accountNumber, String userName,double amount);

    void displayDetails(String username,String password,String accountNumber);
}
