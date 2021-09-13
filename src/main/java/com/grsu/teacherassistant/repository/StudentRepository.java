package com.grsu.teacherassistant.repository;

import com.grsu.teacherassistant.model.entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Integer> {

    Student findByCardUid(String cardUid);

    @Query("Select st FROM Student st JOIN StudentGroup sg WHERE sg.active = ?1")
    List<Student> findStudentByGroupActivity(boolean activity);

/*    SELECT st FROM STUDENT st JOIN STUDENT_LESSON sl ON st.id = sl.student_id
      JOIN LESSON l ON l.id = sl.lesson_id
      WHERE l.id = :lessonId
      AND ((l.group_id NOT NULL AND st.id NOT IN (
          SELECT stg.student_id FROM STUDENT_GROUP stg WHERE stg.group_id = l.group_id))
      OR st.id NOT IN (
          SELECT stg.student_id FROM STUDENT_GROUP stg JOIN STREAM_GROUP sg ON sg.group_id = stg.group_id
          WHERE sg.stream_id = l.stream_id))*/
    @Query("SELECT st FROM Student st JOIN StudentLesson stl JOIN Lesson l WHERE l.id = ?1 AND st.groups NOT IN (" +
        "SELECT g FROM StudentGroup g JOIN Stream s JOIN Lesson l WHERE l.id = ?1)")
    List<Student> findAdditionalStudents(Integer lessonId);

    /*select count(*) from STUDENT_LESSON sl join STUDENT s on sl.student_id = s.id
      join STUDENT_GROUP sg on s.id = sg.student_id join LESSON l on sl.lesson_id = l.id
         where sl.student_id = :studentId and sl.registered = 1
         and l.group_id <> sg.group_id*/
    @Query("SELECT COUNT(s) FROM StudentLesson stl JOIN Student s JOIN StudentGroup JOIN Lesson " +
        "WHERE s.id = ?1 AND stl.registered = true")
    Integer getAdditionalStudentLessonsAmount(Integer studentId);
}
