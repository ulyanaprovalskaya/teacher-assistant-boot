package com.grsu.teacherassistant.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.grsu.teacherassistant.model.constants.Constants.GROUPS_DELIMITER;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Student extends AssistantEntity {

    @Column(name = "card_uid")
    private String cardUid;

    @Column(name = "card_id")
    private Integer cardId;

    @Column(name = "record_book")
    private String recordBook;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "STUDENT_GROUP",
        joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id"))
    @ToString.Exclude
    private List<StudentGroup> groups;

    @MapKey(name = "lessonId")
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private Map<Integer, StudentLesson> studentLessons;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "entity_id", referencedColumnName = "id")
    @Where(clause = "type = 'STUDENT'")
    @ToString.Exclude
    private List<Note> notes;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    @ToString.Exclude
    private List<StudentNotification> notifications;

    @OneToMany(mappedBy = "praepostor", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<StudentGroup> praepostorGroups;

    public String getFullName() {
        if (lastName == null) {
            return firstName;
        }
        if (firstName == null) {
            return lastName;
        }
        return String.join(" ", lastName, firstName);
    }

    public String getGroupNames() {
        if (groups != null) {
            return groups.stream().map(StudentGroup::getName).collect(Collectors.joining(GROUPS_DELIMITER));
        } else {
            return "";
        }
    }

    public void setCardUidFromCardId(int cardId) {
        cardUid = Integer.toHexString(cardId).toUpperCase();
    }

    //http://stackoverflow.com/a/7038867/7464024
    public void setCardIdFromCardUid(String cardUid) {
        try {
            cardId = (int) Long.parseLong(cardUid, 16);
        } catch (NumberFormatException ex) {
            this.cardId = 0;
        }
    }

    /*public void setCardUid(String cardUid) {
        if (isEmpty(cardUid)) {
            this.cardUid = null;
        } else {
            this.cardUid = cardUid.toUpperCase();
            setCardIdFromCardUid(this.cardUid);
        }
    }*/

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
        if (this.cardId != 0) {
            setCardUidFromCardId(this.cardId);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return Objects.equals(cardId, student.cardId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cardId);
    }
}
