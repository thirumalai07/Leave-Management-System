package com.lms.exception;

public class LeaveConflictException extends RuntimeException {
    public LeaveConflictException(String message) {
        super(message);
    }

    public LeaveConflictException(String message, Throwable cause) {
        super(message, cause);
    }
}
