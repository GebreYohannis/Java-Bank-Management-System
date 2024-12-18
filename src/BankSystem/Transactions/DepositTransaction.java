package BankSystem.Transactions;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import BankSystem.Accounts.BankAccount;
import BankSystem.Exceptions.NegativeAmountException;

public class DepositTransaction implements Transaction {
    private final BankAccount account;
    private final double amount;

    public DepositTransaction(BankAccount account, double amount) {
        this.account = account;
        this.amount = amount;
    }
    @Override
    public void execute() throws NegativeAmountException{
        account.deposit(amount);
        account.addTransaction(this);
    }

    @Override
    public String getDetails() {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy HH:mm:ss");
        String formattedDateTime = formatter.format(dateTime);

        String formattedAmount = NumberFormat.getCurrencyInstance().format(amount);
        return ("Deposit of  " + formattedAmount + " to account " +
                account.getAccountNumber() + " on " + formattedDateTime);
    }
}
