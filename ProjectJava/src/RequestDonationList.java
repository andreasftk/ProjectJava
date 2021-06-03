import java.util.*;

public class RequestDonationList{
    //Variables
    ArrayList<RequestDonation> rdEntities = new ArrayList<>();
    //Methods
    public RequestDonation get(int id){
        for(RequestDonation rd:rdEntities){
            if(rd.getEntity().getID() == id){
                return rd;
            }
            else{
                break;
            }
        }
    }
    public void add(RequestDonation rd){
        try{
            for(RequestDonation rdobj:rdEntities){
                if(rd != rdobj){
                    throw new AlreadyExistingEntityException();
                }else{
                    rdEntities.add(rd);
                }
            }
        }
        catch(AlreadyExistingEntityException a3e){a3e.getExceptionInfo();}
    }
}