package com.grsu.teacherassistant.service.impl;

import com.grsu.teacherassistant.dto.LessonDto;
import com.grsu.teacherassistant.model.entity.Discipline;
import com.grsu.teacherassistant.model.entity.Lesson;
import com.grsu.teacherassistant.paging.Paged;
import com.grsu.teacherassistant.paging.Paging;
import com.grsu.teacherassistant.repository.DisciplineRepository;
import com.grsu.teacherassistant.repository.LessonRepository;
import com.grsu.teacherassistant.service.api.LessonService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final DisciplineRepository disciplineRepository;
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
    @Transactional
    public Paged<LessonDto> getAllByDateBetween(LocalDate dateFrom, LocalDate dateTo, Pageable pageable) {
        Page<Lesson> filteredLessons = lessonRepository.getLessonByDateBetween(dateFrom.atTime(00, 00), dateTo.atTime(23, 59), pageable);

        filteredLessons.forEach(lesson -> {
            if (lesson.getDiscipline() == null) {
                Discipline discipline = disciplineRepository.getDisciplineByLessonId(lesson.getId());
                lesson.setDiscipline(discipline);
                lessonRepository.save(lesson);
            }
        });

        Page<LessonDto> filteredLessonsPage = new PageImpl<>(filteredLessons
                .stream()
                .map(lesson -> modelMapper.map(lesson, LessonDto.class))
                .collect(Collectors.toList()), pageable, filteredLessons.getTotalPages());

        return new Paged<>(filteredLessonsPage,  Paging.of(filteredLessonsPage.getTotalPages(), pageable.getPageNumber(), pageable.getPageSize()));
    }
}
