package com.grsu.teacherassistant.service.impl;

import com.grsu.teacherassistant.dto.lesson.LessonDto;
import com.grsu.teacherassistant.dto.lesson.LessonWithSchedule;
import com.grsu.teacherassistant.exception.EntityNotFoundException;
import com.grsu.teacherassistant.mapper.LessonMapper;
import com.grsu.teacherassistant.repository.LessonRepository;
import com.grsu.teacherassistant.service.api.LessonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final LessonMapper lessonMapper;

    @Override
    public void createLesson(LessonDto lesson) {
        lessonRepository.save(lessonMapper.toEntity(lesson));
    }

    @Override
    public void deleteLesson(Integer id) {
        lessonRepository.deleteById(id);
    }

    @Override
    public LessonWithSchedule getById(Integer id) {
        LessonWithSchedule lesson = lessonMapper.toFullDto(lessonRepository
                                                            .findById(id)
                                                            .orElseThrow(() -> new EntityNotFoundException(
                                                                    String.format(
                                                                            "Lesson with id=%d not found",
                                                                            id))));

        log.info("Found lesson {} by id={}", lesson, id);
        return lesson;
    }

    @Override
    public List<LessonDto> getAllByDateBetween(LocalDate dateFrom, LocalDate dateTo) {
        return lessonRepository.getLessonByDateBetween(dateFrom.atTime(0, 0), dateTo.atTime(23, 59))
                               .stream()
                               .map(lessonMapper::toDto)
                               .collect(Collectors.toList());
    }
}
