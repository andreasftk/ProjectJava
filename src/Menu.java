import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;

public class Menu {

    public void login(BufferedReader reader, Organization org) throws IOException {
        System.out.print("Enter your phone number: ");
        String phone = reader.readLine();
        String str = phone;

        if (str.compareTo(org.getAdmin().getPhone()) == 0) {
            System.out.println(
                    "Welcome, Admin " + org.getAdmin().getName() + "\nPhone Number: " + org.getAdmin().getPhone());
            this.runAdminStarterMenu();
            int choice = reader.read();

            if (choice != 1 && choice != 2 && choice != 3 && choice != 4) {
                System.out.print("\nInvalid action. Please choose again: ");
                choice = reader.read();
                this.runAdminMenu(choice, reader, org);
            } else {
                this.runAdminMenu(choice, reader, org);
            }
        } else if (str.compareTo(this.scanBeneficiaryList(str, reader, org).getPhone()) == 0) {
            System.out.println("Welcome to " + org.getName() + ",\nBeneficiary "
                    + this.scanBeneficiaryList(str, reader, org).getName() + "\nPhone Number: "
                    + this.scanBeneficiaryList(str, reader, org).getPhone());
            this.runBeneficiaryStarterMenu();
            int choice = reader.read();

            if (choice != 1 && choice != 2 && choice != 3 && choice != 4) {
                try {
                    throw new MenuNavigationException();
                } catch (MenuNavigationException mne) {
                    mne.getExceptionInfo();
                    choice = reader.read();
                    this.runBeneficiaryMenu(choice, reader, this.scanBeneficiaryList(str, reader, org), org);
                }
            } else {
                this.runBeneficiaryMenu(choice, reader, this.scanBeneficiaryList(str, reader, org), org);
            }
        } else if (str.compareTo(this.scanDonatorList(str, reader, org).getPhone()) == 0) {
            System.out.println(
                    "Welcome to " + org.getName() + ",\nDonator " + this.scanDonatorList(str, reader, org).getName()
                            + "\nPhone Number: " + this.scanDonatorList(str, reader, org).getPhone());
            this.runDonatorStarterMenu();
            int choice = reader.read();

            if (choice != 1 && choice != 2 && choice != 3 && choice != 4) {
                try {
                    throw new MenuNavigationException();
                } catch (MenuNavigationException mne) {
                    mne.getExceptionInfo();
                    choice = reader.read();
                    this.runDonatorMenu(choice, reader, this.scanDonatorList(str, reader, org), org);
                }
            } else {
                this.runDonatorMenu(choice, reader, this.scanDonatorList(str, reader, org), org);
            }
        } else {
            System.out.println("This phone number is not registered. Would you like to register as: ");
            System.out.println("\n1. Beneficiary\n2. Donator\n3. No, Exit");
            int choice = reader.read();
            if (choice == 1) {
                System.out.println("Please enter your name: ");
                String name = reader.readLine();
                System.out.println("Please enter your phone number: ");
                String phone1 = reader.readLine();
                Beneficiary ben = new Beneficiary(name, phone1, org);
                this.runBeneficiaryStarterMenu();
                int choice1 = reader.read();
                this.runBeneficiaryMenu(choice1, reader, ben, org);
            } else if (choice == 2) {
                System.out.println("Please enter your name: ");
                String name = reader.readLine();
                System.out.println("Please enter your phone number: ");
                String phone1 = reader.readLine();
                Donator don = new Donator(name, phone1, org);
                this.runDonatorStarterMenu();
                int choice1 = reader.read();
                this.runDonatorMenu(choice1, reader, don, org);
            } else {
                System.out.println("\nThank you for using our program. The process will now terminate.\n");
                System.exit(0);
            }

        }
    }

    public void runAdminStarterMenu() {
        System.out.println("\nAvailable Actions:\n------------------");
        System.out.println("1. View\n2. Monitor Organization\n3. Log Out\n4. Exit");
        System.out.print("\nChoose an option based on option number: ");
    }

    public void runBeneficiaryStarterMenu() {
        System.out.println("\nAvailable Actions:\n------------------");
        System.out.println("1. Add Request\n2. Show Requests\n3. Commit\n4. Log Out\n5. Exit");
        System.out.print("\nChoose an option based on option number: ");
    }

