package com.patternproject.exceptions;

/**
 * @author Koliadin Nikita
 */
public class InvalidInputException extends RuntimeException {

    public InvalidInputException(String message, Throwable cause) {
        super(message, cause);
    }
}
