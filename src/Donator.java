import java.util.*;

public class Donator extends User {

    public Donator(String name, String phone, Organization org) {
        super(name, phone);
        org.insertDonator(this);
    }

    private ArrayList<Offers> offersList = new ArrayList<Offers>();

    public RequestDonation get(Offers offer) {
        RequestDonation[] rdtemp = {};
        int[] id = {};

        this.offersList.forEach((off) -> {
            off.getrdEntities().forEach((rdn0) -> {
                offer.getrdEntities().forEach((rdn1) -> {
                    if (rdn0.getEntity().getName() == rdn1.getEntity().getName()) {
                        id[0] = rdn0.getEntity().getID();
                    }
                });
            });
        });
        this.offersList.forEach((off) -> {
            off.getrdEntities().forEach((rdn0) -> {
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

    public void commitOffer(Offers offer, Organization org) {
        // ελέγχει αν υπάρχει στιγμιότυπο Offers στην offersList, το οποίο ταυτίζεται με
        // το όρισμα offer που δίνουμε, και αν ισχύει, κάνει commit το offer αυτό.
        this.offersList.forEach((off) -> {
            if (off == offer) {
                offer.commit(org);
            }
        });
    }

    public ArrayList<Offers> getOffersList() {
        return this.offersList;
    }

    public void printOffersList() {
        int[] i = { 1 };
        System.out.println('\n' + this.getName() + " has offered:\n-------------------");
        this.offersList.forEach((rdl) -> {
            rdl.getrdEntities().forEach((rd) -> {
                System.out.println(i + rd.getEntity().getName() + " - Quantity: " + rd.getQuantity());
                i[0]++;
            });
        });
    }

    public void commitOffersList(Organization org) {
        this.offersList.forEach((rq) -> {
            this.commitOffer(rq, org);
        });
    }

    public void modifyOffer(Offers off, RequestDonation rd, double quantity) {
        off.modify(rd, quantity);
    }

}
