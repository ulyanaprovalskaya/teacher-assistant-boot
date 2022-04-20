package com.grsu.teacherassistant.dto;

import com.grsu.teacherassistant.dto.student.StudentDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class StudentGroupDto {

    private Integer id;
    private String name;
    private boolean active;
    private DepartmentDto department;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime expirationDate;
    private List<StudentDto> students;
    private StudentDto praepostor;
}
