package com.grsu.teacherassistant.service.api;

import com.grsu.teacherassistant.model.entity.Stream;
import com.grsu.teacherassistant.model.entity.StudentGroup;

import java.util.List;

public interface StreamService {

    void createStream(Stream stream);

    void deleteStream(Integer id);

    void addGroupToStream(StudentGroup group, Stream stream);

    List<Stream> getAll();
}
