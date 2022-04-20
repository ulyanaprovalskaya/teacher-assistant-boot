package com.grsu.teacherassistant.mapper;

import com.grsu.teacherassistant.dto.student.StudentDto;
import com.grsu.teacherassistant.dto.student.StudentWithAttendanceDto;
import com.grsu.teacherassistant.entity.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentDto toDto(Student student);

    StudentWithAttendanceDto toFullDto(Student student);

    Student toEntity(StudentDto studentDto);
}
