package com.grsu.teacherassistant.converter;

import com.grsu.teacherassistant.dto.StreamDto;
import com.grsu.teacherassistant.service.api.StreamService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StreamConverter implements Converter<String, StreamDto> {

    private final StreamService streamService;

    @Override
    public StreamDto convert(String streamId) {
        return streamService.getAll().stream().findFirst().orElse(null);
    }
}
