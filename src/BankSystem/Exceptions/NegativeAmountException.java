package BankSystem.Exceptions;

public class NegativeAmountException extends Exception{
    public NegativeAmountException(String message) {
        super(message);
    }
}
