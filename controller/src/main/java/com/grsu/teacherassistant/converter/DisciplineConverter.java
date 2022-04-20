package com.grsu.teacherassistant.converter;

import com.grsu.teacherassistant.dto.DisciplineDto;
import com.grsu.teacherassistant.service.api.DisciplineService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DisciplineConverter implements Converter<String, DisciplineDto> {

    private final DisciplineService disciplineService;

    @Override
    public DisciplineDto convert(String disciplineId) {
        return disciplineService.getById(Integer.valueOf(disciplineId));
    }
}
