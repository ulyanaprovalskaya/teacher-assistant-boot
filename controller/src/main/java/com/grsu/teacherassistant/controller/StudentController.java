package com.grsu.teacherassistant.controller;

import com.grsu.teacherassistant.dto.FilterDto;
import com.grsu.teacherassistant.dto.FiltersDto;
import com.grsu.teacherassistant.dto.StreamDto;
import com.grsu.teacherassistant.dto.StudentDto;
import com.grsu.teacherassistant.model.entity.Student;
import com.grsu.teacherassistant.service.api.GroupService;
import com.grsu.teacherassistant.service.api.PageRequestService;
import com.grsu.teacherassistant.service.api.StudentService;
import com.grsu.teacherassistant.service.mapper.StudentMapper;
import com.grsu.teacherassistant.utils.FilterSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Collections;

@Controller
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final StudentMapper studentMapper;
    private final PageRequestService<Student> pageRequestService;
    private final GroupService groupService;

    @GetMapping
    public String showStudents(Model model,
                               @ModelAttribute FiltersDto filtersDto,
                               @PageableDefault Pageable pageable,
                               @RequestParam(value = "isArchived", defaultValue = "false") boolean isArchived,
                               @RequestParam(value = "sortDirection", defaultValue = "DESC") String sortDirection,
                               @RequestParam(value = "sortField", defaultValue = "lastName") String sortField,
                               @RequestParam(value = "search", required = false) String search) {
        Page<Student> allStudents = studentService
                .getAllStudents(pageable, sortDirection, sortField, isArchived, search);
        filtersDto.setFilters(Arrays.asList(new FilterDto(), new FilterDto(), new FilterDto()));
        pageRequestService.buildDefaultPageModel(model, sortDirection, sortField, allStudents)
                .addAttribute("students", studentMapper.toStudents(allStudents))
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
        StudentDto studentDto = studentService.getById(id);
        model.addAttribute("groups", studentDto.getGroups());
//        model.addAttribute("disciplines", Collections.singleton(stream.getDiscipline()));
//        model.addAttribute("departments", Collections.singleton(stream.getDepartment()));
        model.addAttribute("student", studentDto);
        return "dialogs/studentInfo";
    }

}
