import java.util.*;

public class Offers extends RequestDonationList {

    public Offers(Entity entity, double quantity) {
        RequestDonation rd = new RequestDonation(entity, quantity);
        this.getrdEntities().add(rd);
    }

    public void commit(Organization org) {
        Iterator<RequestDonation> itr = getrdEntities().iterator();
        boolean flagComp = false;
        if (itr.hasNext()) {
            RequestDonation rd = itr.next();
            org.addDonation(rd);
            flagComp = true;
        }
        if (flagComp) {
            getrdEntities().removeAll(getrdEntities());
        }
    }

}