package invoice_generator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RideRepository {

    Map<String, ArrayList<Ride>> userRides=null;

    public RideRepository() {
        this.userRides = new HashMap<>();
    }

    public void addRides(String userId, Ride[] rides) {
        ArrayList<Ride> rideList = this.userRides.get(userId);
        if (rideList==null){
            this.userRides.put(userId,new ArrayList<>(Arrays.asList(rides)));
        }
    }

    public Ride[] getRides(String userId)throws InvoiceServiceException {
        try {
            return this.userRides.get(userId).toArray(new Ride[0]);
        }catch (NullPointerException e ){
            throw new InvoiceServiceException(e.getMessage(),
                    InvoiceServiceException.ExceptionType.INVALID_DATA);
        }

    }
}