    public void runDonatorStarterMenu() {
        System.out.println("\nAvailable Actions:\n------------------");
        System.out.println("1. Add Offer\n2. Show Offers\n3. Commit\n4. Log Out\n5. Exit");
        System.out.print("\nChoose an option based on option number: ");
    }

    public void runAdminMenu(int choice, BufferedReader reader, Organization org) throws IOException {
        switch (choice) {
            case 1:
                this.runViewMenu(reader, org);
                break;
            case 2:
                this.runMonitorMenu(reader, org);
                break;
            case 3:
                System.out.println("\nThank you for using our program. You will be logged out in 3 seconds.\n");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.login(reader, org);
                break;
            case 4:
                System.out.println("\nThank you for using our program. The process will now terminate.\n");
                System.exit(0);
                break;
            default:
                System.out.print("\nInvalid action. Please choose again: ");
                choice = reader.read();
                runAdminMenu(choice, reader, org);
                break;
        }
    }

    private void runBeneficiaryMenu(int choice, BufferedReader reader, Beneficiary ben, Organization org)
            throws IOException {
        switch (choice) {
            case 1:
                System.out.println("\nAvailable Entities:\n-----------------");
                System.out.println("1. Materials");
                System.out.println("2. Services");
                System.out.println("3. Back");
                System.out.print("\nChoose a category based on number: ");

                int choice1 = reader.read();

                if (choice1 == 1) {
                    org.listMaterials();
                    System.out.print("\nSelect an entity you would like to request: ");
                    int choice2 = reader.read();
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
                    Requests rq = new Requests(itr.next().getEntity(), quantity);

                    ben.addRequest(rq, org);

                    System.out.println("\n1. Back\n2. Exit");
                    System.out.print("\nChoose an action based on action number: ");
                    int choice3 = reader.read();
                    if (choice3 == 1) {
                        this.runBeneficiaryMenu(choice3, reader, ben, org);
                    } else if (choice3 == 2) {
                        System.out.println("\nThank you for using our program. The process will now terminate.\n");
                        System.exit(0);
                    } else {
                        try {
                            throw new MenuNavigationException();
                        } catch (MenuNavigationException mne) {
                            mne.getExceptionInfo();
                            this.runBeneficiaryStarterMenu();
                            choice3 = reader.read();
                            this.runBeneficiaryMenu(choice3, reader, ben, org);
                        }
                    }
                } else if (choice1 == 2) {
                    org.listServices();
                    System.out.print("\nSelect a service you would like to request: ");
                    int choice2 = reader.read();
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
                    Requests rq = new Requests(itr.next().getEntity(), quantity);
                    ben.addRequest(rq, org);
                    System.out.println("\n1. Back\n2. Exit");
                    System.out.print("\nChoose an action based on action number: ");
                    int choice3 = reader.read();
                    if (choice3 == 1) {
                        this.runBeneficiaryMenu(choice3, reader, ben, org);
                    } else if (choice3 == 2) {
                        System.out.println("\nThank you for using our program. The process will now terminate.\n");
                        System.exit(0);
                    } else {
                        try {
                            throw new MenuNavigationException();
                        } catch (MenuNavigationException mne) {
                            mne.getExceptionInfo();
                            this.runBeneficiaryStarterMenu();
                            choice3 = reader.read();
                            this.runBeneficiaryMenu(choice3, reader, ben, org);
                        }
                    }
                } else if (choice1 == 3) {
                    this.runBeneficiaryStarterMenu();
                    int choice2 = reader.read();
                    this.runBeneficiaryMenu(choice2, reader, ben, org);
                } else if (choice1 != 1 && choice1 != 2 && choice1 != 3) {
                    try {
                        throw new MenuNavigationException();
                    } catch (MenuNavigationException mne) {
                        mne.getExceptionInfo();
                    }
                    this.runBeneficiaryStarterMenu();
                    int choice2 = reader.read();
                    this.runBeneficiaryMenu(choice2, reader, ben, org);
                }
                break;
            case 2:
                ben.printRequestsList();
                System.out.println("\n1. Select a request\n2. Delete all requests\n3. Commit");
                int choice2 = reader.read();
                if (choice2 == 1) {
                    int choice3 = reader.read();
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
                    int choice4 = reader.read();

                    if (choice4 == 1) {
                        ben.getRequestsList().remove(itr.next());
                        System.out.println("\nThe selected request has been deleted.");
                    } else if (choice4 == 2) {
                        System.out.print("\nPlease enter new quantity of selected request: ");
                        int quantity = reader.read();
                        try {
                            ben.modifyRequest(itr.next(), itr0.next(), quantity, org);
                        } catch (DeservedAmountException dae) {
                            dae.getExceptionInfo();
                        } catch (FalseAmountException fae) {
                            fae.getExceptionInfo();
                        }
                    }

                    System.out.println("\n Do you want to go back? If so press 0: ");
                    int choice5 = reader.read();
                    if (choice5 == 0) {
                        this.runBeneficiaryMenu(choice5, reader, ben, org);
                    } else {
                        try {
                            throw new MenuNavigationException();
                        } catch (MenuNavigationException mne) {
                            mne.getExceptionInfo();
                            this.runBeneficiaryStarterMenu();
                            choice5 = reader.read();
                            this.runBeneficiaryMenu(choice5, reader, ben, org);
                        }
                    }
                } else if (choice2 == 2) {
                    ben.getRequestsList().clear();
                    System.out.println("\nThe requests list will be cleared. All requests will be deleted.");
                } else {

                }
                break;
            case 3:
                ben.commitRequestsList(org);
                System.out.println("\nAll requests have been commited.");
                break;
            case 4:
                System.out.println("\nThank you for using our program. You will be logged out in 3 seconds.\n");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.login(reader, org);
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
                    this.runBeneficiaryStarterMenu();
                    choice = reader.read();
                    runBeneficiaryMenu(choice, reader, ben, org);
                }
        }
    }

    public void runDonatorMenu(int choice, BufferedReader reader, Donator don, Organization org) throws IOException {
        switch (choice) {
            case 1:
                System.out.println("\nAvailable Entities:\n-----------------");
                System.out.println("1. Materials");
                System.out.println("2. Services");
                System.out.println("3. Back");
                System.out.print("\nChoose a category based on number: ");

                int choice1 = reader.read();

                if (choice1 == 1) {
                    org.listMaterials();
                    System.out.print("\nSelect an entity you would like to donate: ");
                    int choice2 = reader.read();
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
                    Offers rq = new Offers(itr.next().getEntity(), quantity);
                    don.add(rq);
                    System.out.println("\n1. Back\n2. Exit");
                    System.out.print("\nChoose an action based on action number: ");
                    int choice3 = reader.read();
                    if (choice3 == 1) {
                        this.runDonatorMenu(choice3, reader, don, org);
                    } else if (choice3 == 2) {
                        System.out.println("\nThank you for using our program. The process will now terminate.\n");
                        System.exit(0);
                    } else {
                        try {
                            throw new MenuNavigationException();
                        } catch (MenuNavigationException mne) {
                            mne.getExceptionInfo();
                            this.runBeneficiaryStarterMenu();
                            choice3 = reader.read();
                            this.runDonatorMenu(choice3, reader, don, org);
                        }
                    }
                } else if (choice1 == 2) {
                    org.listServices();
                    System.out.print("\nSelect a service you would like to request: ");
                    int choice2 = reader.read();
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
                    Offers of = new Offers(itr.next().getEntity(), itr.next().getQuantity());
                    don.add(of);
                    System.out.println("\n1. Back\n2. Exit");
                    System.out.print("\nChoose an action based on action number: ");
                    int choice3 = reader.read();
                    if (choice3 == 1) {
                        this.runDonatorMenu(choice3, reader, don, org);
                    } else if (choice3 == 2) {
                        System.out.println("\nThank you for using our program. The process will now terminate.\n");
                        System.exit(0);
                    } else {
                        try {
                            throw new MenuNavigationException();
                        } catch (MenuNavigationException mne) {
                            mne.getExceptionInfo();
                            this.runDonatorStarterMenu();
                            choice3 = reader.read();
                            this.runDonatorMenu(choice3, reader, don, org);
                        }
                    }
                } else if (choice1 == 3) {
                    this.runDonatorStarterMenu();
                    int choice2 = reader.read();
                    this.runDonatorMenu(choice2, reader, don, org);
                } else if (choice1 != 1 && choice1 != 2 && choice1 != 3) {
                    try {
                        throw new MenuNavigationException();
                    } catch (MenuNavigationException mne) {
                        mne.getExceptionInfo();
                        this.runDonatorStarterMenu();
                        choice1 = reader.read();
                        this.runDonatorMenu(choice1, reader, don, org);
                    }
                    this.runDonatorStarterMenu();
                    int choice2 = reader.read();
                    this.runDonatorMenu(choice2, reader, don, org);
                }
                break;

            case 2:

                don.printOffersList();
                System.out.println("\n1. Select a request\n2. Delete all requests\n3. Commit");
                int choice2 = reader.read();
                if (choice2 == 1) {
                    int choice3 = reader.read();
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
                    int choice4 = reader.read();

                    if (choice4 == 1) {
                        don.getOffersList().remove(itr.next());
                        System.out.println("\nThe selected offer has been deleted.");
                    } else if (choice4 == 2) {
                        System.out.print("\nPlease enter new quantity of selected offer: ");
                        int quantity = reader.read();
                        don.modifyOffer(itr.next(), itr0.next(), quantity);
                    }

                    System.out.println("\n Do you want to go back? If so press 0: ");
                    int choice5 = reader.read();
                    if (choice5 == 0) {
                        this.runDonatorMenu(choice5, reader, don, org);
                    } else {
                        try {
                            throw new MenuNavigationException();
                        } catch (MenuNavigationException mne) {
                            mne.getExceptionInfo();
                            this.runBeneficiaryStarterMenu();
                            choice5 = reader.read();
                            this.runDonatorMenu(choice5, reader, don, org);
                        }
                    }
                } else if (choice2 == 2) {
                    don.getOffersList().clear();
                    System.out.println("\nThe offers list will be cleared.");
                }
                break;
            case 3:
                don.commitOffersList(org);
                System.out.println("\nAll offers have been commited.");
                break;
            case 4:
                System.out.println("\nThank you for using our program. You will be logged out in 3 seconds.\n");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.login(reader, org);
                break;
            case 5:
                System.out.println("\nThank you for using our program. The process will now terminate.\n");
                System.exit(0);
                break;
            default:
                System.out.print("\nInvalid action. Please choose again: ");
                choice = reader.read();
                runDonatorMenu(choice, reader, don, org);
        }
    }

    public void runViewMenu(BufferedReader reader, Organization org) throws IOException {
        System.out.println("\nAvailable Entities:\n-----------------");
        System.out.println("1. Materials");
        System.out.println("2. Services");
        System.out.println("3. Back");
        System.out.print("\nChoose an action based on action number: ");

        int choice1 = reader.read();

        if (choice1 == 1) {
            org.listMaterials();
            System.out.print("\nChoose a material: ");
            int choice2 = reader.read();
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
            int choice2 = reader.read();
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
            this.runAdminStarterMenu();
            choice1 = reader.read();
            this.runAdminMenu(choice1, reader, org);
        } else if (choice1 != 1 && choice1 != 2 && choice1 != 3) {
            System.out.print("\nInvalid action. Please choose again:");
            try {
                throw new MenuNavigationException();
            } catch (MenuNavigationException mne) {
                this.runAdminStarterMenu();
                choice1 = reader.read();
                this.runAdminMenu(choice1, reader, org);
            }
        }
    }

    public void runMonitorMenu(BufferedReader reader, Organization org) throws IOException {
        System.out.println("\nAvailable Actions:\n-----------------------------");
        System.out.println("1. Beneficiaries List");
        System.out.println("2. Donators List");
        System.out.println("3. Reset Beneficiaries List");
        System.out.print("\nChoose an action based on action number: ");

        int choice1 = reader.read();

        if (choice1 == 1) {
            org.listBeneficiaries();
            System.out.print("\nChoose a Beneficiary: ");
            int choice2 = reader.read();
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

            choice2 = reader.read();

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
            int choice2 = reader.read();
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

            choice2 = reader.read();

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
            choice1 = reader.read();
            this.runAdminMenu(choice1, reader, org);
        }
    }

    public Beneficiary scanBeneficiaryList(String phone, BufferedReader reader, Organization org) {
        boolean valid = false;
        ListIterator<Beneficiary> itr = org.getBeneficiaryList().listIterator();
        while (!valid) {
            while (itr.hasNext()) {
                if (phone.compareTo(itr.next().getPhone()) == 0) {
                    valid = true;
                }
            }
        }
        return itr.previous();
    }

    public Donator scanDonatorList(String phone, BufferedReader reader, Organization org) {
        boolean valid = false;
        ListIterator<Donator> itr = org.getDonatorList().listIterator();
        while (!valid) {
            while (itr.hasNext()) {
                if (phone.compareTo(itr.next().getPhone()) == 0) {
                    valid = true;
                }
            }

        }
        return itr.previous();
    }
}