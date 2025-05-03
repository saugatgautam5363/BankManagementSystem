package LoginandRegister;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        LoginRegister lr = new LoginRegister();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("""
                    \n1. Register
                    2. Login
                    3. Exit
                    """);
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter your Username: ");
                    String username = scanner.nextLine();

                    System.out.print("Enter your Password: ");
                    String password = scanner.nextLine();

                    lr.register(name, username, password);
                }
                case 2 -> {
                    System.out.print("Enter your Username: ");
                    String loginUsername = scanner.nextLine();

                    System.out.print("Enter your Password: ");
                    String loginPassword = scanner.nextLine();

                    lr.login(loginUsername, loginPassword);
                }
                case 3 -> {
                    System.out.println("Exiting the program. Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid choice!! Please enter 1, 2, or 3.");
            }
        }

        scanner.close();
    }
}
