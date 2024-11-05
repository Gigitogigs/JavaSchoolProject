import java.util.ArrayList;
import java.util.Scanner;

public class BankingSystem {
    private Scanner userIn = new Scanner(System.in);
    private ArrayList<Account> accounts = new ArrayList<Account>();

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void createAccount() {
        System.out.println("Enter account name: ");
        String name = userIn.nextLine();

        System.out.println("How much would you like to deposit: ");
        double accountBalance = userIn.nextDouble();

        System.out.println("Enter your PIN: ");
        String pin = userIn.nextLine();

        String accountNumber = generateAccountNumber();
        Account newAccount = new Account( name, accountNumber, accountBalance, pin);
        accounts.add(newAccount);

    }

    //deposit
    public void deposit() {
        System.out.println("Enter account number; ");
        String  accountNumber = userIn.nextLine();
        Account account = findAccount(accountNumber);

        if (account != null){
            System.out.println("How much would you like to deposit: ");
            double amount = userIn.nextDouble();
            userIn.nextLine();
            account.deposit(amount);

            System.out.println("Deposit successful. New balance is " +account.getAccountBalance());
        } else {
            System.out.println("Deposit unsuccessful");
        }

    }

    //withdraw
    public void withdraw() {
        System.out.println("Enter account number; ");
        String  accountNumber = userIn.nextLine();
        userIn.nextLine();
        Account account = findAccount(accountNumber);

        System.out.println("Enter PIN:");
        String pin = userIn.nextLine(); 
        
        if(account.checkPin(pin)){
            System.out.println("How much would you like to withdraw: ");
            double amount = userIn.nextDouble();
            account.withdraw(amount);
            /*if(amount >= accountBalance) {
                System.out.println("Withdrawal unsuccessful. Not enough funds.");
            } else {
                accountBalance -= amount;
                System.out.println("Withdrawal successful. New balance is " +accountBalance);
            }*/
        } else {
            System.out.println("Wrong PIN. Try again!!");
        }
    }

    public Account findAccount(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public String generateAccountNumber() {
        return "ACCT" + (accounts.size() + 1);
    }

    public static void main(String[] args) {
        BankingSystem bankSystem = new BankingSystem();
        bankSystem.createAccount();
        // Add further interactions as needed.
    }

   

}
