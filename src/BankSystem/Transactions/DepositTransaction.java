package BankSystem.Transactions;

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
        return "Deposit of  " + amount + " to account " + account.getAccountNumber();
    }
}
