import java.util.*;

public class Organization {

    String name;
    Admin admin;

    ArrayList<RequestDonation> currentDonations = new ArrayList<RequestDonation>();
    ArrayList<Beneficiary> beneficiaryList = new ArrayList<Beneficiary>();
    ArrayList<Donator> donatorList = new ArrayList<Donator>();
    ArrayList<Entity> entityList = new ArrayList<Entity>();

    public ArrayList<RequestDonation> getCurrentDonations() {
        return this.currentDonations;
    }

    public void addDonation(RequestDonation e) {
        this.currentDonations.add(e);
    }

    public void setAdmin(String name, String phone) {
        admin = new Admin(name, phone, true);
    }

    public Admin getAdmin() {
        return admin;
    }

    public ArrayList<Entity> getEntityList() {
        return this.entityList;
    }

    public void addEntity(Entity entity) {
        entityList.add(entity);
    }

    public void removeEntity(Entity entity) {
        entityList.remove(entity);
    }

    public void insertDoantor(Donator donator) {
        donatorList.add(donator);
    }

    public void removeDonator(Donator donator) {
        donatorList.remove(donator);
    }

    public void insertBeneficiary(Beneficiary ben) {
        beneficiaryList.add(ben);
    }

    public void removeBeneficiary(Beneficiary ben) {
        beneficiaryList.remove(ben);
    }

    public void listEntities() {
        System.out.println("Materials:\n----------------------\n");
        entityList.forEach((ent) -> {
            if (ent.getDescription() == "Material") {
                System.out.println(ent.getName());
            }
        });
        entityList.forEach((ent) -> {
            if (ent.getDescription() == "Service") {
                System.out.println(ent.getName());
            }
        });
    }

    public void listBeneficiaries() {
        System.out.println("Beneficiaries:\n-----------------------\n");
        beneficiaryList.forEach((ben) -> {
            System.out.println(ben.getName());
            ben.receivedList.forEach((rcl) -> {
                rcl.rdEntities.forEach((rd) -> {
                    if (rd.getEntity().getDescription() == "Material") {
                        System.out.println(rd.getEntity().getName());
                    }
                });
                rcl.rdEntities.forEach((rd) -> {
                    if (rd.getEntity().getDescription() == "Service") {
                        System.out.println(rd.getEntity().getName());
                    }
                });
            });
        });
    }

    public void listDonators() {
        System.out.println("Donators:\n-----------------------\n");
        donatorList.forEach((don) -> {
            System.out.println(don.getName());
        });
    }

}