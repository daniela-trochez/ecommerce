package com.pragma.ecommerce.domain.spi;

import com.pragma.ecommerce.domain.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICategoryPersistencePort {

    void saveCategory(Category category);

    boolean existsByName(String name);

    // método para listar categorías con paginación
    Page<Category> listCategories(Pageable pageable);
}
