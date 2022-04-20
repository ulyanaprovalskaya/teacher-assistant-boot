package com.grsu.teacherassistant.service.api;

import com.grsu.teacherassistant.dto.lesson.StudentLessonDto;
import com.grsu.teacherassistant.dto.student.StudentDto;
import com.grsu.teacherassistant.dto.student.StudentWithAttendanceDto;
import com.grsu.teacherassistant.entity.LessonType;
import com.grsu.teacherassistant.repository.projection.AdditionalLesson;
import java.util.List;


public interface StudentService {

    List<AdditionalLesson> getStudentAdditionalLessons(Integer studentId, Integer disciplineId);

    Integer getStudentTotalSkipsAmount(Integer studentId, Integer disciplineId);

    Integer getStudentSkipsAmountByLessonType(Integer studentId, Integer disciplineId, LessonType type);

    void createStudent(StudentDto student);

    void deleteStudent(Integer id);

    void editStudent(StudentDto student);

    List<StudentDto> getPresentStudents(Integer lessonId);

    List<StudentDto> getAbsentStudents(Integer lessonId);

    StudentWithAttendanceDto getStudentAttendance(Integer studentId, Integer lessonId);

    String getTotalSkipsInfo(List<StudentLessonDto> skips);

    String getPracticalSkipsInfo(List<StudentLessonDto> skips);

    String getLectureSkipsInfo(List<StudentLessonDto> skips);
}
