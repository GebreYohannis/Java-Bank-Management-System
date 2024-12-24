## Bank Management System in Java

### Project Structure:
```shell
src/
├── BankSystem/
├   ├── Accounts/
│   │   ├── BankAccount.java
│   │   ├── SavingAccount.java
│   │   └── CheckingAccount.java
│   │
│   ├── Users/
│   │   ├── User.java
│   │   ├── Customer.java
│   │   └── Admin.java
│   │
│   ├── Services/
│   │   ├── BankService.java
│   │
│   ├── Transactions/
│   │   ├── DepositTransaction.java
│   │   ├── WithdrawTransaction.java
│   │   ├── TransferTransaction.java
│   │   └── Transaction.java
│   │
│   ├── Exceptions/
│   │   └── AccountNotFoundException.java
│   │   └──InsufficientBalanceException.java
│   │   └── NegativeAmountException.java
│   │
│   └── BankSystem.java
│
└── Main.java
```
### Code snippets:

#### *Accounts/BankAccount.java*

```java
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

```
#### *Accounts/CheckingAccount.java*
```java
package BankSystem.Accounts;

import BankSystem.Exceptions.InsufficientBalanceException;
import BankSystem.Exceptions.NegativeAmountException;
import BankSystem.Transactions.DepositTransaction;
import BankSystem.Transactions.WithdrawTransaction;

public class CheckingAccount extends BankAccount{
    private final double overdraftLimit;
    public CheckingAccount(String accountNumber, double initialBalance, double overdraftLimit) {
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
    public String accountType() {
       return "Checking";
    }
}

```
#### *Exceptions/*
```java
package BankSystem.Exceptions;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(String message) {
        super(message);
    }
}

```
```java
package BankSystem.Exceptions;

public class InsufficientBalanceException extends Exception{
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

```
```java
package BankSystem.Exceptions;

public class NegativeAmountException extends Exception{
    public NegativeAmountException(String message) {
        super(message);
    }
}

```
#### *Transactions/TransferTransaction.java*

```java
package BankSystem.Transactions;

public interface Transaction {
    public void execute() throws Exception;
    public String getDetails();
}
```
#### *Transactions/TransferTransaction.java*

```java
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

```