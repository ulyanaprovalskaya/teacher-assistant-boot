package com.grsu.teacherassistant.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class StreamDto {

    private Integer id;
    private String name;
    private String description;
    private LocalDateTime createDate;
    private Integer course;
    private boolean active;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime expirationDateTime;
    private List<StudentGroupDto> groups;
    private DisciplineDto discipline;
    private DepartmentDto department;
    private Integer lectureCount;
    private Integer practicalCount;
    private Integer labCount;
}
