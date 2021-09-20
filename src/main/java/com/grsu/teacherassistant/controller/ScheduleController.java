package com.grsu.teacherassistant.controller;

import com.grsu.teacherassistant.dto.ScheduleFilterDto;
import com.grsu.teacherassistant.model.entity.Lesson;
import com.grsu.teacherassistant.service.api.DisciplineService;
import com.grsu.teacherassistant.service.api.GroupService;
import com.grsu.teacherassistant.service.api.LessonService;
import com.grsu.teacherassistant.service.api.LessonTypeService;
import com.grsu.teacherassistant.service.api.ScheduleService;
import com.grsu.teacherassistant.service.api.StreamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final LessonService lessonService;
    private final StreamService streamService;
    private final GroupService groupService;
    private final DisciplineService disciplineService;
    private final LessonTypeService lessonTypeService;
    private final ScheduleService scheduleService;

    @GetMapping
    private String showSchedule(Model model, @ModelAttribute("scheduleFilter")ScheduleFilterDto scheduleFilterDto){
        LocalDate dateFrom = scheduleFilterDto.getDateFrom();
        LocalDate dateTo = scheduleFilterDto.getDateTo();

        if(dateFrom == null){
            dateFrom = LocalDate.now();
        }
        if(dateTo == null){
            dateTo = dateFrom.plusDays(7);
        }

        scheduleFilterDto.setDateFrom(dateFrom);
        scheduleFilterDto.setDateTo(dateTo);

        List<Lesson> lessons = lessonService.getAllByDateBetween(scheduleFilterDto.getDateFrom(), scheduleFilterDto.getDateTo());
        model.addAttribute("filteredLessons", lessons);
        model.addAttribute("streams", streamService.getAll());
        model.addAttribute("groups", groupService.getAll());
        model.addAttribute("disciplines", disciplineService.getAll());
        model.addAttribute("lessonTypes", lessonTypeService.getAll());
        model.addAttribute("lessonsTime", scheduleService.getAll());
        return "schedule";
    }
}
