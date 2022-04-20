package com.grsu.teacherassistant.converter;

import com.grsu.teacherassistant.dto.StudentGroupDto;
import com.grsu.teacherassistant.service.api.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GroupConverter implements Converter<String, List<StudentGroupDto>> {

    private final GroupService groupService;

    @Override
    public List<StudentGroupDto> convert(String groupId) {
        return Collections.singletonList(groupService.getById(Integer.valueOf(groupId)));
    }

}
