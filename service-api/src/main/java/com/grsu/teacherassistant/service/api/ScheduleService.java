package com.grsu.teacherassistant.service.api;

import com.grsu.teacherassistant.dto.lesson.ScheduleDto;

import java.util.List;

public interface ScheduleService {

    List<ScheduleDto> getAll();
}
