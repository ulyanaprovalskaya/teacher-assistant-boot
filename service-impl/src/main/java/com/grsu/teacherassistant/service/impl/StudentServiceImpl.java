package com.grsu.teacherassistant.service.impl;

import com.grsu.teacherassistant.dto.lesson.StudentLessonDto;
import com.grsu.teacherassistant.dto.student.StudentDto;
import com.grsu.teacherassistant.dto.student.StudentWithAttendanceDto;
import com.grsu.teacherassistant.entity.LessonType;
import com.grsu.teacherassistant.entity.Student;
import com.grsu.teacherassistant.exception.EntityNotFoundException;
import com.grsu.teacherassistant.mapper.StudentLessonMapper;
import com.grsu.teacherassistant.mapper.StudentMapper;
import com.grsu.teacherassistant.repository.LessonRepository;
import com.grsu.teacherassistant.repository.StudentLessonRepository;
import com.grsu.teacherassistant.repository.StudentRepository;
import com.grsu.teacherassistant.repository.projection.AdditionalLesson;
import com.grsu.teacherassistant.service.api.DisciplineService;
import com.grsu.teacherassistant.service.api.ImageService;
import com.grsu.teacherassistant.service.api.StudentService;
import com.grsu.teacherassistant.service.filter.StudentFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final LessonRepository lessonRepository;
    private final StudentFilter studentFilter;
    private final StudentLessonRepository studentLessonRepository;
    private final DisciplineService disciplineService;
    private final ImageService imageService;
    private final StudentMapper studentMapper;
    private final StudentLessonMapper studentLessonMapper;

    private static final DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd.MM.yyyy");

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
        studentRepository.save(studentMapper.toEntity(student));
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
    public StudentWithAttendanceDto getById(Integer id) {
        return studentMapper.toFullDto(studentRepository.getById(id));
    }

    @Override
    public List<StudentDto> getPresentStudents(Integer lessonId) {
        List<StudentDto> presentStudents = studentRepository.findByPresenceAndLessonId(1, lessonId)
                                                            .stream()
                                                            .map(studentMapper::toDto)
                                                            .collect(Collectors.toList());
        log.info("Found present students, lessonId = {}, students amount = {}", lessonId, presentStudents.size());
        return presentStudents;
    }

    @Override
    public List<StudentDto> getAbsentStudents(Integer lessonId) {
        List<StudentDto> absentStudents = studentRepository.findByPresenceAndLessonId(0, lessonId)
                                                           .stream()
                                                           .map(studentMapper::toDto)
                                                           .collect(Collectors.toList());
        log.info("Found absent students, lessonId = {}, students amount = {}", lessonId, absentStudents.size());
        return absentStudents;
    }

    @Override
    @Transactional
    public StudentWithAttendanceDto getStudentAttendance(Integer studentId, Integer lessonId) {
        Integer disciplineId = disciplineService.getByLessonId(lessonId).getId();
        Student student = studentRepository.findByIdWithGroup(studentId, disciplineId)
                                           .orElseThrow(() -> new EntityNotFoundException(String.format(
                                                   "Student with id=%d not found",
                                                   studentId)));

        List<StudentLessonDto> studentTotalSkips = studentLessonRepository
                .findByRegisteredAndStudentIdAndLessonStreamDisciplineId(0, studentId, disciplineId)
                .stream()
                .map(studentLessonMapper::toDto)
                .collect(Collectors.toList());

        List<StudentLessonDto> studentLectureSkips =
                getStudentSkipsByLessonType(studentId, disciplineId, LessonType.LessonTypeEnum.LECTURE);
        List<StudentLessonDto> studentPracticalSkips =
                getStudentSkipsByLessonType(studentId, disciplineId, LessonType.LessonTypeEnum.PRACTICAL);
        String studentImagePath = imageService.getStudentImagePath(studentRepository.findById(studentId)
                                                                                    .orElseThrow(() -> new EntityNotFoundException(
                                                                                            String.format(
                                                                                                    "Student with id=%d not found",
                                                                                                    studentId)))
                                                                                    .getCardUid());


        log.info("Found student (id={}) total skips by discipline (id={}): {}",
                 studentId,
                 disciplineId,
                 studentTotalSkips);
        log.info("Found student (id={}) lecture skips by discipline (id={}): {}",
                 studentId,
                 disciplineId,
                 studentLectureSkips);
        log.info("Found student (id={}) practical skips by discipline (id={}): {}",
                 studentId,
                 disciplineId,
                 studentPracticalSkips);

        return studentMapper.toFullDto(student)
                            .setDisciplineId(disciplineId)
                            .setTotalSkips(studentTotalSkips)
                            .setLectureSkips(studentLectureSkips)
                            .setPracticalSkips(studentPracticalSkips)
                            .setImagePath(studentImagePath);
    }

    @Override
    public String getTotalSkipsInfo(List<StudentLessonDto> skips) {
        StringBuilder sb = new StringBuilder();
        for (StudentLessonDto skip : skips) {
            sb.append(skip.getDate().format(formatters))
              .append("  ")
              .append(skip.getType().getName())
              .append("\n");
        }
        String skipsInfo = sb.toString();
        log.info("Found skips info: {}", skipsInfo);
        return skipsInfo;
    }

    @Override
    public String getPracticalSkipsInfo(List<StudentLessonDto> skips) {
        String skipsInfo = getSkipsInfoByLessonType(skips, "Практическое занятие");
        log.info("Found practical skips info: {}", skipsInfo);
        return skipsInfo;
    }

    @Override
    public String getLectureSkipsInfo(List<StudentLessonDto> skips) {
        String skipsInfo = getSkipsInfoByLessonType(skips, "Лекция");
        log.info("Found lecture skips info: {}", skipsInfo);
        return skipsInfo;
    }

    private String getSkipsInfoByLessonType(List<StudentLessonDto> skips, String lessonTypeName) {
        StringBuilder sb = new StringBuilder();
        for (StudentLessonDto skip : skips) {
            if (skip.getType().getName().equals(lessonTypeName)) {
                sb.append(skip.getDate().format(formatters)).append("\n");
            }
        }
        return sb.toString();
    }

    private List<StudentLessonDto> getStudentSkipsByLessonType(Integer studentId,
                                                               Integer disciplineId,
                                                               LessonType.LessonTypeEnum lessonType) {
        return studentLessonRepository
                .findByRegisteredAndStudentIdAndLessonStreamDisciplineIdAndLessonTypeType(0,
                                                                                          studentId,
                                                                                          disciplineId,
                                                                                          lessonType)
                .stream()
                .map(studentLessonMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentDto> getAllByIdInList(List<Integer> studentIds) {
        return studentRepository.findAllByIdIn(studentIds)
                                .stream()
                                .map(studentMapper::toDto)
                                .collect(Collectors.toList());
    }

    @Override
    public List<StudentDto> getAll() {
        return studentRepository.findAll()
                                .stream()
                                .map(studentMapper::toDto)
                                .collect(Collectors.toList());
    }

    @Override
    public Page<StudentDto> getAllStudents(Pageable pageable,
                                           String sortDirection,
                                           String sortField,
                                           boolean isArchived,
                                           String search) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(),
                                                 pageable.getPageSize(),
                                                 Sort.Direction.fromString(sortDirection),
                                                 sortField);
        Page<Student> students;
        if (isArchived) {
            students = studentRepository.getArchivedStudents(studentFilter.getFilter(search), pageRequest);
        } else {
            students = studentRepository.findAll(studentFilter.getFilter(search), pageRequest);
        }

        List<StudentDto> studentDtoList = students
                .stream()
                .map(studentMapper::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(studentDtoList, pageable, studentDtoList.size());
    }

    @Override
    public Page<Student> getAllStudents(Pageable pageable,
                                        Specification<Student> studentSpecification,
                                        String sortDirection,
                                        String sortField,
                                        boolean isArchived) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(),
                                                 pageable.getPageSize(),
                                                 Sort.Direction.fromString(sortDirection),
                                                 sortField);
        if (isArchived) {
            return studentRepository.getArchivedStudents(studentSpecification, pageRequest);
        }
        return studentRepository.findAll(studentSpecification, pageRequest);
    }
}
