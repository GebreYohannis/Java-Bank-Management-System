package BankSystem.Users;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import BankSystem.Accounts.BankAccount;
import BankSystem.Accounts.CheckingAAccount;
import BankSystem.Accounts.SavingAccount;
import BankSystem.Exceptions.NegativeAmountException;

public class User {
    private final String username;
    private final String password;
    private final List<BankAccount> accounts;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.accounts = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public boolean checkPassword(String password) {
       return this.password.equals(password);
    }

    public List<BankAccount> getAccounts() {
        return accounts;
    }

    public void addAccount(BankAccount account) {
        this.accounts.add(account);
    }

    public BankAccount getAccount(String accountNumber) {
        for(BankAccount account: accounts) {
            if (account.getAccountNumber().equals(accountNumber))
                return account;
        }
        return null;
    }

    public void createAccount(String accountNumber, double initialBalance) throws NegativeAmountException {
        if (initialBalance < 0 )
            throw new NegativeAmountException("Initial balance cannot be negative!");

        BankAccount account;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Account Type:");
        System.out.println("1. Checking");
        System.out.println("2. Saving");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                account = new CheckingAAccount(accountNumber, initialBalance, 0);
                break;
            case 2:
                account = new SavingAccount(accountNumber,initialBalance,0);
                break;
            default:
                return;
        }

        this.accounts.add(account);
    }

}
