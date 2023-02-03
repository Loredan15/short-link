package ru.maxol.shortlink.exception;

public class NoFoundElementException extends RuntimeException{

    public NoFoundElementException(String message) {
        super(message);
    }
}
