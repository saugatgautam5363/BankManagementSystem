import LoginandRegister.LoginRegister;
import Services.BankServicesImp;

import javax.swing.*;
import java.awt.event.*;

public class LoginPanel extends JPanel {
    public LoginPanel(BankServicesImp bankServices, LoginRegister loginRegister, MainFrame frame) {
        setLayout(null);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setBounds(50, 30, 100, 30);
        JTextField txtUsername = new JTextField();
        txtUsername.setBounds(150, 30, 200, 30);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(50, 70, 100, 30);
        JPasswordField txtPassword = new JPasswordField();
        txtPassword.setBounds(150, 70, 200, 30);

        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(150, 110, 100, 30);

        JButton btnRegister = new JButton("Go to Register");
        btnRegister.setBounds(260, 110, 140, 30);

        btnLogin.addActionListener(e -> {
            loginRegister.setInput(txtUsername.getText(), new String(txtPassword.getPassword()));
            bankServices.Login(loginRegister);


            if (loginRegister.isAuthenticated()) {
                JOptionPane.showMessageDialog(this, "Login successful!");
                frame.switchTo("dashboard");
            } else {
                JOptionPane.showMessageDialog(this, "Login failed!");
            }
        });

        btnRegister.addActionListener(e -> frame.switchTo("register"));

        add(lblUsername); add(txtUsername);
        add(lblPassword); add(txtPassword);
        add(btnLogin); add(btnRegister);
    }
}
