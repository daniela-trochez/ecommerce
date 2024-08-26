package com.pragma.ecommerce.domain.api;

import com.pragma.ecommerce.domain.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICategoryServicePort {
    void createCategory(Category category);

    // Método para listar categorías con paginación
    Page<Category> listCategories(Pageable pageable);
}
