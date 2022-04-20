package com.grsu.teacherassistant.dto.lesson;

import com.grsu.teacherassistant.dto.NoteDto;
import com.grsu.teacherassistant.dto.StreamDto;
import com.grsu.teacherassistant.dto.StudentGroupDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class LessonWithSchedule {

    private Integer id;
    private String name;
    private String description;
    private LocalDateTime date;
    private LessonTypeDto type;
    private Integer index;
    private ScheduleDto schedule;
    private StreamDto stream;
    private StudentGroupDto group;
    private List<NoteDto> notes;
}
