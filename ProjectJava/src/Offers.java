import java.util.*;

public class Offers extends RequestDonationList {

    public Offers(Entity entity, double quantity) {
        RequestDonation rd = new RequestDonation(entity, quantity);
        this.rdEntities.add(rd);
    }

    public void commit(Organization org) {
        Iterator<RequestDonation> itr = rdEntities.iterator();
        boolean flagComp = false;
        if (itr.hasNext()) {
            RequestDonation rd = itr.next();
            org.addDonation(rd);
            flagComp = true;
        }
        if (flagComp) {
            rdEntities.removeAll(rdEntities);
        }
    }
}