package com.grsu.teacherassistant.service.api;

import com.grsu.teacherassistant.model.entity.Discipline;

import java.util.List;

public interface DisciplineService {

    void createDiscipline(Discipline discipline);

    void deleteDiscipline(Integer id);

    void editDiscipline(Discipline discipline);

    List<Discipline> getAll();

}
