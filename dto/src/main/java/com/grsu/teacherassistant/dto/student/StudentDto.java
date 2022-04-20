package com.grsu.teacherassistant.dto.student;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
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
}
