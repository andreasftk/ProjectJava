public class Beneficiary extends User{
    private int noPersons = 1;

    public Beneficiary(String name, String phone) {
        super(name, phone);
    }

    List<RequestDonationList> recheivedList = new ArrayList<>();
    ArrayList <Requests> requestList = new ArrayList<>();

}
