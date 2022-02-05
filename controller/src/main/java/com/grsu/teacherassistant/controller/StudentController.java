package com.grsu.teacherassistant.controller;

import com.grsu.teacherassistant.dto.Comparison;
import com.grsu.teacherassistant.dto.FilterDto;
import com.grsu.teacherassistant.dto.FiltersDto;
import com.grsu.teacherassistant.model.entity.Student;
import com.grsu.teacherassistant.service.api.PageRequestService;
import com.grsu.teacherassistant.service.api.StudentService;
import com.grsu.teacherassistant.utils.FilterSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    private final PageRequestService<Student> pageRequestService;

    @GetMapping
    public String showStudents(Model model,
                               @ModelAttribute FiltersDto filtersDto,
                               @PageableDefault Pageable pageable,
                               @RequestParam(value = "isArchived", defaultValue = "false") boolean isArchived,
                               @RequestParam(value = "sortDirection", defaultValue = "DESC") String sortDirection,
                               @RequestParam(value = "sortField", defaultValue = "lastName") String sortField) {
        Page<Student> allStudents = studentService
                .getAllStudents(pageable, null, sortDirection, sortField, isArchived);
        filtersDto.setFilters(Arrays.asList(new FilterDto(), new FilterDto(), new FilterDto()));
        pageRequestService.buildModel(model, sortDirection, sortField, allStudents)
                .addAttribute("students", studentService.findAllStudents(allStudents))
                .addAttribute("isArchived", isArchived)
                .addAttribute("filtersDto", filtersDto);
        return "students";
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public String filterStudents(Model model,
                               @RequestBody FiltersDto filtersDto,
                               @PageableDefault Pageable pageable,
                               @RequestParam(required = false, value = "isArchived", defaultValue = "false") boolean isArchived,
                               @RequestParam(required = false, value = "sortDirection", defaultValue = "DESC") String sortDirection,
                               @RequestParam(required = false, value = "sortField", defaultValue = "lastName") String sortField) {
        Page<Student> allStudents = studentService
                .getAllStudents(pageable, new FilterSpecification<>(filtersDto.getFilters()), sortDirection, sortField, isArchived);
        pageRequestService.buildModel(model, sortDirection, sortField, allStudents)
                .addAttribute("students", studentService.findAllStudents(allStudents))
                .addAttribute("isArchived", isArchived)
                .addAttribute("filtersDto", filtersDto);
        return "students";
    }

}
