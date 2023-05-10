package com.example.mosaicticket.Exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id){
        super("Counld not find user"+id);
    }
}
