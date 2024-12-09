package BankSystem.Transactions;

import BankSystem.Accounts.BankAccount;
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
    public void execute() throws NegativeAmountException, InsufficientBalanceException {
        sourceAccount.withdraw(amount);
        sourceAccount.addTransaction(this);

        destinationAccount.deposit(amount);
        destinationAccount.addTransaction(this);
    }

    @Override
    public String getDetails() {
        return ("Transferred of " + amount + " from account " +
                sourceAccount.getAccountNumber() + " to account " +
                destinationAccount.getAccountNumber());
    }
}
