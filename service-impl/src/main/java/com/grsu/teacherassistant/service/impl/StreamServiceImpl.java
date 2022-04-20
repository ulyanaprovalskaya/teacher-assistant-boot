package com.grsu.teacherassistant.service.impl;

import com.grsu.teacherassistant.dto.StreamDto;
import com.grsu.teacherassistant.dto.StudentGroupDto;
import com.grsu.teacherassistant.entity.Stream;
import com.grsu.teacherassistant.entity.StudentGroup;
import com.grsu.teacherassistant.repository.StreamRepository;
import com.grsu.teacherassistant.service.api.StreamService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StreamServiceImpl implements StreamService {

    private final StreamRepository streamRepository;
    private final ModelMapper modelMapper;

    @Override
    public void createStream(StreamDto stream) {
        if (stream.getId() == null) {
            stream.setCreateDate(LocalDateTime.now());
        }
        streamRepository.save(modelMapper.map(stream, Stream.class));
    }

    @Override
    public void deleteStream(Integer id) {
        streamRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void addGroupToStream(StudentGroupDto groupDto, StreamDto streamDto) {
        Stream stream = modelMapper.map(streamDto, Stream.class);
        StudentGroup group = modelMapper.map(groupDto, StudentGroup.class);
        stream.getGroups().add(group);
    }

    @Override
    public StreamDto getByStreamId(Integer id) {
        return modelMapper.map(streamRepository.getById(id), StreamDto.class);
    }

    @Override
    public List<StreamDto> getAll() {
        return  streamRepository.findAll()
                .stream()
                .map(stream -> modelMapper.map(stream, StreamDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<StreamDto> getAllActive() {
        return null;
    }
}
