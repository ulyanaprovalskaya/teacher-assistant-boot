package com.grsu.teacherassistant.model.entity;

import com.grsu.teacherassistant.model.converter.LocalDateTimeAttributeConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Lesson extends AssistantEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "checked")
    private Boolean checked;

    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @Column(name = "create_date", insertable = false, updatable = false)
    private LocalDateTime createDate;

    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @Column(name = "date")
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "discipline_id", referencedColumnName = "id")
    private Discipline discipline;

    @ManyToOne
    @JoinColumn(name = "stream_id", referencedColumnName = "id")
    private Stream stream;

    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    private LessonType type;

    @Column(name = "index_number")
    private Integer index;

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private StudentGroup group;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "entity_id", referencedColumnName = "id")
    @Where(clause = "type = 'LESSON'")
    @ToString.Exclude
    private List<Note> notes;

    @ManyToOne
    @JoinColumn(name = "schedule_id", referencedColumnName = "id")
    private Schedule schedule;

    @MapKey(name = "studentId")
    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private Map<Integer, StudentLesson> studentLessons;

    @Transient
    private Lesson lastLectureLesson;
    @Transient
    private Lesson lastPracticeLesson;
}
