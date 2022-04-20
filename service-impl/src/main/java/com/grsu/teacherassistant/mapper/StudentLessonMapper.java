package com.grsu.teacherassistant.mapper;

import com.grsu.teacherassistant.dto.lesson.StudentLessonDto;
import com.grsu.teacherassistant.entity.StudentLesson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface StudentLessonMapper {

    @Mappings({
            @Mapping(target = "type", source = "lesson.type"),
            @Mapping(target = "date", source = "lesson.date"),
            @Mapping(target = "group", source = "lesson.group"),
            @Mapping(target = "stream", source = "lesson.stream"),
              })
    StudentLessonDto toDto(StudentLesson studentLesson);

    StudentLesson toEntity(StudentLessonDto studentLessonDto);

}
