package com.comp3350_group10.bookstore.persistence;

public class PersistenceException extends RuntimeException {
    public PersistenceException(final Exception e) {
        super(e);
    }
}

