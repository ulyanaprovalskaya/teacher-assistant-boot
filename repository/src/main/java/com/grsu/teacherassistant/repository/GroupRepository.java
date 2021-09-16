package com.grsu.teacherassistant.repository;

import com.grsu.teacherassistant.model.entity.StudentGroup;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GroupRepository extends CrudRepository<StudentGroup, Integer> {

    List<StudentGroup> findAllByActive(boolean active);
}
