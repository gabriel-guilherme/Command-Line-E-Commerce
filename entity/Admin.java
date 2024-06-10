package entity;

public class Admin extends Entity {
    private String password;

    /// CONSTRUCTOR
    public Admin(String password) {
        this.setPassword(password);
    }

    /// GETTERS

    public String getPassword() {
        return this.password;
    }

    /// SETTERS

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin{" + getId() + "}";
    }
}