public class Admin extends User {
    boolean isAdmin = true;

    public Admin(String name, String phone, boolean isAdmin) {
        super(name, phone);
        this.isAdmin=isAdmin;
    }


}
