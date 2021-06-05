public class Main {
    public static void main(String Args[]) {

        Organization org = new Organization();

        Entity Milk = new Material("Milk", "ugro", 155, 1, 2, 4);
        Entity Sugar = new Material("Sugar", "G", 175, 0.5, 1, 2.5);
        Entity Rice = new Material("Rice", "fagito", 643, 0.5, 1, 2);

        org.entityList.add(Milk);
        org.entityList.add(Sugar);
        org.entityList.add(Rice);

        Service medicalSupport = new Service("Medical Support", "Medical Help", 134);
        Service nurserySupport = new Service("Nursery Support", "upostiri3i", 899);
        Service babySitting = new Service("Baby Sitting", "paidiki apasxolisi", 533);

        org.entityList.add(medicalSupport);
        org.entityList.add(nurserySupport);
        org.entityList.add(babySitting);

        Admin admin = new Admin("Bob", "6946075399", true);
        Beneficiary ben1 = new Beneficiary("Kostas", "6954702137");
        Beneficiary ben2 = new Beneficiary("Julie", "6931223146");
        Donator don = new Donator("Vaggelis", "6932123245");

        Offers milk = new Offers(Milk, 5);
        Offers sugar = new Offers(Sugar, 2);
        Offers babysitting = new Offers(babySitting, 3);

        don.add(milk);
        don.commit(milk, org);
        don.add(sugar);
        don.commit(sugar, org);
        don.add(babysitting);
        don.commit(babysitting, org);
        
        Requests rq1 = new Requests();
        rq1.add(Milk, ben1, org)

        ben1.add();
        ben1.add();
    }
}