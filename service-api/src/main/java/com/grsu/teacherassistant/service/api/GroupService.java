package com.grsu.teacherassistant.service.api;

import com.grsu.teacherassistant.dto.StudentDto;
import com.grsu.teacherassistant.dto.StudentGroupDto;

import java.util.List;

public interface GroupService {

    void createGroup(StudentGroupDto group);

    void deleteGroup(Integer id);

    List<StudentGroupDto> getAll();

    List<StudentGroupDto> getActive();

    void addStudentToGroup(StudentDto student, StudentGroupDto group);
}
