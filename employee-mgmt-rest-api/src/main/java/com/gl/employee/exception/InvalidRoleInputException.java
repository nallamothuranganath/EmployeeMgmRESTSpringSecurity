package com.gl.employee.exception;

public class InvalidRoleInputException extends RuntimeException {

    private String errorMessage;

    public InvalidRoleInputException() {

    }

    public InvalidRoleInputException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String toString() {
        return errorMessage;
    }
}
