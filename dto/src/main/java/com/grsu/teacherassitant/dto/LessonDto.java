package com.grsu.teacherassitant.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class LessonDto {

    private Integer id;
    private String name;
    private String description;
    private LocalDateTime createDate;
    private LocalDateTime date;
    private LessonTypeDto type;
    private Integer index;
    private StreamDto stream;
    private StudentGroupDto group;
    private List<NoteDto> notes;

}
