package com.grsu.teacherassistant.service.api;

import com.grsu.teacherassistant.dto.FiltersDto;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;

import java.util.List;

public interface PageRequestService<T> {

    Model buildDefaultPageModel(Model model, String sortDirection, String sortField, Page<T> page);

    Model buildDefaultPageModel(Model model, String sortDirection, String sortField, Page<T> page, FiltersDto filtersDto);

}
