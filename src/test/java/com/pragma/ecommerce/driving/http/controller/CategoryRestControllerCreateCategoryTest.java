package com.pragma.ecommerce.driving.http.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.ecommerce.adapters.driving.http.controller.CategoryRestController;
import com.pragma.ecommerce.adapters.driving.http.dto.request.CreateCategoryRequest;
import com.pragma.ecommerce.adapters.driving.http.mapper.ICategoryRequestMapper;
import com.pragma.ecommerce.domain.api.ICategoryServicePort;
import com.pragma.ecommerce.domain.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CategoryRestControllerCreateCategoryTest {
    private MockMvc mockMvc;

    @Mock
    private ICategoryServicePort categoryServicePort;

    @Mock
    private ICategoryRequestMapper categoryRequestMapper;

    @InjectMocks
    private CategoryRestController categoryRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Configuración manual de MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(categoryRestController).build();
    }

    @Test

    void createCategory_ShouldReturnCreatedStatus() throws Exception {
        // Configurar datos de prueba
        CreateCategoryRequest request = new CreateCategoryRequest();
        request.setName("Books");
        request.setDescription("Books category");

        Category category = new Category(1L, "Books", "Books category");

        // Configurar comportamiento del mock
        doNothing().when(categoryServicePort).createCategory(any(Category.class));
        when(categoryRequestMapper.toCategory(any(CreateCategoryRequest.class))).thenReturn(category);

        // Ejecutar la solicitud de prueba y verificar la respuesta
        mockMvc.perform(post("/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

    // Método utilitario para convertir un objeto a JSON
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}
