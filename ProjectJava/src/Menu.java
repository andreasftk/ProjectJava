import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;

public class Menu {

    public static void login(BufferedReader reader, Organization org) throws IOException {
        System.out.print("Enter your phone number: ");
        String phone = reader.readLine();

        if (phone.equals(org.getAdmin().getPhone())) {
            System.out.println(
                    "Welcome, Admin " + org.getAdmin().getName() + "\nPhone Number: " + org.getAdmin().getPhone());
            runAdminStarterMenu();
            int choice = Integer.parseInt(reader.readLine());
            runAdminMenu(choice, reader, org);

            while (choice != 1 && choice != 2 && choice != 3 && choice != 4) {
                try {
                    throw new MenuNavigationException();
                } catch (MenuNavigationException mne) {
                    mne.getExceptionInfo();
                    runAdminStarterMenu();
                    choice = Integer.parseInt(reader.readLine());
                    runAdminMenu(choice, reader, org);
                }
            }
        } else if (phone.equals(scanBeneficiaryList(phone, reader, org).getPhone())) {
            System.out.println("Welcome to " + org.getName() + ",\nBeneficiary "
                    + scanBeneficiaryList(phone, reader, org).getName() + "\nPhone Number: "
                    + scanBeneficiaryList(phone, reader, org).getPhone());
            runBeneficiaryStarterMenu();
            int choice = Integer.parseInt(reader.readLine());

            if (choice == 1 || choice == 2 || choice == 3 || choice == 4) {
                runBeneficiaryMenu(choice, reader, scanBeneficiaryList(phone, reader, org), org);
            } else {
                try {
                    throw new MenuNavigationException();
                } catch (MenuNavigationException mne) {
                    mne.getExceptionInfo();
                    choice = Integer.parseInt(reader.readLine());
                    runBeneficiaryMenu(choice, reader, scanBeneficiaryList(phone, reader, org), org);
                }
            }
        } else if (phone.equals(scanDonatorList(phone, reader, org).getPhone())) {
            System.out.println(
                    "Welcome to " + org.getName() + ",\nDonator " + scanDonatorList(phone, reader, org).getName()
                            + "\nPhone Number: " + scanDonatorList(phone, reader, org).getPhone());
            runDonatorStarterMenu();
            int choice = Integer.parseInt(reader.readLine());

            if (choice != 1 && choice != 2 && choice != 3 && choice != 4) {
                try {
                    throw new MenuNavigationException();
                } catch (MenuNavigationException mne) {
                    mne.getExceptionInfo();
                    choice = Integer.parseInt(reader.readLine());
                    runDonatorMenu(choice, reader, scanDonatorList(phone, reader, org), org);
                }
            } else {
                runDonatorMenu(choice, reader, scanDonatorList(phone, reader, org), org);
            }
        } else {
            System.out.println("This phone number is not registered. Would you like to register as: ");
            System.out.println("\n1. Beneficiary\n2. Donator\n3. No, Exit");
            int choice = Integer.parseInt(reader.readLine());
            if (choice == 1) {
                System.out.println("Please enter your name: ");
                String name = reader.readLine();
                System.out.println("Please enter your phone number: ");
                String phone1 = reader.readLine();
                Beneficiary ben = new Beneficiary(name, phone1, org);
                runBeneficiaryStarterMenu();
                int choice1 = Integer.parseInt(reader.readLine());
                runBeneficiaryMenu(choice1, reader, ben, org);
            } else if (choice == 2) {
                System.out.println("Please enter your name: ");
                String name = reader.readLine();
                System.out.println("Please enter your phone number: ");
                String phone1 = reader.readLine();
                Donator don = new Donator(name, phone1, org);
                runDonatorStarterMenu();
                int choice1 = Integer.parseInt(reader.readLine());
                runDonatorMenu(choice1, reader, don, org);
            } else {
                System.out.println("\nThank you for using our program. The process will now terminate.\n");
                System.exit(0);
            }
        }
    }

    public static void runAdminStarterMenu() {
        System.out.println("\nAvailable Actions:\n------------------");
        System.out.println("1. View\n2. Monitor Organization\n3. Log Out\n4. Exit");
        System.out.print("\nChoose an option based on option number: ");
    }

    public static void runBeneficiaryStarterMenu() {
        System.out.println("\nAvailable Actions:\n------------------");
        System.out.println("1. Add Request\n2. Show Requests\n3. Commit\n4. Log Out\n5. Exit");
        System.out.print("\nChoose an option based on option number: ");
    }

