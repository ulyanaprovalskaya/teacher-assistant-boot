package com.grsu.teacherassistant.service.api;

import com.grsu.teacherassistant.dto.lesson.LessonTypeDto;

import java.util.List;

public interface LessonTypeService {

    List<LessonTypeDto> getAll();
}
