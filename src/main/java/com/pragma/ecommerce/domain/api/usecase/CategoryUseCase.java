package com.pragma.ecommerce.domain.api.usecase;

import com.pragma.ecommerce.domain.api.ICategoryServicePort;
import com.pragma.ecommerce.domain.exception.*;
import com.pragma.ecommerce.domain.model.Category;
import com.pragma.ecommerce.domain.spi.ICategoryPersistencePort;
import com.pragma.ecommerce.domain.util.ConstantsDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class CategoryUseCase implements ICategoryServicePort {

    private final ICategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void createCategory(Category category) {

        // Lógica de negocio y validaciones

        // Utiliza el puerto de persistencia para verificar la existencia
        if (categoryPersistencePort.existsByName(category.getName())) {
            throw new CategoryAlreadyExistsException("Category with name " + category.getName() + " already exists.");
        }
        validateCategory(category);
        categoryPersistencePort.saveCategory(category);

    }


    private void validateCategory(Category category) {

        if (category.getName() == null || category.getName().isEmpty()) {
            throw new InvalidCategoryNameException(ConstantsDomain.CATEGORY_NAME_NULL_OR_EMPTY);
        }

        if (category.getDescription() == null || category.getDescription().isEmpty()) {
            throw new InvalidCategoryDescriptionException(ConstantsDomain.CATEGORY_DESCRIPTION_NULL_OR_EMPTY);
        }

        if (category.getName().length() > 50) {
            throw new CategoryNameTooLongException(ConstantsDomain.CATEGORY_NAME_TOO_LONG);
        }

        if (category.getDescription().length() > 90) {
            throw new CategoryDescriptionTooLongException(ConstantsDomain.CATEGORY_DESCRIPTION_TOO_LONG);
        }


    }

    @Override
    public Page<Category> listCategories(Pageable pageable) {
        return categoryPersistencePort.listCategories(pageable);
    }






}