    public static void runDonatorStarterMenu() {
        System.out.println("\nAvailable Actions:\n------------------");
        System.out.println("1. Add Offer\n2. Show Offers\n3. Commit\n4. Log Out\n5. Exit");
        System.out.print("\nChoose an option based on option number: ");
    }

    public static void runAdminMenu(int choice, BufferedReader reader, Organization org) throws IOException {
        switch (choice) {
            case 1:
                runViewMenu(reader, org);
                break;
            case 2:
                runMonitorMenu(reader, org);
                break;
            case 3:
                System.out.println("\nThank you for using our program. You will be logged out in 3 seconds.\n");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                login(reader, org);
                break;
            case 4:
                System.out.println("\nThank you for using our program. The process will now terminate.\n");
                System.exit(0);
                break;
            default:
                try {
                    throw new MenuNavigationException();
                } catch (MenuNavigationException mne) {
                    mne.getExceptionInfo();
                    runAdminStarterMenu();
                    choice = Integer.parseInt(reader.readLine());
                    runAdminMenu(choice, reader, org);
                }

        }
    }

    private static void runBeneficiaryMenu(int choice, BufferedReader reader, Beneficiary ben, Organization org)
            throws IOException {
        switch (choice) {
            case 1:
                System.out.println("\nAvailable Entities:\n-----------------");
                System.out.println("1. Materials");
                System.out.println("2. Services");
                System.out.println("3. Back");
                System.out.print("\nChoose a category based on number: ");

                int choice1 = Integer.parseInt(reader.readLine());

                if (choice1 == 1) {
                    org.listMaterials();
                    System.out.print("\nSelect an entity you would like to request: ");
                    int choice2 = Integer.parseInt(reader.readLine());
                    int flag = 0;
                    ListIterator<RequestDonation> itr = org.getCurrentDonations().listIterator();
                    String str = "Material";
                    while (flag != choice2) {
                        while (itr.hasNext()) {
                            if (str.compareTo(itr.next().getEntity().getClass().getName()) == 0) {
                                flag++;
                            }
                        }
                    }
                    itr.previous();
                    double quantity = itr.next().getQuantity();
                    itr.previous();
                    RequestDonation rd = new RequestDonation(itr.next().getEntity(), quantity);
                    Requests rq = new Requests(rd);

                    ben.addRequest(rq);

                    System.out.println("\n1. Back\n2. Exit");
                    System.out.print("\nChoose an action based on action number: ");
                    int choice3 = Integer.parseInt(reader.readLine());
                    if (choice3 == 1) {
                        runBeneficiaryMenu(choice3, reader, ben, org);
                    } else if (choice3 == 2) {
                        System.out.println("\nThank you for using our program. The process will now terminate.\n");
                        System.exit(0);
                    } else {
                        try {
                            throw new MenuNavigationException();
                        } catch (MenuNavigationException mne) {
                            mne.getExceptionInfo();
                            runBeneficiaryStarterMenu();
                            choice3 = Integer.parseInt(reader.readLine());
                            runBeneficiaryMenu(choice3, reader, ben, org);
                        }
                    }
                } else if (choice1 == 2) {
                    org.listServices();
                    System.out.print("\nSelect a service you would like to request: ");
                    int choice2 = Integer.parseInt(reader.readLine());
                    int flag = 0;
                    ListIterator<RequestDonation> itr = org.getCurrentDonations().listIterator();
                    String str = "Service";
                    while (flag != choice2) {
                        while (itr.hasNext()) {
                            if (str.compareTo(itr.next().getEntity().getClass().getName()) == 0) {
                                flag++;
                            }
                        }
                    }
                    itr.previous();
                    double quantity = itr.next().getQuantity();
                    itr.previous();
                    RequestDonation rd = new RequestDonation(itr.next().getEntity(), quantity);
                    Requests rq = new Requests(rd);
                    ben.addRequest(rq);
                    System.out.println("\n1. Back\n2. Exit");
                    System.out.print("\nChoose an action based on action number: ");
                    int choice3 = Integer.parseInt(reader.readLine());
                    if (choice3 == 1) {
                        runBeneficiaryMenu(choice3, reader, ben, org);
                    } else if (choice3 == 2) {
                        System.out.println("\nThank you for using our program. The process will now terminate.\n");
                        System.exit(0);
                    } else {
                        try {
                            throw new MenuNavigationException();
                        } catch (MenuNavigationException mne) {
                            mne.getExceptionInfo();
                            runBeneficiaryStarterMenu();
                            choice3 = Integer.parseInt(reader.readLine());
                            runBeneficiaryMenu(choice3, reader, ben, org);
                        }
                    }
                } else if (choice1 == 3) {
                    runBeneficiaryStarterMenu();
                    int choice2 = Integer.parseInt(reader.readLine());
                    runBeneficiaryMenu(choice2, reader, ben, org);
                } else if (choice1 != 1 && choice1 != 2 && choice1 != 3) {
                    try {
                        throw new MenuNavigationException();
                    } catch (MenuNavigationException mne) {
                        mne.getExceptionInfo();
                    }
                    runBeneficiaryStarterMenu();
                    int choice2 = Integer.parseInt(reader.readLine());
                    runBeneficiaryMenu(choice2, reader, ben, org);
                }
                break;
            case 2:
                ben.printRequestsList();
                System.out.println("\n1. Select a request\n2. Delete all requests");
                int choice2 = Integer.parseInt(reader.readLine());
                if (choice2 == 1) {
                    int choice3 = Integer.parseInt(reader.readLine());
                    int flag = 0;
                    ListIterator<Requests> itr = ben.getRequestsList().listIterator();
                    String str = "Material";
                    ListIterator<RequestDonation> itr0 = itr.next().getrdEntities().listIterator();
                    while (flag != choice3) {
                        while (itr.hasNext()) {
                            ListIterator<RequestDonation> itr1 = itr.next().getrdEntities().listIterator();
                            if (str.compareTo(itr1.next().getClass().getName()) == 0) {
                                flag++;
                                itr0 = itr1;
                            }
                        }
                    }
                    itr.previous();
                    itr0.previous();

                    System.out.println("\n1. Delete Request\n2. Modify Request\n3. Commit Request");
                    int choice4 = Integer.parseInt(reader.readLine());

                    if (choice4 == 1) {
                        ben.getRequestsList().remove(itr.next());
                        System.out.println("\nThe selected request has been deleted.");
                    } else if (choice4 == 2) {
                        System.out.print("\nPlease enter new quantity of selected request: ");
                        int quantity = Integer.parseInt(reader.readLine());
                        try {
                            ben.modify(itr.next(), itr0.next(), quantity, org);
                        } catch (DeservedAmountException dae) {
                            dae.getExceptionInfo();
                        } catch (FalseAmountException fae) {
                            fae.getExceptionInfo();
                        }
                    }

                    System.out.println("\n Do you want to go back? If so press 0: ");
                    int choice5 = Integer.parseInt(reader.readLine());
                    if (choice5 == 0) {
                        runBeneficiaryMenu(choice5, reader, ben, org);
                    } else {
                        try {
                            throw new MenuNavigationException();
                        } catch (MenuNavigationException mne) {
                            mne.getExceptionInfo();
                            runBeneficiaryStarterMenu();
                            choice5 = Integer.parseInt(reader.readLine());
                            runBeneficiaryMenu(choice5, reader, ben, org);
                        }
                    }
                } else if (choice2 == 2) {
                    ben.getRequestsList().clear();
                    System.out.println("\nThe requests list will be cleared. All requests will be deleted.");
                }
                break;
            case 3:
                ben.commit(org);
                System.out.println("\nAll requests have been commited.");
                break;
            case 4:
                System.out.println("\nThank you for using our program. You will be logged out in 3 seconds.\n");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                login(reader, org);
                break;
            case 5:
                System.out.println("\nThank you for using our program. The process will now terminate.\n");
                System.exit(0);
                break;
            default:
                try {
                    throw new MenuNavigationException();
                } catch (MenuNavigationException mne) {
                    mne.getExceptionInfo();
                    runBeneficiaryStarterMenu();
                    choice = Integer.parseInt(reader.readLine());
                    runBeneficiaryMenu(choice, reader, ben, org);
                }
        }
    }

