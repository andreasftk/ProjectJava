import java.util.*;

public class Organization {

    private String name;
    private Admin admin;

    private ArrayList<RequestDonation> currentDonations = new ArrayList<RequestDonation>();
    private ArrayList<Beneficiary> beneficiaryList = new ArrayList<Beneficiary>();
    private ArrayList<Donator> donatorList = new ArrayList<Donator>();
    private ArrayList<Entity> entityList = new ArrayList<Entity>();

    public String getName() {
        return this.name;
    }

    public Organization(String name) {
        this.name = name;
    }

    public ArrayList<RequestDonation> getCurrentDonations() {
        return this.currentDonations;
    }

    public ArrayList<Beneficiary> getBeneficiaryList() {
        return this.beneficiaryList;
    }

    public void addDonation(RequestDonation e) {
        this.currentDonations.add(e);
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Admin getAdmin() {
        return this.admin;
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

    public void insertDonator(Donator donator) {
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
        System.out.println("Materials:\n----------");
        entityList.forEach((ent) -> {
            if (ent.getClass().getName() == "Material") {
                System.out.println(ent.getName());
            }
        });
        System.out.println("\nServices:\n----------");
        entityList.forEach((ent) -> {
            if (ent.getClass().getName() == "Service") {
                System.out.println(ent.getName());
            }
        });
    }

    public void listBeneficiaries() {
        System.out.println("Beneficiaries:\n-------------");
        int[] i = { 1 };
        beneficiaryList.forEach((ben) -> {
            System.out.println(i[0] + ". " + ben.getName());
            ben.getReceivedList().forEach((rcl) -> {
                rcl.getrdEntities().forEach((rd) -> {
                    if (rd.getEntity().getDescription() == "Material") {
                        System.out.println(rd.getEntity().getName());
                    }
                });
                rcl.getrdEntities().forEach((rd) -> {
                    if (rd.getEntity().getDescription() == "Service") {
                        System.out.println(rd.getEntity().getName());
                    }
                });
            });
            i[0]++;
        });
    }

    public void listDonators() {
        System.out.println("Donators:\n----------");
        int[] i = { 1 };
        donatorList.forEach((don) -> {
            System.out.println(i[0] + ". " + don.getName());
            i[0]++;
        });
    }

    public void listMaterials() {
        int[] i = { 1 };
        System.out.println("\nMaterials:\n----------");
        currentDonations.forEach((rd) -> {
            if (rd.getEntity().getClass().getName() == "Material") {
                System.out.println(i[0] + ". " + rd.getEntity().getName() + " (" + rd.getQuantity() + ")");
                i[0]++;
            }
        });
    }

    public void listServices() {
        int[] i = { 1 };
        System.out.println("\nServices:\n---------");
        currentDonations.forEach((rd) -> {
            if (rd.getEntity().getClass().getName() == "Service") {
                System.out.println(i[0] + ". " + rd.getEntity().getName() + " (" + rd.getQuantity() + ")");
                i[0]++;
            }
        });
    }

    public ArrayList<Donator> getDonatorList() {
        return this.donatorList;
    }

}