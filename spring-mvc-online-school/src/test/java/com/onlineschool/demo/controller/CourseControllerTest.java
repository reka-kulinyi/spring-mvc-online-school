package com.onlineschool.demo.controller;

import com.onlineschool.demo.course.SchoolCourse;
import com.onlineschool.demo.entity.User;
import com.onlineschool.demo.service.CourseService;
import com.onlineschool.demo.service.SubjectService;
import com.onlineschool.demo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class CourseControllerTest {

    @Mock
    private CourseService courseService;

    @Mock
    private UserService userService;

    @Mock
    private SubjectService subjectService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private CourseController courseController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(courseController).build();
    }

    @Test
    void testControllerShowNewCourseForm() throws Exception {

        mockMvc.perform(get("/courses/addNewCourse"))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("courseInstructor"))
        .andExpect(model().attributeExists("subjects"))
        .andExpect(model().attributeExists("schoolCourse"))
        .andExpect(view().name("course-form"));
    }

    @Test
    void testShowCourseForm() {
        // given
        String view = courseController.showNewCourseForm(1l, model);

        // then
        then(userService).should().getInstructorById(1l);
        then(subjectService).should().getAllSubjects();
        then(model).should(times(3)).addAttribute(anyString(), any());
        assertThat("course-form".equals(view));
    }

    @Test
    void testShowCourseFormWithModelAttributes() {
        // given
        String view = courseController.showNewCourseForm(1l, model);

        // then
        then(model).should().addAttribute("courseInstructor", any(User.class));
        then(model).should().addAttribute("subjects", anyList());
        then(model).should().addAttribute("schoolCourse", any(SchoolCourse.class));
    }

    @Test
    void testSaveCourseValid() throws Exception {

        given(bindingResult.hasErrors()).willReturn(false);

        mockMvc.perform(post("courses/saveCourse"))
                .andExpect(model().attributeExists("instructor"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/instructors?instructorId={instructorId}"));
    }

    @Test
    void testSaveCourseNotValid() throws Exception {

        mockMvc.perform(post("courses/saveCourse"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/courses/addNewCourse?userId={instructorId}"));
    }

    @Test
    void testSaveCourseBindingResultNoError() {
        // given
        SchoolCourse schoolCourse = new SchoolCourse();
        User user = new User("Joe", "Joe", "Smith",
                "joe@hamstermail.com", "password",
                true, "Hi I am Joe");
        user.setId(1l);
        given(bindingResult.hasErrors()).willReturn(false);


        //when
        String viewName = courseController.saveCourse(schoolCourse, bindingResult, model);

        // then
        then(courseService).should().saveCourse(schoolCourse, user);
        assertThat(viewName).isEqualToIgnoringCase("redirect:/instructors?instructorId=1");
    }

    @Test
    void testSaveCourseBindingResultHasError() {
        // given
        SchoolCourse schoolCourse = new SchoolCourse();
        User user = new User("Joe", "Joe", "Smith",
                "joe@hamstermail.com", "password",
        true, "Hi I am Joe");
        user.setId(1l);
        given(bindingResult.hasErrors()).willReturn(true);
        given(userService.findByUsername(anyString())).willReturn(user);


        //when
        String viewName = courseController.saveCourse(schoolCourse, bindingResult, model);

        // then
        then(courseService).should(times(0)).saveCourse(schoolCourse, user);
        assertThat(viewName).isEqualToIgnoringCase("redirect:/courses/addNewCourse?userId=1");
    }

    @Test
    void deleteCourseById() {

        // when
        String viewName = courseController.deleteCourseById(1l, "ref");

        // then
        then(courseService).should().deleteCourseById(1l);
        assertThat(viewName).isEqualToIgnoringCase("redirect:ref");
    }
}