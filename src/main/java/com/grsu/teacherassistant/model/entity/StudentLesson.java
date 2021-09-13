package com.grsu.teacherassistant.model.entity;

import com.grsu.teacherassistant.model.converter.LocalTimeAttributeConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "STUDENT_LESSON")
public class StudentLesson extends AssistantEntity {

    @Column(name = "registered")
    private Boolean registered;

    @Convert(converter = LocalTimeAttributeConverter.class)
    @Column(name = "registration_time")
    private LocalTime registrationTime;

    @Column(name = "registration_type")
    private String registrationType;

    @Column(name = "mark")
    private String mark;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "lesson_id", referencedColumnName = "id")
    private Lesson lesson;

    @Column(name = "student_id", insertable = false, updatable = false)
    private Integer studentId;

    @Column(name = "lesson_id", insertable = false, updatable = false)
    private Integer lessonId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "entity_id", referencedColumnName = "id")
    @Where(clause = "type = 'STUDENT_LESSON'")
    @ToString.Exclude
    private List<Note> notes;

    public List<Note> getNotes() {
        return notes;
    }

    public boolean isRegistered() {
        return Boolean.TRUE.equals(registered);
    }
}
