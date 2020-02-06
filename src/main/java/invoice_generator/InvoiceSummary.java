package invoice_generator;

public class InvoiceSummary {
    double averageFare;
    int numOfRides;
    double totalFare;

    public InvoiceSummary(int numOfRides, double totalFare) {
        this.numOfRides = numOfRides;
        this.totalFare = totalFare;
        this.averageFare=totalFare/numOfRides;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceSummary that = (InvoiceSummary) o;
        return Double.compare(that.averageFare, averageFare) == 0 &&
                numOfRides == that.numOfRides &&
                Double.compare(that.totalFare, totalFare) == 0;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return "InvoiceSummary{" +
                "averageFare=" + averageFare +
                ", numOfRides=" + numOfRides +
                ", totalFare=" + totalFare +
                '}';
    }

}