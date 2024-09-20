package exceptions;

/**
 *
 * @author matin
 */
public class OwnerException extends Exception{
     public OwnerException(String message) {
        super(message);
    }

    public OwnerException() {
        super();
    }
}
