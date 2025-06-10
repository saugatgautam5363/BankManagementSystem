import javax.swing.*;
import LoginandRegister.LoginRegister;
import Services.BankServicesImp;

public class RegisterPanel extends JPanel {
    public RegisterPanel(BankServicesImp bankServices, LoginRegister loginRegister, MainFrame frame) {
        setLayout(null);

        JLabel lblName = new JLabel("Full Name:");
        lblName.setBounds(50, 30, 100, 30);
        JTextField txtName = new JTextField();
        txtName.setBounds(150, 30, 200, 30);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setBounds(50, 70, 100, 30);
        JTextField txtUsername = new JTextField();
        txtUsername.setBounds(150, 70, 200, 30);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(50, 110, 100, 30);
        JPasswordField txtPassword = new JPasswordField();
        txtPassword.setBounds(150, 110, 200, 30);

        JLabel lblAccNo = new JLabel("Account No:");
        lblAccNo.setBounds(50, 150, 100, 30);
        JTextField txtAccNo = new JTextField();
        txtAccNo.setBounds(150, 150, 200, 30);

        JButton btnSubmit = new JButton("Register");
        btnSubmit.setBounds(150, 200, 100, 30);

        JButton btnBack = new JButton("Back to Login");
        btnBack.setBounds(260, 200, 140, 30);

        btnSubmit.addActionListener(e -> {
            loginRegister.setRegisterInput(
                    txtName.getText(),
                    txtUsername.getText(),
                    new String(txtPassword.getPassword()),
                    txtAccNo.getText()
            );
            bankServices.register(loginRegister);
            JOptionPane.showMessageDialog(this, "User registered!");
            frame.switchTo("login");
        });

        btnBack.addActionListener(e -> frame.switchTo("login"));

        add(lblName); add(txtName);
        add(lblUsername); add(txtUsername);
        add(lblPassword); add(txtPassword);
        add(lblAccNo); add(txtAccNo);
        add(btnSubmit); add(btnBack);
    }
}
