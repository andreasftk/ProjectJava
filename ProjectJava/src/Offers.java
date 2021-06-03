import java.util.*;

public class Offers extends RequestDonationList{
    //Methods
    void commit(){
        Iterator<RequestDonation> itr = rdEntities.iterator();
        boolean flagComp = false;
        if(itr.hasNext()){
            RequestDonation rd = itr.next();
            Organization.addDonation(rd);
            flagComp = true;
        }else if(flagComp){
            rdEntities.removeAll(rdEntities);
        }
    }
}