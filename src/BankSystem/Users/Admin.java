package BankSystem.Users;

public class Admin extends User{
    private final String adminID;
    public Admin(String username, String password, String adminID) {
        super(username, password);
        this.adminID = adminID;
    }

    public String getAdminID() {
        return adminID;
    }
}
