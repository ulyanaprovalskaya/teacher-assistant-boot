package com.grsu.teacherassistant.dto.lesson;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
public class ScheduleDto {

    private Integer id;
    private LocalTime begin;
    private LocalTime end;
    private Integer number;
}
