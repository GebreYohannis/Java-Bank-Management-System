package BankSystem.Accounts;

import java.text.NumberFormat;
import java.util.ArrayList;

import BankSystem.Exceptions.InsufficientBalanceException;
import BankSystem.Exceptions.NegativeAmountException;
import BankSystem.Transactions.Transaction;

public abstract class BankAccount {
    protected String accountNumber;
    protected double balance;
    protected ArrayList<Transaction> transactions;

    public BankAccount(String accountNumber,double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.transactions = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public abstract void deposit(double amount) throws NegativeAmountException;
    public abstract void withdraw(double amount) throws NegativeAmountException,
            InsufficientBalanceException;

    public void printStatement() {
        System.out.println("Account Number: " + this.accountNumber);
        System.out.println("Balance: " + NumberFormat.getCurrencyInstance().format(this.balance));
        System.out.println("******** Transactions *******");
        for(final Transaction transaction: transactions)
            System.out.println(transaction.getDetails());
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

    public abstract String accountType();
}
