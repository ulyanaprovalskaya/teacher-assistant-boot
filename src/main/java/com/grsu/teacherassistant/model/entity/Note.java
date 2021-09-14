package com.grsu.teacherassistant.model.entity;

import com.grsu.teacherassistant.model.converter.LocalDateTimeAttributeConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Note extends AssistantEntity {

    @Column(name = "type")
    private String type;

    @Column(name = "description")
    private String description;

    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "entity_id")
    private Integer entityId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Note note = (Note) o;
        return Objects.equals(createDate, note.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), createDate);
    }
}
