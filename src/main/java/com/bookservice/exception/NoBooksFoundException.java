package com.bookservice.exception;

/**
 * Exception thrown when there are no books found in the system
 *
 * @author NIHARIKA GADDE
 */
public class NoBooksFoundException extends RuntimeException {

    public NoBooksFoundException() {
        super("There are no books present in database...");
    }

    public NoBooksFoundException(String message) {
        super(message);
    }
}
