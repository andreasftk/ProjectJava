import java.util.*;

public class Donator extends User {

    public Donator(String name, String phone) {
        super(name, phone);
    }

    ArrayList<Offers> offersList = new ArrayList<Offers>();

    public RequestDonation get(Offers offer) {
        RequestDonation[] rdtemp = {};
        int[] id = {};

        this.offersList.forEach((off) -> {
            off.rdEntities.forEach((rdn0) -> {
                offer.rdEntities.forEach((rdn1) -> {
                    if (rdn0.getEntity().getName() == rdn1.getEntity().getName()) {
                        id[0] = rdn0.getEntity().getID();
                    }
                });
            });
        });
        this.offersList.forEach((off) -> {
            off.rdEntities.forEach((rdn0) -> {
                if (rdn0.getEntity().getID() == id[0]) {
                    rdtemp[0] = rdn0;
                }
            });
        });
        return rdtemp[0];
    }

    public void add(Offers offer) {
        offersList.add(offer);
    }

    public void remove(Offers offer) {
        offersList.remove(offer);
    }

    public void commit(Offers offer, Organization org) {
        // ελέγχει αν υπάρχει στιγμιότυπο Offers στην offersList, το οποίο ταυτίζεται με
        // το όρισμα offer που δίνουμε, και αν ισχύει, κάνει commit το offer αυτό.
        this.offersList.forEach((off) -> {
            if (off == offer) {
                offer.commit(org);
            }
        });
    }
}