    public static void runDonatorMenu(int choice, BufferedReader reader, Donator don, Organization org)
            throws IOException {
        switch (choice) {
            case 1:
                System.out.println("\nAvailable Entities:\n-----------------");
                System.out.println("1. Materials");
                System.out.println("2. Services");
                System.out.println("3. Back");
                System.out.print("\nChoose a category based on number: ");

                int choice1 = Integer.parseInt(reader.readLine());

                if (choice1 == 1) {
                    org.listMaterials();
                    System.out.print("\nSelect an entity you would like to donate: ");
                    int choice2 = Integer.parseInt(reader.readLine());
                    int flag = 0;
                    ListIterator<RequestDonation> itr = org.getCurrentDonations().listIterator();
                    String str = "Material";
                    while (flag != choice2) {
                        while (itr.hasNext()) {
                            if (str.compareTo(itr.next().getEntity().getClass().getName()) == 0) {
                                flag++;
                            }
                        }
                    }
                    itr.previous();
                    double quantity = itr.next().getQuantity();
                    itr.previous();
                    RequestDonation rd = new RequestDonation(itr.next().getEntity(), quantity);
                    Offers rq = new Offers(rd);
                    don.add(rq);
                    System.out.println("\n1. Back\n2. Exit");
                    System.out.print("\nChoose an action based on action number: ");
                    int choice3 = Integer.parseInt(reader.readLine());
                    if (choice3 == 1) {
                        runDonatorMenu(choice3, reader, don, org);
                    } else if (choice3 == 2) {
                        System.out.println("\nThank you for using our program. The process will now terminate.\n");
                        System.exit(0);
                    } else {
                        try {
                            throw new MenuNavigationException();
                        } catch (MenuNavigationException mne) {
                            mne.getExceptionInfo();
                            runBeneficiaryStarterMenu();
                            choice3 = Integer.parseInt(reader.readLine());
                            runDonatorMenu(choice3, reader, don, org);
                        }
                    }
                } else if (choice1 == 2) {
                    org.listServices();
                    System.out.print("\nSelect a service you would like to request: ");
                    int choice2 = Integer.parseInt(reader.readLine());
                    int flag = 0;
                    ListIterator<RequestDonation> itr = org.getCurrentDonations().listIterator();
                    String str = "Service";
                    while (flag != choice2) {
                        while (itr.hasNext()) {
                            if (str.compareTo(itr.next().getEntity().getClass().getName()) == 0) {
                                flag++;
                            }
                        }
                    }
                    itr.previous();
                    double quantity = itr.next().getQuantity();
                    itr.previous();
                    RequestDonation rd = new RequestDonation(itr.next().getEntity(), quantity);
                    Offers off = new Offers(rd);
                    don.add(off);
                    System.out.println("\n1. Back\n2. Exit");
                    System.out.print("\nChoose an action based on action number: ");
                    int choice3 = Integer.parseInt(reader.readLine());
                    if (choice3 == 1) {
                        runDonatorMenu(choice3, reader, don, org);
                    } else if (choice3 == 2) {
                        System.out.println("\nThank you for using our program. The process will now terminate.\n");
                        System.exit(0);
                    } else {
                        try {
                            throw new MenuNavigationException();
                        } catch (MenuNavigationException mne) {
                            mne.getExceptionInfo();
                            runDonatorStarterMenu();
                            choice3 = Integer.parseInt(reader.readLine());
                            runDonatorMenu(choice3, reader, don, org);
                        }
                    }
                } else if (choice1 == 3) {
                    runDonatorStarterMenu();
                    int choice2 = Integer.parseInt(reader.readLine());
                    runDonatorMenu(choice2, reader, don, org);
                } else if (choice1 != 1 && choice1 != 2 && choice1 != 3) {
                    try {
                        throw new MenuNavigationException();
                    } catch (MenuNavigationException mne) {
                        mne.getExceptionInfo();
                        runDonatorStarterMenu();
                        choice1 = Integer.parseInt(reader.readLine());
                        runDonatorMenu(choice1, reader, don, org);
                    }
                    runDonatorStarterMenu();
                    int choice2 = Integer.parseInt(reader.readLine());
                    runDonatorMenu(choice2, reader, don, org);
                }
                break;

            case 2:
                don.printOffersList();
                System.out.println("\n1. Select a request\n2. Delete all requests\n3. Commit");
                int choice2 = Integer.parseInt(reader.readLine());
                if (choice2 == 1) {
                    int choice3 = Integer.parseInt(reader.readLine());
                    int flag = 0;
                    ListIterator<Offers> itr = don.getOffersList().listIterator();
                    String str = "Material";
                    ListIterator<RequestDonation> itr0 = itr.next().getrdEntities().listIterator();
                    while (flag != choice3) {
                        while (itr.hasNext()) {
                            ListIterator<RequestDonation> itr1 = itr.next().getrdEntities().listIterator();
                            if (str.compareTo(itr1.next().getClass().getName()) == 0) {
                                flag++;
                                itr0 = itr1;
                            }
                        }
                    }
                    itr.previous();
                    itr0.previous();

                    System.out.println("\n1. Delete Offer\n2. Modify Offer\n3. Commit Offer");
                    int choice4 = Integer.parseInt(reader.readLine());

                    if (choice4 == 1) {
                        don.getOffersList().remove(itr.next());
                        System.out.println("\nThe selected offer has been deleted.");
                    } else if (choice4 == 2) {
                        System.out.print("\nPlease enter new quantity of selected offer: ");
                        int quantity = Integer.parseInt(reader.readLine());
                        don.modify(itr.next(), itr0.next(), quantity);
                    }

                    System.out.println("\n Do you want to go back? If so press 0: ");
                    int choice5 = Integer.parseInt(reader.readLine());
                    if (choice5 == 0) {
                        runDonatorMenu(choice5, reader, don, org);
                    } else {
                        try {
                            throw new MenuNavigationException();
                        } catch (MenuNavigationException mne) {
                            mne.getExceptionInfo();
                            runBeneficiaryStarterMenu();
                            choice5 = Integer.parseInt(reader.readLine());
                            runDonatorMenu(choice5, reader, don, org);
                        }
                    }
                } else if (choice2 == 2) {
                    don.getOffersList().clear();
                    System.out.println("\nThe offers list will be cleared.");
                }
                break;
            case 3:
                don.commit(org);
                System.out.println("\nAll offers have been commited.");
                break;
            case 4:
                System.out.println("\nThank you for using our program. You will be logged out in 3 seconds.\n");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                login(reader, org);
                break;
            case 5:
                System.out.println("\nThank you for using our program. The process will now terminate.\n");
                System.exit(0);
                break;
            default:
                System.out.print("\nInvalid action. Please choose again: ");
                choice = Integer.parseInt(reader.readLine());
                runDonatorMenu(choice, reader, don, org);
        }
    }

