package com.pragma.ecommerce.domain.exception;

public class CategoryNameTooLongException  extends  RuntimeException{

    public CategoryNameTooLongException(String message) {
        super(message);
    }
}
