package com.grsu.teacherassistant.service.impl;

import com.grsu.teacherassistant.dto.FiltersDto;
import com.grsu.teacherassistant.service.api.PageRequestService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class PageRequestServiceImpl<T> implements PageRequestService<T> {

    public Model buildDefaultPageModel(Model model, String sortDirection, String sortField, Page<T> page) {
        fillDefaultPageModel(model, sortDirection, sortField, page);
        return model;
    }

    public Model buildDefaultPageModel(Model model, String sortDirection, String sortField, Page<T> page, FiltersDto filtersDto) {
        fillDefaultPageModel(model, sortDirection, sortField, page);
        model.addAttribute("filtersDto", filtersDto);
        return model;
    }

    private void fillDefaultPageModel(Model model, String sortDirection, String sortField, Page<T> page) {
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("currentPage", page.getPageable().getPageNumber());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("reverseSortDir", "ASC".equals(sortDirection) ? "DESC" : "ASC");
    }
}
