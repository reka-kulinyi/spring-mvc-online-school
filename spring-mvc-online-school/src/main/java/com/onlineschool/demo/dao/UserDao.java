package com.onlineschool.demo.dao;
import java.util.List;

import com.onlineschool.demo.entity.User;

public interface UserDao {

	List<User> getAllInstructors();
}
