package com.pragma.ecommerce.configuration.exceptionhandler;

import com.pragma.ecommerce.adapters.driving.http.controller.CategoryRestController;
import com.pragma.ecommerce.adapters.driving.http.dto.request.CreateCategoryRequest;
import com.pragma.ecommerce.adapters.driving.http.mapper.ICategoryRequestMapper;
import com.pragma.ecommerce.domain.api.ICategoryServicePort;
import com.pragma.ecommerce.domain.exception.CategoryAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.pragma.ecommerce.domain.exception.InvalidCategoryNameException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ControllerAdvisorTest {

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
        mockMvc = MockMvcBuilders.standaloneSetup(categoryRestController)
                .setControllerAdvice(new ControllerAdvisor()) // Configura el ControllerAdvice aquí
                .build();
    }

    @Test
    void handleCategoryAlreadyExistsException_ShouldReturnBadRequest() throws Exception {
        // Configurar comportamiento del mock para lanzar una excepción
        doThrow(new CategoryAlreadyExistsException("Category already exists"))
                .when(categoryServicePort).createCategory(any());

        // Configurar datos de prueba
        CreateCategoryRequest request = new CreateCategoryRequest();
        request.setName("Books");
        request.setDescription("Books category");

        // Ejecutar la solicitud de prueba y verificar la respuesta
        mockMvc.perform(post("/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Category already exists"));
    }

    @Test
    void handleInvalidCategoryNameException_ShouldReturnBadRequest() throws Exception {
        doThrow(new InvalidCategoryNameException("Invalid category name"))
                .when(categoryServicePort).createCategory(any());

        CreateCategoryRequest request = new CreateCategoryRequest();
        request.setName("");
        request.setDescription("Description");

        mockMvc.perform(post("/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Invalid category name"));
    }

    // Agrega métodos similares para las otras excepciones

    // Método utilitario para convertir un objeto a JSON
    private static String asJsonString(final Object obj) {
        try {
            return new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }






}
