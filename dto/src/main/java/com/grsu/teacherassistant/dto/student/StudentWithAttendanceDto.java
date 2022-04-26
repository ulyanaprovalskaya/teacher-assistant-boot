package com.grsu.teacherassistant.dto.student;

import com.grsu.teacherassistant.dto.lesson.StudentLessonDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@ToString
public class StudentWithAttendanceDto extends StudentDto {

    private Integer studentId;
    private Integer disciplineId;
    private List<StudentLessonDto> totalSkips;
    private List<StudentLessonDto> lectureSkips;
    private List<StudentLessonDto> practicalSkips;
    private List<StudentLessonDto> additionalLessons;
    private String imagePath;
}
