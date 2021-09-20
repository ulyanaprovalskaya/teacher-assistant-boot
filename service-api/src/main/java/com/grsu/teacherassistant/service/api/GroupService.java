package com.grsu.teacherassistant.service.api;

import com.grsu.teacherassistant.model.entity.Student;
import com.grsu.teacherassistant.model.entity.StudentGroup;

import java.util.List;

public interface GroupService {

    void createGroup(StudentGroup group);

    void deleteGroup(Integer id);

    List<StudentGroup> getAll();

    List<StudentGroup> getActive();

    void addStudentToGroup(Student student, StudentGroup group);
}
