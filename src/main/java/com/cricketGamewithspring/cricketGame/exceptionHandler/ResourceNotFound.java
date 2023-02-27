package com.cricketGamewithspring.cricketGame.exceptionHandler;

public class ResourceNotFound extends RuntimeException {

    public ResourceNotFound(String message) {
        super(message);
    }
}
