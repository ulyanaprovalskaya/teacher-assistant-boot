package com.grsu.teacherassistant.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class StudentDto {

    private Integer id;
    private String cardUid;
    private Integer cardId;
    private String recordBook;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String phone;
    private String email;
    private List<NoteDto> notes;
}
