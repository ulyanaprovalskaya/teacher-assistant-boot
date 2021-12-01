package com.grsu.teacherassistant.service.api;

import com.grsu.teacherassistant.dto.LessonDto;
import com.grsu.teacherassistant.paging.Paged;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface LessonService {

    void createLesson(LessonDto lesson);

    void deleteLesson(Integer lessonId);

    Paged<LessonDto> getAllByDateBetween(LocalDate dateFrom, LocalDate dateTo, Pageable pageable);

}
