package com.grsu.teacherassistant.repository;

import com.grsu.teacherassistant.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
}
