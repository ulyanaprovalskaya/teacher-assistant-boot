package com.grsu.teacherassistant.service.impl;

import com.grsu.teacherassistant.dto.DepartmentDto;
import com.grsu.teacherassistant.mapper.DepartmentMapper;
import com.grsu.teacherassistant.repository.DepartmentRepository;
import com.grsu.teacherassistant.service.api.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    @Override
    public List<DepartmentDto> getAll() {
        return departmentRepository.findAll()
                .stream()
                .map(departmentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDto getById(Integer id) {
        return departmentMapper.toDto(departmentRepository.getById(id));
    }

    @Override
    public void createOrUpdateDepartment(DepartmentDto departmentDto) {
        departmentRepository.save(departmentMapper.toEntity(departmentDto));
    }

    @Override
    public void deleteDepartment(Integer id) {
        departmentRepository.deleteById(id);
    }
}
