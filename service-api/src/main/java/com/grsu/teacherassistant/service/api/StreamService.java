package com.grsu.teacherassistant.service.api;

import com.grsu.teacherassistant.dto.StreamDto;
import com.grsu.teacherassistant.dto.StudentGroupDto;

import java.util.List;

public interface StreamService {

    void createStream(StreamDto stream);

    void deleteStream(Integer id);

    void addGroupToStream(StudentGroupDto group, StreamDto stream);

    List<StreamDto> getAll();

    List<StreamDto> getAllActive();
}
