package com.grsu.teacherassistant.dto.student;

import com.grsu.teacherassistant.dto.NoteDto;
import com.grsu.teacherassistant.dto.StudentGroupDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;
import static com.grsu.teacherassistant.constants.Constants.GROUPS_DELIMITER;

@Getter
@Setter
@NoArgsConstructor
@ToString
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

    public String getFullName() {
        if (lastName == null) {
            return firstName;
        }
        if (firstName == null) {
            return lastName;
        }
        return String.join(" ", lastName, firstName);
    }
}
