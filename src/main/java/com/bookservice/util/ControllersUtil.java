package com.bookservice.util;

import com.bookservice.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Utility class for Controllers. It can be used for returning
 * different types of Response Entities
 *
 * @author NIHARIKA GADDE
 */
public class ControllersUtil {

    private ControllersUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Helper method for returning the {@link ResponseEntity} of type <T>
     * with {@link HttpStatus} INTERNAL_SERVER_ERROR (500)
     *
     * @return {@link ResponseEntity}
     */
    public static <T> ResponseEntity<T> getInternalServerErrorResponseEntity() {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Helper method for returning the {@link ResponseEntity} of type {@link ErrorDto}
     * with {@link HttpStatus} INTERNAL_SERVER_ERROR (500)
     *
     * @param ex Exception
     * @return {@link ResponseEntity}
     */
    public static ResponseEntity<ErrorDto> getInternalServerErrorResponseEntity(Exception ex) {
        return new ResponseEntity<>(Utils.getErrorDtoFromException(ex), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Helper method for returning the {@link ResponseEntity} of type <T>
     * with {@link HttpStatus} OK (200)
     *
     * @param <T> Type of the value
     * @return {@link ResponseEntity}
     */
    public static <T> ResponseEntity<T> getOkResponseEntity(T returnObject) {
        return new ResponseEntity<>(returnObject, HttpStatus.OK);
    }

    /**
     * Helper method for returning the {@link ResponseEntity} of type <T>
     * with {@link HttpStatus} CREATED (201)
     *
     * @param <T> Type of the value
     * @return {@link ResponseEntity}
     */
    public static <T> ResponseEntity<T> getCreatedResponseEntity(T returnObject) {
        return new ResponseEntity<>(returnObject, HttpStatus.CREATED);
    }

    /**
     * Helper method for returning the {@link ResponseEntity} of type <T>
     * with {@link HttpStatus} NOT_FOUND (404)
     *
     * @return {@link ResponseEntity}
     */
    public static <T> ResponseEntity<T> getNotFoundResponseEntity() {
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    /**
     * Helper method for returning the {@link ResponseEntity} of type <T>
     * with {@link HttpStatus} NO_CONTENT (204)
     *
     * @return {@link ResponseEntity}
     */
    public static <T> ResponseEntity<T> getNoContentResponseEntity() {
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
