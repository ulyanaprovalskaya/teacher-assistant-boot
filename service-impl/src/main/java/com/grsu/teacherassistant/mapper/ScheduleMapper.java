package com.grsu.teacherassistant.mapper;

import com.grsu.teacherassistant.dto.lesson.ScheduleDto;
import com.grsu.teacherassistant.entity.Schedule;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {

    ScheduleDto toDto(Schedule schedule);

    Schedule toEntity(ScheduleDto scheduleDto);
}
