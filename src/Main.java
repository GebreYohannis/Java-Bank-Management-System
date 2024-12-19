
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
            System.out.println("3. Exit");
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

        if (isLoggedIn) {
            while(true) {
                System.out.println("1. Check balance");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. Transfer");
                System.out.println("5. Print Statement");
                System.out.println("6. Logout");
                System.out.print("Choice: ");

                Scanner scanner = new Scanner(System.in);
                byte choice = scanner.nextByte();
                if (choice == 6){
                    logout();
                    break;
                }
                bankSystem.performTask(choice);
            }
        }
    }

    public static void logout() {
        bankSystem.logout();
    }
}