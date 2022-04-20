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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public StudentDto getById(Integer id) {
        return modelMapper.map(studentRepository.getById(id), StudentDto.class);
    }

    @Override
    public List<StudentDto> getAllByIdInList(List<Integer> studentIds) {
        return studentRepository.findAllByIdIn(studentIds)
                .stream()
                .map(student -> modelMapper.map(student, StudentDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentDto> getAll() {
        return studentRepository.findAll()
                .stream()
                .map(student -> modelMapper.map(student, StudentDto.class))
                .collect(Collectors.toList());
    }


    @Override
    public Page<Student> getAllStudents(Pageable pageable, String sortDirection, String sortField, boolean isArchived) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.Direction.fromString(sortDirection), sortField);
        if (isArchived) {
            return studentRepository.getArchivedStudents(pageRequest);
        }
        return studentRepository.findAll(pageRequest);
    }

    @Override
    public Page<Student> getAllStudents(Pageable pageable, Specification<Student> studentSpecification, String sortDirection, String sortField, boolean isArchived) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.Direction.fromString(sortDirection), sortField);
        if (isArchived) {
            return studentRepository.getArchivedStudents(studentSpecification, pageRequest);
        }
        return studentRepository.findAll(studentSpecification, pageRequest);
    }
}
