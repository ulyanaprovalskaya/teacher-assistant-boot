package com.grsu.teacherassistant.controller;

import com.grsu.teacherassistant.dto.DisciplineDto;
import com.grsu.teacherassistant.dto.StreamDto;
import com.grsu.teacherassistant.service.api.DepartmentService;
import com.grsu.teacherassistant.service.api.DisciplineService;
import com.grsu.teacherassistant.service.api.GroupService;
import com.grsu.teacherassistant.service.api.StreamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;

@Controller
@RequestMapping("/streams")
@RequiredArgsConstructor
public class StreamController {

    private final StreamService streamService;
    private final DisciplineService disciplineService;
    private final GroupService groupService;
    private final DepartmentService departmentService;

    @GetMapping("/parameters")
    public String getParameters(Model model) {
        model.addAttribute("disciplines", disciplineService.getAll());
        model.addAttribute("groups", groupService.getAll());
        model.addAttribute("stream", new StreamDto());
        model.addAttribute("departments", departmentService.getAll());
        return "dialogs/streamInfo";
    }

    @GetMapping
    public String getStreams(Model model) {
        model.addAttribute("stream", new StreamDto());
        model.addAttribute("streams", streamService.getAll());
        model.addAttribute("disciplines", disciplineService.getAll());
        model.addAttribute("departments", departmentService.getAll());
        model.addAttribute("groups", groupService.getAll());
        return "streams";
    }

    @PostMapping
    public String createStream(@ModelAttribute("stream") StreamDto streamDto) {
        streamService.createStream(streamDto);
        return "redirect:/streams";
    }

    @GetMapping("/{id}")
    public String getStream(Model model, @PathVariable Integer id){
        StreamDto stream = streamService.getByStreamId(id);
        model.addAttribute("groups", stream.getGroups());
        model.addAttribute("disciplines", Collections.singleton(stream.getDiscipline()));
        model.addAttribute("departments", Collections.singleton(stream.getDepartment()));
        model.addAttribute("stream", stream);
        return "dialogs/streamInfo";
    }

    @DeleteMapping("/{id}")
    public String deleteStream(@PathVariable Integer id){
        streamService.deleteStream(id);
        return "redirect:/streams";
    }


}
