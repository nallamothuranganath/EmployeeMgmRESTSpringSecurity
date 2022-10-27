package com.gl.employee.exception;

public class InvalidRoleException extends RuntimeException{

    private String errorMessage;

    public InvalidRoleException(){

    }

    public InvalidRoleException(String errorMessage){
            this.errorMessage = errorMessage;
    }

    public String toString(){
        return errorMessage;
    }
}
