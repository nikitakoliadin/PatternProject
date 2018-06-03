package com.patternproject.exceptions;

/**
 * @author Koliadin Nikita
 *
 * This exception should be thrown when the input data was incorrect.
 */
public class InvalidInputException extends RuntimeException {

    public InvalidInputException(String message, Throwable cause) {
        super(message, cause);
    }
}
