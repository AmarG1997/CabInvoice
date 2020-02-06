package cab_invoice_test;


import invoice_generator.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.when;


public class InvoiceSummaryMockTest {


    private InvoiceService invoiceService;

    @Mock
    RideRepository rideRepository;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setUp(){
        invoiceService = new InvoiceService(rideRepository);
    }


    @Test
    public void whenUsedMockOfRideRepo_shouldReturnUserId() throws InvoiceServiceException {
        String userId="aaa";
        Ride[] rides = {new Ride(2.0, 5),
                new Ride(0.1,1)
        };
        Mockito.doNothing().when(rideRepository).addRides(userId,rides);
        when(rideRepository.getRides(userId)).thenReturn(rides);
        InvoiceSummary summary = invoiceService.getInvoiceSummary(userId);
        InvoiceSummary invoiceSummary = new InvoiceSummary(2, 30);
        Assert.assertEquals(invoiceSummary,summary);
    }

    @Test
    public void whenGivenWrongUserId_shouldThrowException() throws InvoiceServiceException {
        try {
            String userId = "aaa";
            String userId1 = "bbb";
            Ride[] rides = {new Ride(2.0, 5),
                    new Ride(0.1, 1)
            };
            Mockito.doNothing().when(rideRepository).addRides(userId, rides);
            when(rideRepository.getRides(userId1)).thenThrow(new InvoiceServiceException("NO_USER_FOUND", InvoiceServiceException.ExceptionType.INVALID_DATA));
            InvoiceSummary summary = invoiceService.getInvoiceSummary(userId1);
            InvoiceSummary invoiceSummary = new InvoiceSummary(2, 30);
        } catch (InvoiceServiceException e) {
            Assert.assertEquals(InvoiceServiceException.ExceptionType.INVALID_DATA, e.type);
        }
    }

    @Test
    public void whenGivenWrongNumOfRides_shouldReturnNotEquals() throws InvoiceServiceException {
            String userId = "aaa";
            Ride[] rides = {new Ride(2.0, 5),
                    new Ride(0.1, 1),
                    new Ride(0.2,3)
            };
            Mockito.doNothing().when(rideRepository).addRides(userId, rides);
            when(rideRepository.getRides(userId)).thenReturn(rides);
            InvoiceSummary summary = invoiceService.getInvoiceSummary(userId);
            InvoiceSummary invoiceSummary = new InvoiceSummary(4, 35);
            Assert.assertNotEquals(invoiceSummary,summary);
    }

    @Test
    public void whenGivenNullUserId_shouldThrowException() throws InvoiceServiceException {
        try {
            String userId = "fdsf";
            Ride[] rides = {new Ride(2.0, 5),
                    new Ride(0.1, 1),
                    new Ride(0.2, 3)
            };
            Mockito.doNothing().when(rideRepository).addRides(userId, rides);
            when(rideRepository.getRides(null)).thenThrow(new InvoiceServiceException("NULL_VALUE",InvoiceServiceException.ExceptionType.INVALID_DATA));
            InvoiceSummary summary = invoiceService.getInvoiceSummary(null);
            InvoiceSummary invoiceSummary = new InvoiceSummary(3, 35);
        } catch (InvoiceServiceException e) {
            Assert.assertEquals(InvoiceServiceException.ExceptionType.INVALID_DATA, e.type);
        }
    }
}
