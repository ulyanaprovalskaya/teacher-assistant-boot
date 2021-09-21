package com.grsu.teacherassistant.controller;

import com.grsu.teacherassistant.service.api.DisciplineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/disciplines")
@RequiredArgsConstructor
public class DisciplineController {

    private final DisciplineService disciplineService;

    @GetMapping
    public String showDisciplines(Model model){
        model.addAttribute("disciplines", disciplineService.getAll());
        return "disciplines";
    }

    @PostMapping
    public String addDiscipline(Model model){
        return "schedule";
    }

    @DeleteMapping("/{id}")
    public String deleteDiscipline(@PathVariable Integer id){
        disciplineService.deleteDiscipline(id);
        return "disciplines :: disciplinesFragment";
    }
}
