package com.onlineschool.demo.controller;

import com.onlineschool.demo.entity.User;
import com.onlineschool.demo.service.CourseService;
import com.onlineschool.demo.service.ReviewService;
import com.onlineschool.demo.service.SubjectService;
import com.onlineschool.demo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class DemoControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private CourseService courseService;

    @Mock
    private SubjectService subjectService;

    @Mock
    private ReviewService reviewService;

    @Mock
    private Model testModel;

    @Mock
    Authentication authentication;

    @InjectMocks
    private DemoController demoController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(demoController).build();
        authentication = SecurityContextHolder.getContext().getAuthentication();
    }

    // - - - home method tests - - -
    @Test
    void testHome() {
        // when
        String viewName = demoController.home(testModel);

        // then
        assertThat(viewName).isEqualToIgnoringCase("home");
    }

    @Test
    void testHomeHelperMethods() {
        // when
        demoController.home(testModel);

        // then
        then(userService).should().getNewestInstructors(4);
        then(subjectService).should().getAllSubjects();
        then(reviewService).should().getRecentReviews(4);
    }

    @Test
    void testHomeWithAuthenticationNull() {
        // given
        authentication = null;

        // when
        demoController.home(testModel);

        // then
        then(userService).should(never()).findByUsername(anyString());
        then(testModel).should(times(3)).addAttribute(anyString(), anyList());
    }

    @Test
    void testHomeWithAuthenticationNotNull() {

        // when
        demoController.home(testModel);

        // then
        then(userService).should(times(1)).findByUsername(anyString());
        then(testModel).should(times(3)).addAttribute(anyString(), anyList());
        then(testModel).should(times(1)).addAttribute("currentUser", any(User.class));
    }

    @Test
    void testHomeWithMvcTestAuthenticationNull() throws Exception {
         // given
        authentication = null;

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recentReviews"))
                .andExpect(model().attributeExists("instructors"))
                .andExpect(model().attributeExists("subjects"))
                .andExpect(model().attributeDoesNotExist("currentUser"))
                .andExpect(view().name("/"));
    }

    @Test
    void testHomeWithMvcTestAuthenticationNotNull() throws Exception {

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recentReviews"))
                .andExpect(model().attributeExists("instructors"))
                .andExpect(model().attributeExists("subjects"))
                .andExpect(model().attributeExists("currentUser"))
                .andExpect(view().name("/"));
    }

    // - - - tests for showInstructorProfile method - - -
    @Test
    void testShowInstructorProfile() {
        // when
        String viewName = demoController.showInstructorProfile(1l, testModel);

        // then
        assertThat(viewName).isEqualToIgnoringCase("instructor-profile-page");
    }


    @Test
    void testShowInstructorProfileWithAuthNotNull() {
        // when
        demoController.home(testModel);

        // then
        then(userService).should().findByUsername(anyString());
        then(testModel).should().addAttribute("currentUser", any(User.class));
        then(testModel).should().addAttribute("instructor", any(User.class));
        then(testModel).should().addAttribute("reviews", anyList());
    }

    @Test
    void testShowInstructorProfileWithAuthNull() {
        // when
        authentication = null;
        demoController.home(testModel);

        // then
        then(userService).should(never()).findByUsername(anyString());
        then(testModel).should(never()).addAttribute("currentUser", any(User.class));
        then(testModel).should().addAttribute("instructor", any(User.class));
        then(testModel).should().addAttribute("reviews", anyList());
    }

    @Test
    void testShowInstructorProfileWithMvcTestAuthenticationNotNull() throws Exception {

        mockMvc.perform(get("/instructors"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("currentUser"))
                .andExpect(model().attributeExists("instructor"))
                .andExpect(model().attributeExists("reviews"))
                .andExpect(view().name("/instructor-profile-page"));
    }

    @Test
    void testShowInstructorProfileWithMvcTestAuthenticationNull() throws Exception {

        // given
        authentication = null;

        mockMvc.perform(get("/instructors"))
                .andExpect(status().isOk())
                .andExpect(model().attributeDoesNotExist("currentUser"))
                .andExpect(model().attributeExists("instructor"))
                .andExpect(model().attributeExists("reviews"))
                .andExpect(view().name("/instructor-profile-page"));

    }

    // - - - tests for searchInstructorsBySubjectAndPrice - - -
    @Test
    void testSearchInstructorsBySubjectAndPrice() {

        // when
        String viewName = demoController.searchInstructorsBySubjectAndPrice(anyString(), anyDouble(), testModel);

        // then
        then(userService).should().getInstructorsBySubjectAndPrice(anyString(), anyDouble());
        then(testModel).should().addAttribute("instructors", anyList());
        assertThat(viewName).isEqualToIgnoringCase("instructor-list");
    }


    @Test
    void testSearchInstructorsBySubjectAndPriceWithMvcTest() throws Exception {
            mockMvc.perform(get("/search"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("instructors"))
                .andExpect(view().name("/instructor-list"));
    }

    @Test
    void testSearchInstructorsBySubjectAndPriceWithTeacherList() {
        // given
        User teacher1 = new User();
        User teacher2 = new User();
        List<User> instructors = new ArrayList<>();
        instructors.add(teacher1);
        instructors.add(teacher2);
        given(userService.getInstructorsBySubjectAndPrice(anyString(), anyDouble())).willReturn(instructors);

        // when
        demoController.searchInstructorsBySubjectAndPrice(anyString(), anyDouble(), testModel);

        // then
        then(courseService).should(times(2)).getCoursesByInstructor(any(User.class));
        then(teacher1).should().setCourses(anyList());
        then(teacher2).should().setCourses(anyList());
    }

    // - - - tests for showAllInstructors method - - -
    @Test
    void testShowAllInstructors() {
        // when
        String viewName = demoController.showAllInstructors(testModel);

        // then
        then(userService).should().getAllInstructors();
        then(testModel).should().addAttribute("instructors", anyList());
        assertThat(viewName).isEqualToIgnoringCase("instructors");
    }

    @Test
    void testShowAllInstructorsWithMvcTest() throws Exception {
        mockMvc.perform(get("/instructors/all"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("instructors"))
                .andExpect(view().name("/instructors"));
    }
}