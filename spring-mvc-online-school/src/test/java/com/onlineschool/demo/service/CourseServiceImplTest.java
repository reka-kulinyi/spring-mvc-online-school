package com.onlineschool.demo.service;

import com.onlineschool.demo.course.SchoolCourse;
import com.onlineschool.demo.dao.CourseDao;
import com.onlineschool.demo.dao.SubjectDao;
import com.onlineschool.demo.entity.Course;
import com.onlineschool.demo.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CourseServiceImplTest {

    @Mock
    private CourseDao courseDao;

    @Mock
    private SubjectDao subjectDao;

    @InjectMocks
    CourseService courseService;

    @Test
    void getCoursesByInstructor() {
        // given
        User instructor = new User();
        List<Course> courses = new ArrayList<>();
        courses.add(new Course());
        when(courseDao.getCoursesByInstructor(instructor)).thenReturn(courses);

        // when
        List<Course> returnedCourses = courseService.getCoursesByInstructor(instructor);

        // then
        then(courseDao).should().getCoursesByInstructor(instructor);
        assertThat(returnedCourses).hasSize(1);
    }

    @Test
    void deleteCourseById() {
        // given
        long id = 1L;

        // when
        courseService.deleteCourseById(id);

        // then
        then(courseDao).should().deleteCourseById(1L);
    }

    @Test
    void saveCourse() {
        // given
        BigDecimal price = new BigDecimal(4);
        String subjectName = "abcd";
        SchoolCourse schoolCourse = new SchoolCourse();
        schoolCourse.setPrice(price);
        schoolCourse.setSubjectName(subjectName);

        // when
        courseService.saveCourse(schoolCourse, new User());

        // then
        then(subjectDao).should().getSubjectByName("abcd");
        then(courseDao).should().save(any(Course.class));
    }
}