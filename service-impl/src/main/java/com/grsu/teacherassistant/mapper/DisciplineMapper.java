package com.grsu.teacherassistant.mapper;

import com.grsu.teacherassistant.dto.DisciplineDto;
import com.grsu.teacherassistant.entity.Discipline;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DisciplineMapper {

    DisciplineDto toDto(Discipline discipline);

    Discipline toEntity(DisciplineDto disciplineDto);
}
