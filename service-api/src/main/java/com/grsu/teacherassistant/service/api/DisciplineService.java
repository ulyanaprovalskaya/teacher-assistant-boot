package com.grsu.teacherassistant.service.api;

import com.grsu.teacherassistant.dto.DisciplineDto;

import java.util.List;

public interface DisciplineService {

    void createDiscipline(DisciplineDto discipline);

    void deleteDiscipline(Integer id);

    void editDiscipline(DisciplineDto discipline);

    List<DisciplineDto> getAll();

}
