package com.grsu.teacherassistant.dto.lesson;

import com.grsu.teacherassistant.dto.GroupDto;
import com.grsu.teacherassistant.dto.StreamDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@ToString
public class StudentLessonDto {

    private Integer id;
    private LocalDateTime date;
    private LessonTypeDto type;
    private StreamDto stream;
    private GroupDto group;
}
