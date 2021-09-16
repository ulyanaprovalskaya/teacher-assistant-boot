package com.grsu.teacherassitant.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ScheduleFilterDto {

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate dateFrom;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate dateTo;
}
