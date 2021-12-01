package com.grsu.teacherassistant.controller;

import com.grsu.teacherassistant.dto.DisciplineDto;
import com.grsu.teacherassistant.dto.LessonDto;
import com.grsu.teacherassistant.dto.ScheduleFilterDto;
import com.grsu.teacherassistant.paging.Paged;
import com.grsu.teacherassistant.service.api.DisciplineService;
import com.grsu.teacherassistant.service.api.GroupService;
import com.grsu.teacherassistant.service.api.LessonService;
import com.grsu.teacherassistant.service.api.LessonTypeService;
import com.grsu.teacherassistant.service.api.ScheduleService;
import com.grsu.teacherassistant.service.api.StreamService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    private String showSchedule(Model model, @PageableDefault(size = 5) Pageable pageable){
        ScheduleFilterDto scheduleFilterDto = new ScheduleFilterDto();
        scheduleFilterDto.setDateFrom(LocalDate.now());
        scheduleFilterDto.setDateTo(scheduleFilterDto.getDateFrom().plusDays(7));

        Paged<LessonDto> lessons = lessonService.getAllByDateBetween(scheduleFilterDto.getDateFrom(), scheduleFilterDto.getDateTo(), pageable);

        model.addAttribute("scheduleFilter", scheduleFilterDto);
        model.addAttribute("lessons", lessons);
        return "schedule";
    }

    @PostMapping
    private String filterLessons(Model model, @ModelAttribute("scheduleFilter") ScheduleFilterDto scheduleFilterDto, @PageableDefault(size = 3) Pageable pageable){
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

        Paged<LessonDto> lessons = lessonService.getAllByDateBetween(scheduleFilterDto.getDateFrom(), scheduleFilterDto.getDateTo(), pageable);
        model.addAttribute("lessons", lessons);
        model.addAttribute("streams", streamService.getAll());
        model.addAttribute("groups", groupService.getAll());
        model.addAttribute("disciplines", disciplineService.getAll());
        model.addAttribute("lessonTypes", lessonTypeService.getAll());
        model.addAttribute("lessonsTime", scheduleService.getAll());
        model.addAttribute("discipline", new DisciplineDto());
        model.addAttribute("pageable", pageable);
        return "schedule";
    }

    @GetMapping("/streams/{id}")
    private String getGroupsByStreamId(Model model, @PathVariable Integer id) {
        model.addAttribute("groups", groupService.getGroupsByStreamId(id));
        return "fragments/filterSelectGroup";
    }
}
