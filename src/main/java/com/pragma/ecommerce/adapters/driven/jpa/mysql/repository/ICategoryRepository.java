package com.pragma.ecommerce.adapters.driven.jpa.mysql.repository;

import com.pragma.ecommerce.adapters.driven.jpa.mysql.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {
    // Métodos personalizados si es necesario

    boolean existsByName(String name);

}
