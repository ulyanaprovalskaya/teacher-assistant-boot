package com.grsu.teacherassistant.service.api;

import com.grsu.teacherassistant.model.entity.Discipline;

public interface DisciplineService {

    void createDiscipline(Discipline discipline);

    void deleteDiscipline(Integer id);

    void editDiscipline(Discipline discipline);

}
