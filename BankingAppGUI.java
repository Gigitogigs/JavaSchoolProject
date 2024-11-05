import javax.swing.*;

public class BankingAppGUI extends JFrame {
    private BankingSystem bankSystem;
    private JTextField nameField;
    private JTextField pinField;
    private JTextField initialBalanceField;
    private JTextField depositAmountField;
    private JTextField withdrawAmountField;
    private JTextField accountNumberField;

    public BankingAppGUI() {
        bankSystem = new BankingSystem();
        createUI();
    }

    private void createUI() {
        setTitle("Banking Application");
        setSize(800, 400); // Set the window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS)); // Vertical flow layout

        // Account Creation Fields
        add(new JLabel("Account Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Initial Balance:"));
        initialBalanceField = new JTextField();
        add(initialBalanceField);

        add(new JLabel("PIN:"));
        pinField = new JTextField();
        add(pinField);

        // Button for account creation
        JButton createButton = new JButton("Create Account");
        createButton.addActionListener(e -> createAccount());
        add(createButton); // Place button in the layout

        // Deposit Fields
        add(new JLabel("Account Number (Deposit):"));
        accountNumberField = new JTextField();
        add(accountNumberField);

        add(new JLabel("Deposit Amount:"));
        depositAmountField = new JTextField();
        add(depositAmountField);

        // Button for deposit
        JButton depositButton = new JButton("Deposit");
        depositButton.addActionListener(e -> deposit());
        add(depositButton); // Place button in the layout

        // Withdrawal Fields
        add(new JLabel("Account Number (Withdraw):"));
        accountNumberField = new JTextField();
        add(accountNumberField);

        add(new JLabel("Withdraw Amount:"));
        withdrawAmountField = new JTextField();
        add(withdrawAmountField);

        // Button for withdrawal
        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(e -> withdraw());
        add(withdrawButton); // Place button in the layout

        setVisible(true);
    }

    private void createAccount() {
        String name = nameField.getText();
        String pin = pinField.getText();
        try {
            double initialBalance = Double.parseDouble(initialBalanceField.getText());
            if (!pin.isEmpty()) {
                String accountNumber = bankSystem.generateAccountNumber();
                Account newAccount = new Account(name, accountNumber, initialBalance, pin);
                bankSystem.getAccounts().add(newAccount);
                JOptionPane.showMessageDialog(this, "Account created successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "PIN cannot be empty!");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for balance.");
        }
    }

    private void deposit() {
        String accountNumber = accountNumberField.getText();
        try {
            double amount = Double.parseDouble(depositAmountField.getText());
            Account account = findAccount(accountNumber);
            if (account != null) {
                account.deposit(amount);
                JOptionPane.showMessageDialog(this, "Deposit successful! New balance: " + account.getAccountBalance());
            } else {
                JOptionPane.showMessageDialog(this, "Account not found!");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid amount for deposit.");
        }
    }

    private void withdraw() {
        String accountNumber = accountNumberField.getText();
        String pin = pinField.getText(); // Use the PIN from the input field
        try {
            double amount = Double.parseDouble(withdrawAmountField.getText());
            Account account = findAccount(accountNumber);
            if (account != null && account.checkPin(pin)) {
                account.withdraw(amount);
                JOptionPane.showMessageDialog(this, "Withdrawal successful! New balance: " + account.getAccountBalance());
            } else {
                JOptionPane.showMessageDialog(this, "Invalid account or PIN!");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid amount for withdrawal.");
        }
    }

    private Account findAccount(String accountNumber) {
        for (Account account : bankSystem.getAccounts()) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BankingAppGUI::new);
    }
}
