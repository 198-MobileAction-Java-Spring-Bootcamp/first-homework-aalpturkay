package com.alp.Homework.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id){
        super(String.format("User with id: %d does not exist", id));
    }
}
