package com.grsu.teacherassistant.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class StudentGroupDto {

    private Integer id;
    private String names;
    private boolean active;
    private LocalDateTime expirationDate;
    private List<StudentDto> students;
    private StudentDto praepostor;
}
