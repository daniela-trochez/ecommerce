package com.pragma.ecommerce.driving.http.controller;

import com.pragma.ecommerce.adapters.driving.http.controller.CategoryRestController;
import com.pragma.ecommerce.domain.api.ICategoryServicePort;
import com.pragma.ecommerce.domain.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CategoryRestControllerListCategoriesTest {


    private MockMvc mockMvc;

    @Mock
    private ICategoryServicePort categoryServicePort;

    @InjectMocks
    private CategoryRestController categoryRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Configuración manual de MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(categoryRestController).build();
    }

    @Test
    void listCategories_ShouldReturnPagedCategories() throws Exception {
        // Configurar datos de prueba
        Category category = new Category(1L, "Books", "Books category");
        PageRequest pageable = PageRequest.of(0, 10, Sort.by("name").ascending());
        Page<Category> categoryPage = new PageImpl<>(List.of(category));

        when(categoryServicePort.listCategories(pageable)).thenReturn(categoryPage);

        // Ejecutar la solicitud de prueba y verificar la respuesta
        mockMvc.perform(get("/categories")
                        .param("page", "0")
                        .param("size", "10")
                        .param("sortDirection", "asc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("Books"))
                .andExpect(jsonPath("$.totalElements").value(1));
    }

}
