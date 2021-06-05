import java.util.ArrayList;

public class Requests extends RequestDonationList {

    private double requestAmount = 0;

    public Requests(Entity entity, double quantity) {
        RequestDonation rd = new RequestDonation(entity, quantity);
    }

    public Requests() {
    }

    public void add(RequestDonation rd, Beneficiary ben, Organization org)
            throws DeservedAmountException, FalseAmountException {

        boolean[] flag0 = { false };
        boolean flag1 = false;
        rdEntities.forEach((rdn) -> {
            org.getEntityList().forEach((ent) -> {
                if (ent == rdn.getEntity()) {
                    flag0[0] = true;
                }
            });
        });

        if (validRequestDonation(rd, ben, org)) {
            flag1 = true;
        }

        if (flag0[0] && flag1) {
            rdEntities.add(rd);
        }

        if (!flag0[0]) {
            throw new FalseAmountException();
        } else if (!flag1) {
            throw new DeservedAmountException();
        }
    }

    public void modify(RequestDonation rd, Beneficiary ben, double quantity, Organization org)
            throws DeservedAmountException, FalseAmountException {

        boolean[] flag0 = { false };
        boolean flag1 = false;
        rdEntities.forEach((rdn) -> {
            org.getEntityList().forEach((ent) -> {
                if (rdn.getEntity() == ent) {
                    flag0[0] = true;
                }
            });
        });

        if (validRequestDonation(rd, ben, org)) {
            flag1 = true;
        }

        if (flag0[0] && flag1) {
            ben.requestsList.forEach((rq) -> {
                rq.rdEntities.forEach((rdn) -> {
                    if (rdn.getEntity() == rd.getEntity()) {
                        rq.modify(rd, quantity);
                    }
                });
            });
        }

        if (!flag0[0]) {
            throw new FalseAmountException();
        } else if (!flag1) {
            throw new DeservedAmountException();
        }
    }

    public boolean validRequestDonation(RequestDonation rd, Beneficiary ben, Organization org) {
        boolean valid = false;
        if (rd.getEntity().getClass().getName() == "Material") {
            double level = 0;

            if (ben.getNoPersons() == 1) {
                level = ((Material) rd.getEntity()).getLvl1();
            } else if (ben.getNoPersons() >= 2 && ben.getNoPersons() <= 4) {
                level = ((Material) rd.getEntity()).getLvl2();
            } else {
                level = ((Material) rd.getEntity()).getLvl3();
            }
            ben.receivedList.forEach((rc) -> {
                rc.rdEntities.forEach((rdn) -> {
                    if (rdn.getEntity() == rd.getEntity()) {
                        rc.setReceivedAmount(rd.getQuantity());
                    }
                });
            });

            ben.requestsList.forEach((rq) -> {
                rq.rdEntities.forEach((rdn) -> {
                    if (rdn.getEntity() == rd.getEntity()) {
                        requestAmount += rd.getQuantity();
                    }
                });
            });

            if (this.getReceivedAmount() + requestAmount < level) {
                valid = true;
            } else {
                valid = false;
            }
        } else if (rd.getEntity().getClass().getName() == "Service") {
            valid = true;
        }
        return valid;
    }

    public double getReceivedAmount() {
        return super.getReceivedAmount();
    }

    public void commit(RequestDonation rd, Beneficiary ben, Organization org) {

        boolean[] flag0 = { false };
        boolean flag1 = false;
        rdEntities.forEach((rdn) -> {
            org.getEntityList().forEach((ent) -> {
                if (rdn.getEntity() == ent) {
                    flag0[0] = true;
                }
            });
        });

        if (validRequestDonation(rd, ben, org)) {
            flag1 = true;
        }

        if (flag0[0] && flag1) {
            ArrayList<RequestDonation> currentDonations;
            currentDonations = org.getCurrentDonations();
            currentDonations.forEach((rdn) -> {
                if (rdn.getEntity() == rd.getEntity()) {
                    rdn.setQuantity(0);
                }
            });
            this.rdEntities.remove(rd);
            ben.receivedList.get(ben.receivedList.size()).rdEntities.add(rd);
        }
    }
}