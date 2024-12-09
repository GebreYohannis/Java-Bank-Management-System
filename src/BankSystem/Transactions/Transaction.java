package BankSystem.Transactions;

public interface Transaction {
    public void execute() throws Exception;
    public String getDetails();
}
