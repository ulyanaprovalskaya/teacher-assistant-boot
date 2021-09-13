package com.grsu.teacherassistant.model.entity;

import com.grsu.teacherassistant.model.converter.LocalDateTimeAttributeConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "SCHEDULE_VERSION")
public class ScheduleVersion extends AssistantEntity {

    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @Column(name = "end_date")
    private LocalDateTime endDate;

    @OneToMany(mappedBy = "version", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Schedule> schedules;
}
