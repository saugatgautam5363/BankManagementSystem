package Services;

import BankSystem.User;
import LoginandRegister.LoginRegister;
import LoginandRegister.Users;

import java.util.List;

public interface BanksServices {
    void Login(LoginRegister loginRegister);

    void register(LoginRegister loginRegister);

    void UserAccount(User user, String accountNumber, String userName);

    void depositAmount(String accountNumber, String userName, double amount);
    void Withdraw(String accountNumber, String userName,double amount);

}
