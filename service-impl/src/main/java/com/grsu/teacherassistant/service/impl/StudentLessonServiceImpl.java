package com.grsu.teacherassistant.service.impl;

import com.grsu.teacherassistant.entity.StudentLesson;
import com.grsu.teacherassistant.exception.EntityNotFoundException;
import com.grsu.teacherassistant.exception.RedundantStudentLessonRegistrationStatus;
import com.grsu.teacherassistant.repository.StudentLessonRepository;
import com.grsu.teacherassistant.service.api.StudentLessonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentLessonServiceImpl implements StudentLessonService {

    private final StudentLessonRepository studentLessonRepository;

    @Override
    @Transactional
    public void updateRegistered(Integer studentId, Integer lessonId, Integer registered) {
        StudentLesson studentLesson = studentLessonRepository
                .findByStudentIdAndAndLessonId(studentId, lessonId)
                .orElseThrow(() -> new EntityNotFoundException(String.format(
                        "StudentLesson wasn't found by studentId=%d and lessonId=%d",
                        studentId,
                        lessonId)));

        if (studentLesson.getRegistered() == null || !studentLesson.getRegistered().equals(registered)) {
            studentLesson.setRegistered(registered);
            if (registered.equals(1)) {
                studentLesson.setRegistrationTime(LocalTime.now());
            }
            studentLessonRepository.save(studentLesson);
        } else {
            throw new RedundantStudentLessonRegistrationStatus(String.format(
                    "StudentLesson (id=%d) registered status is already %d",
                    studentLesson.getId(),
                    registered));
        }

    }
}
