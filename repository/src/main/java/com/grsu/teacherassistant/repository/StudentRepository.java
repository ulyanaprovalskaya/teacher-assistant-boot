package com.grsu.teacherassistant.repository;

import com.grsu.teacherassistant.model.entity.LessonType;
import com.grsu.teacherassistant.model.entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Integer> {

    Student findByCardUid(String cardUid);

    @Query("SELECT st FROM Student st JOIN StudentGroup sg WHERE sg.active = ?1")
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


    /*select l.date, l.type_id from STUDENT st
            join STUDENT_LESSON sl on sl.student_id = st.id
            join LESSON l on l.id = sl.lesson_id and l.type_id in (1, 2, 3)
            join SCHEDULE sch on sch.id = l.schedule_id
            join STREAM str on str.id = l.stream_id
            join DISCIPLINE d on str.discipline_id = d.id
            where st.id in (:studentId) and (sl.registered is null or sl.registered = 0)
            and d.id = :disciplineId
            and ((date(l.date) < date('now', 'localtime'))
                or (date(l.date) = date('now', 'localtime')
                and time(sch.begin) <= time('now', 'localtime')) or l.id = :lessonId)\n")*/
    //
    @Query("SELECT st FROM Student st JOIN StudentLesson sl JOIN Lesson l JOIN Schedule sch JOIN Stream JOIN Discipline d " +
        "WHERE st.id = ?1 AND sl.registered = false AND d.id = ?2")
    Integer getStudentTotalSkipsAmount(Integer studentId, Integer disciplineId);

    @Query("SELECT st FROM Student st JOIN StudentLesson sl JOIN Lesson l JOIN Schedule sch JOIN Stream JOIN Discipline d " +
            "WHERE st.id = ?1 AND sl.registered = false AND d.id = ?2 AND l.type = ?3")
    Integer getStudentSkipsAmountByLessonType(Integer studentId, Integer disciplineId, LessonType lessonType);

}
