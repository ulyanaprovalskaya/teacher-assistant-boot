package com.grsu.teacherassistant.service.impl;

import com.grsu.teacherassistant.model.entity.Schedule;
import com.grsu.teacherassistant.repository.ScheduleRepository;
import com.grsu.teacherassistant.service.api.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Override
    public List<Schedule> getAll() {
        return scheduleRepository.findAll();
    }
}
