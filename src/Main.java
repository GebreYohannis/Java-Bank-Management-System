
import java.util.Scanner;

import BankSystem.BankSystem;
import BankSystem.Users.User;

public class Main {
    private static final BankSystem bankSystem = new BankSystem();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("****** Bank System *****");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Check Account");
            System.out.println("4. Logout");
            System.out.println("5. Exit");

            System.out.print("Choice: ");
            int choice = scanner.nextInt();

            if (performTask(choice)) return;
        }
    }

    private static boolean performTask(int choice) {
        switch (choice) {
            case 1:
                register();
                break;
            case 2:
                login();
                break;
            case 3:
                checkAccount();
                break;
            case 4:
                logout();
                break;
            case 5:
                System.out.println("Exiting the system ....");
                scanner.close();
                return true;
            default:
                System.out.println("Invalid choice! Please try again!");
        }
        return false;
    }

    public static void register() {
        System.out.print("Username: ");
        String username = scanner.next();

        System.out.print("Password: ");
        String password = scanner.next();

        System.out.print("Account Number: ");
        String accountNumber = scanner.next();

        System.out.print("Initial Balance: ");
        double initialBalance = scanner.nextDouble();

        bankSystem.register(username, password,accountNumber,initialBalance);
    }

    public static void login() {
        System.out.print("Username: ");
        String username = scanner.next();

        System.out.print("Password: ");
        String password = scanner.next();

        boolean isLoggedIn =  bankSystem.login(username, password);
        while (isLoggedIn){
            bankSystem.performTask();
            if (!bankSystem.logout())
                isLoggedIn = false;
        }

    }

    public static void logout() {
        bankSystem.logout();
    }

    public static void checkAccount() {
        User currentUser = bankSystem.getCurrentUser();
        if (currentUser != null) {
            System.out.print("Account Number: ");
            String accountNumber = scanner.next();
            var account = bankSystem.getAccountByNumber(accountNumber);
            if (account != null)
                System.out.println("Account found: " +
                        account.getAccountNumber() + " is " +
                        account.accountType() + " Account");

            else System.out.println("Account  not found!");
        } else
            System.out.println("No user is logged in!");
    }

}