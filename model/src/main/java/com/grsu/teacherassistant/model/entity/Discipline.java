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
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Discipline extends AssistantEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @Column(name = "create_date", insertable = false, updatable = false)
    private LocalDateTime createDate;

    @Column(name = "active")
    private Boolean active;

    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    @OneToMany(mappedBy = "discipline", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Lesson> lessons;

    @OneToMany(mappedBy = "discipline", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Stream> streams;

}
