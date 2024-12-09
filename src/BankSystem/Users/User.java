package BankSystem.Users;

import BankSystem.Accounts.BankAccount;

import java.util.ArrayList;
import java.util.List;

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
}
