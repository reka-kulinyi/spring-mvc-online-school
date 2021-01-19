package com.onlineschool.demo.service;

import com.onlineschool.demo.dao.RoleDao;
import com.onlineschool.demo.dao.UserDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserDao userDao;

    @Mock
    private RoleDao roleDao;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    void getAllInstructors() {
    }

    @Test
    void getInstructorById() {
    }

    @Test
    void getInstructorsBySubjectAndPrice() {
    }

    @Test
    void getNewestInstructors() {
    }

    @Test
    void getInstructorsBySubject() {
    }

    @Test
    void findByUsername() {
    }

    @Test
    void loadUserByUsername() {
    }

    @Test
    void getUserById() {
    }

    @Test
    void save() {
    }

    @Test
    void save1() {
    }
}