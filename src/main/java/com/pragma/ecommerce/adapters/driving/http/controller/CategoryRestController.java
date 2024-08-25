package com.pragma.ecommerce.adapters.driving.http.controller;

import com.pragma.ecommerce.adapters.driving.http.dto.request.CreateCategoryRequest;
import com.pragma.ecommerce.adapters.driving.http.mapper.ICategoryRequestMapper;
import com.pragma.ecommerce.domain.api.ICategoryServicePort;
import com.pragma.ecommerce.domain.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryRestController {

    private final ICategoryServicePort categoryServicePort;
    private final ICategoryRequestMapper categoryRequestMapper;

    public CategoryRestController(ICategoryServicePort categoryServicePort, ICategoryRequestMapper categoryRequestMapper) {
        this.categoryServicePort = categoryServicePort;
        this.categoryRequestMapper = categoryRequestMapper;
    }

    @PostMapping
    public ResponseEntity<Void> createCategory(@RequestBody CreateCategoryRequest request) {
        categoryServicePort.createCategory(categoryRequestMapper.toCategory(request));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }




}
