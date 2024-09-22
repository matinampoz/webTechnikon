package exceptions;

import jakarta.ws.rs.core.Response.Status;

/**
 *
 * @author matin
 */
public class WebApplicationException extends Exception{
    public WebApplicationException(String message, Status NOT_FOUND) {
        super(message);
    }

    public WebApplicationException() {
        super();
    }
}
