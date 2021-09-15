package com.grsu.teacherassistant.service.impl;

import com.grsu.teacherassistant.model.entity.Student;
import com.grsu.teacherassistant.model.entity.StudentGroup;
import com.grsu.teacherassistant.repository.GroupRepository;
import com.grsu.teacherassistant.service.api.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    @Override
    public void createGroup(StudentGroup group) {
        groupRepository.save(group);
    }

    @Override
    public void deleteGroup(Integer id) {
        groupRepository.deleteById(id);
    }

    @Override
    public List<StudentGroup> getAll() {
        return (List<StudentGroup>) groupRepository.findAll();
    }

    @Override
    public List<StudentGroup> getActive() {
        return groupRepository.findAllByActive(true);
    }

    @Override
    @Transactional
    public void addStudentToGroup(Student student, StudentGroup group) {
        group.getStudents().add(student);
    }
}
