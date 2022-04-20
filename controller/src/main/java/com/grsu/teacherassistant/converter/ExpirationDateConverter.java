package com.grsu.teacherassistant.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class ExpirationDateConverter implements Converter<String, LocalDateTime> {

    @Override
    public LocalDateTime convert(String expirationDate) {
        return LocalDateTime.parse(expirationDate, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
}
