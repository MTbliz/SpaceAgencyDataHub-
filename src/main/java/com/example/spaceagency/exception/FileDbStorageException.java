package com.example.spaceagency.exception;

public class FileDbStorageException extends Exception {
    public FileDbStorageException(String message, Exception ex) {
        super(message, ex);
    }

    public FileDbStorageException(String message) {
        super(message);
    }

}
