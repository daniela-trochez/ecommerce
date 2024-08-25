package com.pragma.ecommerce.domain.api;

import com.pragma.ecommerce.domain.model.Category;

public interface ICategoryServicePort {
    void createCategory(Category category);
}
