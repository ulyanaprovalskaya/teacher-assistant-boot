package com.grsu.teacherassistant.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FilterDto {
    private Comparison comparison;
    private String filterParameter;
    private String valueOfFilterParameter;


    public FilterDto(Comparison comparison, String filterParameter, String valueOfFilterParameter) {
        this.comparison = comparison;
        this.filterParameter = filterParameter;
        this.valueOfFilterParameter = valueOfFilterParameter;
    }
}


