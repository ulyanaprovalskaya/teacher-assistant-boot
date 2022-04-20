package com.grsu.teacherassistant.mapper;

import com.grsu.teacherassistant.dto.lesson.LessonTypeDto;
import com.grsu.teacherassistant.entity.LessonType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LessonTypeMapper {

    LessonTypeDto toDto(LessonType lessonType);

    LessonType toEntity(LessonTypeDto lessonTypeDto);
}
