package com.grsu.teacherassistant.dto;

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
