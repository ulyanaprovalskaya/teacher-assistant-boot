package com.grsu.teacherassistant.repository;

import com.grsu.teacherassistant.model.entity.Discipline;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface DisciplineRepository extends CrudRepository<Discipline, Integer> {

    @Query("SELECT d FROM Lesson l JOIN l.stream s JOIN s.discipline d WHERE l.id = ?1")
    Discipline getDisciplineByLessonId(Integer id);
}