    public static void runViewMenu(BufferedReader reader, Organization org) throws IOException {
        System.out.println("\nAvailable Entities:\n-----------------");
        System.out.println("1. Materials");
        System.out.println("2. Services");
        System.out.println("3. Back");
        System.out.print("\nChoose an action based on action number: ");

        int choice1 = Integer.parseInt(reader.readLine());

        if (choice1 == 1) {
            org.listMaterials();
            System.out.print("\nChoose a material: ");
            int choice2 = Integer.parseInt(reader.readLine());
            int flag = 0;
            ListIterator<RequestDonation> itr = org.getCurrentDonations().listIterator();
            String str = "Material";
            while (flag != choice2) {
                while (itr.hasNext()) {
                    if (str.compareTo(itr.next().getEntity().getClass().getName()) == 0) {
                        flag++;
                    }
                }
                System.out
                        .println(itr.previous().getEntity().toString() + "\nQuantity: " + itr.previous().getQuantity());
            }
        } else if (choice1 == 2) {
            org.listServices();
            System.out.print("\nChoose a service: ");
            int choice2 = Integer.parseInt(reader.readLine());
            int flag = 0;
            ListIterator<RequestDonation> itr = org.getCurrentDonations().listIterator();
            String str = "Service";
            while (flag != choice2) {
                while (itr.hasNext()) {
                    if (str.compareTo(itr.next().getEntity().getClass().getName()) == 0) {
                        flag++;
                    }
                }
                System.out
                        .println(itr.previous().getEntity().toString() + "\nQuantity: " + itr.previous().getQuantity());
            }
        } else if (choice1 == 3) {
            runAdminStarterMenu();
            choice1 = Integer.parseInt(reader.readLine());
            runAdminMenu(choice1, reader, org);
        } else if (choice1 != 1 && choice1 != 2 && choice1 != 3) {
            System.out.print("\nInvalid action. Please choose again:");
            try {
                throw new MenuNavigationException();
            } catch (MenuNavigationException mne) {
                runAdminStarterMenu();
                choice1 = Integer.parseInt(reader.readLine());
                runAdminMenu(choice1, reader, org);
            }
        }
    }

