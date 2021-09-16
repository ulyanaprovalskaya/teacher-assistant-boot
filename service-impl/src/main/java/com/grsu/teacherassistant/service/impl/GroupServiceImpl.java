package com.grsu.teacherassistant.service.impl;

import com.grsu.teacherassistant.dto.StudentDto;
import com.grsu.teacherassistant.dto.StudentGroupDto;
import com.grsu.teacherassistant.model.entity.Student;
import com.grsu.teacherassistant.model.entity.StudentGroup;
import com.grsu.teacherassistant.repository.GroupRepository;
import com.grsu.teacherassistant.service.api.GroupService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final ModelMapper modelMapper;

    @Override
    public void createGroup(StudentGroupDto group) {
        groupRepository.save(modelMapper.map(group, StudentGroup.class));
    }

    @Override
    public void deleteGroup(Integer id) {
        groupRepository.deleteById(id);
    }

    @Override
    public List<StudentGroupDto> getAll() {
        return ((List<StudentGroup>) groupRepository.findAll())
                .stream()
                .map(studentGroup -> modelMapper.map(studentGroup, StudentGroupDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentGroupDto> getActive() {
        return groupRepository.findAllByActive(true)
                .stream()
                .map(studentGroup -> modelMapper.map(studentGroup, StudentGroupDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void addStudentToGroup(StudentDto studentDto, StudentGroupDto groupDto) {
        Student student = modelMapper.map(studentDto, Student.class);
        StudentGroup group  = modelMapper.map(groupDto, StudentGroup.class);
        group.getStudents().add(student);
    }
}
