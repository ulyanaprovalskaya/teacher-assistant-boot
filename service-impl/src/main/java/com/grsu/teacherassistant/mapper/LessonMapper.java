package com.grsu.teacherassistant.mapper;

import com.grsu.teacherassistant.dto.lesson.LessonDto;
import com.grsu.teacherassistant.dto.lesson.LessonWithSchedule;
import com.grsu.teacherassistant.entity.Lesson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LessonMapper {

    @Mapping(source = "lesson.stream.discipline", target = "discipline")
    LessonDto toDto(Lesson lesson);

    LessonWithSchedule toFullDto(Lesson lesson);

    Lesson toEntity(LessonDto lessonDto);
}
