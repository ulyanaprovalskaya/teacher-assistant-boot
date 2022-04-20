package com.grsu.teacherassistant.repository;

import com.grsu.teacherassistant.entity.Discipline;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface DisciplineRepository extends CrudRepository<Discipline, Integer> {

    @Query("SELECT d FROM Discipline d JOIN d.streams s JOIN s.lessons l WHERE l.id = ?1")
    Optional<Discipline> findByLessonId(Integer id);
}
