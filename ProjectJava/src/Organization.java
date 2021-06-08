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

    public ArrayList<Donator> getDonatorList() {
        return this.donatorList;
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
        int[] valid = { 0 };
        this.entityList.forEach((ent) -> {
            if (ent.getID() == entity.getID()) {
                try {
                    throw new FalseIdException();
                } catch (FalseIdException fie) {
                    fie.getExceptionInfo();
                }
            } else if (ent.getName() == entity.getName()) {
                try {
                    throw new AlreadyExistingEntityException();
                } catch (AlreadyExistingEntityException a3e) {
                    a3e.getExceptionInfo();
                }
            } else {
                valid[0] = 1;
            }
        });
        if (valid[0] == 1) {
            this.entityList.add(entity);
        }
    }

    public void removeEntity(Entity entity) {
        this.entityList.remove(entity);
    }

    public void addDonator(Donator donator) {
        this.donatorList.add(donator);
    }

    public void removeDonator(Donator donator) {
        this.donatorList.remove(donator);
    }

    public void addBeneficiary(Beneficiary ben) {
        this.beneficiaryList.add(ben);
    }

    public void removeBeneficiary(Beneficiary ben) {
        this.beneficiaryList.remove(ben);
    }

    public void listEntities() {
        System.out.println("Materials:\n----------");
        this.entityList.forEach((ent) -> {
            if (ent.getClass().getName() == "Material") {
                System.out.println(ent.getName());
            }
        });
        System.out.println("\nServices:\n----------");
        this.entityList.forEach((ent) -> {
            if (ent.getClass().getName() == "Service") {
                System.out.println(ent.getName());
            }
        });
    }

    public void listBeneficiaries() {
        System.out.println("Beneficiaries:\n-------------");
        int[] i = { 1 };
        this.beneficiaryList.forEach((ben) -> {
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
        this.donatorList.forEach((don) -> {
            System.out.println(i[0] + ". " + don.getName());
            i[0]++;
        });
    }

    public void listMaterials() {
        int[] i = { 1 };
        System.out.println("\nMaterials:\n----------");
        this.currentDonations.forEach((rd) -> {
            if (rd.getEntity().getClass().getName() == "Material") {
                System.out.println(i[0] + ". " + rd.getEntity().getName() + " (" + rd.getQuantity() + ")");
                i[0]++;
            }
        });
    }

    public void listServices() {
        int[] i = { 1 };
        System.out.println("\nServices:\n---------");
        this.currentDonations.forEach((rd) -> {
            if (rd.getEntity().getClass().getName() == "Service") {
                System.out.println(i[0] + ". " + rd.getEntity().getName() + " (" + rd.getQuantity() + ")");
                i[0]++;
            }
        });
    }
}