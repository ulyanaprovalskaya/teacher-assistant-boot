package com.grsu.teacherassistant.service.impl;

import com.grsu.teacherassistant.model.entity.LessonType;
import com.grsu.teacherassistant.model.entity.Student;
import com.grsu.teacherassistant.repository.LessonRepository;
import com.grsu.teacherassistant.repository.StudentRepository;
import com.grsu.teacherassistant.repository.projection.AdditionalLesson;
import com.grsu.teacherassistant.service.api.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final LessonRepository lessonRepository;

    @Override
    public List<AdditionalLesson> getStudentAdditionalLessons(Integer studentId, Integer disciplineId) {
        return lessonRepository.getStudentAdditionalLessons(studentId);
    }

    @Override
    public Integer getStudentTotalSkipsAmount(Integer studentId, Integer disciplineId) {
        return studentRepository.getStudentTotalSkipsAmount(studentId, disciplineId);
    }

    @Override
    public Integer getStudentSkipsAmountByLessonType(Integer studentId, Integer disciplineId, LessonType type) {
        return studentRepository.getStudentSkipsAmountByLessonType(studentId, disciplineId, type);
    }

    @Override
    public void createStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Integer id) {
        studentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void editStudent(Student student) {

    }
}
