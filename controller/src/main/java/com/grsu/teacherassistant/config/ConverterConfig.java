package com.grsu.teacherassistant.config;

import com.grsu.teacherassistant.converter.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class ConverterConfig implements WebMvcConfigurer {

    private final DepartmentConverter departmentConverter;

    private final DisciplineConverter disciplineConverter;

    private final GroupConverter groupConverter;

    private final GroupsConverter groupsConverter;

    private final StreamConverter streamConverter;

    private final ExpirationDateConverter expirationDateConverter;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(departmentConverter);
        registry.addConverter(disciplineConverter);
        registry.addConverter(groupConverter);
        registry.addConverter(groupsConverter);
        registry.addConverter(streamConverter);
        registry.addConverter(expirationDateConverter);
    }
}
