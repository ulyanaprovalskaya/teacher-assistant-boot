package com.grsu.teacherassistant.mapper;

import com.grsu.teacherassistant.dto.StreamDto;
import com.grsu.teacherassistant.entity.Stream;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StreamMapper {

    StreamDto toDto(Stream stream);

    Stream toEntity(StreamDto streamDto);
}