    public static void runMonitorMenu(BufferedReader reader, Organization org) throws IOException {
        System.out.println("\nAvailable Actions:\n-----------------------------");
        System.out.println("1. Beneficiaries List");
        System.out.println("2. Donators List");
        System.out.println("3. Reset Beneficiaries List");
        System.out.print("\nChoose an action based on action number: ");

        int choice1 = Integer.parseInt(reader.readLine());

        if (choice1 == 1) {
            org.listBeneficiaries();
            System.out.print("\nChoose a Beneficiary: ");
            int choice2 = Integer.parseInt(reader.readLine());
            int flag = 0;
            ListIterator<Beneficiary> itr = org.getBeneficiaryList().listIterator();
            while (flag != choice2) {
                while (itr.hasNext()) {
                    if (itr.next().getClass().getName() == "Beneficiary") {
                        flag++;
                    }
                }
            }
            itr.previous().printReceivedList();

            System.out.println("\nAvailable Actions:\n------------------");
            System.out.println("\n1. Delete Beneficiary's received list\n2. Delete Beneficiary");
            System.out.print("\nChoose action based on action number: ");

            choice2 = Integer.parseInt(reader.readLine());

            if (choice2 == 1) {
                itr.previous();
                itr.next().getReceivedList().clear();
                System.out.println("\nSuccesfully cleared selected Beneficiary's received list.");
            } else if (choice2 == 2) {
                itr.previous();
                org.getBeneficiaryList().remove(itr.next());
                System.out.println("\nSuccesfully removed selected Beneficiary.");
            }
        } else if (choice1 == 2) {
            org.listDonators();
            System.out.print("\nChoose a Donator: ");
            int choice2 = Integer.parseInt(reader.readLine());
            int flag = 0;
            ListIterator<Donator> itr = org.getDonatorList().listIterator();
            while (flag != choice2) {
                while (itr.hasNext()) {
                    if (itr.next().getClass().getName() == "Donator") {
                        flag++;
                    }
                }
            }
            itr.previous().printOffersList();

            System.out.println("\nAvailable Actions:\n------------------");
            System.out.println("\n1. Delete Donator");
            System.out.print("\nChoose action based on action number: ");

            choice2 = Integer.parseInt(reader.readLine());

            if (choice2 == 1) {
                itr.previous();
                org.getDonatorList().remove(itr.next());
                System.out.println("\nSuccesfully removed selected Donator.");
            }

        } else if (choice1 == 3) {
            org.getBeneficiaryList().forEach((ben) -> {
                ben.getReceivedList().clear();
            });
            System.out.println("\nSuccesfully cleared rhe received list ff all Beneficiaries.");

        } else if (choice1 != 1 && choice1 != 2 && choice1 != 3) {
            System.out.print("\nInvalid  action. Please choose again: ");
            choice1 = Integer.parseInt(reader.readLine());
            runAdminMenu(choice1, reader, org);
        }
    }

