package com.grsu.teacherassistant.service.impl;

import com.grsu.teacherassistant.model.entity.Stream;
import com.grsu.teacherassistant.model.entity.StudentGroup;
import com.grsu.teacherassistant.repository.StreamRepository;
import com.grsu.teacherassistant.service.api.StreamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StreamServiceImpl implements StreamService {

    private final StreamRepository streamRepository;

    @Override
    public void createStream(Stream stream) {
        streamRepository.save(stream);
    }

    @Override
    public void deleteStream(Integer id) {
        streamRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void addGroupToStream(StudentGroup group, Stream stream) {
        stream.getGroups().add(group);
    }

    @Override
    public List<Stream> getAll() {
        return (List<Stream>) streamRepository.findAll();
    }

    @Override
    public List<Stream> getAllActive() {
        return null;
    }
}
