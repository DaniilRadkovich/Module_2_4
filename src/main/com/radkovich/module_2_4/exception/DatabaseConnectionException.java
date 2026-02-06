package com.radkovich.module_2_4.exception;

public class DatabaseConnectionException extends RepositoryException {
    public DatabaseConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
