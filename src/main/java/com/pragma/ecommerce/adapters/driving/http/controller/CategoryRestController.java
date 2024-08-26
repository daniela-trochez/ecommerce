package com.pragma.ecommerce.adapters.driving.http.controller;

import com.pragma.ecommerce.adapters.driving.http.dto.request.CreateCategoryRequest;
import com.pragma.ecommerce.adapters.driving.http.mapper.ICategoryRequestMapper;
import com.pragma.ecommerce.domain.api.ICategoryServicePort;
import com.pragma.ecommerce.domain.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/categories")
public class CategoryRestController {

    private final ICategoryServicePort categoryServicePort;
    private final ICategoryRequestMapper categoryRequestMapper;

    public CategoryRestController(ICategoryServicePort categoryServicePort, ICategoryRequestMapper categoryRequestMapper) {
        this.categoryServicePort = categoryServicePort;
        this.categoryRequestMapper = categoryRequestMapper;
    }

    /*@PostMapping
    public ResponseEntity<Void> createCategory(@RequestBody CreateCategoryRequest request) {
        categoryServicePort.createCategory(categoryRequestMapper.toCategory(request));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }*/
    @PostMapping
    public ResponseEntity<Void> createCategory(@RequestBody CreateCategoryRequest request) {
        Category category = categoryRequestMapper.toCategory(request);
        categoryServicePort.createCategory(category);

        // Aquí creamos el URI para la nueva categoría y lo establecemos en el encabezado `Location`
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(category.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }




    // Nuevo endpoint para listar categorías
    @GetMapping
    public ResponseEntity<Page<Category>> listCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "asc") String sortDirection) {

        Sort sort = sortDirection.equalsIgnoreCase("desc") ? Sort.by("name").descending() : Sort.by("name").ascending();
        PageRequest pageable = PageRequest.of(page, size, sort);
        Page<Category> categories = categoryServicePort.listCategories(pageable);
        return ResponseEntity.ok(categories);
    }




}
