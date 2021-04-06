package com.comp3350_group10.bookstore.Exceptions;

public class PasswordLengthException extends RuntimeException{
    public PasswordLengthException(final Exception cause) {
        super(cause);
    }
}
