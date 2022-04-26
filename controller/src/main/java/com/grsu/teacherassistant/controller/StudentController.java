package com.grsu.teacherassistant.controller;

import com.grsu.teacherassistant.dto.FilterDto;
import com.grsu.teacherassistant.dto.FiltersDto;
import com.grsu.teacherassistant.dto.student.StudentDto;
import com.grsu.teacherassistant.dto.student.StudentWithAttendanceDto;
import com.grsu.teacherassistant.service.api.GroupService;
import com.grsu.teacherassistant.service.api.PageRequestService;
import com.grsu.teacherassistant.service.api.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;

@Controller
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final PageRequestService<StudentDto> pageRequestService;
    private final GroupService groupService;

    @GetMapping
    public String showStudents(Model model,
                               @ModelAttribute FiltersDto filtersDto,
                               @PageableDefault Pageable pageable,
                               @RequestParam(value = "isArchived", defaultValue = "false") boolean isArchived,
                               @RequestParam(value = "sortDirection", defaultValue = "DESC") String sortDirection,
                               @RequestParam(value = "sortField", defaultValue = "lastName") String sortField,
                               @RequestParam(value = "search", required = false) String search) {
        Page<StudentDto> allStudents = studentService
                .getAllStudents(pageable, sortDirection, sortField, isArchived, search);
        filtersDto.setFilters(Arrays.asList(new FilterDto(), new FilterDto(), new FilterDto()));
        pageRequestService.buildDefaultPageModel(model, sortDirection, sortField, allStudents)
                .addAttribute("students", allStudents)
                .addAttribute("search", search)
                .addAttribute("isArchived", isArchived);
        return "students";
    }


    @PostMapping
    public String createStream(@ModelAttribute("student") StudentDto studentDto) {
        studentService.createStudent(studentDto);
        return "redirect:/students";
    }

    @GetMapping("/parameters")
    public String getParameters(Model model) {
        model.addAttribute("student", new StudentDto());
        model.addAttribute("groups", groupService.getAll());
        return "dialogs/studentInfo";
    }

    @GetMapping("/{id}")
    public String getStudent(Model model, @PathVariable Integer id){
        StudentWithAttendanceDto studentDto = studentService.getById(id);
        model.addAttribute("groups", studentDto.getGroups());
//        model.addAttribute("disciplines", Collections.singleton(stream.getDiscipline()));
//        model.addAttribute("departments", Collections.singleton(stream.getDepartment()));
        model.addAttribute("student", studentDto);
        return "dialogs/studentInfo";
    }

}
