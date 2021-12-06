package com.grsu.teacherassistant.service.api;

import com.grsu.teacherassistant.dto.DisciplineDto;

import java.util.List;

public interface DisciplineService {

    void createDiscipline(DisciplineDto discipline);

    void updateDiscipline(DisciplineDto discipline);

    void deleteDiscipline(Integer id);

    List<DisciplineDto> getAll();

    DisciplineDto getById(Integer id);

    DisciplineDto getDisciplineByLessonId(Integer lessonId);

}
