package com.grsu.teacherassistant.model.converter;


import com.grsu.teacherassistant.model.entity.LessonType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LessonTypeAttributeConverter implements AttributeConverter<LessonType, Integer> {

	@Override
	public Integer convertToDatabaseColumn(LessonType lessonType) {
		return null;//lessonType.getCode();
	}

	@Override
	public LessonType convertToEntityAttribute(Integer code) {
		return null;//LessonType.getLessonTypeByCode(code);
	}
}
