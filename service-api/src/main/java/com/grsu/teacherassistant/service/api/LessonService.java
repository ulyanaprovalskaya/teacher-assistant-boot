package com.grsu.teacherassistant.service.api;

import com.grsu.teacherassistant.dto.LessonDto;

import java.time.LocalDate;
import java.util.List;

public interface LessonService {

    void createLesson(LessonDto lesson);

    void deleteLesson(Integer lessonId);

    List<LessonDto> getAllByDateBetween(LocalDate dateFrom, LocalDate dateTo);

}
