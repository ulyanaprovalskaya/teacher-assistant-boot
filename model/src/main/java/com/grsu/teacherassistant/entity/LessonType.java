package com.grsu.teacherassistant.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "LESSON_TYPE")
public class LessonType extends AssistantEntity{

	@Enumerated(EnumType.STRING)
	private LessonTypeEnum type;

	private String name;

	public enum LessonTypeEnum {
		LECTURE,
		PRACTICAL,
		LAB,
		ATTESTATION,
		EXAM
	}
}
