package BankSystem.Transactions;

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
        return "Withdrew of " + amount + " from account " + account.getAccountNumber();
    }
}
