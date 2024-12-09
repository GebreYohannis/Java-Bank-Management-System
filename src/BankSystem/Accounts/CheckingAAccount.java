package BankSystem.Accounts;

import BankSystem.Exceptions.InsufficientBalanceException;
import BankSystem.Exceptions.NegativeAmountException;
import BankSystem.Transactions.DepositTransaction;
import BankSystem.Transactions.WithdrawTransaction;

public class CheckingAAccount extends BankAccount{
    private final double overdraftLimit;
    public CheckingAAccount(String accountNumber, double initialBalance, double overdraftLimit) {
        super(accountNumber, initialBalance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void deposit(double amount) throws NegativeAmountException {
        if(amount <= 0)
            throw new NegativeAmountException("Amount must be positive!");
        balance += amount;
        this.addTransaction(new DepositTransaction(this,amount));
    }

    @Override
    public void withdraw(double amount) throws NegativeAmountException, InsufficientBalanceException {
        if(amount <= 0)
            throw new NegativeAmountException("Amount must be positive!");
        if(amount > balance + overdraftLimit)
            throw new InsufficientBalanceException("Overdraft limit exceeded!");
        balance -= amount;
        this.addTransaction(new WithdrawTransaction(this, amount));
    }

    @Override
    public void accountType() {
        System.out.println("Account Type: Checking");
    }
}
