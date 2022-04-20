package com.grsu.teacherassistant.service.api;

import com.grsu.teacherassistant.dto.DepartmentDto;

import java.util.List;

public interface DepartmentService {
    List<DepartmentDto> getAll();

    DepartmentDto getById(Integer id);

    void createOrUpdateDepartment(DepartmentDto departmentDto);

    void deleteDepartment(Integer id);

}
