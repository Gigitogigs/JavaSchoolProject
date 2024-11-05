public class Account {
    String name;
    String accountNumber;
    double accountBalance;
    String pin;

    public Account(String name, String accountNumber, double accountBalance, String pin) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
        this.pin = pin;
    }

    //get and set name

    public double getAccountBalance (){ return accountBalance; }
    public String getAccountNumber(){ return accountNumber; }

    //withdraw
    public void withdraw(double amount) {
        if (accountBalance >= amount) {
            accountBalance -= amount;
        } else {
            System.out.println("Insufficient balance");
        }
    }

    //deposit
    public void deposit(double amount) {
        accountBalance += amount;
        System.out.println("Deposit Successful"); //+accountBalance
    }

    //pin manipulation
    public void changePin(String newPin){
        pin = newPin;
    }

    //Authentication
    public boolean checkPin(String inputPin) {
        return pin.equals(inputPin);
    }

}


