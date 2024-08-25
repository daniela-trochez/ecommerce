package com.pragma.ecommerce.configuration;

import com.pragma.ecommerce.adapters.driven.jpa.mysql.adapter.CategoryAdapter;
import com.pragma.ecommerce.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.pragma.ecommerce.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.pragma.ecommerce.domain.api.ICategoryServicePort;
import com.pragma.ecommerce.domain.api.usecase.CategoryUseCase;
import com.pragma.ecommerce.domain.spi.ICategoryPersistencePort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public ICategoryPersistencePort categoryPersistencePort(ICategoryRepository categoryRepository, ICategoryEntityMapper categoryEntityMapper) {
        return new CategoryAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    public ICategoryServicePort categoryServicePort(ICategoryPersistencePort categoryPersistencePort) {
        return new CategoryUseCase(categoryPersistencePort);
    }
}
