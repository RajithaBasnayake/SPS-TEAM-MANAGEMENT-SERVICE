package com.sps.team.exception;

/**
 * Team Already Exists Exception
 *
 * Thrown when attempting to create a team with a name that already exists
 */
public class TeamAlreadyExistsException extends RuntimeException {

    public TeamAlreadyExistsException(String message) {
        super(message);
    }

    public TeamAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
