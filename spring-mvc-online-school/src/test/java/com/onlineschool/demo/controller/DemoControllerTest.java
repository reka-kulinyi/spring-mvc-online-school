package com.onlineschool.demo.controller;

import com.onlineschool.demo.entity.User;
import com.onlineschool.demo.service.CourseService;
import com.onlineschool.demo.service.ReviewService;
import com.onlineschool.demo.service.SubjectService;
import com.onlineschool.demo.service.UserService;
import com.onlineschool.demo.user.SchoolUserForUpdate;
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
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    private BindingResult bindingResult;

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
                .andExpect(view().name("instructor-profile-page"));
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
                .andExpect(view().name("instructor-profile-page"));

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
                .andExpect(view().name("instructor-list"));
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
                .andExpect(view().name("instructors"));
    }

    // - - - tests for showAllSubjects method - - -
    @Test
    void testShowAllSubjects() {
        // when
        String viewName = demoController.showAllSubjects(testModel);

        // then
        then(subjectService).should().getAllSubjects();
        then(testModel).should().addAttribute("subjects", anyList());
        assertThat(viewName).isEqualToIgnoringCase("subjects");
    }

    @Test
    void testShowAllSubjectsWithMvcTest() throws Exception {
        mockMvc.perform(get("/subjects/all"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("subjects"))
                .andExpect(view().name("subjects"));
    }

    // - - - tests for showInstructorsBySubject method - - -
    @Test
    void testShowInstructorsBySubject() {

        // when
        String viewName = demoController.showInstructorsBySubject(anyString(), testModel);

        // then
        then(userService).should().getInstructorsBySubject(anyString());
        then(testModel).should().addAttribute("instructors");
        assertThat(viewName).isEqualToIgnoringCase("instructor-list");
    }

    @Test
    void testShowInstructorsBySubjectWithMvcTest() throws Exception {
        mockMvc.perform(get("/instructors/subjects"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("instructors"))
                .andExpect(view().name("instructor-list"));
    }

    // - - - tests for showUpdateForm method - - -
    @Test
    void testShowUpdateForm() {
        // given
        SchoolUserForUpdate userForUpdate = new SchoolUserForUpdate();

        // when
        String viewName = demoController.showUpdateForm(1l, testModel);

        // then
        then(userService).should().getUserById(1l);
        then(userForUpdate).should().setId(1l);
        then(userForUpdate).should().setEmail(anyString());
        then(userForUpdate).should().setFirstName(anyString());
        then(userForUpdate).should().setLastName(anyString());
        then(userForUpdate).should().setUsername(anyString());
        then(userForUpdate).should().setIntroduction(anyString());
        then(testModel).should().addAttribute("schoolUserForUpdate", userForUpdate);
        assertThat(viewName).isEqualToIgnoringCase("user-update-form");
    }

    @Test
    void testShowUpdateFormUserMethods() {
        // given
        User user = new User();
        given(userService.getUserById(anyLong())).willReturn(user);

        // when
        String viewName = demoController.showUpdateForm(1l, testModel);

        // then
        then(user).should().getEmail();
        then(user).should().getFirstName();
        then(user).should().getLastName();
        then(user).should().getUsername();
        then(user).should().getIntroduction();
        then(user).shouldHaveNoMoreInteractions();
        assertThat(viewName).isEqualToIgnoringCase("user-update-form");
    }

    @Test
    void testShowUpdateFormWithMvcTest() throws Exception {
        mockMvc.perform(get("/instructors/updateForm"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("schoolUserForUpdate"))
                .andExpect(view().name("user-update-form"));
    }

    // - - - tests for processUserUpdate method - - -
    @Test
    void testProcessUserUpdateNoBindingResultErrorWithSameUsername() {
        // given
        SchoolUserForUpdate schoolUserForUpdate = new SchoolUserForUpdate();
        schoolUserForUpdate.setUsername("user");
        User userToUpdate = new User();
        userToUpdate.setUsername("user");
        given(bindingResult.hasErrors()).willReturn(false);

        // when
        String viewName = demoController.processUserUpdate(schoolUserForUpdate, bindingResult, testModel);

        // then
        then(userService).should().getUserById(anyLong());
        then(userToUpdate).should().getUsername();
        then(schoolUserForUpdate).should().getUsername();
        then(userService).should().save(schoolUserForUpdate, userToUpdate);
        assertThat(viewName).isEqualToIgnoringCase("update-confirmation");
    }

    @Test
    void testProcessUserUpdateNoBindingResultErrorWithDifferentUsername() {
        // given
        SchoolUserForUpdate schoolUserForUpdate = new SchoolUserForUpdate();
        schoolUserForUpdate.setUsername("user1");
        User userToUpdate = new User();
        userToUpdate.setUsername("user2");
        given(bindingResult.hasErrors()).willReturn(false);

        // when
        String viewName = demoController.processUserUpdate(schoolUserForUpdate, bindingResult, testModel);

        // then
        then(userService).should().getUserById(anyLong());
        then(userToUpdate).should().getUsername();
        then(schoolUserForUpdate).should().getUsername();
        then(userService).should().save(schoolUserForUpdate, userToUpdate);
        assertThat(viewName).isEqualToIgnoringCase("re-login");
    }

    @Test
    void testProcessUserUpdateWithBindingResultError() {
        // given
        SchoolUserForUpdate schoolUserForUpdate = new SchoolUserForUpdate();
        User userToUpdate = new User();
        given(bindingResult.hasErrors()).willReturn(true);

        // when
        String viewName = demoController.processUserUpdate(schoolUserForUpdate, bindingResult, testModel);

        // then
        then(userService).should().getUserById(anyLong());
        then(userToUpdate).should().getUsername();
        then(schoolUserForUpdate).should().getUsername();
        then(userService).should(never()).save(schoolUserForUpdate, userToUpdate);
        assertThat(viewName).isEqualToIgnoringCase("user-update-form");
    }

    @Test
    void testProcessUserUpdateWithMvcTest() throws Exception {
        mockMvc.perform(post("/users/update/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("schoolUserForUpdate"))
                .andExpect(view().name("user-update-form"));
    }
}