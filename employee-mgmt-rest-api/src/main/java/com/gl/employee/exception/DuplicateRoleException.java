package com.gl.employee.exception;

public class DuplicateRoleException extends RuntimeException{

    private String errorMessage;

    public DuplicateRoleException(){

    }

    public DuplicateRoleException(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public String toString(){
        return errorMessage;
    }


}
