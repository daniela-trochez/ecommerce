package com.pragma.ecommerce.adapters.driven.jpa.mysql.repository;

import com.pragma.ecommerce.adapters.driven.jpa.mysql.entity.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {
    // Métodos personalizados si es necesario

    boolean existsByName(String name);

    // Método para encontrar todas las categorías con paginación
    Page<CategoryEntity> findAll(Pageable pageable);



}
