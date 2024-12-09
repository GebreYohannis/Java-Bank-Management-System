package BankSystem.Users;

public class Customer extends User {
    private final String customerID;
    public Customer(String username, String password, String customerID) {
        super(username, password);
        this.customerID = customerID;
    }

    public String getCustomerID() {
        return customerID;
    }
}
