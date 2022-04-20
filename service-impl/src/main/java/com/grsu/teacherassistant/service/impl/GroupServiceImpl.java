package com.grsu.teacherassistant.service.impl;

import com.grsu.teacherassistant.dto.student.StudentDto;
import com.grsu.teacherassistant.dto.StudentGroupDto;
import com.grsu.teacherassistant.entity.Student;
import com.grsu.teacherassistant.entity.StudentGroup;
import com.grsu.teacherassistant.mapper.StudentGroupMapper;
import com.grsu.teacherassistant.mapper.StudentMapper;
import com.grsu.teacherassistant.repository.GroupRepository;
import com.grsu.teacherassistant.service.api.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final StudentGroupMapper studentGroupMapper;
    private final StudentMapper studentMapper;

    @Override
    public void createGroup(StudentGroupDto group) {
        groupRepository.save(studentGroupMapper.toEntity(group));
    }

    @Override
    public void deleteGroup(Integer id) {
        groupRepository.deleteById(id);
    }

    @Override
    public StudentGroupDto getById(Integer id) {
        StudentGroup byId = groupRepository.getById(id);
        return modelMapper.map(byId, StudentGroupDto.class);
    }

    @Override
    public List<StudentGroupDto> getAll() {
        return ((List<StudentGroup>) groupRepository.findAll())
                .stream()
                .map(studentGroupMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentGroupDto> getActive() {
        return groupRepository.findAllByActive(true)
                .stream()
                .map(studentGroupMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentGroupDto> getGroupsByStreamId(Integer id) {
        return groupRepository.findAllByStreamId(id)
                .stream()
                .map(studentGroupMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentGroupDto> getAllByGroupIds(List<Integer> ids) {
        return groupRepository.findAllByIdIn(ids)
                .stream()
                .map(studentGroup -> modelMapper.map(studentGroup, StudentGroupDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void addStudentToGroup(StudentDto studentDto, StudentGroupDto groupDto) {
        Student student = studentMapper.toEntity(studentDto);
        StudentGroup group  = studentGroupMapper.toEntity(groupDto);
        group.getStudents().add(student);
    }
}
