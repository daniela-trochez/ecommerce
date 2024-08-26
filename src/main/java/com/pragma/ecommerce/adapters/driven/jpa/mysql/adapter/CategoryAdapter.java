package com.pragma.ecommerce.adapters.driven.jpa.mysql.adapter;

import com.pragma.ecommerce.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.pragma.ecommerce.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.pragma.ecommerce.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.pragma.ecommerce.domain.model.Category;
import com.pragma.ecommerce.domain.spi.ICategoryPersistencePort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class CategoryAdapter implements ICategoryPersistencePort {

    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;

    public CategoryAdapter(ICategoryRepository categoryRepository, ICategoryEntityMapper categoryEntityMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryEntityMapper = categoryEntityMapper;
    }

    @Override
    public void saveCategory(Category category) {

        CategoryEntity categoryEntity = categoryEntityMapper.toEntity(category);
        categoryRepository.save(categoryEntity);

    }

    @Override
    public boolean existsByName(String name) {
        return categoryRepository.existsByName(name);
    }

    @Override
    public Page<Category> listCategories(Pageable pageable) {
        Page<CategoryEntity> categoryEntities = categoryRepository.findAll(pageable);
        return categoryEntities.map(categoryEntityMapper::toModel);
    }
}
