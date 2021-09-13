package com.grsu.teacherassistant.model.entity;

import com.grsu.teacherassistant.model.converter.LocalTimeAttributeConverter;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Schedule extends AssistantEntity {

    @Convert(converter = LocalTimeAttributeConverter.class)
    @Column(name = "begin")
    private LocalTime begin;

    @Convert(converter = LocalTimeAttributeConverter.class)
    @Column(name = "end")
    private LocalTime end;

    @Column(name = "number")
    private Integer number;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Lesson> lessons;

    @ManyToOne
    @JoinColumn(name = "version_id", referencedColumnName = "id")
    private ScheduleVersion version;

    public String getCaption() {
        return String.format("[%s] %s - %s", number, begin, end);
    }

    public String getTime() {
        return String.format("%s - %s", begin, end);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Schedule schedule = (Schedule) o;
        return Objects.equals(number, schedule.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), number);
    }
}
