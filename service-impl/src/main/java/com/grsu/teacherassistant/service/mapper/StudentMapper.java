package com.grsu.teacherassistant.service.mapper;

import com.grsu.teacherassistant.dto.StudentDto;
import com.grsu.teacherassistant.model.entity.Student;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class StudentMapper {

    private final ModelMapper modelMapper;

    public List<StudentDto> toStudents(Page<Student> studentPage) {
        return studentPage
                .getContent()
                .stream()
                .map(student -> modelMapper.map(student, StudentDto.class))
                .collect(Collectors.toList());
    }
}
