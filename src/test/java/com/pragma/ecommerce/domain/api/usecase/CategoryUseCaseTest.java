package com.pragma.ecommerce.domain.api.usecase;

import com.pragma.ecommerce.domain.exception.CategoryAlreadyExistsException;
import com.pragma.ecommerce.domain.exception.CategoryNameTooLongException;
import com.pragma.ecommerce.domain.exception.InvalidCategoryDescriptionException;
import com.pragma.ecommerce.domain.exception.InvalidCategoryNameException;
import com.pragma.ecommerce.domain.model.Category;
import com.pragma.ecommerce.domain.spi.ICategoryPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

 class CategoryUseCaseTest {

    private ICategoryPersistencePort categoryPersistencePort;
    private CategoryUseCase categoryUseCase;

    @BeforeEach
    void setUp() {
        categoryPersistencePort = mock(ICategoryPersistencePort.class);
        categoryUseCase = new CategoryUseCase(categoryPersistencePort);
    }

    // Prueba 1: Validar que el nombre de la categoría no sea nulo
    @Test
    void createCategory_ShouldThrowInvalidCategoryNameException_WhenNameIsNull() {
        // Crear una categoría con nombre nulo
        Category category = new Category(null, "Valid description");

        // Verificar que se lanza InvalidCategoryNameException
        Exception exception = assertThrows(InvalidCategoryNameException.class, () -> {
            categoryUseCase.createCategory(category);
        });

        // Asegurar que el mensaje de la excepción es el esperado
        assertEquals("Category name cannot be null or empty", exception.getMessage());
    }

    // Prueba 2: Validar que el nombre de la categoría no sea una cadena vacía
    @Test
    void createCategory_ShouldThrowInvalidCategoryNameException_WhenNameIsEmpty() {
        // Crear una categoría con nombre vacío
        Category category = new Category("", "Valid description");

        // Verificar que se lanza InvalidCategoryNameException
        Exception exception = assertThrows(InvalidCategoryNameException.class, () -> {
            categoryUseCase.createCategory(category);
        });

        // Asegurar que el mensaje de la excepción es el esperado
        assertEquals("Category name cannot be null or empty", exception.getMessage());
    }

    // Prueba 3: Validar que el nombre de la categoría no exceda los 50 caracteres
    @Test
    void createCategory_ShouldThrowCategoryNameTooLongException_WhenNameIsTooLong() {
        // Crear una categoría con nombre de más de 50 caracteres
        Category category = new Category("a".repeat(51), "Valid description");

        // Verificar que se lanza CategoryNameTooLongException
        Exception exception = assertThrows(CategoryNameTooLongException.class, () -> {
            categoryUseCase.createCategory(category);
        });

        // Asegurar que el mensaje de la excepción es el esperado
        assertEquals("Category name cannot exceed 50 characters", exception.getMessage());
    }

    // Prueba 4: Validar que la descripción de la categoría no sea nula
    @Test
    void createCategory_ShouldThrowInvalidCategoryDescriptionException_WhenDescriptionIsNull() {
        // Crear una categoría con descripción nula
        Category category = new Category("Valid name", null);

        // Verificar que se lanza InvalidCategoryDescriptionException
        Exception exception = assertThrows(InvalidCategoryDescriptionException.class, () -> {
            categoryUseCase.createCategory(category);
        });

        // Asegurar que el mensaje de la excepción es el esperado
        assertEquals("Category description cannot be null or empty", exception.getMessage());
    }

    // Prueba 5: Validar que la descripción de la categoría no sea una cadena vacía
    @Test
    void createCategory_ShouldThrowInvalidCategoryDescriptionException_WhenDescriptionIsEmpty() {
        // Crear una categoría con descripción vacía
        Category category = new Category("Valid name", "");

        // Verificar que se lanza InvalidCategoryDescriptionException
        Exception exception = assertThrows(InvalidCategoryDescriptionException.class, () -> {
            categoryUseCase.createCategory(category);
        });

        // Asegurar que el mensaje de la excepción es el esperado
        assertEquals("Category description cannot be null or empty", exception.getMessage());
    }

    // Prueba 6: Validar que no se pueda crear una categoría si el nombre ya existe
    @Test
    void createCategory_ShouldThrowCategoryAlreadyExistsException_WhenCategoryNameExists() {
        // Configurar el mock para que devuelva true cuando se verifica la existencia del nombre
        when(categoryPersistencePort.existsByName("Existing name")).thenReturn(true);

        // Crear una categoría con un nombre que ya existe
        Category category = new Category("Existing name", "Valid description");

        // Verificar que se lanza CategoryAlreadyExistsException
        Exception exception = assertThrows(CategoryAlreadyExistsException.class, () -> {
            categoryUseCase.createCategory(category);
        });

        // Asegurar que el mensaje de la excepción es el esperado
        assertEquals("Category with name Existing name already exists.", exception.getMessage());
    }
    // Prueba 7: Validar que se pueda crear una categoría cuando los datos son válidos
    @Test
    void createCategory_ShouldSaveCategory_WhenCategoryIsValid() {
        // Configurar el mock para que devuelva false cuando se verifica la existencia del nombre
        when(categoryPersistencePort.existsByName("Valid name")).thenReturn(false);

        // Crear una categoría válida
        Category category = new Category("Valid name", "Valid description");

        // Verificar que no se lanza ninguna excepción
        assertDoesNotThrow(() -> categoryUseCase.createCategory(category));

        // Verificar que el método saveCategory del puerto de persistencia es llamado
        verify(categoryPersistencePort).saveCategory(category);
    }








}
