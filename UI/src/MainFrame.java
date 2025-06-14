import javax.swing.*;
import java.awt.*;
import LoginandRegister.LoginRegister;
import Services.BankServicesImp;

public class MainFrame extends JFrame {
    CardLayout cardLayout;
    JPanel mainPanel;

    public MainFrame() {
        setTitle("Bank Management System");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Initialize service and login/register
        BankServicesImp bankServices = new BankServicesImp();
        LoginRegister loginRegister = new LoginRegister(); // Ensure this is implemented correctly

        // Screens
        LoginPanel loginPanel = new LoginPanel(bankServices, loginRegister, this);
        RegisterPanel registerPanel = new RegisterPanel(bankServices, loginRegister, this);
        DashboardPanel dashboardPanel = new DashboardPanel(bankServices, this);

        // Add panels
        mainPanel.add(loginPanel, "login");
        mainPanel.add(registerPanel, "register");
        mainPanel.add(dashboardPanel, "dashboard");

        add(mainPanel);
        cardLayout.show(mainPanel, "login");
    }

    public void switchTo(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}
