import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class BankManagementSystemUI {
    // Main method to launch the application
    public static void main(String[] args) {
        try {
            // Set system look and feel
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Launch the login screen
        SwingUtilities.invokeLater(() -> {
            new LoginUI();
        });
    }
}

/**
 * Login UI Class
 */
class LoginUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton;

    public LoginUI() {
        // Basic frame setup
        setTitle("Bank Management System - Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Create main panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(240, 240, 240));

        // Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0, 102, 204));
        headerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        headerPanel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Bank Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        // Form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(new CompoundBorder(
                new LineBorder(new Color(200, 200, 200)),
                new EmptyBorder(20, 20, 20, 20)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Username field
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(usernameLabel, gbc);

        usernameField = new JTextField(15);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(usernameField, gbc);

        // Password field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(passwordLabel, gbc);

        passwordField = new JPasswordField(15);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(passwordField, gbc);

        // Login button
        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBackground(new Color(0, 102, 204));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.insets = new Insets(15, 5, 5, 5);
        formPanel.add(loginButton, gbc);

        // Register button
        registerButton = new JButton("Register New Account");
        registerButton.setFont(new Font("Arial", Font.PLAIN, 12));
        registerButton.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.insets = new Insets(5, 5, 5, 5);
        formPanel.add(registerButton, gbc);

        // Add action listeners
        loginButton.addActionListener(e -> {
            // Connect this to your existing login logic
            new DashboardUI();
            dispose();
        });

        registerButton.addActionListener(e -> {
            // Connect this to your existing registration logic
            new RegisterUI();
            dispose();
        });

        // Add panels to main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Add main panel to frame
        add(mainPanel);
        setVisible(true);
    }
}

/**
 * Registration UI Class
 */
class RegisterUI extends JFrame {
    private JTextField usernameField, fullNameField, initialDepositField;
    private JPasswordField passwordField, confirmPasswordField;
    private JButton registerButton, backButton;

    public RegisterUI() {
        // Basic frame setup
        setTitle("Bank Management System - Register");
        setSize(450, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Create main panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(240, 240, 240));

        // Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0, 153, 51));
        headerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        headerPanel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Create New Account");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        // Form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(new CompoundBorder(
                new LineBorder(new Color(200, 200, 200)),
                new EmptyBorder(20, 20, 20, 20)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Full Name field
        addFormField(formPanel, "Full Name:", fullNameField = new JTextField(15), gbc, 0);

        // Username field
        addFormField(formPanel, "Username:", usernameField = new JTextField(15), gbc, 2);

        // Password field
        addFormField(formPanel, "Password:", passwordField = new JPasswordField(15), gbc, 4);

        // Confirm Password field
        addFormField(formPanel, "Confirm Password:", confirmPasswordField = new JPasswordField(15), gbc, 6);

        // Initial Deposit field
        addFormField(formPanel, "Initial Deposit:", initialDepositField = new JTextField(15), gbc, 8);

        // Register button
        registerButton = new JButton("Register");
        registerButton.setFont(new Font("Arial", Font.BOLD, 14));
        registerButton.setBackground(new Color(0, 153, 51));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.insets = new Insets(15, 5, 5, 5);
        formPanel.add(registerButton, gbc);

        // Back button
        backButton = new JButton("Back to Login");
        backButton.setFont(new Font("Arial", Font.PLAIN, 12));
        backButton.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.insets = new Insets(5, 5, 5, 5);
        formPanel.add(backButton, gbc);

        // Add action listeners
        registerButton.addActionListener(e -> {
            // Connect this to your existing registration logic
            JOptionPane.showMessageDialog(this, "Registration successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            new LoginUI();
            dispose();
        });

        backButton.addActionListener(e -> {
            new LoginUI();
            dispose();
        });

        // Add panels to main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Add main panel to frame
        add(mainPanel);
        setVisible(true);
    }

    private void addFormField(JPanel panel, String labelText, JTextField field, GridBagConstraints gbc, int row) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(label, gbc);

        field.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = row + 1;
        panel.add(field, gbc);
    }
}

/**
 * Dashboard UI Class
 */
class DashboardUI extends JFrame {
    private JTabbedPane tabbedPane;
    private JLabel balanceLabel;
    private JTextField depositAmountField, withdrawAmountField;
    private JButton depositButton, withdrawButton, logoutButton;

    public DashboardUI() {
        // Basic frame setup
        setTitle("Bank Management System - Dashboard");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create tabbed pane
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Arial", Font.BOLD, 14));

        // Add tabs
        tabbedPane.addTab("Account", createAccountPanel());
        tabbedPane.addTab("Deposit", createDepositPanel());
        tabbedPane.addTab("Withdraw", createWithdrawPanel());
        tabbedPane.addTab("Users", createUsersPanel());

        // Create bottom panel with logout button
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("Arial", Font.BOLD, 14));
        logoutButton.setBackground(new Color(204, 0, 0));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setFocusPainted(false);

        logoutButton.addActionListener(e -> {
            new LoginUI();
            dispose();
        });

        bottomPanel.add(logoutButton);

        // Add components to frame
        add(tabbedPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel createAccountPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Welcome panel
        JPanel welcomePanel = new JPanel();
        welcomePanel.setBackground(new Color(0, 102, 204));
        welcomePanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        welcomePanel.setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Welcome, User!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        welcomeLabel.setForeground(Color.WHITE);
        welcomePanel.add(welcomeLabel, BorderLayout.WEST);

        // Account details panel
        JPanel detailsPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        detailsPanel.setBorder(new CompoundBorder(
                new TitledBorder(new LineBorder(new Color(200, 200, 200)), "Account Details",
                        TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14)),
                new EmptyBorder(15, 15, 15, 15)
        ));

        // Account details
        detailsPanel.add(createBoldLabel("Account Number:"));
        detailsPanel.add(createPlainLabel("ACC123456789"));

        detailsPanel.add(createBoldLabel("Account Type:"));
        detailsPanel.add(createPlainLabel("Savings"));

        detailsPanel.add(createBoldLabel("Current Balance:"));
        balanceLabel = new JLabel("$1,000.00");
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 16));
        balanceLabel.setForeground(new Color(0, 153, 51));
        detailsPanel.add(balanceLabel);

        // Add panels to main panel
        panel.add(welcomePanel, BorderLayout.NORTH);
        panel.add(detailsPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createDepositPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Title panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(0, 153, 51));
        titlePanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel titleLabel = new JLabel("Deposit Money");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);

        // Form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Amount label
        JLabel amountLabel = new JLabel("Enter Amount to Deposit:");
        amountLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(amountLabel, gbc);

        // Amount field
        depositAmountField = new JTextField(15);
        depositAmountField.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(depositAmountField, gbc);

        // Deposit button
        depositButton = new JButton("Deposit");
        depositButton.setFont(new Font("Arial", Font.BOLD, 14));
        depositButton.setBackground(new Color(0, 153, 51));
        depositButton.setForeground(Color.WHITE);
        depositButton.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(20, 10, 10, 10);
        formPanel.add(depositButton, gbc);

        // Add action listener
        depositButton.addActionListener(e -> {
            // Connect this to your existing deposit logic
            String amount = depositAmountField.getText();
            if (!amount.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Deposit successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                depositAmountField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Please enter an amount", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Add panels to main panel
        panel.add(titlePanel, BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createWithdrawPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Title panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(204, 102, 0));
        titlePanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel titleLabel = new JLabel("Withdraw Money");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);

        // Form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Amount label
        JLabel amountLabel = new JLabel("Enter Amount to Withdraw:");
        amountLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(amountLabel, gbc);

        // Amount field
        withdrawAmountField = new JTextField(15);
        withdrawAmountField.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(withdrawAmountField, gbc);

        // Withdraw button
        withdrawButton = new JButton("Withdraw");
        withdrawButton.setFont(new Font("Arial", Font.BOLD, 14));
        withdrawButton.setBackground(new Color(204, 102, 0));
        withdrawButton.setForeground(Color.WHITE);
        withdrawButton.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(20, 10, 10, 10);
        formPanel.add(withdrawButton, gbc);

        // Add action listener
        withdrawButton.addActionListener(e -> {
            // Connect this to your existing withdraw logic
            String amount = withdrawAmountField.getText();
            if (!amount.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Withdrawal successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                withdrawAmountField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Please enter an amount", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Add panels to main panel
        panel.add(titlePanel, BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createUsersPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Title panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(51, 51, 153));
        titlePanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel titleLabel = new JLabel("User Management");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);

        // Table panel
        // Sample data - replace with your actual data
        String[] columnNames = {"Username", "Full Name", "Balance", "Account Type"};
        Object[][] data = {
                {"user1", "John Doe", "$1,000.00", "Savings"},
                {"user2", "Jane Smith", "$2,500.00", "Checking"},
                {"user3", "Bob Johnson", "$750.00", "Savings"}
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(model);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(25);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(51, 51, 153));
        table.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(table);

        // Add components to panel
        panel.add(titlePanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JLabel createBoldLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        return label;
    }

    private JLabel createPlainLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        return label;
    }
}