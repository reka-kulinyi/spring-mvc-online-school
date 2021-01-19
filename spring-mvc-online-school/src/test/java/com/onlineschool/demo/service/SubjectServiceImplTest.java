package com.onlineschool.demo.service;

import com.onlineschool.demo.dao.SubjectDao;
import com.onlineschool.demo.entity.Course;
import com.onlineschool.demo.entity.Subject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class SubjectServiceImplTest {

    @Mock
    private SubjectDao subjectDao;

    @InjectMocks
    SubjectService subjectService;

    @Test
    void getAllSubjects() {
        // given
        List<Subject> subjects = new ArrayList<>();
        subjects.add(new Subject());
        subjects.add(new Subject());
        when(subjectDao.getAllSubjects()).thenReturn(subjects);

        // when
        List<Subject> returnedSubjects = subjectService.getAllSubjects()

        // then
        then(subjectDao).should().getAllSubjects();
        assertThat(returnedSubjects).hasSize(1);
    }
}