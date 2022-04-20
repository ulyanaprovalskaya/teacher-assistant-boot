package com.grsu.teacherassistant.service.api;

import com.grsu.teacherassistant.dto.lesson.LessonDto;
import com.grsu.teacherassistant.dto.lesson.LessonWithSchedule;

import java.time.LocalDate;
import java.util.List;

public interface LessonService {

    void createLesson(LessonDto lesson);

    void deleteLesson(Integer lessonId);

    LessonWithSchedule getById(Integer lessonId);

    List<LessonDto> getAllByDateBetween(LocalDate dateFrom, LocalDate dateTo);
}
