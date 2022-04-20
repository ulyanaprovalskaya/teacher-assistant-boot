package com.grsu.teacherassistant.service.api;

import com.grsu.teacherassistant.dto.student.StudentDto;
import com.grsu.teacherassistant.dto.StudentGroupDto;
import com.grsu.teacherassistant.model.entity.Student;
import com.grsu.teacherassistant.model.entity.StudentGroup;

import java.util.List;

public interface GroupService {

    void createGroup(StudentGroupDto group);

    void deleteGroup(Integer id);

    StudentGroupDto getById(Integer id);

    List<StudentGroupDto> getAll();

    List<StudentGroupDto> getActive();

    List<StudentGroupDto> getGroupsByStreamId(Integer id);

    List<StudentGroupDto> getAllByGroupIds(List<Integer> ids);

    void addStudentToGroup(StudentDto student, StudentGroupDto group);
}
