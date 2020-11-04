package com.onlineschool.demo.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.JoinColumn;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	@JsonIgnore
	@Column(name="password")
	private String password;
	
	@Column(name="is_teacher")
	private boolean isTeacher;
	
    @Column(name="created_at")
    private LocalDate createdAt;
    
    @Column(name="introduction")
    private String introduction;
    
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name="users_roles",
	joinColumns=@JoinColumn(name="user_id"),
	inverseJoinColumns=@JoinColumn(name="role_id"))
	private Set<Role> roles;
	
    
	@OneToMany(mappedBy="instructor", fetch = FetchType.EAGER,
	cascade= {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	private List<Course> courses;

    // - - - constructors - - -
	public User() {
	}

	public User(String username, String firstName, String lastName, String email, String password, 
			boolean isTeacher, String introduction) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.isTeacher = isTeacher;
		this.introduction = introduction;
	}
	
	public User(String username, String firstName, String lastName, String email, String password, 
			boolean isTeacher, String introduction, Set<Role> roles) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.isTeacher = isTeacher;
		this.introduction = introduction;
		this.roles = roles;
	}
	
	public User(String username, String firstName, String lastName, String email, boolean isTeacher, 
			LocalDate createdAt, String introduction, Set<Role> roles, List<Course> courses) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.isTeacher = isTeacher;
		this.createdAt = createdAt;
		this.introduction = introduction;
		this.roles = roles;
		this.courses = courses;
	}

	// - - - helper methods
	@PrePersist
    public void setCreateAt() {
    	this.createdAt = LocalDate.now();
    }
	
	public void addCourse(Course course) {
		if(courses == null) {
			courses = new ArrayList<>();
		}
		course.setInstructor(this);
		courses.add(course);
	}

	// - - - getters and setters - - -
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isTeacher() {
		return isTeacher;
	}

	public void setTeacher(boolean isTeacher) {
		this.isTeacher = isTeacher;
	}
	
	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	@JsonManagedReference
	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	// - - - toString() - - -
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", isTeacher=" + isTeacher + ", createdAt=" + createdAt + ", introduction="
				+ introduction + ", roles=" + roles + ", courses=" + courses + "]";
	}

}
