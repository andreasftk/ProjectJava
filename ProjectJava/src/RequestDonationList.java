import java.util.*;

public class RequestDonationList {

    ArrayList<RequestDonation> rdEntities = new ArrayList<RequestDonation>();
    private double receivedAmount = 0;

    public RequestDonation get(int id) {
        Iterator<RequestDonation> iter0 = rdEntities.iterator();
        while (iter0.hasNext()) {
            if (iter0.next().getEntity().getID() == id) {
                break;
            }
        }
        return iter0.next();
    }

    public void add(RequestDonation rd) throws AlreadyExistingEntityException {
        for (RequestDonation rdobj : rdEntities) {
            if (rd != rdobj) {
                throw new AlreadyExistingEntityException();
            } else {
                rdEntities.add(rd);
            }
        }
    }

    public void remove(RequestDonation rd) {
        rdEntities.remove(rd);
    }

    public void modify(RequestDonation rd, double quantity) {
        Iterator<RequestDonation> iter1 = this.rdEntities.iterator();
        if (iter1.next() == rd) {
            rd.setQuantity(quantity);
        }
    }

    public void monitor() {
        for (RequestDonation rd : rdEntities) {
            System.out.println("Entity: " + rd.getEntity() + ", Quantity: " + rd.getQuantity());
        }
    }

    public void reset() {
        rdEntities.clear();
    }

    public void setReceivedAmount(double amount) {
        this.receivedAmount = amount;
    }

    public double getReceivedAmount() {
        return this.receivedAmount;
    }
}