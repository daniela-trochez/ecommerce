package com.pragma.ecommerce.adapters.driving.http.mapper;

import com.pragma.ecommerce.adapters.driving.http.dto.request.CreateCategoryRequest;
import com.pragma.ecommerce.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ICategoryRequestMapper {

    // Ignorar el campo 'id' al mapear de CreateCategoryRequest a Category
    @Mapping(target = "id", ignore = true)
    Category toCategory(CreateCategoryRequest request);


}
