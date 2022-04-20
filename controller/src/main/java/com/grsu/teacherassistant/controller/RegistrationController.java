package com.grsu.teacherassistant.controller;

import com.grsu.teacherassistant.dto.lesson.LessonWithSchedule;
import com.grsu.teacherassistant.dto.student.StudentDto;
import com.grsu.teacherassistant.dto.student.StudentWithAttendanceDto;
import com.grsu.teacherassistant.service.api.LessonService;
import com.grsu.teacherassistant.service.api.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final StudentService studentService;
    private final LessonService lessonService;

    @GetMapping
    String registration(Model model, @ModelAttribute("lessonId") Integer lessonId) {
        List<StudentDto> absentStudents = studentService.getAbsentStudents(lessonId);
        List<StudentDto> presentStudents = studentService.getPresentStudents(lessonId);
        LessonWithSchedule lesson = lessonService.getById(lessonId);
        model.addAttribute("lessonNo", String.format("[%d]", lesson.getSchedule().getNumber()));
        model.addAttribute("lesson", lesson);

        StudentWithAttendanceDto selectedStudent;
        if(!absentStudents.isEmpty()) {
            selectedStudent = studentService.getStudentAttendance(absentStudents.get(0).getId(), lessonId);
        } else {
            selectedStudent = studentService.getStudentAttendance(presentStudents.get(0).getId(), lessonId);
        }
        model.addAttribute("selectedStudent", selectedStudent);
        model.addAttribute("totalSkips", studentService.getTotalSkipsInfo(selectedStudent.getTotalSkips()));
        model.addAttribute("lectureSkips", studentService.getLectureSkipsInfo(selectedStudent.getTotalSkips()));
        model.addAttribute("practicalSkips", studentService.getPracticalSkipsInfo(selectedStudent.getTotalSkips()));
        return "registration";
    }

    @GetMapping("/student")
    String getStudentInfo(Model model,
                          @ModelAttribute("studentId") Integer studentId,
                          @ModelAttribute("lessonId") Integer lessonId) {
        StudentWithAttendanceDto selectedStudent = studentService.getStudentAttendance(studentId, lessonId);
        model.addAttribute("selectedStudent", selectedStudent);
        model.addAttribute("totalSkips", studentService.getTotalSkipsInfo(selectedStudent.getTotalSkips()));
        model.addAttribute("lectureSkips", studentService.getLectureSkipsInfo(selectedStudent.getTotalSkips()));
        model.addAttribute("practicalSkips", studentService.getPracticalSkipsInfo(selectedStudent.getTotalSkips()));
        return "fragments/studentInfo";
    }
}
