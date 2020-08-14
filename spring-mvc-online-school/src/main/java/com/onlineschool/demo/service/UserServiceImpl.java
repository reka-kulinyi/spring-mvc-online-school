package com.onlineschool.demo.service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onlineschool.demo.dao.RoleDao;
import com.onlineschool.demo.dao.UserDao;
import com.onlineschool.demo.entity.User;
import com.onlineschool.demo.entity.Role;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public List<User> getAllInstructors() {
		return userDao.getAllInstructors();
	}

	@Override
	@Transactional
	public User getInstructorById(long id) {
		return userDao.getInstructorById(id);
	}

	@Override
	@Transactional
	public List<User> getInstructorsBySubjectAndPrice(String searchSubject, double maxPrice) {
		BigDecimal bd = new BigDecimal(maxPrice);
		return userDao.getInstructorsBySubjectAndPrice(searchSubject, bd);

	}

	@Override
	@Transactional
	public List<User> getNewestInstructors(int i) {
		return userDao.getNewestInstructors(i);
	}
	
	@Override
	@Transactional
	public List<User> getInstructorsBySubject(String subject) {
		return userDao.getInstructorsBySubject(subject);
	}

	@Override
	@Transactional
	public User findByUsername(String username) {
		return userDao.findUserByUsername(username);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userDao.findUserByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("Invalid username or password");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), 
				user.getPassword(), mapRolesToAuthorities(user.getRoles()));
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
	
}
