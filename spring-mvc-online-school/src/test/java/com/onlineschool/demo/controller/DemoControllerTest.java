package com.onlineschool.demo.controller;

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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
        then(testModel).should(times(3)).addAttribute(anyString(), any());
    }

    @Test
    void testHomeWithAuthenticationNotNull() {

        // when
        demoController.home(testModel);

        // then
        then(userService).should(times(1)).findByUsername(anyString());
        then(testModel).should(times(4)).addAttribute(anyString(), any());
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

}