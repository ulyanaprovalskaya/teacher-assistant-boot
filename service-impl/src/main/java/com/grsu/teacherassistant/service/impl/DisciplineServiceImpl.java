package com.grsu.teacherassistant.service.impl;

import com.grsu.teacherassistant.dto.DisciplineDto;
import com.grsu.teacherassistant.entity.Discipline;
import com.grsu.teacherassistant.exception.EntityNotFoundException;
import com.grsu.teacherassistant.mapper.DisciplineMapper;
import com.grsu.teacherassistant.repository.DisciplineRepository;
import com.grsu.teacherassistant.service.api.DisciplineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DisciplineServiceImpl implements DisciplineService {

    private final DisciplineRepository disciplineRepository;
    private final DisciplineMapper disciplineMapper;

    @Override
    public void createDiscipline(DisciplineDto disciplineDto) {
        disciplineDto.setCreateDate(LocalDateTime.now());
        disciplineDto.setActive(true);
        disciplineRepository.save(disciplineMapper.toEntity(disciplineDto));
    }

    @Override
    public void updateDiscipline(DisciplineDto disciplineDto) {
        disciplineRepository.save(disciplineMapper.toEntity(disciplineDto));
    }

    @Override
    public void deleteDiscipline(Integer id) {
        disciplineRepository.deleteById(id);
    }

    @Override
    public List<DisciplineDto> getAll() {
        return ((List<Discipline>) disciplineRepository.findAll())
                .stream()
                .map(disciplineMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DisciplineDto getById(Integer id) {
        return disciplineMapper.toDto(disciplineRepository.findById(id)
                                                          .orElseThrow(() -> new EntityNotFoundException(String.format(
                                                                  "Discipline with id=%d not found", id))));
    }

    @Override
    public DisciplineDto getByLessonId(Integer lessonId) {
        DisciplineDto disciplineDto = disciplineMapper.toDto(disciplineRepository.findByLessonId(lessonId)
                                                          .orElseThrow(() -> new EntityNotFoundException(String.format(
                                                                  "Discipline wasn't found by lessonId=%d ", lessonId))));
        log.info("Discipline {} was found by lessonId={}", disciplineDto, lessonId);
        return disciplineDto;
    }
}
