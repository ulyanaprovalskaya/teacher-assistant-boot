package com.grsu.teacherassistant.service.impl;

import com.grsu.teacherassistant.model.entity.Discipline;
import com.grsu.teacherassistant.repository.DisciplineRepository;
import com.grsu.teacherassistant.service.api.DisciplineService;
import com.grsu.teacherassistant.dto.DisciplineDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DisciplineServiceImpl implements DisciplineService {

    private final DisciplineRepository disciplineRepository;
    private final ModelMapper modelMapper;

    @Override
    public void createDiscipline(DisciplineDto disciplineDto) {
        disciplineRepository.save(modelMapper.map(disciplineDto, Discipline.class));
    }

    @Override
    public void deleteDiscipline(Integer id) {
        disciplineRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void editDiscipline(DisciplineDto disciplineDto) {

    }

    @Override
    public List<DisciplineDto> getAll() {
        return ((List<Discipline>)disciplineRepository.findAll())
                .stream()
                .map(d -> modelMapper.map(d, DisciplineDto.class))
                .collect(Collectors.toList());
    }
}
