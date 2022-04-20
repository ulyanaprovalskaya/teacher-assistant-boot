package com.grsu.teacherassistant.converter;

import com.grsu.teacherassistant.dto.DepartmentDto;
import com.grsu.teacherassistant.dto.StreamDto;
import com.grsu.teacherassistant.service.api.DepartmentService;
import com.grsu.teacherassistant.service.api.StreamService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DepartmentConverter implements Converter<String, DepartmentDto> {

    private final DepartmentService departmentService;

    @Override
    public DepartmentDto convert(String departmentId) {
        return departmentService.getById(Integer.valueOf(departmentId));
    }
}
