package com.grsu.teacherassistant.converter;

import com.grsu.teacherassistant.dto.StudentDto;
import com.grsu.teacherassistant.service.api.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class StudentsConverter implements Converter<String[], List<StudentDto>> {

    private final StudentService studentService;

    @Override
    public List<StudentDto> convert(String[] studentIds) {
        List<Integer> ids = Arrays.stream(studentIds)
                .map(Integer::valueOf)
                .collect(Collectors.toList());
        return studentService.getAllByIdInList(ids);
    }

}
