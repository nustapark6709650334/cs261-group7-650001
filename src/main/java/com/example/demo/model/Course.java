package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@Table(name = "courses")
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "course_code", nullable = false, unique=true)
	private String courseCode;
	@Column(name = "course_name", nullable = false)
	private String courseName;
	@Column(name = "course_detail", nullable = false)
	private String courseDetail;
	@Column(name = "course_group", nullable = false)
	private String courseGroup;
	@Column(name = "credit", nullable = false)
	private int credit;
	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Course(String courseCode, String courseName,String courseDetail, String courseGroup,int credit) {
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.courseDetail = courseDetail;
		this.courseGroup = courseGroup;
		this.credit = credit;
	}

}
