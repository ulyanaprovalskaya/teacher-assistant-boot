package com.grsu.teacherassistant.service.api;

import com.grsu.teacherassistant.model.entity.LessonType;
import com.grsu.teacherassistant.model.entity.Student;
import com.grsu.teacherassistant.repository.projection.AdditionalLesson;

public interface StudentService {

    AdditionalLesson getStudentAdditionalLessons(Integer studentId, Integer disciplineId);

    Integer getStudentTotalSkipsAmount(Integer studentId, Integer disciplineId);

    Integer getStudentSkipAmountByLessonType(Integer studentId, Integer disciplineId, LessonType type);

    void createStudent(Student student);

    void deleteStudent(Integer id);

    void editStudent(Student student);
}
