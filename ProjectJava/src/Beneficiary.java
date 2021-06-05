import java.util.*;

public class Beneficiary extends User {
    private int noPersons = 1;

    public Beneficiary(String name, String phone) {
        super(name, phone);
    }

    ArrayList<RequestDonationList> receivedList = new ArrayList<RequestDonationList>();
    ArrayList<Requests> requestsList = new ArrayList<Requests>();

    public int getNoPersons() {
        return this.noPersons;
    }

    public void add(Requests request) {
        int size = requestsList.size();
        if (size > 0) {
            if (!request.rdEntities.isEmpty()) {
                Iterator<Requests> itr = requestsList.iterator();
                if (itr.next() == request) {
                    requestsList.add(request);
                }
            }
        }
    }

    public String getName() {
        return super.getName();
    }

}
