package com.onlineschool.demo.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name="review")
public class Review {
	
	@EmbeddedId
	ReviewKey id;
	
	@ManyToOne
	@MapsId("student_id")
	@JoinColumn(name="student_id")
	private User student;
	
	@ManyToOne
	@MapsId("course_id")
	@JoinColumn(name="course_id")
	private Course course;

	@Column(name="review_text")
	private String text;
	
	@Column(name="created_at")
	private LocalDate createdAt;
	
	public Review() {}

	public Review(ReviewKey id, User student, Course course, String text) {
		this.id = id;
		this.student = student;
		this.course = course;
		this.text = text;
	}
	
	@PrePersist
	public void onCreate() {
		this.createdAt = LocalDate.now();
	}

	public User getStudent() {
		return student;
	}

	public void setStudent(User student) {
		this.student = student;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", student=" + student + ", course=" + course + ", text=" + text + ", createdAt="
				+ createdAt + "]";
	}
	
	
}
