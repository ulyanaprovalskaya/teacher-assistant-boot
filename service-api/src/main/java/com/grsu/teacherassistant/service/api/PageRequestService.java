package com.grsu.teacherassistant.service.api;

import org.springframework.data.domain.Page;
import org.springframework.ui.Model;

public interface PageRequestService<T> {

    Model buildModel(Model model, String sortDirection, String sortField, Page<T> page);

}
