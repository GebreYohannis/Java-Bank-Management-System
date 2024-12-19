package BankSystem.Transactions;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import BankSystem.Accounts.BankAccount;
import BankSystem.Exceptions.InsufficientBalanceException;
import BankSystem.Exceptions.NegativeAmountException;

public class WithdrawTransaction implements Transaction {
    private final BankAccount account;
    private final double amount;

    public WithdrawTransaction(BankAccount account, double amount) {
        this.account = account;
        this.amount = amount;
    }

    @Override
    public void execute() throws NegativeAmountException, InsufficientBalanceException {
        account.withdraw(amount);
        account.addTransaction(this);
    }

    @Override
    public String getDetails() {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy HH:mm:ss");
        String formattedDateTime = formatter.format(dateTime);

        String formattedAmount = NumberFormat.getCurrencyInstance().format(amount);
        return "Withdrew of " + formattedAmount + " from account " +
                account.getAccountNumber() + " on " + formattedDateTime;
    }
}
