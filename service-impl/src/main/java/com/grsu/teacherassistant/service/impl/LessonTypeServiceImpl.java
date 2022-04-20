package com.grsu.teacherassistant.service.impl;

import com.grsu.teacherassistant.dto.lesson.LessonTypeDto;
import com.grsu.teacherassistant.repository.LessonTypeRepository;
import com.grsu.teacherassistant.service.api.LessonTypeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LessonTypeServiceImpl implements LessonTypeService {

    private final LessonTypeRepository lessonTypeRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<LessonTypeDto> getAll() {
        return lessonTypeRepository.findAll()
                .stream()
                .map(lessonType -> modelMapper.map(lessonType, LessonTypeDto.class))
                .collect(Collectors.toList());
    }
}
