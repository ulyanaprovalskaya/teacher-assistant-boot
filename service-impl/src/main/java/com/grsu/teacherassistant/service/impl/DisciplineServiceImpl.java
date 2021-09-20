package com.grsu.teacherassistant.service.impl;

import com.grsu.teacherassistant.model.entity.Discipline;
import com.grsu.teacherassistant.repository.DisciplineRepository;
import com.grsu.teacherassistant.service.api.DisciplineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DisciplineServiceImpl implements DisciplineService {

    private final DisciplineRepository disciplineRepository;

    @Override
    public void createDiscipline(Discipline discipline) {
        disciplineRepository.save(discipline);
    }

    @Override
    public void deleteDiscipline(Integer id) {
        disciplineRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void editDiscipline(Discipline discipline) {

    }

    @Override
    public List<Discipline> getAll() {
        return (List<Discipline>)disciplineRepository.findAll();
    }
}
