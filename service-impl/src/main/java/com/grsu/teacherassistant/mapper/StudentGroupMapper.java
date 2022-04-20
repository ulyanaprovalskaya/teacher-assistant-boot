package com.grsu.teacherassistant.mapper;

import com.grsu.teacherassistant.dto.StudentGroupDto;
import com.grsu.teacherassistant.entity.StudentGroup;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentGroupMapper {

    StudentGroupDto toDto(StudentGroup group);

    StudentGroup toEntity(StudentGroupDto groupDto);
}
