package com.grsu.teacherassistant.service.api;

import com.grsu.teacherassistant.model.entity.Lesson;

import java.time.LocalDate;
import java.util.List;

public interface LessonService {

    void createLesson(Lesson lesson);

    void deleteLesson(Integer lessonId);

    List<Lesson> getAllByDateBetween(LocalDate dateFrom, LocalDate dateTo);

}
