package BankSystem.Transactions;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import BankSystem.Accounts.BankAccount;
import BankSystem.Exceptions.AccountNotFoundException;
import BankSystem.Exceptions.InsufficientBalanceException;
import BankSystem.Exceptions.NegativeAmountException;

public class TransferTransaction implements Transaction{
    private final BankAccount sourceAccount;
    private final BankAccount destinationAccount;
    private final double amount;

    public TransferTransaction(BankAccount sourceAccount,
                               BankAccount destinationAccount,
                               double amount) {
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.amount = amount;
    }

    @Override
    public void execute() throws NegativeAmountException,
            InsufficientBalanceException,
            AccountNotFoundException {
        if (destinationAccount == null)
            throw new AccountNotFoundException("Destination account is not found!");
        sourceAccount.withdraw(amount);
        sourceAccount.addTransaction(this);

        destinationAccount.deposit(amount);
        destinationAccount.addTransaction(this);
    }

    @Override
    public String getDetails() {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy HH:mm:ss");
        String formattedDateTime = formatter.format(dateTime);

        String formattedAmount = NumberFormat.getCurrencyInstance().format(amount);
        return ("Transferred of " + formattedAmount + " from account " +
                sourceAccount.getAccountNumber() + " to account " +
                destinationAccount.getAccountNumber()) + " on " + formattedDateTime;
    }
}
