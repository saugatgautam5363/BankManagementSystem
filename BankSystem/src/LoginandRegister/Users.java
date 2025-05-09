package LoginandRegister;

public class Users {
    private String name;
    private String userName;
    private String password;
    private String accountNumber;

    public Users(String name, String userName, String password, String accountNumber) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.accountNumber = accountNumber;
    }

    public String getName() {
        return name;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    @Override
    public String toString() {
        return "Name: "+name+","+
                "UserName: "+userName+","+
                "Password: "+password+","+
                "Account Number: "+accountNumber;
    }
}
