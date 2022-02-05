package com.grsu.teacherassistant.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class FiltersDto {

    private List<FilterDto> filters = new ArrayList<>();
}
