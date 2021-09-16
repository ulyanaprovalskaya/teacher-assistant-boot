package com.grsu.teacherassitant.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class NoteDto {

    private Integer id;
    private String type;
    private LocalDateTime createDate;
}
