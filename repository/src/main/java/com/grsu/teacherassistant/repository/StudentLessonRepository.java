package com.grsu.teacherassistant.repository;

import com.grsu.teacherassistant.entity.LessonType;
import com.grsu.teacherassistant.entity.StudentLesson;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface StudentLessonRepository extends JpaRepository<StudentLesson, Integer> {

    List<StudentLesson> findByRegisteredAndStudentIdAndLessonStreamDisciplineId(int registered,
                                                                                Integer lessonId,
                                                                                Integer disciplineId);

    List<StudentLesson> findByRegisteredAndStudentIdAndLessonStreamDisciplineIdAndLessonTypeType(int registered,
                                                                                             Integer lessonId,
                                                                                             Integer disciplineId,
                                                                                             LessonType.LessonTypeEnum lessonType);

    Optional<StudentLesson> findByStudentIdAndAndLessonId(Integer studentId, Integer lessonId);
}
