package com.grsu.teacherassistant.service.impl;

import com.grsu.teacherassistant.dto.LessonDto;
import com.grsu.teacherassistant.model.entity.Lesson;
import com.grsu.teacherassistant.repository.LessonRepository;
import com.grsu.teacherassistant.service.api.LessonService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final ModelMapper modelMapper;

    @Override
    public void createLesson(LessonDto lesson) {
        lessonRepository.save(modelMapper.map(lesson, Lesson.class));
    }

    @Override
    public void deleteLesson(Integer id) {
        lessonRepository.deleteById(id);
    }

    @Override
    public List<LessonDto> getAllByDateBetween(LocalDate dateFrom, LocalDate dateTo) {
        return lessonRepository.getLessonByDateBetween(dateFrom.atTime(00, 00), dateTo.atTime(23, 59))
                .stream()
                .map(lesson -> modelMapper.map(lesson, LessonDto.class))
                .collect(Collectors.toList());
    }
}
