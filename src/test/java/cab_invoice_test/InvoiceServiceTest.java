package cab_invoice_test;

import invoice_generator.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InvoiceServiceTest {

    InvoiceService invoiceService =null;


    @Before
    public void setUp(){
        invoiceService = new InvoiceService(new RideRepository());
    }

    @Test
    public void givenDistanceAndTime_shouldReturnTotalFare() {
        double distance = 2.0;
        int time = 5;
        double fare = invoiceService.addRides(distance, time);
        Assert.assertEquals(25, fare, 0.0);
    }

    @Test
    public void givenLessDistanceAndTime_shouldReturnMinimumFare() {
        double fare = invoiceService.addRides(0.2, 1);
        Assert.assertEquals(5, fare, 0.0);
    }

    @Test
    public void givenMultipleRides_shouldReturnInvoiceSummary() {
        Ride[] rides = {new Ride(2.0, 5),
                new Ride(0.1,1)
        };
        InvoiceSummary summary = invoiceService.calculateFare(rides);
        InvoiceSummary invoiceSummary = new InvoiceSummary(2, 30);
        Assert.assertEquals(invoiceSummary,summary);
    }

    @Test
    public void givenUserIdAndRides_shouldReturnInvoiceSummary() throws InvoiceServiceException {
        String userId="aaa";
        Ride[] rides = {new Ride(2.0, 5),
                new Ride(0.1,1)
        };
        invoiceService.addRides(userId,rides);
        InvoiceSummary summary = invoiceService.getInvoiceSummary(userId);
        InvoiceSummary invoiceSummary = new InvoiceSummary(2, 30);
        Assert.assertEquals(invoiceSummary,summary);
    }
}
