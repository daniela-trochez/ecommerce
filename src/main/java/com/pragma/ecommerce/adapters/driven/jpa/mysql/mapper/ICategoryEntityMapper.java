package com.pragma.ecommerce.adapters.driven.jpa.mysql.mapper;


import com.pragma.ecommerce.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.pragma.ecommerce.domain.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICategoryEntityMapper {

    CategoryEntity toEntity(Category category);
    Category toModel(CategoryEntity categoryEntity);
}
