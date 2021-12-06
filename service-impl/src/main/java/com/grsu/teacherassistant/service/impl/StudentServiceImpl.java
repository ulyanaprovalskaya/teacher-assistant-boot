package com.grsu.teacherassistant.service.impl;

import com.grsu.teacherassistant.dto.StudentDto;
import com.grsu.teacherassistant.model.entity.LessonType;
import com.grsu.teacherassistant.model.entity.Student;
import com.grsu.teacherassistant.repository.LessonRepository;
import com.grsu.teacherassistant.repository.StudentRepository;
import com.grsu.teacherassistant.repository.projection.AdditionalLesson;
import com.grsu.teacherassistant.service.api.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final LessonRepository lessonRepository;
    private final ModelMapper modelMapper;

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
    public void createStudent(StudentDto student) {
        studentRepository.save(modelMapper.map(student, Student.class));
    }

    @Override
    public void deleteStudent(Integer id) {
        studentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void editStudent(StudentDto studentDto) {
    }


    @Override
    public Page<Student> getAllStudents(Pageable pageable, Specification<Student> studentSpecification) {
        return studentRepository.findAll(studentSpecification,pageable);
    }

    @Override
    public List<StudentDto> findAllStudents(Page<Student> studentPage) {
        return studentPage
                .getContent()
                .stream()
                .map(student -> modelMapper.map(student, StudentDto.class))
                .collect(Collectors.toList());
    }


}
