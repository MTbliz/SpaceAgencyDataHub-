package com.example.spaceagency.exception;

public class ProductNotFoundException extends Exception {

    public ProductNotFoundException(){
        super();
    }

    public ProductNotFoundException(String url){
        super(String.format("Product with" + url + "not found"));
    }
}
