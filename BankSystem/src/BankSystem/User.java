package BankSystem;

public class User {
    String name;
    String username;
    String password;
    String accountNumber;
    double balance;

    public User(String name,String username, String password,String accountNumber) {
        this.username = username;
        this.password = password;
        this.accountNumber = accountNumber;
        this.balance = 0.0;
        this.name = name;
    }

    public User() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void displayUserAccountDetails(){
        System.out.println("Balance: "+getBalance());
        System.out.println("Account Number: "+getAccountNumber());
        System.out.println("userName: "+getUsername());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
