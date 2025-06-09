import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import BankSystem.User;
import Services.BankServicesImp;
public class BankManagementSystem {
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private HashMap<String, User> users;
    private User currentUser;

    public BankManagementSystem() {
        users = new HashMap<>();
        frame = new JFrame("Bank Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(getLoginPanel(), "Login");
        mainPanel.add(getRegisterPanel(), "Register");
        mainPanel.add(getDashboardPanel(), "Dashboard");

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private JPanel getLoginPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 1));
        JLabel label = new JLabel("Login", SwingConstants.CENTER);
        JTextField userField = new JTextField();
        JPasswordField passField = new JPasswordField();
        JButton loginBtn = new JButton("Login");
        JButton switchToRegister = new JButton("Register");

        panel.add(label);
        panel.add(new JLabel("Username:"));
        panel.add(userField);
        panel.add(new JLabel("Password:"));
        panel.add(passField);
        panel.add(loginBtn);
        panel.add(switchToRegister);

        loginBtn.addActionListener(e -> {
            String user = userField.getText();
            String pass = new String(passField.getPassword());
            if (users.containsKey(user) && users.get(user).checkPassword(pass)) {
                currentUser = users.get(user);
                updateDashboard();
                cardLayout.show(mainPanel, "Dashboard");
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid credentials!");
            }
        });

        switchToRegister.addActionListener(e -> cardLayout.show(mainPanel, "Register"));
        return panel;
    }

    private JPanel getRegisterPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 1));
        JLabel label = new JLabel("Register", SwingConstants.CENTER);
        JTextField userField = new JTextField();
        JPasswordField passField = new JPasswordField();
        JButton registerBtn = new JButton("Register");
        JButton switchToLogin = new JButton("Back to Login");

        panel.add(label);
        panel.add(new JLabel("New Username:"));
        panel.add(userField);
        panel.add(new JLabel("New Password:"));
        panel.add(passField);
        panel.add(registerBtn);
        panel.add(switchToLogin);

        registerBtn.addActionListener(e -> {
            String user = userField.getText();
            String pass = new String(passField.getPassword());
            if (!users.containsKey(user)) {
                users.put(user, new User(user, pass));
                JOptionPane.showMessageDialog(frame, "Registered Successfully!");
                cardLayout.show(mainPanel, "Login");
            } else {
                JOptionPane.showMessageDialog(frame, "User already exists!");
            }
        });

        switchToLogin.addActionListener(e -> cardLayout.show(mainPanel, "Login"));
        return panel;
    }

    private JLabel userLabel = new JLabel();
    private JLabel balanceLabel = new JLabel();
    private JTextField amountField = new JTextField();

    private JPanel getDashboardPanel() {
        JPanel panel = new JPanel(new GridLayout(8, 1));

        JButton depositBtn = new JButton("Deposit");
        JButton withdrawBtn = new JButton("Withdraw");
        JButton logoutBtn = new JButton("Logout");

        panel.add(new JLabel("Welcome,", SwingConstants.CENTER));
        panel.add(userLabel);
        panel.add(new JLabel("Current Balance:", SwingConstants.CENTER));
        panel.add(balanceLabel);
        panel.add(new JLabel("Amount:"));
        panel.add(amountField);
        panel.add(depositBtn);
        panel.add(withdrawBtn);
        panel.add(logoutBtn);

        depositBtn.addActionListener(e -> {
            try {
                double amt = Double.parseDouble(amountField.getText());
                BankServicesImp.depositAmount(amt);
                updateDashboard();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid amount!");
            }
        });

        withdrawBtn.addActionListener(e -> {
            try {
                double amt = Double.parseDouble(amountField.getText());
                if (BankServicesImp.Withdraw(amt)) {
                    JOptionPane.showMessageDialog(frame, "Insufficient balance!");
                } else {
                    updateDashboard();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid amount!");
            }
        });

        logoutBtn.addActionListener(e -> {
            currentUser = null;
            cardLayout.show(mainPanel, "Login");
        });

        return panel;
    }

    private void updateDashboard() {
        if (currentUser != null) {
            userLabel.setText(currentUser.getUsername());
            balanceLabel.setText("Rs. " + currentUser.getBalance());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BankManagementSystem::new);
    }
}
