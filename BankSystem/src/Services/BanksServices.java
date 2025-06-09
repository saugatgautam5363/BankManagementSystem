package Services;

import BankSystem.User;
import LoginandRegister.LoginRegister;

import java.util.List;

public interface BanksServices {
    void Adduser(User user);
    void Login(LoginRegister loginRegister);

    void register(LoginRegister loginRegister);

     void depositAmount(double amount);



    void Withdraw(double amount);

     void displayDetails(String username) ;
}
