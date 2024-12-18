package BankSystem.Services;

import BankSystem.Accounts.BankAccount;
import BankSystem.Transactions.TransferTransaction;

public class BankService {

    public  void deposit(BankAccount account,double amount) {
        try {
        account.deposit(amount);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void withdraw(BankAccount account,double amount) {
        try {
            account.withdraw(amount);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void transfer(BankAccount source, BankAccount destination, double amount) {
        try {
            TransferTransaction transferTransaction = new TransferTransaction(source,destination,amount);
            transferTransaction.execute();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void printStatement(BankAccount account) {
        account.printStatement();
    }
}
