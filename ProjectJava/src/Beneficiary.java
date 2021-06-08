import java.util.*;

public class Beneficiary extends User {
    private int noPersons = 1;

    public Beneficiary(String name, String phone, Organization org) {
        super(name, phone);
        org.addBeneficiary(this);
    }

    public Beneficiary() {
    }

    private ArrayList<RequestDonationList> receivedList = new ArrayList<RequestDonationList>();
    private ArrayList<Requests> requestsList = new ArrayList<Requests>();

    public int getNoPersons() {
        return this.noPersons;
    }

    public void addRequest(Requests request) {
        this.requestsList.add(request);
    }

    public void addReceived(Requests request) {
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
                System.out.println(i[0] + rd.getEntity().getName() + " - Quantity: " + rd.getQuantity());
                i[0]++;
            });
        });
    }

    public void printRequestsList() {
        int[] i = { 1 };
        System.out.println('\n' + this.getName() + " has requested:\n-------------------");
        this.requestsList.forEach((rdl) -> {
            rdl.getrdEntities().forEach((rd) -> {
                System.out.println(i[0] + "." + rd.getEntity().getName() + " - Quantity: " + rd.getQuantity());
                i[0]++;
            });
        });
    }

    public void commit(Requests rq, Organization org) {
        rq.getrdEntities().forEach((rd) -> {
            rq.commit(rd, this, org);
        });
    }

    public void commit(Organization org) {
        this.requestsList.forEach((rq) -> {
            this.commit(rq, org);
        });
    }

    public void remove(Requests rq) {
        rq.getrdEntities().forEach((rd) -> {
            rq.remove(rd);
        });
    }

    public void modify(Requests rq, RequestDonation rd, double quantity, Organization org)
            throws DeservedAmountException, FalseAmountException {
        rq.modify(rd, this, quantity, org);
    }
}
