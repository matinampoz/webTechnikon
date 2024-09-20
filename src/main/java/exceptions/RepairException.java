package exceptions;

/**
 *
 * @author matin
 */
public class RepairException extends Exception{
    public RepairException(String message) {
        super(message);
    }

    public RepairException() {
        super();
    }
}
