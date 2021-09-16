package com.grsu.teacherassistant.service.api;

import com.grsu.teacherassistant.dto.StreamDto;
import com.grsu.teacherassistant.dto.StudentGroupDto;
import com.grsu.teacherassistant.model.entity.Stream;
import com.grsu.teacherassistant.model.entity.StudentGroup;

import java.util.List;

public interface StreamService {

    void createStream(StreamDto stream);

    void deleteStream(Integer id);

    void addGroupToStream(StudentGroupDto group, StreamDto stream);

    List<StreamDto> getAll();

    List<StreamDto> getAllActive();
}