    public static Beneficiary scanBeneficiaryList(String phone, BufferedReader reader, Organization org) {
        boolean valid = false;
        Beneficiary ben = new Beneficiary();
        ListIterator<Beneficiary> itr0 = org.getBeneficiaryList().listIterator();
        while (!valid) {
            if (itr0.hasNext()) {
                while (itr0.hasNext()) {
                    if (phone.equals(itr0.next().getPhone())) {
                        valid = true;
                        itr0.previous();
                        ben = itr0.next();
                    }
                }
            }
        }
        itr0 = org.getBeneficiaryList().listIterator(org.getBeneficiaryList().size());
        while (!valid) {
            if (itr0.hasPrevious()) {
                while (itr0.hasPrevious()) {
                    if (phone.equals(itr0.previous().getPhone())) {
                        valid = true;
                        itr0.next();
                        ben = itr0.previous();
                    }
                }
            }
        }
        return ben;
    }

    public static Donator scanDonatorList(String phone, BufferedReader reader, Organization org) {
        boolean valid = false;
        Donator don = new Donator();
        ListIterator<Donator> itr1 = org.getDonatorList().listIterator(org.getDonatorList().size());
        while (!valid) {
            if (itr1.hasPrevious()) {
                while (itr1.hasPrevious()) {
                    if (phone.equals(itr1.previous().getPhone())) {
                        valid = true;
                        itr1.next();
                        don = itr1.previous();
                    }
                }
            }
        }
        itr1 = org.getDonatorList().listIterator();
        while (!valid) {
            if (itr1.hasNext()) {
                while (itr1.hasNext()) {
                    if (phone.equals(itr1.next().getPhone())) {
                        valid = true;
                        itr1.previous();
                        don = itr1.next();
                    }
                }
            }
        }
        return don;
    }
}