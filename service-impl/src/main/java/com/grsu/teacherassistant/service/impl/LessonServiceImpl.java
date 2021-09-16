package com.grsu.teacherassistant.service.impl;

import com.grsu.teacherassistant.model.entity.Lesson;
import com.grsu.teacherassistant.repository.LessonRepository;
import com.grsu.teacherassistant.service.api.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;

    @Override
    public void createLesson(Lesson lesson) {
        lessonRepository.save(lesson);
    }

    @Override
    public void deleteLesson(Integer id) {
        lessonRepository.deleteById(id);
    }

    @Override
    public List<Lesson> getAllByDateBetween(LocalDate dateFrom, LocalDate dateTo) {
        return lessonRepository.getLessonByDateBetween(dateFrom.atTime(00, 00), dateTo.atTime(23, 59));
    }
}
