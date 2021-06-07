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

        Admin admin = new Admin("Bob", "6946075399", true);
        Beneficiary ben1 = new Beneficiary("Kostas", "6954702137", org);
        Beneficiary ben2 = new Beneficiary("Julie", "6931223146", org);
        Donator don = new Donator("Vaggelis", "6932123245", org);

        org.setAdmin(admin);

        Offers babysitting = new Offers(babySitting, 3);
        Offers milk = new Offers(Milk, 5);
        Offers sugar = new Offers(Sugar, 2);

        don.add(milk);
        don.commitOffer(milk, org);
        don.add(sugar);
        don.commitOffer(sugar, org);
        don.add(babysitting);
        don.commitOffer(babysitting, org);

        Requests milk_ = new Requests(Milk, 1);
        Requests babysitting_ = new Requests(babySitting, 2);

        ben2.addReceived(milk_, org);
        ben2.addRequest(babysitting_, org);

        Menu menu = new Menu();
        try {
            menu.login(reader, org);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}