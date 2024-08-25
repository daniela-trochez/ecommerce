package com.pragma.ecommerce.domain.spi;

import com.pragma.ecommerce.domain.model.Category;

public interface ICategoryPersistencePort {

    void saveCategory(Category category);

    boolean existsByName(String name);
}
