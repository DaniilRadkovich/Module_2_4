package com.radkovich.module_2_4.exception;

public class HibernateRepositoryException extends RuntimeException {
    public HibernateRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
