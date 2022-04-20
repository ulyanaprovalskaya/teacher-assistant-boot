package com.grsu.teacherassistant.service.api;

import com.grsu.teacherassistant.dto.StudentDto;
import com.grsu.teacherassistant.model.entity.LessonType;
import com.grsu.teacherassistant.model.entity.Student;
import com.grsu.teacherassistant.repository.projection.AdditionalLesson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;


public interface StudentService {

    List<AdditionalLesson> getStudentAdditionalLessons(Integer studentId, Integer disciplineId);

    Integer getStudentTotalSkipsAmount(Integer studentId, Integer disciplineId);

    Integer getStudentSkipsAmountByLessonType(Integer studentId, Integer disciplineId, LessonType type);

    void createStudent(StudentDto student);

    void deleteStudent(Integer id);

    void editStudent(StudentDto student);

    StudentDto getById(Integer id);

    List<StudentDto> getAllByIdInList(List<Integer> studentIds);

    List<StudentDto> getAll();

    Page<Student> getAllStudents(Pageable pageable, String sortDirection, String sortField, boolean isArchived, String search);

    Page<Student> getAllStudents(Pageable pageable, Specification<Student> studentSpecification, String sortDirection, String sortField, boolean isArchived);
}
