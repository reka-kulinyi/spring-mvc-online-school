package com.onlineschool.demo.entity;

import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
    
    @ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinTable(name="users_roles",
    joinColumns=@JoinColumn(name="user_id"),
    inverseJoinColumns=@JoinColumn(name="role_id"))
    private Collection<Role> roles;
    
    @PrePersist
    public void setCreateAt() {
    	this.createdAt = LocalDate.now();
    }

    // - - - constructors - - -
	public User() {
	}

	public User(String firstName, String lastName, String email, String password, boolean isTeacher) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.isTeacher = isTeacher;
	}
	
	public User(String firstName, String lastName, String email, String password, boolean isTeacher,
			Collection<Role> roles) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.isTeacher = isTeacher;
		this.roles = roles;
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

	
	// - - - toString() - - -
	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", isTeacher=" + isTeacher + ", createdAt=" + createdAt + ", roles=" + roles + "]";
	}
}
