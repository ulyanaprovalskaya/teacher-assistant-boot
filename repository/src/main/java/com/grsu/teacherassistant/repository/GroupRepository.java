package com.grsu.teacherassistant.repository;

import com.grsu.teacherassistant.model.entity.StudentGroup;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GroupRepository extends CrudRepository<StudentGroup, Integer> {

    List<StudentGroup> findAllByActive(boolean active);

    @Query("SELECT sg FROM Stream s JOIN s.groups sg WHERE s.id = ?1")
    List<StudentGroup> findAllByStreamId(Integer id);
}
