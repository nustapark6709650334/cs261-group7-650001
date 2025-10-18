package com.example.demo.model.dto;

public class CourseResponse {

    private Long id; 
    private String courseCode;
    private String courseName;
    private String courseDetail;
    private String courseGroup;
    private int credit;

    // Constructors
    public CourseResponse() {}
    
    public CourseResponse(Long id, String courseCode, String courseName, String courseDetail, String courseGroup, int credit) {
        this.id = id;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.courseDetail = courseDetail;
        this.courseGroup = courseGroup;
        this.credit = credit;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public String getCourseDetail() { return courseDetail; }
    public void setCourseDetail(String courseDetail) { this.courseDetail = courseDetail; }

    public String getCourseGroup() { return courseGroup; }
    public void setCourseGroup(String courseGroup) { this.courseGroup = courseGroup; }

    public int getCredit() { return credit; }
    public void setCredit(int credit) { this.credit = credit; }
}