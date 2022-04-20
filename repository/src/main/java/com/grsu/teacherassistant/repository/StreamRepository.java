package com.grsu.teacherassistant.repository;

import com.grsu.teacherassistant.model.entity.Stream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface StreamRepository extends JpaRepository<Stream, Integer> {
}
