package com.grsu.teacherassistant.controller;

import com.grsu.teacherassistant.dto.StreamDto;
import com.grsu.teacherassistant.dto.StudentGroupDto;
import com.grsu.teacherassistant.service.api.DepartmentService;
import com.grsu.teacherassistant.service.api.DisciplineService;
import com.grsu.teacherassistant.service.api.GroupService;
import com.grsu.teacherassistant.service.api.StreamService;
import com.grsu.teacherassistant.service.api.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collections;

@Controller
@RequestMapping("/groups")
@RequiredArgsConstructor
public class GroupController {

    private final StreamService streamService;
    private final DisciplineService disciplineService;
    private final GroupService groupService;
    private final DepartmentService departmentService;
    private final StudentService studentService;

    @GetMapping("/parameters")
    public String getParameters(Model model) {
        model.addAttribute("group", new StudentGroupDto());
        model.addAttribute("allStudents", studentService.getAll());
        model.addAttribute("students", new ArrayList<>());
        model.addAttribute("departments", departmentService.getAll());
        return "dialogs/groupInfo";
    }

    @GetMapping
    public String getGroups(Model model) {
        model.addAttribute("stream", new StreamDto());
        model.addAttribute("streams", streamService.getAll());
        model.addAttribute("disciplines", disciplineService.getAll());
        model.addAttribute("departments", departmentService.getAll());
        model.addAttribute("groups", groupService.getAll());
        return "groups";
    }

    @PostMapping
    public String createGroup(@ModelAttribute("group") StudentGroupDto studentGroupDto) {
        groupService.createGroup(studentGroupDto);
        return "redirect:/groups";
    }

    //todo: разобраться с выводом expirationDate
    @GetMapping("/{id}")
    public String getGroup(Model model, @PathVariable Integer id){
        StudentGroupDto studentGroupDto = groupService.getById(id);
        model.addAttribute("allStudents", studentService.getAll());
        model.addAttribute("groupStudents", studentGroupDto.getStudents());
        model.addAttribute("departments", Collections.singleton(studentGroupDto.getDepartment()));
        model.addAttribute("group", studentGroupDto);
        return "dialogs/groupInfo";
    }

    @DeleteMapping("/{id}")
    public String deleteGroup(@PathVariable Integer id){
        groupService.deleteGroup(id);
        return "redirect:/groups";
    }


}
