package com.pragma.ecommerce.domain.exception;

public class InvalidCategoryNameException  extends  RuntimeException{
    public InvalidCategoryNameException(String message) {
        super(message);
    }
}
