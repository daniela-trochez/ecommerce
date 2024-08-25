package com.pragma.ecommerce.domain.exception;

public class CategoryDescriptionTooLongException extends RuntimeException{

    public CategoryDescriptionTooLongException(String message) {
        super(message);
    }
}
