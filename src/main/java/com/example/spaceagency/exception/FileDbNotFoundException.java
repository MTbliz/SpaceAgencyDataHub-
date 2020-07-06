package com.example.spaceagency.exception;

public class FileDbNotFoundException extends Exception {
    public FileDbNotFoundException(){
        super();
    }

    public FileDbNotFoundException(Long id){
        super(String.format("File with id %d not found", id));
    }

    public FileDbNotFoundException(String url){
        super(String.format("File with"  + url + "not found"));
    }
}