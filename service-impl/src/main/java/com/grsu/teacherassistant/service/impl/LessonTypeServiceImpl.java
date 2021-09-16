package com.grsu.teacherassistant.service.impl;

import com.grsu.teacherassistant.model.entity.LessonType;
import com.grsu.teacherassistant.repository.LessonTypeRepository;
import com.grsu.teacherassistant.service.api.LessonTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonTypeServiceImpl implements LessonTypeService {

    private final LessonTypeRepository lessonTypeRepository;

    @Override
    public List<LessonType> getAll() {
        return lessonTypeRepository.findAll();
    }
}
