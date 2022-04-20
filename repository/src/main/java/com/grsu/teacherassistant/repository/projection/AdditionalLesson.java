package com.grsu.teacherassistant.repository.projection;

import com.grsu.teacherassistant.entity.Department;
import com.grsu.teacherassistant.entity.GroupType;
import com.grsu.teacherassistant.entity.Lesson;
import com.grsu.teacherassistant.entity.LessonType;
import com.grsu.teacherassistant.entity.Stream;
import com.grsu.teacherassistant.entity.Student;

import java.time.LocalDateTime;
import java.util.List;

public interface AdditionalLesson {
    LocalDateTime getDate();

    LessonType getType();

    StudentGroupInfo getGroup();

    interface StudentGroupInfo {
        Integer getId();

        boolean isActive();

        Department getDepartment();

        LocalDateTime getExpirationDate();

        List<Lesson> getLessons();

        String getName();

        Student getPraepostor();

        List<Stream> getStreams();

        List<Student> getStudents();

        GroupType getType();
    }
}
