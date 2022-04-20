package com.grsu.teacherassistant.converter;

import com.grsu.teacherassistant.util.DateUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Converter(autoApply = true)
public class LocalTimeAttributeConverter implements AttributeConverter<LocalTime, String> {

    @Override
    public String convertToDatabaseColumn(LocalTime localTime) {
        if (localTime == null) {
            return null;
        }
        return localTime.format(DateTimeFormatter.ISO_TIME);
    }

    @Override
    public LocalTime convertToEntityAttribute(String s) {
        if (s == null) {
            return null;
        }
        try {
            return LocalTime.parse(s);
        } catch (DateTimeParseException ex) {
            ex.printStackTrace();
        }
        try {
            return LocalTime.parse(s, DateUtils.TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            return LocalTime.parse(s, DateUtils.DATE_TIME_FORMATTER_EN);

        }
    }
}
