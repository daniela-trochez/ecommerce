package com.pragma.ecommerce.domain.api.usecase;

import com.pragma.ecommerce.domain.model.Category;
import com.pragma.ecommerce.domain.spi.ICategoryPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CategoryUseCaseListCategoriesTest {

    @Mock
    private ICategoryPersistencePort categoryPersistencePort;

    private CategoryUseCase categoryUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        categoryUseCase = new CategoryUseCase(categoryPersistencePort);
    }

    @Test
    void listCategories_ShouldReturnPagedCategories() {
        // Configurar datos de prueba
        Category category = new Category(1L, "Books", "Books category");
        Pageable pageable = PageRequest.of(0, 10);
        Page<Category> categoryPage = new PageImpl<>(List.of(category));

        when(categoryPersistencePort.listCategories(pageable)).thenReturn(categoryPage);

        // Ejecutar el caso de uso
        Page<Category> result = categoryUseCase.listCategories(pageable);

        // Verificar los resultados
        assertEquals(1, result.getTotalElements());
        assertEquals("Books", result.getContent().get(0).getName());
        verify(categoryPersistencePort).listCategories(pageable);
    }
}
