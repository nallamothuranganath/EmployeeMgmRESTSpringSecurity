package com.gl.employee.exception;

public class InvalidEmployeeDataException extends RuntimeException{

    private String errorMessage;

    public InvalidEmployeeDataException(){

    }

    public InvalidEmployeeDataException(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public String toString(){
        return errorMessage;
    }
}
