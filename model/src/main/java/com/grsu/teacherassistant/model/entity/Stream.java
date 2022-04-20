package com.grsu.teacherassistant.model.entity;

import com.grsu.teacherassistant.model.converter.LocalDateTimeAttributeConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.grsu.teacherassistant.model.constants.Constants.GROUPS_DELIMITER;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Stream extends AssistantEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @Column(name = "create_date", insertable = false, updatable = false)
    private LocalDateTime createDate;

    @Column(name = "course")
    private Integer course;

    @Column(name = "active")
    private boolean active;

    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "STREAM_GROUP",
        joinColumns = @JoinColumn(name = "stream_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id"))
    @ToString.Exclude
    private List<StudentGroup> groups;

    @OneToMany(mappedBy = "stream", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Lesson> lessons;

    @ManyToOne
    @JoinColumn(name = "discipline_id", referencedColumnName = "id")
    private Discipline discipline;

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;

    @Column(name = "lecture_count")
    private Integer lectureCount;

    @Column(name = "practical_count")
    private Integer practicalCount;

    @Column(name = "lab_count")
    private Integer labCount;

    public String getGroupNames() {
        return groups.stream().map(StudentGroup::getName).collect(Collectors.joining(GROUPS_DELIMITER));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Stream stream = (Stream) o;
        return Objects.equals(name, stream.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }
}
