package Services;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import BankSystem.User;
import LoginandRegister.LoginRegister;

import static Services.BankServicesImp.currentLoggedInUser;

public class BankUI extends JFrame {
    private BankServicesImp bankService = new BankServicesImp();
    private LoginRegister loginRegister = new LoginRegister();

    private CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel = new JPanel(cardLayout);

    public BankUI() {
        setTitle("💰 Modern Bank Management System");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        // Panels
        mainPanel.add(createLoginPanel(), "login");
        mainPanel.add(createRegisterPanel(), "register");
        mainPanel.add(createDashboardPanel(), "dashboard");

        add(mainPanel);
        cardLayout.show(mainPanel, "login");

        setVisible(true);
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("🔐 Login to Your Bank Account");
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        gbc.gridwidth = 2;
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(title, gbc);

        JTextField usernameField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);
        JButton loginBtn = new JButton("Login");
        JButton switchToRegister = new JButton("Create Account");

        gbc.gridwidth = 1;

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        panel.add(usernameField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(loginBtn, gbc);
        gbc.gridx = 1;
        panel.add(switchToRegister, gbc);

        loginBtn.addActionListener(e -> {
            String user = usernameField.getText().trim();
            String pass = new String(passwordField.getPassword()).trim();

            if (user.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required!");
                return;
            }

            bankService.Login(loginRegister, user, pass);
            if (bankService.isLoggedIn()) {
                JOptionPane.showMessageDialog(this, "Welcome back, " + user + "!");
                cardLayout.show(mainPanel, "dashboard");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password.");
            }
        });

        switchToRegister.addActionListener(e -> cardLayout.show(mainPanel, "register"));
        return panel;
    }

    private JPanel createRegisterPanel() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        JTextField nameField = new JTextField();
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JTextField accountNumField = new JTextField();
        JButton registerBtn = new JButton("Register");
        JButton backBtn = new JButton("← Back");

        panel.add(new JLabel("Full Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(new JLabel("Account Number:"));
        panel.add(accountNumField);
        panel.add(registerBtn);
        panel.add(backBtn);

        registerBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            String user = usernameField.getText().trim();
            String pass = new String(passwordField.getPassword()).trim();
            String acc = accountNumField.getText().trim();

            if (name.isEmpty() || user.isEmpty() || pass.isEmpty() || acc.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields!");
                return;
            }

            loginRegister.register(name, user, pass, acc);
            JOptionPane.showMessageDialog(this, "Account created successfully.");
            cardLayout.show(mainPanel, "login");
        });

        backBtn.addActionListener(e -> cardLayout.show(mainPanel, "login"));
        return panel;
    }

    private JPanel createDashboardPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        JLabel welcomeLabel = new JLabel("🏦 Welcome to Your Dashboard");
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton depositBtn = new JButton("➕ Deposit");
        JButton withdrawBtn = new JButton("➖ Withdraw");
        JButton viewBtn = new JButton("👁 View Account Details");
        JButton logoutBtn = new JButton("🔓 Logout");

        depositBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        withdrawBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoutBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        depositBtn.addActionListener(e -> {
            String amt = JOptionPane.showInputDialog(this, "Enter amount to deposit:");
            if (amt != null) {
                try {
                    double amount = Double.parseDouble(amt);
                    bankService.depositAmount(amount);
//                    Deposit deposit
//                    User user =
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Invalid amount.");
                }
            }
        });

        withdrawBtn.addActionListener(e -> {
            String amt = JOptionPane.showInputDialog(this, "Enter amount to withdraw:");
            if (amt != null) {
                try {
                    double amount = Double.parseDouble(amt);
                    bankService.Withdraw(amount);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Invalid amount.");
                }
            }
        });

        viewBtn.addActionListener(e -> {
                if (currentLoggedInUser == null) {
                    // Show error dialog if not logged in
                    JOptionPane.showMessageDialog(null, "Please login first to view account details.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String userInfo = "Username: " + currentLoggedInUser.getUsername() + "\n"
                        + "Account Number: " + currentLoggedInUser.getAccountNumber() + "\n"
                        + String.format("Balance: %.2f", currentLoggedInUser.getBalance());

                // Show info in a dialog box
                JOptionPane.showMessageDialog(null, userInfo, "Account Details", JOptionPane.INFORMATION_MESSAGE);


        });

        logoutBtn.addActionListener(e -> {
            bankService.logout();
            cardLayout.show(mainPanel, "login");
        });

        panel.add(welcomeLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(depositBtn);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(withdrawBtn);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(viewBtn);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(logoutBtn);

        return panel;
    }

    public static void main(String[] args) {
        try {
//            UIManager.setLookAndFeel(new FlatLightLaf());  // Optional modern theme
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(BankUI::new);
    }
}
