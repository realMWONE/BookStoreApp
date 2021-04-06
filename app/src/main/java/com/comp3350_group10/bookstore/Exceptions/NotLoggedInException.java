package com.comp3350_group10.bookstore.Exceptions;

public class NotLoggedInException extends RuntimeException{
        public NotLoggedInException(final Exception cause) {
            super(cause);
        }
}
