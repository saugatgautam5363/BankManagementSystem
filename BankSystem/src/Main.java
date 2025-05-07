import BankSystem.*;
import Services.BankServicesImp;
import Services.BanksServices;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BanksServices bank = new BankServicesImp();
        boolean quits = true;
        while(quits){
            System.out.println("""
                    1. Deposit Amount:
                    2. Withdraw Amount:
                    """);
            System.out.println("Enter your Choices: ");
            int choices = scanner.nextInt();
            switch (choices){
                case 1:
                    System.out.print("Enter Your Account number: ");
                    String accountNumber = scanner.nextLine();
                    System.out.print("Enter Your UserName: ");
                    String userName = scanner.nextLine();
                    System.out.print("Enter Your Password: ");
                    String password = scanner.nextLine();
                    double amount = scanner.nextDouble();
                    bank.depositAmount(accountNumber,userName,amount);
            }
        }
    }
}