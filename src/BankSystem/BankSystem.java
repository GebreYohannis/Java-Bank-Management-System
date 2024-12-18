package BankSystem;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import BankSystem.Accounts.BankAccount;
import BankSystem.Services.BankService;
import BankSystem.Users.User;

public class BankSystem {
    private final BankService bankService = new BankService();
    private final Map<String, User> users;
    private User currentUser;

    public BankSystem() {
        this.users = new HashMap<>();
    }

    public void register(String username, String password,
                         String accountNumber, double initialBalance) {
        if(!users.containsKey(username)) {
            try {
                User newUser = new User(username, password);
                users.put(username, newUser);
                newUser.createAccount(accountNumber,initialBalance);
                System.out.println("User successfully registered!");
            } catch (Exception e) {
                System.out.println("Failed to create account: " + e.getMessage());
            }
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

    public boolean logout() {
        currentUser = null;
        System.out.println("Logged out successfully!");
        return true;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public BankAccount getAccountByNumber(String accountNumber) {
        if(currentUser != null)
            return currentUser.getAccount(accountNumber);
        return null;
    }

    public void performTask() {

            System.out.println("1. Check balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Print Statement");
            System.out.println("6. Logout");
            System.out.print("Choice: ");

            Scanner scanner = new Scanner(System.in);
            byte choice = scanner.nextByte();

            BankAccount account = getAccountByNumber(currentUser.getAccounts().get(0).getAccountNumber());
            switch (choice) {
                case 1:
                    var balance = account.getBalance();
                    System.out.println("Balance: " + NumberFormat.getCurrencyInstance().format(balance));
                    break;
                case 2: {
                    System.out.print("Balance: ");
                    double amount = scanner.nextDouble();
                    bankService.deposit(account, amount);
                    break;
                }
                case 3: {
                    System.out.print("Balance: ");
                    double amount = scanner.nextDouble();
                    bankService.withdraw(account, amount);
                    break;
                }
                case 4:
//                    bankService.transfer(account,account,amount);
                    break;
                case 5:
                    bankService.printStatement(account);
                    break;
                case 6:
                    logout();
                    break;
                default:
                    System.out.println("Invalid choice! Please try again!");
        }
    }
}
