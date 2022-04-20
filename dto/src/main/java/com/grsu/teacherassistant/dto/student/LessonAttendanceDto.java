package com.grsu.teacherassistant.dto.student;

import com.grsu.teacherassistant.dto.lesson.LessonTypeDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@ToString
public class LessonAttendanceDto {

    private LessonTypeDto lessonType;
    private Map<LocalDateTime, Integer> lessons;
}
