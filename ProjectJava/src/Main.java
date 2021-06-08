import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String Args[]) {

        Organization org = new Organization("Koinwniko Pantopwleio");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Material Milk = new Material("Milk", "ugro", 155, 1, 2, 4);
        Material Sugar = new Material("Sugar", "G", 175, 0.5, 1, 2.5);
        Material Rice = new Material("Rice", "fagito", 643, 0.5, 1, 2);

        org.getEntityList().add(Sugar);
        org.getEntityList().add(Rice);

        Service medicalSupport = new Service("Medical Support", "Medical Help", 134);
        Service nurserySupport = new Service("Nursery Support", "upostiri3i", 899);
        Service babySitting = new Service("Baby Sitting", "paidiki apasxolisi", 533);

        org.getEntityList().add(medicalSupport);
        org.getEntityList().add(nurserySupport);
        org.getEntityList().add(babySitting);

        Admin admin = new Admin("Bob", "0", true);
        Beneficiary ben1 = new Beneficiary("Kostas", "1", org);
        Beneficiary ben2 = new Beneficiary("Julie", "2", org);
        Beneficiary ben3 = new Beneficiary("kfgh", "7", org);
        Beneficiary ben4 = new Beneficiary("Takis", "8", org);
        Donator don = new Donator("Vaggelis", "3", org);
        Donator don2 = new Donator("Den Exeis Dikio", "4", org);

        org.setAdmin(admin);

        RequestDonation milk = new RequestDonation(Milk, 5);
        RequestDonation babysitting = new RequestDonation(babySitting, 3);
        RequestDonation sugar = new RequestDonation(Sugar, 2);

        Offers babysitting0 = new Offers(babysitting);
        Offers milk0 = new Offers(milk);
        Offers sugar0 = new Offers(sugar);

        don.add(milk0);
        don.commit(milk0, org);
        don.add(sugar0);
        don.commit(sugar0, org);
        don.add(babysitting0);
        don.commit(babysitting0, org);

        Requests milk1 = new Requests(milk);
        Requests babysitting1 = new Requests(babysitting);

        ben2.addReceived(milk1);
        ben2.addRequest(babysitting1);

        try {
            Menu.login(reader, org);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}