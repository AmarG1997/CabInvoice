package invoice_generator;

public class InvoiceService {
    private static final int COST_PER_TIME = 1;
    private static final double MINIMUM_COST_PER_KILOMETER = 10;
    private static final int MINIMUM_FARE = 5;
    private  RideRepository rideRepository;

    public InvoiceService(RideRepository rideRepository) {
        this.rideRepository =  rideRepository;
    }

    public double addRides(double distance, int time) {
        Double fare = distance * MINIMUM_COST_PER_KILOMETER + time * COST_PER_TIME;
        //if (fare < MINIMUM_FARE)
            return Math.max(fare,MINIMUM_FARE);
       // return fare;
    }

    public InvoiceSummary calculateFare(Ride[] rides) {
        double totalFare = 0.0;
        for (Ride ride : rides) {
            totalFare += this.addRides(ride.distance, ride.time);
        }
        return new InvoiceSummary(rides.length, totalFare);
    }

    public void addRides(String userId, Ride[] rides) {
        rideRepository.addRides(userId,rides);
    }

    public InvoiceSummary getInvoiceSummary(String userId) throws InvoiceServiceException {
        try {
            return this.calculateFare(rideRepository.getRides(userId));
        }catch (NullPointerException e){
            throw new InvoiceServiceException(e.getMessage(),
                    InvoiceServiceException.ExceptionType.INVALID_DATA);
        }

    }
}
