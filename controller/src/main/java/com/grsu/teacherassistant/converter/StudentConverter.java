package com.grsu.teacherassistant.converter;

import com.grsu.teacherassistant.dto.student.StudentDto;
import com.grsu.teacherassistant.service.api.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StudentConverter implements Converter<String, List<StudentDto>> {

    private final StudentService studentService;

    @Override
    public List<StudentDto> convert(String studentId) {
        return Collections.singletonList(studentService.getById(Integer.valueOf(studentId)));
    }

}
