package com.pragma.ecommerce.adapters.driving.http.mapper;

import com.pragma.ecommerce.adapters.driving.http.dto.request.CreateCategoryRequest;
import com.pragma.ecommerce.domain.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICategoryRequestMapper {

    Category toCategory(CreateCategoryRequest request);


}
