package com.grsu.teacherassistant.service.impl;

import com.grsu.teacherassistant.dto.DepartmentDto;
import com.grsu.teacherassistant.entity.Department;
import com.grsu.teacherassistant.repository.DepartmentRepository;
import com.grsu.teacherassistant.service.api.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<DepartmentDto> getAll() {
        return departmentRepository.findAll()
                .stream()
                .map(d -> modelMapper.map(d, DepartmentDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDto getById(Integer id) {
        return modelMapper.map(departmentRepository.getById(id), DepartmentDto.class);
    }

    @Override
    public void createOrUpdateDepartment(DepartmentDto departmentDto) {
        departmentRepository.save(modelMapper.map(departmentDto, Department.class));
    }

    @Override
    public void deleteDepartment(Integer id) {
        departmentRepository.deleteById(id);

    }
}
