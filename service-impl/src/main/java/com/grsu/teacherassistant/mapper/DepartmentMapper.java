package com.grsu.teacherassistant.mapper;

import com.grsu.teacherassistant.dto.DepartmentDto;
import com.grsu.teacherassistant.entity.Department;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    DepartmentDto toDto(Department department);

    Department toEntity(DepartmentDto departmentDto);
}
