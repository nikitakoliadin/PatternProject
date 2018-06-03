package com.patternproject.exceptions;

/**
 * @author Koliadin Nikita
 *
 * This exception should be thrown when there was an attempt to create
 * a util object using reflection.
 */
public class UtilClassException extends RuntimeException {

    public UtilClassException(String message) {
        super(message);
    }
}
