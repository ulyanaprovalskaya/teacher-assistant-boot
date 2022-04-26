package com.grsu.teacherassistant.service.impl;

import com.grsu.teacherassistant.dto.lesson.ScheduleDto;
import com.grsu.teacherassistant.mapper.ScheduleMapper;
import com.grsu.teacherassistant.repository.ScheduleRepository;
import com.grsu.teacherassistant.service.api.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;

    @Override
    public List<ScheduleDto> getAll() {
        return scheduleRepository.findAll()
                .stream()
                .map(scheduleMapper::toDto)
                .collect(Collectors.toList());
    }
}
