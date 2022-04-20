package com.grsu.teacherassistant.dto.lesson;

import com.grsu.teacherassistant.dto.DisciplineDto;
import com.grsu.teacherassistant.dto.NoteDto;
import com.grsu.teacherassistant.dto.StreamDto;
import com.grsu.teacherassistant.dto.StudentGroupDto;
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
    private DisciplineDto discipline;
    private StudentGroupDto group;
    private List<NoteDto> notes;

}
