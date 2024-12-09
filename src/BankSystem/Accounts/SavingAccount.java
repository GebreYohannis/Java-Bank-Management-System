package BankSystem.Accounts;

import BankSystem.Exceptions.InsufficientBalanceException;
import BankSystem.Exceptions.NegativeAmountException;
import BankSystem.Transactions.DepositTransaction;
import BankSystem.Transactions.WithdrawTransaction;

public class SavingAccount extends BankAccount{
    private final double interestRate;
    public SavingAccount(String accountNumber, double initialBalance, double interestRate) {
        super(accountNumber, initialBalance);
        this.interestRate = interestRate;
    }

    @Override
    public void deposit(double amount) throws NegativeAmountException {
        if (amount <= 0)
            throw new NegativeAmountException("Sorry! Amount must be positive!");
        this.balance += amount;
        this.addTransaction(new DepositTransaction(this,amount));
    }

    @Override
    public void withdraw(double amount) throws InsufficientBalanceException, NegativeAmountException {
        if (amount <= 0)
            throw new NegativeAmountException("Amount must be positive!");
        if(amount > balance)
            throw new InsufficientBalanceException("Sorry! You don't have enough balance!");
        balance -= amount;
        this.addTransaction(new WithdrawTransaction(this,amount));
    }

    @Override
    public void accountType() {
        System.out.println("Account Type: Saving");
    }

    public void addInterest() {
        balance += balance * interestRate;
        System.out.println("Interest add. New balance: " + balance);
    }
}
