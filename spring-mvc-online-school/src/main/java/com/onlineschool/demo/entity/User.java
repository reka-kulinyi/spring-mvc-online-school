package com.onlineschool.demo.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
import javax.persistence.JoinColumn;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@Column(name="is_teacher")
	private boolean isTeacher;
	
    @Column(name="created_at")
    private LocalDate createdAt;
    
    @Column(name="introduction")
    private String introduction;
    
    @ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinTable(name="users_roles",
    joinColumns=@JoinColumn(name="user_id"),
    inverseJoinColumns=@JoinColumn(name="role_id"))
    private Collection<Role> roles;
    
    @OneToMany(mappedBy="instructor",
    cascade= {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Course> courses;

    // - - - constructors - - -
	public User() {
	}

	public User(String firstName, String lastName, String email, String password, 
			boolean isTeacher, String introduction) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.isTeacher = isTeacher;
		this.introduction = introduction;
	}
	
	public User(String firstName, String lastName, String email, String password, 
			boolean isTeacher, String introduction, Collection<Role> roles) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.isTeacher = isTeacher;
		this.introduction = introduction;
		this.roles = roles;
	}
	
	public User(String firstName, String lastName, String email, boolean isTeacher, 
			LocalDate createdAt, String introduction, Collection<Role> roles, List<Course> courses) {
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

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
	
	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	// // - - - toString() - - -
	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", isTeacher=" + isTeacher + ", createdAt=" + createdAt + ", roles=" + roles + ", courses=" + courses
				+ "]";
	}

}
