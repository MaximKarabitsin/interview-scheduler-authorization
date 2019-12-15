package com.netcracker.interviewschedulerauthorization.exceptions;

public class SecurityAccessException extends Exception{
    public SecurityAccessException() {
    }

    public SecurityAccessException(String message) {
        super(message);
    }

    public SecurityAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public SecurityAccessException(Throwable cause) {
        super(cause);
    }
}
