import java.util.*;

public class Beneficiary extends User {
    private int noPersons = 1;

    public Beneficiary(String name, String phone, Organization org) {
        super(name, phone);
        org.insertBeneficiary(this);
    }

    private ArrayList<RequestDonationList> receivedList = new ArrayList<RequestDonationList>();
    private ArrayList<Requests> requestsList = new ArrayList<Requests>();

    public int getNoPersons() {
        return this.noPersons;
    }

    public void addRequest(Requests request, Organization org) {
        int size = requestsList.size();
        if (size > 0) {
            if (!request.getrdEntities().isEmpty()) {
                Iterator<Requests> itr = requestsList.iterator();
                if (itr.next() == request) {
                    this.requestsList.add(request);
                }
            }
        }
    }

    public void addReceived(Requests request, Organization org) {
        this.receivedList.add(request);
    }

    public String getName() {
        return super.getName();
    }

    public ArrayList<RequestDonationList> getReceivedList() {
        return this.receivedList;
    }

    public ArrayList<Requests> getRequestsList() {
        return this.requestsList;
    }

    public void printReceivedList() {
        int[] i = { 1 };
        System.out.println('\n' + this.getName() + " has received:\n-------------------");
        this.receivedList.forEach((rdl) -> {
            rdl.getrdEntities().forEach((rd) -> {
                System.out.println(i + rd.getEntity().getName() + " - Quantity: " + rd.getQuantity());
                i[0]++;
            });
        });
    }

    public void printRequestsList() {
        int[] i = { 1 };
        System.out.println('\n' + this.getName() + " has requested:\n-------------------");
        this.requestsList.forEach((rdl) -> {
            rdl.getrdEntities().forEach((rd) -> {
                System.out.println(i + rd.getEntity().getName() + " - Quantity: " + rd.getQuantity());
                i[0]++;
            });
        });
    }

    public void commitRequest(Requests rq, Organization org) {
        rq.getrdEntities().forEach((rd) -> {
            rq.commit(rd, this, org);
        });
    }

    public void commitRequestsList(Organization org) {
        this.requestsList.forEach((rq) -> {
            this.commitRequest(rq, org);
        });
    }

    public void removeRequest(Requests rq) {
        rq.getrdEntities().forEach((rd) -> {
            rq.remove(rd);
        });
    }

    public void modifyRequest(Requests rq, RequestDonation rd, double quantity, Organization org)
            throws DeservedAmountException, FalseAmountException {
        rq.modify(rd, this, quantity, org);
    }
}
