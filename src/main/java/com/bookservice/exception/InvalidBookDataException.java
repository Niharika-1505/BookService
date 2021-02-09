package com.bookservice.exception;

/**
 * Exception thrown when there are required fields missing in Book Model
 *
 * @author NIHARIKA GADDE
 */
public class InvalidBookDataException extends RuntimeException {
    public InvalidBookDataException(String message) {
        super(message);
    }
}
