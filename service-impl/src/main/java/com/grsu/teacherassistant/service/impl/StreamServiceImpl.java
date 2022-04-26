package com.grsu.teacherassistant.service.impl;

import com.grsu.teacherassistant.dto.StreamDto;
import com.grsu.teacherassistant.dto.StudentGroupDto;
import com.grsu.teacherassistant.entity.Stream;
import com.grsu.teacherassistant.entity.StudentGroup;
import com.grsu.teacherassistant.mapper.StreamMapper;
import com.grsu.teacherassistant.mapper.StudentGroupMapper;
import com.grsu.teacherassistant.repository.StreamRepository;
import com.grsu.teacherassistant.service.api.StreamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StreamServiceImpl implements StreamService {

    private final StreamRepository streamRepository;
    private final StreamMapper streamMapper;
    private final StudentGroupMapper studentGroupMapper;

    @Override
    public void createStream(StreamDto stream) {
        if (stream.getId() == null) {
            stream.setCreateDate(LocalDateTime.now());
        }
        streamRepository.save(streamMapper.toEntity(stream));
    }

    @Override
    public void deleteStream(Integer id) {
        streamRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void addGroupToStream(StudentGroupDto groupDto, StreamDto streamDto) {
        Stream stream = streamMapper.toEntity(streamDto);
        StudentGroup group = studentGroupMapper.toEntity(groupDto);
        stream.getGroups().add(group);
    }

    @Override
    public StreamDto getByStreamId(Integer id) {
        return streamMapper.toDto(streamRepository.getById(id));
    }

    @Override
    public List<StreamDto> getAll() {
        return  streamRepository.findAll()
                .stream()
                .map(streamMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<StreamDto> getAllActive() {
        return null;
    }
}
