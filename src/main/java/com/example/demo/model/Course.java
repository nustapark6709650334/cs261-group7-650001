package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "courses")
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "course_code", nullable = false, unique = true, columnDefinition = "NVARCHAR(255)")
	private String courseCode;

	@Column(name = "course_name", nullable = false, columnDefinition = "NVARCHAR(255)")
	private String courseName;

	@Lob
	@Column(name = "course_detail", nullable = false, columnDefinition = "NVARCHAR(MAX)")
	private String courseDetail;

	@Column(name = "course_group", nullable = false, columnDefinition = "NVARCHAR(100)")
	private String courseGroup;

	@Column(name = "credit", nullable = false)
	private int credit;
	
	public Course(String courseCode, String courseName,String courseDetail, String courseGroup,int credit) {
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.courseDetail = courseDetail;
		this.courseGroup = courseGroup;
		this.credit = credit;
	}

}
