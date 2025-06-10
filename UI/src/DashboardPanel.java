import javax.swing.*;
import Services.BankServicesImp;

public class DashboardPanel extends JPanel {
    public DashboardPanel(BankServicesImp bankServices, MainFrame frame) {
        setLayout(null);

        JButton btnDeposit = new JButton("Deposit");
        btnDeposit.setBounds(50, 30, 150, 30);

        JButton btnWithdraw = new JButton("Withdraw");
        btnWithdraw.setBounds(50, 70, 150, 30);

        JButton btnDetails = new JButton("View Details");
        btnDetails.setBounds(50, 110, 150, 30);

        btnDeposit.addActionListener(e -> {
            String amountStr = JOptionPane.showInputDialog("Enter amount to deposit:");
            double amount = Double.parseDouble(amountStr);
            bankServices.depositAmount(amount);
        });

        btnWithdraw.addActionListener(e -> {
            String amountStr = JOptionPane.showInputDialog("Enter amount to withdraw:");
            double amount = Double.parseDouble(amountStr);
            bankServices.Withdraw(amount);
        });

        btnDetails.addActionListener(e -> bankServices.displayDetails(""));

        add(btnDeposit);
        add(btnWithdraw);
        add(btnDetails);
    }
}
