package com.grsu.teacherassistant.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DisciplineDto {

    private Integer id;
    private String name;
    private String description;
    private LocalDateTime createDate;
    private Boolean active;
    private LocalDateTime expirationDate;
    //private List<StreamDto> streams;
}
