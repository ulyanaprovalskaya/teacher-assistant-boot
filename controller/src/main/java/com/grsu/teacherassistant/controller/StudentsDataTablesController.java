package com.grsu.teacherassistant.controller;

import com.grsu.teacherassistant.dto.student.StudentDto;
import com.grsu.teacherassistant.service.api.StudentLessonService;
import com.grsu.teacherassistant.service.api.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/registration")
@RequiredArgsConstructor
public class StudentsDataTablesController {

    private final StudentService studentService;
    private final StudentLessonService studentLessonService;

    @GetMapping("/present-students")
    List<StudentDto> getPresentStudents(@RequestParam("lessonId") Integer lessonId) {
        return studentService.getPresentStudents(lessonId);
    }

    @GetMapping("/absent-students")
    List<StudentDto> getAbsentStudents(@RequestParam("lessonId") Integer lessonId) {
        return studentService.getAbsentStudents(lessonId);
    }

    @GetMapping("/student/register")
    void updateRegistered(@ModelAttribute("studentId") Integer studentId,
                            @ModelAttribute("lessonId") Integer lessonId,
                            @ModelAttribute("registered") Integer registered) {
        studentLessonService.updateRegistered(studentId, lessonId, registered);
    }
}
