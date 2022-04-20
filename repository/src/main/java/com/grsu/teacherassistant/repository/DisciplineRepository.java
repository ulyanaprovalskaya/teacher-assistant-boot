package com.grsu.teacherassistant.repository;

import com.grsu.teacherassistant.model.entity.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface DisciplineRepository extends JpaRepository<Discipline, Integer> {
    @Query(value = "select  d.* from Lesson l " +
            "join Stream str on l.stream_id = str.id " +
            "join Discipline d on str.discipline_id = d.id " +
            "where l.id = :lessonId", nativeQuery = true)
    Discipline getDisciplineByLessonId(Integer lessonId);
}
