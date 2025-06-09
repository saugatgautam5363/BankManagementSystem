import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

public class BankManagementSystem {
    public static void main(String[] args) {
        // Set look and feel to system default
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Start with login screen
        SwingUtilities.invokeLater(() -> {
            BankDatabase.initialize();
            new LoginFrame();
        });
    }
}

// Database to store user information
class BankDatabase {
    private static HashMap<String, User> users = new HashMap<>();

    public static void initialize() {
        // Add a default user for testing
        addUser(new User("admin", "admin123", "Admin User", 1000.0));
    }

    public static void addUser(User user) {
        users.put(user.getUsername(), user);
    }

    public static User getUser(String username) {
        return users.get(username);
    }

    public static boolean validateUser(String username, String password) {
        User user = users.get(username);
        return user != null && user.getPassword().equals(password);
    }

    public static ArrayList<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }
}

// User class to store user information
class User {
    private String username;
    private String password;
    private String fullName;
    private double balance;

    public User(String username, String password, String fullName, double balance) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.balance = balance;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

// Login Frame
class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame() {
        setTitle("Bank Management System - Login");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Title
        JLabel titleLabel = new JLabel("Bank Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(titleLabel, gbc);

        // Username
        JLabel usernameLabel = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(usernameLabel, gbc);

        usernameField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(usernameField, gbc);

        // Password
        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(passwordLabel, gbc);

        passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(passwordField, gbc);

        // Login button
        JButton loginButton = new JButton("Login");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(loginButton, gbc);

        // Register button
        JButton registerButton = new JButton("Register New Account");
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(registerButton, gbc);

        // Add action listeners
        loginButton.addActionListener(e -> login());
        registerButton.addActionListener(e -> openRegisterFrame());

        add(panel);
        setVisible(true);
    }

    private void login() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (BankDatabase.validateUser(username, password)) {
            User user = BankDatabase.getUser(username);
            new DashboardFrame(user);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void openRegisterFrame() {
        new RegisterFrame();
        dispose();
    }
}

// Registration Frame
class RegisterFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JTextField fullNameField;
    private JTextField initialDepositField;

