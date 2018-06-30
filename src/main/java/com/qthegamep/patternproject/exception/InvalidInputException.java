package com.qthegamep.patternproject.exception;

/**
 * @author Koliadin Nikita
 *
 * This exception should be thrown when the input data has been incorrect.
 */
public class InvalidInputException extends RuntimeException {

    public InvalidInputException(String message, Throwable cause) {
        super(message, cause);
    }
}
