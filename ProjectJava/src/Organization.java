import java.util.*;

public class Organization{
    private static List<RequestDonation> currentDonations = new ArrayList();
    public static void addDonation(RequestDonation e){currentDonations.add(e);}
}