    public RegisterFrame() {
        setTitle("Bank Management System - Register");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Title
        JLabel titleLabel = new JLabel("Register New Account");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(titleLabel, gbc);

        // Full Name
        JLabel fullNameLabel = new JLabel("Full Name:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(fullNameLabel, gbc);

        fullNameField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(fullNameField, gbc);

        // Username
        JLabel usernameLabel = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(usernameLabel, gbc);

        usernameField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(usernameField, gbc);

        // Password
        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(passwordLabel, gbc);

        passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(passwordField, gbc);

        // Confirm Password
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(confirmPasswordLabel, gbc);

        confirmPasswordField = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(confirmPasswordField, gbc);

        // Initial Deposit
        JLabel initialDepositLabel = new JLabel("Initial Deposit:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(initialDepositLabel, gbc);

        initialDepositField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(initialDepositField, gbc);

        // Register button
        JButton registerButton = new JButton("Register");
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(registerButton, gbc);

        // Back to login button
        JButton backButton = new JButton("Back to Login");
        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(backButton, gbc);

        // Add action listeners
        registerButton.addActionListener(e -> register());
        backButton.addActionListener(e -> backToLogin());

        add(panel);
        setVisible(true);
    }

    private void register() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        String fullName = fullNameField.getText();
        String initialDepositStr = initialDepositField.getText();

        // Validate inputs
        if (username.isEmpty() || password.isEmpty() || fullName.isEmpty() || initialDepositStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required", "Registration Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match", "Registration Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double initialDeposit;
        try {
            initialDeposit = Double.parseDouble(initialDepositStr);
            if (initialDeposit < 0) {
                JOptionPane.showMessageDialog(this, "Initial deposit cannot be negative", "Registration Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Initial deposit must be a valid number", "Registration Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check if username already exists
        if (BankDatabase.getUser(username) != null) {
            JOptionPane.showMessageDialog(this, "Username already exists", "Registration Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Create new user
        User newUser = new User(username, password, fullName, initialDeposit);
        BankDatabase.addUser(newUser);

        JOptionPane.showMessageDialog(this, "Registration successful! You can now login.", "Registration Success", JOptionPane.INFORMATION_MESSAGE);
        backToLogin();
    }

    private void backToLogin() {
        new LoginFrame();
        dispose();
    }
}

// Dashboard Frame
class DashboardFrame extends JFrame {
    private User currentUser;
    private JLabel balanceLabel;

    public DashboardFrame(User user) {
        this.currentUser = user;

        setTitle("Bank Management System - Dashboard");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        // Account tab
        JPanel accountPanel = createAccountPanel();
        tabbedPane.addTab("Account", accountPanel);

        // Deposit tab
        JPanel depositPanel = createDepositPanel();
        tabbedPane.addTab("Deposit", depositPanel);

        // Withdraw tab
        JPanel withdrawPanel = createWithdrawPanel();
        tabbedPane.addTab("Withdraw", withdrawPanel);

        // All Users tab (only for admin)
        if (currentUser.getUsername().equals("admin")) {
            JPanel usersPanel = createUsersPanel();
            tabbedPane.addTab("All Users", usersPanel);
        }

        add(tabbedPane);

        // Logout button at the bottom
        JPanel bottomPanel = new JPanel();
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> logout());
        bottomPanel.add(logoutButton);

        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel createAccountPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Welcome message
        JLabel welcomeLabel = new JLabel("Welcome, " + currentUser.getFullName() + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(welcomeLabel, gbc);

        // Account details
        JLabel usernameLabel = new JLabel("Username: " + currentUser.getUsername());
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(usernameLabel, gbc);

        balanceLabel = new JLabel("Current Balance: $" + String.format("%.2f", currentUser.getBalance()));
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(balanceLabel, gbc);

        return panel;
    }

    private JPanel createDepositPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Deposit Money");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        JLabel amountLabel = new JLabel("Amount to Deposit:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(amountLabel, gbc);

        JTextField amountField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(amountField, gbc);

        JButton depositButton = new JButton("Deposit");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(depositButton, gbc);

        depositButton.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText());
                if (amount <= 0) {
                    JOptionPane.showMessageDialog(this, "Please enter a positive amount", "Deposit Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                currentUser.deposit(amount);
                updateBalanceLabel();
                JOptionPane.showMessageDialog(this, "Successfully deposited $" + String.format("%.2f", amount), "Deposit Success", JOptionPane.INFORMATION_MESSAGE);
                amountField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid amount", "Deposit Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }

    private JPanel createWithdrawPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Withdraw Money");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        JLabel amountLabel = new JLabel("Amount to Withdraw:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(amountLabel, gbc);

        JTextField amountField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(amountField, gbc);

        JButton withdrawButton = new JButton("Withdraw");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(withdrawButton, gbc);

        withdrawButton.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText());
                if (amount <= 0) {
                    JOptionPane.showMessageDialog(this, "Please enter a positive amount", "Withdraw Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (currentUser.withdraw(amount)) {
                    updateBalanceLabel();
                    JOptionPane.showMessageDialog(this, "Successfully withdrew $" + String.format("%.2f", amount), "Withdraw Success", JOptionPane.INFORMATION_MESSAGE);
                    amountField.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Insufficient balance", "Withdraw Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid amount", "Withdraw Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }

    private JPanel createUsersPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("All Users");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(titleLabel, BorderLayout.NORTH);

        // Create table model
        String[] columnNames = {"Username", "Full Name", "Balance"};
        ArrayList<User> users = BankDatabase.getAllUsers();
        Object[][] data = new Object[users.size()][3];

        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            data[i][0] = user.getUsername();
            data[i][1] = user.getFullName();
            data[i][2] = String.format("$%.2f", user.getBalance());
        }

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private void updateBalanceLabel() {
        balanceLabel.setText("Current Balance: $" + String.format("%.2f", currentUser.getBalance()));
    }

    private void logout() {
        new LoginFrame();
        dispose();
    }
}