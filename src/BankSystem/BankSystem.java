package BankSystem;

import BankSystem.Accounts.BankAccount;
import BankSystem.Users.User;

import java.util.HashMap;
import java.util.Map;

public class BankSystem {
    private final Map<String, User> users;
    private User currentUser;

    public BankSystem() {
        this.users = new HashMap<>();
    }

    public void register(String username, String password) {
        if(!users.containsKey(username)) {
            users.put(username, new User(username, password));
            System.out.println("User successfully registered!");
        } else  {
            System.out.println("User already exist!");
        }
    }

    public boolean login(String username, String password) {
        User user = users.get(username);
        if(user != null && user.checkPassword(password)) {
            currentUser = user;
            System.out.println("Logged in successfully!");
            return true;
        } else {
            System.out.println("Invalid username or password!");
            return false;
        }
    }

    public void logout() {
        currentUser = null;
        System.out.println("Logged out successfully!");
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public BankAccount getAccountByNumber(String accountNumber) {
        if(currentUser != null)
            return currentUser.getAccount(accountNumber);
        return null;
    }

}
