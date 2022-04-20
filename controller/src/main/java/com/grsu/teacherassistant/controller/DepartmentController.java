package com.grsu.teacherassistant.controller;


import com.grsu.teacherassistant.dto.DepartmentDto;
import com.grsu.teacherassistant.dto.DisciplineDto;
import com.grsu.teacherassistant.service.api.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;


    @PostMapping
    public String createDepartment(@ModelAttribute("department") DepartmentDto departmentDto) {
        departmentService.createOrUpdateDepartment(departmentDto);
        return "redirect:/departments";
    }

    @DeleteMapping("/{id}")
    public String deleteDepartment(@PathVariable Integer id){
        departmentService.deleteDepartment(id);
        return "redirect:/departments";
    }

    @GetMapping
    public String showDepartments(Model model){
        model.addAttribute("departments", departmentService.getAll());
        model.addAttribute("department", new DepartmentDto());
        return "departments";
    }

}
