package com.grsu.teacherassistant.controller;

import com.grsu.teacherassistant.dto.DisciplineDto;
import com.grsu.teacherassistant.service.api.DisciplineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/disciplines")
@RequiredArgsConstructor
public class DisciplineController {

    private final DisciplineService disciplineService;

    @GetMapping
    public String showDisciplines(Model model){
        model.addAttribute("disciplines", disciplineService.getAll());
        model.addAttribute("discipline", new DisciplineDto());
        return "disciplines";
    }

    @PostMapping
    public String addOrUpdateDiscipline(HttpServletRequest request, @ModelAttribute("discipline") DisciplineDto disciplineDto){ ;
        if(disciplineDto.getId() == null){
            disciplineService.createDiscipline(disciplineDto);
        }
        else{
            disciplineService.updateDiscipline(disciplineDto);
        }
        return "redirect:" + request.getHeader("Referer");
    }

    @GetMapping("/{id}")
    public String getDisciplineById(Model model, @PathVariable Integer id){
        model.addAttribute("discipline", disciplineService.getById(id));
        return "redirect:/disciplines";
    }

    @DeleteMapping("/{id}")
    public String deleteDiscipline(@PathVariable Integer id){
        disciplineService.deleteDiscipline(id);
        return "redirect:/disciplines";
    }
}
