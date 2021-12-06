package com.grsu.teacherassistant.controller;

import com.grsu.teacherassistant.model.entity.Student;
import com.grsu.teacherassistant.service.api.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public String showStudents(Model model,
                               @PageableDefault Pageable pageable,
                               @RequestParam(value = "sortDirection") String sortDirection,
                               @RequestParam(value = "sortField") String sortField) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),  Sort.Direction.fromString(sortDirection), sortField);
        Page<Student> allStudents = studentService.getAllStudents(pageRequest, null);
        model.addAttribute("students", studentService.findAllStudents(allStudents));
        model.addAttribute("totalPages", allStudents.getTotalPages());
        model.addAttribute("totalItems", allStudents.getTotalElements());
        model.addAttribute("currentPage", pageable.getPageNumber());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("reverseSortDir", "ASC".equals(sortDirection) ? "DESC" : "ASC");
        return "students";
    }

}
