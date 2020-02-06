package invoice_generator;

public class InvoiceServiceException extends Exception{

    public enum ExceptionType{
        INVALID_DATA
    }
    public ExceptionType type;

    public InvoiceServiceException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}
