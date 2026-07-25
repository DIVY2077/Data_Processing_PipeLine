package exception;

public class TransactionParseException extends RuntimeException{
    public TransactionParseException(String message, Throwable cause) {
        super(message, cause);
    }
    public TransactionParseException(String message) {
        super(message);
    }
}
