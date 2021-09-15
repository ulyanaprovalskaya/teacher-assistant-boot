package com.grsu.teacherassistant.repository;

import com.grsu.teacherassistant.model.entity.Lesson;
import com.grsu.teacherassistant.repository.projection.AdditionalLesson;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface LessonRepository extends CrudRepository<Lesson, Integer> {

    /*select lt.id as lessonType, l.DATE as lessonDate, g.name as groupName from STUDENT_LESSON sl
      join STUDENT s on sl.student_id = s.id join STUDENT_GROUP sg on s.id = sg.student_id
      join LESSON l on sl.lesson_id = l.id join 'GROUP' g on l.group_id = g.id
      join LESSON_TYPE lt on l.type_id = lt.id where sl.student_id =:studentId
         and sl.registered = 1 and l.group_id <> sg.group_id*/
    @Query("SELECT l FROM Lesson l JOIN StudentLesson stl JOIN Student s JOIN StudentGroup sg " +
        "WHERE stl.student.id = ?1 AND stl.registered = true AND  l.group.id <> sg.id")
    List<AdditionalLesson> getStudentAdditionalLessons(Integer studentId);

    List<Lesson> getLessonByDateBetween(LocalDateTime from, LocalDateTime to);
}
